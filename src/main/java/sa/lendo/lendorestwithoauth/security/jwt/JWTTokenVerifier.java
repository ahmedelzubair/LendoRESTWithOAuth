package sa.lendo.lendorestwithoauth.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import sa.lendo.lendorestwithoauth.users.domain.UserToken;
import sa.lendo.lendorestwithoauth.users.tokens.TokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class JWTTokenVerifier extends OncePerRequestFilter {


    private final TokenService tokenService;

    public JWTTokenVerifier(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(JWTConfig.HEADER_AUTHORIZATION);

        if (authorizationHeader == null || !authorizationHeader.startsWith(JWTConfig.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = authorizationHeader.replace(JWTConfig.TOKEN_PREFIX, "");

        try {
            DecodedJWT jwt = getDecodedJWT(accessToken);
            String username = jwt.getSubject();
            List<String> roles = jwt.getClaim("authorities").asList(String.class);
            Authentication authentication = geUserAuthentication(username, roles);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        } catch (TokenExpiredException exception) {
            issueNewAccessTokenWithRefreshToken(response, accessToken, exception);
        } catch (JWTVerificationException exception) {
            // log exception
            log.error("Token {} has an issue {}", accessToken, exception.getMessage());
            setHeaderAndBodyError(response, exception);
        }
    }

    private void issueNewAccessTokenWithRefreshToken(HttpServletResponse response, String accessToken, JWTVerificationException exception) throws IOException {
        // check db for refresh token
        // if exists and valid, generate new access token and send it back to client
        // if not, send 401
        try {
            String refreshToken = tokenService.findRefreshTokenByAccessToken(accessToken).getRefreshToken();
            DecodedJWT jwt = getDecodedJWT(refreshToken);
            String[] roles = jwt.getClaim("authorities").asList(String.class).toArray(String[]::new);
            if (jwt.getExpiresAt().getTime() < System.currentTimeMillis()) {
                String newAccessToken = JWTConfig.getJWTToken(JWTConfig.EXPIRATION_TIME, roles, jwt.getSubject());
                response.setContentType(APPLICATION_JSON_VALUE);
                saveNewGeneratedAccessToken(refreshToken, newAccessToken);
                new ObjectMapper().writeValue(response.getOutputStream(), newAccessToken);
            } else {
                response.setContentType(APPLICATION_JSON_VALUE);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                Map<String, String> error = Map.of("error_message", exception.getMessage());
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } catch (TokenExpiredException e) {
            setHeaderAndBodyError(response, new IllegalAccessException("Refresh token expired"));
        }

    }

    private void saveNewGeneratedAccessToken(String refreshToken, String newAccessToken) {
        UserToken userToken = new UserToken();
        userToken.setRefreshToken(refreshToken);
        userToken.setAccessToken(newAccessToken);
        tokenService.updateUserToken(userToken);
    }

    private void setHeaderAndBodyError(HttpServletResponse response, Exception exception) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.addHeader(JWTConfig.HEADER_ERROR_MESSAGE, exception.getMessage());
        Map<String, String> error = Map.of("errorMessage", exception.getMessage());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), error);
    }

    private Authentication geUserAuthentication(String username, List<String> roles) {
        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

        return new UsernamePasswordAuthenticationToken(
                username, null, simpleGrantedAuthorities);
    }

    private DecodedJWT getDecodedJWT(String token) {
        JWTVerifier verifier = JWT.require(JWTConfig.getAlgorithm())
                .withIssuer(JWTConfig.ISSUER)
                .build();
        return verifier.verify(token);
    }

}
