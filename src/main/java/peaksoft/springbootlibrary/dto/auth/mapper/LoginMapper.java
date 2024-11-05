package peaksoft.springbootlibrary.dto.auth.mapper;

import org.springframework.stereotype.Component;
import peaksoft.springbootlibrary.dto.auth.LoginResponse;

@Component
public class LoginMapper {

    public LoginResponse mapToResponse(String token, String roleName){
        return new LoginResponse()
                .setToken(token)
                .setRoleName(roleName);
    }
}
