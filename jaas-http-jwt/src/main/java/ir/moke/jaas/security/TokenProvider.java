package ir.moke.jaas.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class TokenProvider {
    private static final Algorithm algorithm = Algorithm.HMAC256("secret");

    public static String create() {
        return JWT.create()
                .withIssuer("localhost")
                .sign(algorithm);
    }

    public static DecodedJWT verify(String token) {
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("localhost")
                .build();

        return verifier.verify(token);
    }
}
