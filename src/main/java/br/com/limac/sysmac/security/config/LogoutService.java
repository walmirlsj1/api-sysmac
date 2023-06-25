package br.com.limac.sysmac.security.config;

import br.com.limac.sysmac.security.refresh_token.RefreshTokenRepository;
import br.com.limac.sysmac.security.token.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final TokenRepository tokenRepository;
    private final RefreshTokenRepository refreshTokenRepository;


    @Override
    @Transactional
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.substring(7);
        var storedToken = tokenRepository.findByToken(jwt)
                .orElse(null);

        System.out.println("RELATORIO JWT");
        System.out.println("TokenID: " + storedToken.getId());
        System.out.println("UserID: " + storedToken.getUser().getId());
        System.out.println("RefreshTokenId: " + storedToken.getRefreshToken().getId());
        System.out.println("FIM RELATORIO");
        if (storedToken != null) {
//            storedToken.setExpired(true);
//            storedToken.setRevoked(true);
            tokenRepository.revokeAndExpireTokenByTokenId(storedToken.getId());
            refreshTokenRepository.revokeAndExpireRefreshTokenByRefreshTokenId(storedToken.getRefreshToken().getId());
            SecurityContextHolder.clearContext();
        }
    }
}