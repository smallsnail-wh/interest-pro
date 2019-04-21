package com.interest.message.oauth2;

import com.interest.common.exception.InterestAuthenticationEntryPoint;
import com.interest.common.exception.handler.InterestAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MyResourceServerConfigurerAdapter extends ResourceServerConfigurerAdapter {


    @Autowired
    private RedisConnectionFactory redisConnection;

    @Autowired
    private InterestAccessDeniedHandler interestAccessDeniedHandler;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.authenticationEntryPoint(new InterestAuthenticationEntryPoint()).accessDeniedHandler(interestAccessDeniedHandler);
        resources.tokenStore(new RedisTokenStore(redisConnection));
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers("/public/**").permitAll()
                .antMatchers("/**/*.jpg", "/**/*.png", "/**/*.jpeg").permitAll()
                .antMatchers("/interest/**").permitAll()
                .antMatchers("/**/*.html", "/**/*.css", "/**/*.js", "/**/swagger-resources/**", "/**/*.woff2", "/**/v2/**").permitAll()
                .anyRequest()
                .authenticated();

        http.cors().disable()
                .csrf().disable();

    }

}
