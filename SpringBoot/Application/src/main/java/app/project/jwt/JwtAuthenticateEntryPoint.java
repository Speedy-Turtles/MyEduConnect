<<<<<<< HEAD
package app.project.jwt;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;
@Service
public class JwtAuthenticateEntryPoint implements AuthenticationEntryPoint, Serializable {
	
	private static final long serialVersionUID = 1L;

		@Override
		public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException)
				throws IOException, ServletException {
			  response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
			
		}
}
=======
package app.project.jwt;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;
@Service
public class JwtAuthenticateEntryPoint implements AuthenticationEntryPoint, Serializable {
	
	private static final long serialVersionUID = 1L;

		@Override
		public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException)
				throws IOException, ServletException {
			  response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
			
		}
}
>>>>>>> c475a485596fd9a763dff634b70b71401dae7c73
