package poc.springbootexample.models.User;

import poc.springbootexample.config.Role;
import poc.springbootexample.models.Group.GroupOneOne;

import javax.persistence.*;
import java.io.Serializable;

// Created by norner on 11/03/2017.



@Entity
@Table(name = "usersOneOne")
public class UserOneOne implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private long id;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ROLE")
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "GROUP_ID")  //indicates that foreign key is help by group_id (in group class)
    private GroupOneOne group;

    @Version
    @Column(name = "version")
    private long version;


    public UserOneOne() {}

    public UserOneOne(long id) {
        this.id = id;
    }

    public UserOneOne(String email, String name, Role role, GroupOneOne group) {
        this.email = email;
        this.name = name;
        this.role = role;
        this.group = group;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public GroupOneOne getGroup() {
        return group;
    }

    public void setGroup(GroupOneOne group) {
        this.group = group;
    }
}
