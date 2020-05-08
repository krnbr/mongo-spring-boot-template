package in.neuw.example.data.documents;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Karanbir Singh on 05/07/2020
 */
@Getter
@Setter
@Accessors(chain = true)
@Document(collection = "users")
@JsonInclude(JsonInclude.Include.NON_NULL)
@TypeAlias("user")
public class User implements Persistable<String> {

    @Id()
    private String id;

    @Field("username")
    @Indexed(unique = true)
    @JsonProperty("username")
    private String userName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @CreatedDate
    private ZonedDateTime created;

    @LastModifiedDate
    private ZonedDateTime updated;

    private Boolean deleted;

    private Boolean enabled;

    @DBRef(lazy = true)
    @JsonProperty("roles")
    private List<Role> roles = new ArrayList();

    @Override
    @JsonIgnore
    public boolean isNew() {
        if(getCreated() == null)
            return true;
        else
            return false;
    }
}
