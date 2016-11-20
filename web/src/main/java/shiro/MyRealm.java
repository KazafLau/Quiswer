package shiro;

import entities.User;
import function.UserFunction;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.context.annotation.Bean;

/**
 * Created by kazaf on 16-11-7.
 */

public class MyRealm extends AuthorizingRealm {

    private UserFunction userFunction;

    public UserFunction getUserFunction() {
        return userFunction;
    }

    public void setUserFunction(UserFunction userFunction) {
        this.userFunction = userFunction;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;

        String useremail=token.getUsername();
        int userID=userFunction.GetUserID(useremail);
        User user=userFunction.GetUserDAO(userID);
        if(user!=null){
            return  new SimpleAuthenticationInfo(user.getUseremail(),user.getUserpassword(),getName());
        }
        return null;
    }
}
