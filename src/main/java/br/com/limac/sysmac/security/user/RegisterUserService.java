package br.com.limac.sysmac.security.user;

//import br.com.limac.sysmac.security.auth.RegisterRequest;

import br.com.limac.sysmac.domain.exception.NegocioException;
import br.com.limac.sysmac.security.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RegisterUserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest request) {
        var user = User.builder().name(request.getName()).email(request.getEmail()).username(request.getUsername()).password(passwordEncoder.encode(request.getPassword())).role(Role.USER).build();
        repository.save(user);
    }


    public void alterPassword(Long id, RegisterRequest request) {
//        throw new RuntimeException("NOT IMPLEMENTED.");
        var user = User.builder().password(passwordEncoder.encode(request.getPassword()))
//                .role(Role.USER)
                .build();
//        repository.save(user);
    }

    public void alterPassword(UserIdEPassword userIdEPassword) {
        User user = buscarOuFalhar(userIdEPassword.id);
        user.setPassword(passwordEncoder.encode(userIdEPassword.password));
        repository.save(user);
    }

    public void alterRole(UserIdERole userIdERole) {
        User user = buscarOuFalhar(userIdERole.id);
        user.setRole(userIdERole.role);
        repository.save(user);
    }

    private User buscarOuFalhar(Long id) {
        return this.repository.findById(id).orElseThrow(() -> new NegocioException("Usuário não encontrado"));
    }
}
