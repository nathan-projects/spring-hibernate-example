package poc.springbootexample.models.Group;

import com.fasterxml.jackson.annotation.JsonIgnore;
import poc.springbootexample.models.User.UserOneMany;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by norner on 11/03/2017.
 */

@Entity
@Table(name = "groups")
@DiscriminatorColumn(name = "GROUP_TYPE", discriminatorType=DiscriminatorType.STRING)
@NamedQueries({
        @NamedQuery(name = "GroupOneMany.findGroup", query = "SELECT group FROM GroupOneMany group WHERE group.id = :id")
})
public class GroupOneMany implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GROUP_ID")
    private long id;

    @Column(name = "GROUP_NAME")
    private String groupName;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserOneMany> users;

    @Column(name = "GROUP_TYPE", insertable = false, updatable = false)
    @JsonIgnore
    private String groupType;

    public GroupOneMany() {}

    public GroupOneMany(long id) {
        this.id = id;
    }

    public GroupOneMany(String groupName) {
       this.groupName = groupName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<UserOneMany> getUsers() {
        return users;
    }

    public void setUsers(Set<UserOneMany> users) {
        this.users = users;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }
}
