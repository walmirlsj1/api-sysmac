package br.com.limac.sysmac.security.user.fixture;


import br.com.limac.sysmac.security.auth.RegisterRequest;
import br.com.limac.sysmac.security.user.RegisterUserService;
import br.com.limac.sysmac.security.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserFixture {
    private RegisterUserService service;
    private UserRepository userRepository;

    @PostConstruct
    public void addUserDefault() {
        if (userRepository.findByUsername("walmirlsj").isPresent()) return;

        RegisterRequest request = new RegisterRequest();
//        request.setId(1L);
        request.setName("Walmir Luiz da Silva Junior");
        request.setEmail("walmir004@gmail.com");
        request.setUsername("walmirlsj");
        request.setPassword("SgH9430-=");

////        UserEntity user1 = service.find(1L);
//        User user1 = new User();
//        user1.setId(1L);
//        user1.setFirstname("walmir");
//        user1.setUsername("walmirlsj");
//        user1.setPassword("senha");
//        user1.setEmail("walmir_004@hotmail.com");
//        user1.setCredentialsNonExpired(true);
//        user1.setEnabled(true);
//        user1.setAccountNonExpired(true);
//        user1.setAccountNonLocked(true);
        service.register(request);
    }
//    public UserFixture(BCryptPasswordEncoder bcryptEncoder) {
//        users = new UserDBService();
//        bcryptEncoder = new BCryptPasswordEncoder();


//        bcryptEncoder = new BCryptPasswordEncoder();
//
//        UserEntity user2 = new UserEntity();
//        user2.setId(0L);
//        user2.setUsername("admin");
//        user2.setPassword(bcryptEncoder.encode("admin"));
//        user2.setEmail("walmir004@hotmail.com");
//        user2.setCredentialsNonExpired(true);
//        user2.setEnabled(true);
//        users.save(user2);


//        System.out.println(user1.getUsername() + " " + user1.getPassword() + " " +
//                user1.getAuthorities() + " " + user1.getEmail() + " " + user1.getCreatedAt());
//
//        System.out.println("");
//        System.out.println(user2.getUsername() + " " + user2.getPassword() + " " +
//                user2.getAuthorities() + " " + user2.getEmail() + " " + user2.getCreatedAt());

//    }


}
