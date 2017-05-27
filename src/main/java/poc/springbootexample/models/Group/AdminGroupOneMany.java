package poc.springbootexample.models.Group;

import javax.persistence.*;

/**
 * Created by norner on 11/03/2017.
 */

@Entity
@DiscriminatorValue(value = "Admin")
public class AdminGroupOneMany extends GroupOneMany {

    private static final long serialVersionUID = -4791261083735661295L;

    public AdminGroupOneMany() {}

    public AdminGroupOneMany(String groupName) {
        setGroupName(groupName);
    }
}
