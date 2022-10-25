package sa.lendo.lendorestwithoauth.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JWTTokenVerifier extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(JWTConfig.HEADER_STRING);

        if (authorizationHeader == null || !authorizationHeader.startsWith(JWTConfig.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.replace(JWTConfig.TOKEN_PREFIX, "");
        try {
            DecodedJWT jwt = getDecodedJWT(token);

            String username = jwt.getSubject();
            List<String> roles = jwt.getClaim("authorities").asList(String.class);

            Authentication authentication = getAuthentication(username, roles);

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (JWTVerificationException exception) {
            //Invalid signature/claims
            throw new IllegalStateException(String.format("Token %s cannot be trusted", token));
        }

        filterChain.doFilter(request, response);
    }

    private Authentication getAuthentication(String username, List<String> roles) {
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
