package dancebase.springmvc.security.rest.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

import javax.sql.DataSource;


@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        User.UserBuilder builder = User.withDefaultPasswordEncoder();
//        auth.inMemoryAuthentication().withUser(builder.username("Rasim").password("rasl_1998").roles("ADMIN"))
//                .withUser(builder.username("ivan").password("ivan").roles("CLIENT"));
   auth.jdbcAuthentication().dataSource(dataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests().
                antMatchers("/").hasAnyRole("ADMIN","CLIENT")
               .antMatchers("/adminPage").hasRole("ADMIN").and().formLogin().permitAll();
    }
}
