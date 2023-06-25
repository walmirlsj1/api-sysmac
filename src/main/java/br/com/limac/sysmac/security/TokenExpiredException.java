package br.com.limac.sysmac.security;

import io.jsonwebtoken.JwtException;

public class TokenExpiredException extends JwtException {

    public TokenExpiredException() {
        super("Token Expired");
    }

//    public TokenExpiredException(String message, Throwable cause) {
//        super(message, cause);
//    }
}
