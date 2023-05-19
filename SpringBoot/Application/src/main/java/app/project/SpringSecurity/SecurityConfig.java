
package app.project.SpringSecurity;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import app.project.jwt.JwtAuthenticateEntryPoint;
import app.project.jwt.JwtRequestFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
	
	@Autowired
	private JwtAuthenticateEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private UserDetailsImpl userservice;
	
	@Autowired
    private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
		try {
			authProvider.setPasswordEncoder(passwordEncoder());
			authProvider.setUserDetailsService(userDetailsService());
		}catch(Exception e) {
			System.out.println("die :"+e.getMessage());
		}
		return authProvider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		try {
			/*auth.authenticationProvider(authenticationProvider());
			auth.userDetailsService(userDetailsService);*/
			auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/*@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	*/
	@Override
	public void configure(HttpSecurity http) {
		try {
			http.cors().and().csrf().disable()
			.authorizeRequests()
			.antMatchers("/login").permitAll()
			.antMatchers("/SignUp").permitAll()
			.antMatchers("/verify").permitAll()
			.antMatchers("/ForgotPassword").permitAll()
			.antMatchers("/ChangerPassword").permitAll()
			.antMatchers("/getSpecialte").permitAll()
			.antMatchers("/updateWelcome").permitAll()
			.antMatchers("/getClasse").permitAll()
			.antMatchers("/getRoleByid").permitAll()
			.antMatchers("/GetAllRole").permitAll()
			.antMatchers("/getClasseById").permitAll()
			.antMatchers("/ExistToken").permitAll()
			.antMatchers("/ExistMail").permitAll()
			.antMatchers("/UpdateStatus").permitAll()
			.antMatchers("/getUsers").permitAll()
			.antMatchers("/GetNotif").permitAll()
			.antMatchers("/DeleteNotificationById").permitAll()
			.antMatchers("/DeleteAllNotif").permitAll()
			.antMatchers("/GetNotifNotSeen").permitAll()
			.antMatchers("/updateNotif").permitAll()
			.antMatchers("/getUserAuthentifie").permitAll()
			.antMatchers("/uploadPhoto").permitAll()
			.antMatchers("/EditInfoPersonnel").permitAll()
			.antMatchers("/getDocuments").permitAll()
			.antMatchers("/CheckPassword").permitAll()
			.antMatchers("/ChangerPasswordActuel").permitAll()
			.antMatchers("/SendChangedEmail").permitAll()
			.antMatchers("/updateEmail").permitAll()
			.antMatchers("/getSepecialite").permitAll()
			.antMatchers("/deleteSpec").permitAll()
			.antMatchers("/updateSpecilte").permitAll()
			.antMatchers("/AddSpec").permitAll()
			.antMatchers("/AddClasse").permitAll()
			.antMatchers("/ChangerSpecialite").permitAll()
			.antMatchers("/GetAllSpec").permitAll()
			.antMatchers("/GetClasse").permitAll()
			.antMatchers("/getAllClasse").permitAll()
			.antMatchers("/UpdateClasse").permitAll()
			.antMatchers("/deleteClasse").permitAll()
			.antMatchers("/ChangerClasse").permitAll()
			.antMatchers("/getDoc").permitAll()
			.antMatchers("/ChangerEtat").permitAll()
			.antMatchers("/DeleteUserDoc").permitAll()
			.antMatchers("/AddMessage").permitAll()
			.antMatchers("/GetAllMessages").permitAll()
			.antMatchers("/AllUserChat").permitAll()
			.antMatchers("/AllChatPrivate").permitAll()
			.antMatchers("/getUserByID").permitAll()
			.antMatchers("/AddMessagePrivate").permitAll()
			.anyRequest().authenticated()
			 .and()
			 .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		 	    // Add a filter to validate the tokens with every request
				http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	    @Bean
	    public CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(Arrays.asList("*"));
	        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
	        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));

	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);

	        return  source;
	    }
	   
	
}

