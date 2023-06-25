package br.com.limac.sysmac.security.refresh_token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    @Query(value = """
            select t from RefreshToken t inner join User u\s
            on t.user.id = u.id\s
            where u.id = :id and (t.expired = false or t.revoked = false)\s
            """)
    List<RefreshToken> findAllValidRefreshTokenByUser(Long id);

//    Optional<RefreshToken> findByUserIdAndRefreshToken(Long userId, String token);

    @Modifying
    @Query("update RefreshToken rt SET rt.expired = true, rt.revoked = true where rt.user.id = :userId")
    void revokeAndExpireAllRefreshTokenByUser(Long userId);

    @Modifying
    @Query("update RefreshToken rt SET rt.expired = true, rt.revoked = true where rt.user.id = :userId and rt.refreshToken = :refreshToken")
    void revokeAndExpireAllRefreshTokenByUserAndRefreshTokens(Long userId, String refreshToken);



    @Modifying
    @Query("update RefreshToken rt SET rt.expired = true, rt.revoked = true where rt.id = :refreshTokenId")
    void revokeAndExpireRefreshTokenByRefreshTokenId(Long refreshTokenId);
}