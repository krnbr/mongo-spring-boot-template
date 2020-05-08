package in.neuw.example.service;

import in.neuw.example.data.documents.Role;
import in.neuw.example.data.documents.User;
import in.neuw.example.data.documents.UserProfile;
import in.neuw.example.data.repositories.RoleRepository;
import in.neuw.example.data.repositories.UserProfileRepository;
import in.neuw.example.data.repositories.UserRepository;
import in.neuw.example.exception.AppRuntimeException;
import in.neuw.example.model.UserRequest;
import in.neuw.example.utils.constants.ErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.Arrays;

/**
 * @author Karanbir Singh on 05/08/2020
 */
@Slf4j
@Service
public class UserService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Transactional
    public Mono<UserProfile> saveUserAndItsProfile(final UserRequest userRequest) {

        Mono<Role> roleMono = roleRepository.findByRole("USER");

        Mono<User> userMono = roleMono.flatMap(r -> {
            User user = new User()
                    .setUserName(userRequest.getUsername())
                    .setPassword(userRequest.getPassword());
            user.setRoles(Arrays.asList(r));
            return userRepository.save(user);
        }).onErrorResume(ex -> {
            log.error(ex.getMessage());
            if(ex instanceof DuplicateKeyException) {
                String errorMessage = "The user with the username '"+userRequest.getUsername()+"' already exists";
                log.error(errorMessage);
                return Mono.error(new AppRuntimeException(errorMessage, ErrorCodes.CONFLICT, ex));
            }
            return Mono.error(new AppRuntimeException(ex.getMessage(), ErrorCodes.INTERNAL_SERVER_ERROR, ex));
        });

        Mono<UserProfile> userProfileMono = userMono.flatMap(u -> {
            UserProfile userProfile = new UserProfile()
                    .setAddress(userRequest.getAddress())
                    .setEmail(userRequest.getEmail())
                    .setMobile(userRequest.getMobile())
                    .setUser(u);
            return userProfileRepository.save(userProfile);
        }).onErrorResume(ex -> {
            log.error(ex.getMessage());
            if(ex instanceof DuplicateKeyException) {
                String errorMessage = "The user with the profile mobile'"+userRequest.getMobile()+"' and/or - email '"+userRequest.getEmail()+"' already exists";
                log.error(errorMessage);
                return Mono.error(new AppRuntimeException(errorMessage, ErrorCodes.CONFLICT, ex));
            }
            return Mono.error(new AppRuntimeException(ex.getMessage(), ErrorCodes.INTERNAL_SERVER_ERROR, ex));
        });

        return userProfileMono;

    }

}
