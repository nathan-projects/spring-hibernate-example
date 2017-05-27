package poc.springbootexample.models.User;

import poc.springbootexample.config.Role;
import poc.springbootexample.models.Group.GroupOneMany;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by norner on 11/03/2017.
 */

@Entity
@Table(name = "users")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE) //default single table per class - maps subclasses to base class table
//can also have joined - table per class/subclass - abstract and concrete classes get their own table
//can also have TABLE_PER_CLASS - which is a table per concrete class (not abstract)
public class UserOneMany implements Serializable {

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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "GROUP_ID")  //indicates that foreign key is help by group_id (in group class)
    private GroupOneMany group;

    @Version
    @Column(name = "version")
    private long version;

    //TODO - play with jpa/hibernate annotations incl. cascading deletes
    //eg. add group (note that group is a reserved keyword )
    //if you delete a group, you delete all users from that group
    //one to many - groups to users
    //users can have many groups - bonus task


    //@Version - used for tracking updations. for 1,2,3,3 updates or for timestamps when updating
    //@Transient - not serialised - ie not persisted to the db - if semantics are different
    //@OrderBy("firstName asc") - orders a collection - better to just do it in the query
    //@Lob - for annotating large objects - eg. images
    //@PrimaryKeyJoinColumn - used in uni directional one to one when the two share the same primary key. the second entity's id doesn't need a generatedvalue

    //navigation bar with different pages testing out different joins: TODO: many to many and one to one
    //TODO:
    //@MapsId
    //@JoinTable
    //@ManyToMany
    //@JsonIgnore - what does this do??

    public UserOneMany() {}

    public UserOneMany(long id) {
        this.id = id;
    }

    public UserOneMany(String email, String name, Role role, GroupOneMany group) {
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

    public GroupOneMany getGroup() {
        return group;
    }

    public void setGroup(GroupOneMany group) {
        this.group = group;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
