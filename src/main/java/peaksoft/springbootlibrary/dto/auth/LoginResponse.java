package peaksoft.springbootlibrary.dto.auth;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class LoginResponse {

    private String token;

    private String roleName;

}
