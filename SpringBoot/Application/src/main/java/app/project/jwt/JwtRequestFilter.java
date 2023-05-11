
package app.project.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import app.project.SpringSecurity.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;

@Service
public class JwtRequestFilter extends OncePerRequestFilter {
	    @Autowired
	    private UserDetailsImpl jwtUserDetailsService;

	    @Autowired
	    private jwtTokenUtil jwtTokenUtil;

		@Override
		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
				FilterChain filterChain) throws ServletException, IOException {
		
		final String requestTokenHeader=request.getHeader("Authorization");
		String username=null;
		String jwtToken=null;
		
		 if(requestTokenHeader!=null && requestTokenHeader.startsWith("beare")) {
			//when i start read token in 7 because  JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
			  jwtToken=requestTokenHeader.substring(7);
			  try {
				  username=jwtTokenUtil.getUsernameFromToken(jwtToken);
			  }catch(IllegalArgumentException e) {
				  System.out.println("can't get token");
			  }catch(ExpiredJwtException e) {
				  System.out.println("token expired");
			  }
		 }else {
			 logger.warn("JWT Token Invalid");
		 }
		 
		 if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			 UserDetails userDetails=this.jwtUserDetailsService.loadUserByUsername(username);
			 if(jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				 UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =new UsernamePasswordAuthenticationToken(userDetails,
						 null,userDetails.getAuthorities());
				   usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				   SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			 }
		 }
		
		  filterChain.doFilter(request, response);
		}
	    
}

