package sa.lendo.lendorestwithoauth.security.jwt;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JWTUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JWTUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            UserLoginData userLoginData = new ObjectMapper().readValue(request.getInputStream(), UserLoginData.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userLoginData.getUsername(), userLoginData.getPassword());
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException("Error in getting username and password");
        }

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        System.out.println("Failed authentication with exception: " + failed.getMessage());
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {


        String token = JWT.create()
                .withSubject(authResult.getName())
                .withArrayClaim("authorities", authResult.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).toArray(String[]::new))
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + JWTConfig.EXPIRATION_TIME))
                .withIssuer(request.getRequestURL().toString())
                .sign(JWTConfig.getAlgorithm());

        String refreshToken = JWT.create()
                .withSubject(authResult.getName())
                .withArrayClaim("authorities", authResult.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).toArray(String[]::new))
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + JWTConfig.REFRESH_EXPIRATION_TIME))
                .withIssuer(JWTConfig.ISSUER)
                .sign(JWTConfig.getAlgorithm());

        response.addHeader(JWTConfig.HEADER_STRING, JWTConfig.TOKEN_PREFIX + token);
        response.addHeader(JWTConfig.HEADER_REFRESH_TOKEN, JWTConfig.TOKEN_PREFIX + refreshToken);


    }
}
