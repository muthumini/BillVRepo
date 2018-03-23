package lk.dialog.BillVerification.security;

import lk.dialog.BillVerification.model.Role;
import lk.dialog.BillVerification.model.User;
import lk.dialog.BillVerification.service.RoleService;
import lk.dialog.BillVerification.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Set;

public class BVAUserDetailsService implements UserDetailsService {

    private UserService userService;

    private RoleService roleService;

    public BVAUserDetailsService(UserService userService, RoleService roleService) {
        Assert.notNull(userService, "UserService cannot be null");
        Assert.notNull(roleService, "RoleService cannot be null");
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Set<GrantedAuthority> authorities = new HashSet<>();
        User user = userService.findUserByUsername(s);

        if (user == null) {
            throw new UsernameNotFoundException(s);
        }


        Role role = user.getRole();
            authorities.add(new SimpleGrantedAuthority(role.getRole()));

      /*  List<UserGroup> groups = user.getUserGroups();
        groups.forEach(userGroup -> userGroupService.getRolesOfGroup(userGroup.getName(), user.getTenant().getId()).forEach(role ->
                roleService.getPermissions(role.getName(), user.getTenant().getId()).forEach(permission ->
                        authorities.add(new SimpleGrantedAuthority(permission.getName())))));
*/
        /*
            User Status
            9 - Account Expired
            8 - Account Locked
            7 - Credentials Expired
            6 - Email not verified
         */
        BVAUser jwtUser = new BVAUser(s, authorities, true, true,
                true, true, user.getPassword());
        return jwtUser;
    }
}
