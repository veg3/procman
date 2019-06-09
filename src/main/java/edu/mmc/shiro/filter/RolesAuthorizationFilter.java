package edu.mmc.shiro.filter;


import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;  
  
import org.apache.shiro.subject.Subject;  
import org.apache.shiro.web.filter.authz.AuthorizationFilter; 
public class RolesAuthorizationFilter extends AuthorizationFilter {  
  
    @Override  
    protected boolean isAccessAllowed(ServletRequest req, ServletResponse resp, Object mappedValue) throws Exception {  
        Subject subject = getSubject(req, resp);  
        String[] rolesArray = (String[]) mappedValue;
        for (int i = 0; i < rolesArray.length; i++) {
            if (subject.hasRole(rolesArray[i])) { //若当前用户是rolesArray中的任何一个，则有权限访问  
                return true;  
            }  
        }  
        return false;
    }  
}