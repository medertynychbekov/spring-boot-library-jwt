package peaksoft.springbootlibrary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.springbootlibrary.dto.UserRequest;
import peaksoft.springbootlibrary.dto.UserResponse;
import peaksoft.springbootlibrary.service.UserService;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping
//    @PreAuthorize("hasAnyAuthority('ADMIN' ,'USER')")
    @PermitAll()
    public UserResponse save(@RequestBody UserRequest userRequest) {
        return userService.save(userRequest);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public UserResponse findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public List<UserResponse> findAll() {
        return userService.findAll();
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserResponse updateById(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        return userService.updateById(id, userRequest);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserResponse deleteById(@PathVariable Long id) {
        return userService.deleteById(id);
    }


}
