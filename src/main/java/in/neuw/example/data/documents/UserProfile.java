package in.neuw.example.data.documents;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;

/**
 * @author Karanbir Singh on 05/08/2020
 */
@Getter
@Setter
@Accessors(chain = true)
@Document(collection = "user_profiles")
@JsonInclude(JsonInclude.Include.NON_NULL)
@TypeAlias("user_profile")
public class UserProfile implements Persistable<String> {

    @Id
    private String id;

    @Indexed(unique = true)
    private String mobile;

    @Indexed(unique = true)
    private String email;

    private String address;

    private String firstName;

    private String lastName;

    @DBRef
    private User user;

    @CreatedDate
    private ZonedDateTime created;

    @LastModifiedDate
    private ZonedDateTime updated;

    private Boolean deleted;

    private Boolean enabled;

    @Override
    @JsonIgnore
    public boolean isNew() {
        if(getCreated() == null)
            return true;
        else
            return false;
    }


}
