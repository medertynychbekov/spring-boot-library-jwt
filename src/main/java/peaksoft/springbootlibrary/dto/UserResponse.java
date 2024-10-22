package peaksoft.springbootlibrary.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserResponse {

    private Long id;

    private String name;

    private String password;

    private List<String> roles;
}
