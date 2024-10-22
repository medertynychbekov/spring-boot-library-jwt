package peaksoft.springbootlibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.springbootlibrary.dto.UserRequest;
import peaksoft.springbootlibrary.dto.UserResponse;
import peaksoft.springbootlibrary.mapper.UserMapper;
import peaksoft.springbootlibrary.model.RoleEntity;
import peaksoft.springbootlibrary.model.UserEntity;
import peaksoft.springbootlibrary.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private final RoleService roleService;

    @Transactional
    public UserResponse save(UserRequest userRequest) {
        UserEntity userEntity = userMapper.mapToEntity(userRequest);
        String roleName = userRequest.getName().equalsIgnoreCase("ADMIN") ? "ADMIN" : "USER";
        RoleEntity roleEntity = roleService.findByName(roleName);
        if (roleEntity == null) {
            roleEntity = new RoleEntity()
                    .setName(roleName);
        }
        List<RoleEntity> roleEntityList = userEntity.getRoles();
        List<UserEntity> userEntityList = roleEntity.getUserEntityList();
        userEntityList.add(userEntity);
        roleEntityList.add(roleEntity);
        roleEntity.setUserEntityList(userEntityList);
        userEntity.setRoles(roleEntityList);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userMapper.mapToResponse(userRepository.save(userEntity));
    }

    public UserResponse findById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        if (userEntity == null) {
            return null;
        }
        return userMapper.mapToResponse(userEntity);
    }

    public List<UserResponse> findAll() {
        List<UserEntity> userEntityList = userRepository.findAll();
//        List<UserResponse> userResponseList = new ArrayList<>();
        /** альтернативо mapping list */
//        for (UserEntity userEntity : userEntityList) {
//            UserResponse userResponse = userMapper.mapToResponse(userEntity);
//            userResponseList.add(userResponse);
//        }
        return userEntityList
                .stream()
                .map(userMapper::mapToResponse)
                .toList();
    }

    public UserResponse updateById(Long id, UserRequest userRequest) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        userEntity.setName(userRequest.getName());
        userEntity.setPassword(userRequest.getPassword());
        userRepository.save(userEntity);
        return userMapper.mapToResponse(userEntity);
    }

    public UserResponse deleteById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        UserResponse userResponse = userMapper.mapToResponse(userEntity);
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return userResponse;
        } else {
            return null;
        }
    }
}
