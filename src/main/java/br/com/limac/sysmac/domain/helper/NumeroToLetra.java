package br.com.limac.sysmac.domain.helper;

import java.util.regex.Pattern;

/**
 * @author Mouse
 * @web http://jc-mouse.blogspot.com/
 */
public class NumeroToLetra {

    private final String[] UNIDADES = {"", "um ", "dois ", "três ", "quatro ", "cinco ", "seis ", "sete ", "oito ", "nove "};
    private final String[] DECENAS = {"dez ", "onze ", "doze ", "treze ", "quatorze ", "quinze ", "desesseis ",
            "dezessete ", "dezoito ", "dezenove", "vinte ", "trinta ", "quarenta ",
            "cinquenta ", "sessenta ", "setenta ", "oitenta ", "noventa "};
    private final String[] CENTENAS = {"", "cento ", "duzentos ", "trezentos ", "quatrocentos ", "quinhentos ", "seiscentos ",
            "setecentos ", "oitocentos ", "novecentos "};

    public NumeroToLetra() {
    }

    public String Convertir(String numero, boolean mayusculas) {
        String literal = "";
        String parte_decimal;
        //si el numero utiliza (.) en lugar de (,) -> se reemplaza
        numero = numero.replace(".", ",");
        //si el numero no tiene parte decimal, se le agrega ,00
        if (numero.indexOf(",") == -1) {
            numero = numero + ",00";
        }
        //se valida formato de entrada -> 0,00 y 999 999 999,00
        if (Pattern.matches("\\d{1,9},\\d{1,2}", numero)) {
            //se divide el numero 0000000,00 -> entero y decimal
            String Num[] = numero.split(",");
            //de da formato al numero decimal
//            Num[1] + "/100 Reais."/
            parte_decimal = "";
            if (Integer.parseInt(Num[1]) > 99) {//si es centena
                parte_decimal = getCentenas(Num[1]);
            } else if (Integer.parseInt(Num[1]) > 9) {//si es decena
                parte_decimal = getDecenas(Num[1]);
            } else {//sino unidades -> 9
                parte_decimal = getUnidades(Num[1]);
            }
            if (!parte_decimal.isEmpty()) {
                parte_decimal = " e " + parte_decimal + "centavos";
//                parte_decimal += "centavos";
            }
//            parte_decimal = Convertir(Num[1],false) + " Reais.";

            //se convierte el numero a literal
            if (Integer.parseInt(Num[0]) == 0) {//si el valor es cero
                literal = "zero ";
            } else if (Integer.parseInt(Num[0]) > 999999) {//si es millon
                literal = getMillones(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 999) {//si es miles
                literal = getMiles(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 99) {//si es centena
                literal = getCentenas(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 9) {//si es decena
                literal = getDecenas(Num[0]);
            } else {//sino unidades -> 9
                literal = getUnidades(Num[0]);
            }
            //devuelve el resultado en mayusculas o minusculas
            if (mayusculas) {
                return "(" + (literal + "reais" + parte_decimal).toUpperCase() + ")";
            } else {
                return (literal + "reais" + parte_decimal);
            }
        } else {//error, no se puede convertir
            throw new RuntimeException("Erro ao converter número para texto, limite de 999 milhões");
//            return literal = null;
        }
    }

    /* funciones para convertir los numeros a literales */

    private String getUnidades(String numero) {// 1 - 9
        //si tuviera algun 0 antes se lo quita -> 09 = 9 o 009=9
        String num = numero.substring(numero.length() - 1);
        return UNIDADES[Integer.parseInt(num)];
    }

    private String getDecenas(String num) {// 99
        int n = Integer.parseInt(num);
        if (n < 10) {//para casos como -> 01 - 09
            return getUnidades(num);
        } else if (n > 19) {//para 20...99
            String u = getUnidades(num);
            if (u.equals("")) { //para 20,30,40,50,60,70,80,90
                return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8];
            } else {
                return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8] + "e " + u;
            }
        } else {//numeros entre 11 y 19
            return DECENAS[n - 10];
        }
    }

    private String getCentenas(String num) {// 999 o 099
        String d = "";
        if (Integer.parseInt(num) > 99) {//es centena
            if (Integer.parseInt(num) == 100) {//caso especial
                return "cem ";
            } else {
                d = getDecenas(num.substring(1));
                if (d.equals("")) return CENTENAS[Integer.parseInt(num.substring(0, 1))];
                return CENTENAS[Integer.parseInt(num.substring(0, 1))] + "e " + d;
            }
        } else {//por Ej. 099
            //se quita el 0 antes de convertir a decenas
            return getDecenas(Integer.parseInt(num) + "");
        }
    }

    private String getMiles(String numero) {// 999 999
        //obtiene las centenas
        String c = numero.substring(numero.length() - 3);
        //obtiene los miles
        String m = numero.substring(0, numero.length() - 3);
        String n = "";
        String cc = "";
        //se comprueba que miles tenga valor entero
        if (Integer.parseInt(m) > 0) {
            n = getCentenas(m);
            cc = getCentenas(c);
            if (cc != "") cc = "e " + cc;
            if (Integer.parseInt(m) == 1) return "mil " + cc;
            return n + "mil " + cc;
        } else {
            return "" + getCentenas(c);
        }

    }

    private String getMillones(String numero) { //000 000 000
        //se obtiene los miles
        String miles = numero.substring(numero.length() - 6);
        //se obtiene los millones
        String millon = numero.substring(0, numero.length() - 6);
        String n = "";
        String nn = "";
        if (millon.length() > 1) {
            n = getCentenas(millon) + "milhões ";
        } else {
            nn = getUnidades(millon);
            if (nn.equals("um ")) n = nn + "milhão ";
            else n = nn + "milhões ";
        }
        String miles1 = getMiles(miles);
        if (miles1.equals("")) return n;
        return n + "e " + miles1;
    }
}