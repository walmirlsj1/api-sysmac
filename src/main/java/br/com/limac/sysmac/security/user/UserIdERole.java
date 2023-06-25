package br.com.limac.sysmac.security.user;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class UserIdERole {
    Long id;
    @Enumerated(EnumType.STRING)
    Role role;
}
