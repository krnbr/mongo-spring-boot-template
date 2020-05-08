package in.neuw.example.data.repositories;

import in.neuw.example.data.documents.Role;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface RoleRepository extends ReactiveMongoRepository<Role, String> {

    Mono<Role> findByRole(String role);

    Flux<Role> findAllByRoleIn(List<String> roles);

}
