package bo.edu.ucb.todoappbackend.bl;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import bo.edu.ucb.todoappbackend.dao.UserDao;
import bo.edu.ucb.todoappbackend.dto.LoginRequestDto;
import bo.edu.ucb.todoappbackend.dto.TokenDto;
import bo.edu.ucb.todoappbackend.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class AuthBl {
    private static final Logger LOG = LoggerFactory.getLogger(AuthBl.class); // LOGGER
    public static final String KEY = "TODO-APP-2-2023-UCB";
    private final UserDao userDao;

    public AuthBl(UserDao userDao) { // la inyección siempre se hace por el constructor
        this.userDao = userDao;
    }

    public TokenDto login(LoginRequestDto loginRequestDto) {
        User user = userDao.findByUsernameAndPassword(loginRequestDto.getUsername(), loginRequestDto.getPassword());
        if (user == null) {
            LOG.info("Error al autenticar: Credenciales inválidas");
            throw new RuntimeException("Credenciales inválidas");
        }
        else {
            TokenDto tokenDto = new TokenDto();
            tokenDto.setAuthToken(generateToken(user.getUserId(), "AUTH", 120));
            tokenDto.setRefreshToken(generateToken(user.getUserId(), "REFRESH", 240));
            LOG.info("Autenticación exitosa");
            return tokenDto;
        }
    }

    private String generateToken(Long userId, String type, int minutes) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(KEY);
            LOG.info("Generando token " + userId + " " + type + " " + minutes);
            return JWT.create()
                    .withIssuer("www.ucb.edu.bo")
                    .withClaim("userId", userId)
                    .withClaim("type", type)
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000L * 60 * minutes)) // 24 horas
                    .sign(algorithm);
        } catch (JWTCreationException ex) {
            LOG.info("Error al generar el token " + userId + " " + type + " " + minutes);
            throw new RuntimeException("Error al generar el token", ex);
        }
    }

    public boolean validateToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256(KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("www.ucb.edu.bo")
                    .build();
            assert token != null;
            decodedJWT = verifier.verify(token);
            LOG.info("Token válido: " + decodedJWT.getClaim("userId").asLong());
            return true;
        } catch (JWTVerificationException ex) {
            LOG.info("Token inválido: " + ex.getMessage());
            return false;
        }
    }

    public Long getUserIdFromToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256(KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("www.ucb.edu.bo")
                    .build();
            assert token != null;
            decodedJWT = verifier.verify(token);
            LOG.info("Token válido: " + decodedJWT.getClaim("userId").asLong());
            return decodedJWT.getClaim("userId").asLong();
        } catch (JWTVerificationException ex) {
            LOG.info("Token inválido: " + ex.getMessage());
            return null;
        }
    }
}
