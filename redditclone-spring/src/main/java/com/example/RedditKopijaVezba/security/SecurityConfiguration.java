package com.example.RedditKopijaVezba.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureAuthentication(
            AuthenticationManagerBuilder authenticationManagerBuilder)
            throws Exception {

        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean()
            throws Exception {
        AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
        authenticationTokenFilter
                .setAuthenticationManager(authenticationManagerBean());
        return authenticationTokenFilter;
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.headers().cacheControl().disable();
        //Neophodno da ne bi proveravali autentifikaciju
        httpSecurity.cors();


        //sledeca linija je neophodna zbog nacina na koji h2 konzola komunicira sa aplikacijom
        httpSecurity.headers().frameOptions().disable();
        httpSecurity.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/registration").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/izmenaProfila").permitAll()
                .antMatchers(HttpMethod.POST,"/auth/promenaLozinke").permitAll()

                .antMatchers(HttpMethod.GET, "/posts/all").permitAll()
                .antMatchers(HttpMethod.POST, "/posts").permitAll()
                .antMatchers(HttpMethod.DELETE, "/posts/{id}").permitAll()
                .antMatchers(HttpMethod.PUT, "/posts/{id}").permitAll()
                .antMatchers(HttpMethod.POST, "/posts/{id}/upvote").permitAll()
                .antMatchers(HttpMethod.POST, "/posts/{id}/downvote").permitAll()
                //.antMatchers(HttpMethod.GET, "/posts/karma/{postId}").permitAll()


                .antMatchers(HttpMethod.GET, "/zajednice/all").permitAll()
                .antMatchers(HttpMethod.POST, "/zajednice").permitAll()
                .antMatchers(HttpMethod.DELETE, "/zajednice/{id}").permitAll()
                .antMatchers(HttpMethod.PUT, "/zajednice/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/zajednice/korisnik/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/zajednice/{id}/posts").permitAll()

                .antMatchers(HttpMethod.GET, "/komentar/post/{id}").permitAll()
                .antMatchers(HttpMethod.POST, "/komentar").permitAll()
                .antMatchers(HttpMethod.PUT, "/komentar/{id}").permitAll()
                .antMatchers(HttpMethod.DELETE, "/komentar/{id}").permitAll()

                .antMatchers(HttpMethod.DELETE, "/admin/{id}").permitAll()


                .anyRequest().authenticated();

        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

}
