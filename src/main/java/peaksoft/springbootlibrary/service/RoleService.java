package peaksoft.springbootlibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.springbootlibrary.dto.RoleResponse;
import peaksoft.springbootlibrary.mapper.RoleMapper;
import peaksoft.springbootlibrary.model.RoleEntity;
import peaksoft.springbootlibrary.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    public List<RoleResponse> findAll(){
        List<RoleEntity> roleEntityList = roleRepository.findAll();
        List<RoleResponse> roleResponseList = new ArrayList<>();
        for (RoleEntity roleEntity : roleEntityList){
            RoleResponse roleResponse = roleMapper.mapToResponse(roleEntity);
            roleResponseList.add(roleResponse);
        }
        return roleResponseList;
    }

    public  RoleEntity findByName(String name){
        return roleRepository.findByName(name);
    }

}
