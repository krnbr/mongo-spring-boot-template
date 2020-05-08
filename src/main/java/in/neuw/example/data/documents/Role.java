package in.neuw.example.data.documents;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.ZonedDateTime;

/**
 * @author Karanbir Singh on 05/07/2020
 */
@Getter
@Setter
@Accessors(chain = true)
@Document(collection = "roles")
@TypeAlias("role")
public class Role implements Persistable<String> {

    @Id
    private String id;

    @Field("role_name")
    @Indexed(unique = true)
    private String role;

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
