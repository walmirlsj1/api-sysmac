package br.com.limac.sysmac.security.user;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserModelAssembler {

    public UserModel toModel(User user) {
        UserModel userModel = new UserModel();

        userModel.setId(user.getId());
        userModel.setName(user.getName());
        userModel.setUsername(user.getUsername());
        userModel.setEmail(user.getEmail());
        userModel.setRole(user.getRole());

        return userModel;
    }

    public List<UserModel> toModelList(List<User> users) {
        return users
                .stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Page<UserModel> toPageModel(Page<User> users) {
        return new PageImpl<>(this.toModelList(users.getContent()), users.getPageable(), users.getTotalElements());
    }
}
