package br.com.limac.sysmac.security.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query(value = """
            select t from Token t inner join User u\s
            on t.user.id = u.id\s
            where u.id = :userId and (t.expired = false or t.revoked = false)\s
            """)
    List<Token> findAllValidTokenByUser(Long userId);

    Optional<Token> findByToken(String token);


    @Modifying
    @Query("update Token t SET t.expired = true, t.revoked = true where t.user.id = :id and t.refreshToken.id = :refreshTokenId")
    void revokeAndExpireAllTokensByUserId(Long id);

    @Modifying
    @Query("update Token t SET t.expired = true, t.revoked = true where t.user.id = :id and t.refreshToken.id = :refreshTokenId")
    void revokeAndExpireAllTokensByUserIdAndRefreshTokenId(Long id, Long refreshTokenId);


    @Modifying
    @Query("update Token t SET t.expired = true, t.revoked = true where t.id = :tokenId")
    void revokeAndExpireTokenByTokenId(Long tokenId);
}