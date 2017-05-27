package poc.springbootexample.config;

import java.util.Set;

/**
 * Created by norner on 14/04/2017.
 */
public class UserManyManyForm {
    private String name;
    private String email;
    private Role roleVal;
    private Set<Long> groupVal;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRoleVal() {
        return roleVal;
    }

    public void setRoleVal(Role roleVal) {
        this.roleVal = roleVal;
    }

    public Set<Long> getGroupVal() {
        return groupVal;
    }

    public void setGroupVal(Set<Long> groupVal) {
        this.groupVal = groupVal;
    }
}
