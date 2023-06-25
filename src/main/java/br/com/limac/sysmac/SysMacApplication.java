package br.com.limac.sysmac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;
import java.util.TimeZone;

@SpringBootApplication
public class SysMacApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        Locale.setDefault(new Locale("pt", "BR"));
        SpringApplication.run(SysMacApplication.class, args);
    }

}
