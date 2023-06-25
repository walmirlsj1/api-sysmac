package br.com.limac.sysmac.security;

import io.jsonwebtoken.JwtException;

public class TokenInvalidException extends JwtException {

    public TokenInvalidException() {
        super("Token Invalid");
    }

}
