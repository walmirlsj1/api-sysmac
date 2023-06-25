package br.com.limac.sysmac.security.auth;

import br.com.limac.sysmac.security.TokenNotFoundException;
import br.com.limac.sysmac.security.config.JwtService;
import br.com.limac.sysmac.security.refresh_token.RefreshToken;
import br.com.limac.sysmac.security.refresh_token.RefreshTokenRepository;
import br.com.limac.sysmac.security.token.Token;
import br.com.limac.sysmac.security.token.TokenRepository;
import br.com.limac.sysmac.security.token.TokenType;
import br.com.limac.sysmac.security.user.User;
import br.com.limac.sysmac.security.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = repository.findByUsername(request.getUsername()).orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

//        revokeAllUserTokens(user);

        RefreshToken refreshToken1 = saveUserRefreshToken(user, refreshToken);
        saveUserToken(user, jwtToken, refreshToken1);

        return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
    }

    private void saveUserToken(User user, String jwtToken, RefreshToken refreshToken) {
        var token = Token.builder().user(user).token(jwtToken).tokenType(TokenType.BEARER).refreshToken(refreshToken).expired(false).revoked(false).build();
        tokenRepository.save(token);
    }

    private RefreshToken saveUserRefreshToken(User user, String jwtToken) {
        var token = RefreshToken.builder().user(user).refreshToken(jwtToken).tokenType(TokenType.BEARER).expired(false).revoked(false).build();
        return refreshTokenRepository.save(token);
    }

    private void revokeAllUserRefreshTokens(User user) {
        refreshTokenRepository.revokeAndExpireAllRefreshTokenByUser(user.getId());
    }

    private void revokeAllUserTokens(User user, RefreshToken refreshToken) {
//        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
//        if (validUserTokens.isEmpty())
//            return;
//        validUserTokens.forEach(token -> {
//            token.setExpired(true);
//            token.setRevoked(true);
//        });
//        tokenRepository.saveAll(validUserTokens);
        tokenRepository.revokeAndExpireAllTokensByUserIdAndRefreshTokenId(user.getId(), refreshToken.getId());
    }
    @Transactional
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        refreshToken = authHeader.substring(7);
        username = jwtService.extractUsername(refreshToken);


        if (username != null) {
            var user = this.repository.findByUsername(username).orElseThrow(() -> new BadCredentialsException("Token Invalid"));

            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                RefreshToken refreshToken1 = user.getTokens().stream().filter(t -> refreshToken.equals(t.getRefreshToken().getRefreshToken()))
                        .map(Token::getRefreshToken).filter(rt -> !rt.revoked && !rt.expired)
                        .findFirst().orElseThrow(TokenNotFoundException::new);
//                Token token = tokenRepository.findByToken(accessToken).orElseThrow();

//                revokeAllUserTokens(user, refreshToken1);

                saveUserToken(user, accessToken, refreshToken1);

                var authResponse = AuthenticationResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }


//    public void refreshToken2(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//        final String refreshToken;
//        final String username;
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            return;
//        }
//
//        refreshToken = authHeader.substring(7);
//        username = jwtService.extractUsername(refreshToken);
//
//
//        if (username != null) {
//            var user = this.repository.findByUsername(username).orElseThrow(() -> new BadCredentialsException("Token Invalid"));
//
//            if (jwtService.isTokenValid(refreshToken, user)) {
////                tokenRepository.revokeAndExpireAllTokens();
//
//            }
//        }
//    }
}