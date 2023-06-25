package br.com.limac.sysmac.security;

import io.jsonwebtoken.JwtException;

public class TokenNotFoundException extends JwtException {

    public TokenNotFoundException() {
        super("Token Not Found");
    }

}
