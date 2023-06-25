package br.com.limac.sysmac.security.user;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserModel {
    private Long id;
    private String name;
    private String username;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

//    private OffsetDateTime lastAccess;
//    private OffsetDateTime dataCriacao;
//    private OffsetDateTime dataAtualizacao;
}
