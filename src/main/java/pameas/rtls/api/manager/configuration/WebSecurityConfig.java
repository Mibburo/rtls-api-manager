package pameas.rtls.api.manager.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // allow only specific ip to access endpoint
                // TODO: switch to live ip
                .antMatchers("/saveLocationData").permitAll()// hasIpAddress("127.0.0.1")
                .and()
                .csrf().disable();
    }
}

