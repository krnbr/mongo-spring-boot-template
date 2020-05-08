package in.neuw.example.web.controller;

import in.neuw.example.data.documents.UserProfile;
import in.neuw.example.model.UserRequest;
import in.neuw.example.service.UserService;
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
@RequestMapping("/apis/user/profile")
public class UserProfileApisController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Mono<UserProfile> saveUserProfile(final @RequestBody UserRequest userRequest) {
        return userService.saveUserAndItsProfile(userRequest);
    }

}
