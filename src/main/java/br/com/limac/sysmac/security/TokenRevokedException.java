package br.com.limac.sysmac.security;

import io.jsonwebtoken.JwtException;

public class TokenRevokedException extends JwtException {

    public TokenRevokedException() {
        super("Token Revoked");
    }

}
