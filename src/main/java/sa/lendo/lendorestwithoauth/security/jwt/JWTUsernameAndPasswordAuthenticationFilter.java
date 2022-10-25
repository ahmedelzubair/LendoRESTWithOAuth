package sa.lendo.lendorestwithoauth.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import sa.lendo.lendorestwithoauth.users.domain.UserToken;
import sa.lendo.lendorestwithoauth.users.tokens.TokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public JWTUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
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
        super.unsuccessfulAuthentication(request, response, failed);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {


        String accessToken = JWTConfig.getJWTToken(JWTConfig.EXPIRATION_TIME, authResult.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toArray(String[]::new), authResult.getName());

        String refreshToken = JWTConfig.getJWTToken(JWTConfig.REFRESH_EXPIRATION_TIME, authResult.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toArray(String[]::new), authResult.getName());

        saveFirstUserTokensToDB(accessToken, refreshToken, authResult.getName());

        response.addHeader(JWTConfig.HEADER_AUTHORIZATION, JWTConfig.TOKEN_PREFIX + accessToken);
        response.addHeader(JWTConfig.HEADER_REFRESH_TOKEN, JWTConfig.TOKEN_PREFIX + refreshToken);

    }

    private void saveFirstUserTokensToDB(String accessToken, String refreshToken, String username) {
        UserToken userToken = new UserToken();
        userToken.setAccessToken(accessToken);
        userToken.setRefreshToken(refreshToken);
        userToken.setUsername(username);

        tokenService.saveNewUserToken(userToken);
    }


}
