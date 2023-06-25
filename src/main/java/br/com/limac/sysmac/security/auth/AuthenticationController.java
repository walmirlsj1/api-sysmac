package br.com.limac.sysmac.security.auth;

import br.com.limac.sysmac.security.TokenExpiredException;
import br.com.limac.sysmac.security.TokenInvalidException;
import br.com.limac.sysmac.security.TokenNotFoundException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }


//    @PostMapping("/authenticate")
//    public ResponseEntity<?> authenticate(
//            HttpServletResponse response,
//            @RequestBody AuthenticationRequest request
//    ) {
//        AuthenticationResponse r = service.authenticate(request);
//        CookieService.setCookie(response, "access_token", r.getAccessToken(), 60*60*24*1);
//        CookieService.setCookie(response, "refresh_token", r.getRefreshToken(), 60*60*24*1);
//
//        return ResponseEntity.ok(r);
//    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        try {
            service.refreshToken(request, response);
        } catch (TokenInvalidException e1) {
            throw new TokenInvalidException();
        } catch (TokenNotFoundException e) {
            throw new TokenNotFoundException();
        } catch (JwtException e) {
            throw new TokenExpiredException();
        }
    }


    @PostMapping("/revoke-token")
    public void revokeToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        try {
//            service.(request, response);
        } catch (JwtException e) {
            throw new TokenExpiredException();
        }
    }


}