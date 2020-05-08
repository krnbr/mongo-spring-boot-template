package in.neuw.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author Karanbir Singh on 05/08/2020
 */
@Getter
@Setter
@Accessors(chain = true)
public class RoleDto {

    private String name;

}
