package br.com.limac.sysmac.security.config;

import br.com.limac.sysmac.api.exceptionhandler.ApiExceptionHandler;
import br.com.limac.sysmac.security.TokenExpiredException;
import br.com.limac.sysmac.security.TokenInvalidException;
import br.com.limac.sysmac.security.TokenRevokedException;
import br.com.limac.sysmac.security.token.TokenRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;
    private final ApiExceptionHandler apiExceptionHandler;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().contains("/api/v1/auth")) {
            filterChain.doFilter(request, response);
            return;
        }
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        String userEmail1;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
//        try {

        jwt = authHeader.substring(7);

        try {
            userEmail = jwtService.extractUsername(jwt);
        } catch (ExpiredJwtException expired) {
            System.out.println("MSG:::: ");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token Expired");
            return;
        }

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            var token = tokenRepository.findByToken(jwt).orElseThrow(TokenInvalidException::new);

            if (token.revoked) throw new TokenRevokedException();
            if (token.expired) throw new TokenExpiredException();

//                var isTokenValid = tokenRepository.findByToken(jwt)
//                        .map(t -> !t.isExpired() && !t.isRevoked())
//                        .orElse(false);

            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);


    }
}