package in.neuw.example.data.repositories;

import in.neuw.example.data.documents.UserProfile;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserProfileRepository extends ReactiveMongoRepository<UserProfile, String> {
}
