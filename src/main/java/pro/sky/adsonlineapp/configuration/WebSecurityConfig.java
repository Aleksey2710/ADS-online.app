package pro.sky.adsonlineapp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pro.sky.adsonlineapp.service.UserService;
import pro.sky.adsonlineapp.service.impl.UserServiceImpl;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class WebSecurityConfig {

//    private UserServiceImpl userService;

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v3/api-docs",
            "/webjars/**",
            "/login",
            "/register"
    };


//  @Bean
//  public InMemoryUserDetailsManager userDetailsService() {
//    UserDetails user =
//        User.builder()
//            .username("user@gmail.com")
//            .password("password")
//            .passwordEncoder((plainText) -> passwordEncoder().encode(plainText))
//            .roles("USER")
//            .build();
//    return new InMemoryUserDetailsManager(user);
//  }

//    @Bean
//    public JdbcUserDetailsManager users(DataSource dataSource) {
//
//        UserDetails admin =
//                User.builder()
//                        .username("user@gmail.com")
//                        .password("password")
//                        .passwordEncoder((plainText) -> passwordEncoder().encode(plainText))
//                        .roles("ADMIN")
//                        .build();
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
////        if (jdbcUserDetailsManager.userExists(admin.getUsername())) {
////            jdbcUserDetailsManager.deleteUser(admin.getUsername());
////        }
////        jdbcUserDetailsManager.createUser(admin);
//        return jdbcUserDetailsManager;
//    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests()
//                .mvcMatchers(AUTH_WHITELIST).permitAll()
//                .mvcMatchers(HttpMethod.GET, "/ads").permitAll()
//                .antMatchers("/ads/**", "/users/**").hasRole("ADMIN")
//                .antMatchers(ENDPOINTS_FOR_USERS).hasRole("USER")
//                .and()
//                .httpBasic(withDefaults());
//
//        return http.build();
//    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        daoAuthenticationProvider.setUserDetailsService(userService);

        return new DaoAuthenticationProvider();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeHttpRequests(
                        (authorization) ->
                                authorization
                                        .mvcMatchers(AUTH_WHITELIST).permitAll()
                                        .mvcMatchers(HttpMethod.GET, "/ads").permitAll()
//                                        .mvcMatchers(ENDPOINTS_FOR_ADMINS).hasRole("ADMIN")
//                                        .mvcMatchers(ENDPOINTS_FOR_USERS).hasRole("USER")
                                        .mvcMatchers("/ads/**", "/users/**").authenticated())
                .cors()
                .and()
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
