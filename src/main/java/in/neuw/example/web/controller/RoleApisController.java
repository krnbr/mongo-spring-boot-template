package in.neuw.example.web.controller;

import in.neuw.example.data.documents.Role;
import in.neuw.example.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Karanbir Singh on 05/08/2020
 */
@Slf4j
@RestController
@RequestMapping("/apis/roles")
public class RoleApisController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public Mono<Role> saveRole(final @RequestBody Role role) {
        return roleService.saveRole(role);
    }

}
