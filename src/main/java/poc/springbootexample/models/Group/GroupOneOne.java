package poc.springbootexample.models.Group;

import com.fasterxml.jackson.annotation.JsonIgnore;
import poc.springbootexample.models.User.UserOneOne;

import javax.persistence.*;
import java.io.Serializable;

/*Created by norner on 11/03/2017.*/

@Entity
@Table(name = "groupsOneOne")
public class GroupOneOne implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GROUP_ID")
    private long id;

    @Column(name = "GROUP_NAME")
    private String groupName;

    @OneToOne(mappedBy = "group", cascade = CascadeType.ALL)
    private UserOneOne user;

    @Column(name = "GROUP_TYPE", insertable = false, updatable = false)
    @JsonIgnore
    private String groupType;

    public GroupOneOne() {}

    public GroupOneOne(long id) {
        this.id = id;
    }

    public GroupOneOne(String groupName) {
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

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public UserOneOne getUser() {
        return user;
    }

    public void setUser(UserOneOne user) {
        this.user = user;
    }
}
