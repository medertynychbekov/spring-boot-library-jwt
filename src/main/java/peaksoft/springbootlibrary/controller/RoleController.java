package peaksoft.springbootlibrary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.springbootlibrary.dto.RoleResponse;
import peaksoft.springbootlibrary.service.RoleService;

import java.util.List;

@Secured("ADMIN")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/role")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<RoleResponse> findAll() {
        return roleService.findAll();
    }
}
