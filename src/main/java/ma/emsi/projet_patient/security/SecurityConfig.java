package ma.emsi.projet_patient.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
       PasswordEncoder passwordEncoder=passwordEncoder();
        auth.inMemoryAuthentication().withUser("user").password(passwordEncoder.encode("password")).roles("USER");
        auth.inMemoryAuthentication().withUser("asmaa").password(passwordEncoder.encode("asmaa98")).roles("ADMIN","USER");

    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
     http.formLogin().loginPage("/login");
     http.authorizeRequests().antMatchers("/save**/**","/delete**/**","/edit**/**","/form**/**").hasRole("ADMIN");
     http.authorizeRequests().antMatchers("/patients**/**").hasRole("USER");
     http.authorizeRequests().antMatchers("/user**/**", "/login", "/webjars/**").permitAll();
     http.authorizeRequests().anyRequest().authenticated();
     http.csrf();
     //http.authorizeRequests().antMatchers("/user**/**").permitAll();
     http.exceptionHandling().accessDeniedPage("/notAuthorized");

     //http.csrf().disable();

    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }





}
;