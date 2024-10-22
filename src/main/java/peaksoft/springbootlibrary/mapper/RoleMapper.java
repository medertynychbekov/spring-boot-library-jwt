package peaksoft.springbootlibrary.mapper;

import org.springframework.stereotype.Component;
import peaksoft.springbootlibrary.dto.RoleResponse;
import peaksoft.springbootlibrary.model.RoleEntity;

@Component
public class RoleMapper {

    public RoleResponse mapToResponse(RoleEntity roleEntity) {
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setId(roleEntity.getId());
        roleResponse.setName(roleResponse.getName());
        return roleResponse;
    }


}
