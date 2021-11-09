package sportEvents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import sportEvents.service.UserSecurity;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableTransactionManagement
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    AuthenticationSuccessHandler successHandler;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http./*sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().*/
                authorizeRequests()
                .antMatchers("/api/players/{userId}/**")
                .access("@userSecurity.hasUserId(authentication,#userId) or hasRole('ADMIN')")
                .antMatchers("/api/players/**").hasAnyRole("ADMIN")
                .antMatchers("/api/organizers/{userId}/**")
                .access("@userSecurity.hasUserId(authentication,#userId) or hasRole('ADMIN')")
                .antMatchers("/api/organizers/**").hasAnyRole("ADMIN")
                .antMatchers("/api/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/api/events/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .and()
                .formLogin()
                .successHandler(successHandler)
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and().httpBasic() //TODO DISABLE FOR PRODUCTION - POSTMAN
                .and()
                .csrf().disable(); //TODO DISABLE FOR PRODUCTION - POSTMAN
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}