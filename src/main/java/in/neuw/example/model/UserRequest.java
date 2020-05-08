package in.neuw.example.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Karanbir Singh on 05/08/2020
 */
@Getter
@Setter
@Accessors(chain = true)
@Slf4j
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequest {

    private String username;
    private String password;
    private String mobile;
    private String email;
    private String address;
    private String firstName;
    private String lastName;

}
