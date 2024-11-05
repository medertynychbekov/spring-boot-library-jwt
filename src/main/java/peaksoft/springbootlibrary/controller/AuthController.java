package peaksoft.springbootlibrary.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.springbootlibrary.config.jwt.JwtUtil;
import peaksoft.springbootlibrary.dto.UserRequest;
import peaksoft.springbootlibrary.dto.UserResponse;
import peaksoft.springbootlibrary.dto.auth.LoginRequest;
import peaksoft.springbootlibrary.dto.auth.LoginResponse;
import peaksoft.springbootlibrary.dto.auth.mapper.LoginMapper;
import peaksoft.springbootlibrary.model.UserEntity;
import peaksoft.springbootlibrary.repository.UserRepository;
import peaksoft.springbootlibrary.service.UserService;

import javax.annotation.security.PermitAll;

@PermitAll
@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {

    private final UserService userService;

    private final UserRepository userRepository;

    private final LoginMapper loginMapper;

    private final UserDetailsService userDetailsService;

    private final JwtUtil jwtUtil;

    @PostMapping("/sign-up")
    public UserResponse signUp(@RequestBody UserRequest userRequest) {
        return userService.save(userRequest);
    }

    @PostMapping("/sign-in")
    public LoginResponse signIn(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                );

        UserEntity user = userRepository.findByName(token.getName());

        return loginMapper.mapToResponse(
                jwtUtil.generateToken(
                        userDetailsService.loadUserByUsername(
                                user.getUsername()
                        )
                ),
                user.getRoles().toString());
    }
}
