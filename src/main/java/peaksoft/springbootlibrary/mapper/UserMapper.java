package peaksoft.springbootlibrary.mapper;

import org.springframework.stereotype.Component;
import peaksoft.springbootlibrary.dto.UserRequest;
import peaksoft.springbootlibrary.dto.UserResponse;
import peaksoft.springbootlibrary.model.RoleEntity;
import peaksoft.springbootlibrary.model.UserEntity;

@Component
public class UserMapper {

    public UserEntity mapToEntity(UserRequest userRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userRequest.getName());
        userEntity.setPassword(userRequest.getPassword());
        return userEntity;
    }

    public UserResponse mapToResponse(UserEntity userEntity){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(userEntity.getId());
        userResponse.setName(userEntity.getName());
        userResponse.setPassword(userEntity.getPassword());
        userResponse.setRoles(userEntity.getRoles().stream().map(RoleEntity::getName).toList());
        return userResponse;
    }
}
