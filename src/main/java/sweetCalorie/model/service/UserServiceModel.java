package sweetCalorie.model.service;

import sweetCalorie.constant.GlobalConstants;
import sweetCalorie.model.entity.Role;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class UserServiceModel {

    private String id;
    private String username;
    private String password;
    private String email;
    private Set<Role> authorities;

    public UserServiceModel() {
        this.authorities = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotNull(message = GlobalConstants.USERNAME_NEEDED)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @NotNull(message = GlobalConstants.PASSWORD_NEEDED)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull(message = GlobalConstants.EMAIL_NEEDED)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }
}
