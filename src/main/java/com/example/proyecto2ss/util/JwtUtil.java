package com.example.proyecto2ss.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;

public class JwtUtil {
    private static final String CLAVE_SECRETA = "MiClaveSecretaParaProyecto2SS";
    // El token dura 8 horas
    private static final long TIEMPO_EXPIRACION = 8 * 60 * 60 * 1000;

    // creacion del token
    public static String generarToken(String username, String rol) {
        Algorithm algoritmo = Algorithm.HMAC256(CLAVE_SECRETA);
        return JWT.create()
                .withSubject(username)
                .withClaim("rol", rol)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TIEMPO_EXPIRACION))
                .sign(algoritmo);
    }

    // leer el token
    public static DecodedJWT validarToken(String token) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(CLAVE_SECRETA);
            JWTVerifier verifier = JWT.require(algoritmo).build();
            return verifier.verify(token);
        } catch (Exception e) {
            return null;
        }
    }

}
