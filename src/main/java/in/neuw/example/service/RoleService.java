package in.neuw.example.service;

import in.neuw.example.data.documents.Role;
import in.neuw.example.data.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author Karanbir Singh on 05/08/2020
 */
@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Mono<Role> saveRole(Role role) {
        return roleRepository.save(role);
    }

}
