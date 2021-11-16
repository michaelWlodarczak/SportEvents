package sportEvents.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import sportEvents.entity.enums.UserType;
import sportEvents.entity.repositories.UserRepository;
import sportEvents.service.SecurityUserDetails;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecuritySuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String redirectUrl = "/";
        String type = "";
        UserType userType;
      if (authentication.getPrincipal() instanceof UserDetails) {
              SecurityUserDetails userDetails = (SecurityUserDetails) authentication.getPrincipal();
              if(!userDetails.isEnabled()){
                  throw new IllegalStateException();
              }
              userType = userRepository.getUserType(userDetails.getUserId());
             switch (userType) {
                 case PLAYER:
                     type = "api/players";
                     break;
                 case ORGANIZER:
                     type = "api/organizers";
                     break;
             }
              redirectUrl += type + "/"+userDetails.getUserId();
          new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);
      } else {
            throw new IllegalStateException();
        }

    }
}
