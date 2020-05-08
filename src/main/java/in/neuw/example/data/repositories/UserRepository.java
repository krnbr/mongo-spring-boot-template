package in.neuw.example.data.repositories;

import in.neuw.example.data.documents.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Mono<User> findByUserName(String userName);

}