package br.com.limac.sysmac.security.refresh_token;

import br.com.limac.sysmac.security.token.Token;
import br.com.limac.sysmac.security.token.TokenType;
import br.com.limac.sysmac.security.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RefreshToken {

    @Id
    @GeneratedValue
    public Long id;

    @Column(unique = true)
    public String refreshToken;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType;

    public boolean revoked;

    public boolean expired;

    @OneToMany(mappedBy = "refreshToken")
    private List<Token> tokens;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;
}