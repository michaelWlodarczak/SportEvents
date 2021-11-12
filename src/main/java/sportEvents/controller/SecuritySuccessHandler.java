package sportEvents.controller;

import org.springframework.aop.framework.adapter.DefaultAdvisorAdapterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import sportEvents.entity.enums.UserType;
import sportEvents.entity.repositories.UserRepository;
import sportEvents.service.SecurityUserDetails;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static sportEvents.entity.enums.UserType.ORGANIZER;
import static sportEvents.entity.enums.UserType.PLAYER;

@Controller
public class SecuritySuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        String redirectUrl = "/";
        String type = "";
        UserType userType;
        if (authentication.getPrincipal() instanceof UserDetails) {
            SecurityUserDetails securityUserDetails = (SecurityUserDetails) authentication.getPrincipal();
            if (!securityUserDetails.isEnabled()) {
                throw new IllegalStateException();
            }
            userType = userRepository.getUserType(securityUserDetails.getUserId());
            switch (userType) {
                case PLAYER:
                    type = "api/players";
                    break;
                case ORGANIZER:
                    type = "api/organizers";
                    break;
            }
            redirectUrl += type + "/" + securityUserDetails.getUserId();
            new DefaultRedirectStrategy().sendRedirect(httpServletRequest,httpServletResponse,redirectUrl);
        }else {
            throw new IllegalStateException();
        }
    }
}
