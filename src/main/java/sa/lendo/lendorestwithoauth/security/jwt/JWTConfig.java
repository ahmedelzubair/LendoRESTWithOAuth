package sa.lendo.lendorestwithoauth.security.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.context.annotation.Configuration;

@Configuration("jwtConfig")
public class JWTConfig {

    public static final String SECRET = "NdRfUjXn2r5u8x/A?D(G+KbPeShVkYp3s6v9y$B&E)H@McQfTjWnZq4t7w!z%C*F";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final long REFRESH_EXPIRATION_TIME = EXPIRATION_TIME * 2; // 20 days
    public static final String HEADER_REFRESH_TOKEN = "Refresh-Token";


    public static Algorithm getAlgorithm() {
        return Algorithm.HMAC256(JWTConfig.SECRET.getBytes());
    }
}