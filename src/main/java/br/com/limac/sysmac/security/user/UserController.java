package br.com.limac.sysmac.security.user;

import br.com.limac.sysmac.security.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final RegisterUserService service;
    private final UserRepository userRepository;
    private final UserModelAssembler userModelAssembler;


    @PostMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void register(
            @RequestBody RegisterRequest request
    ) {
        service.register(request);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> listar(@RequestParam(name = "filter", required = false) String filter, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(userModelAssembler.toPageModel(userRepository.findAll(pageable)));
    }

    @PutMapping("/alterarSenha")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void alterarSenha(@RequestBody UserIdEPassword userIdEPassword) {
        this.service.alterPassword(userIdEPassword);


        /**
         * tem que verifica se o senha alterada pertence ao usuario que solicitou a alteração, ou se role é ADMIN
         */
    }


    @PutMapping("/alterarRole")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void alterarRole(@RequestBody UserIdERole userIdERole) {
        this.service.alterRole(userIdERole);
    }
}
