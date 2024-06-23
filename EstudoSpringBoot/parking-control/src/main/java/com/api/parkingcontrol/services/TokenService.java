package com.api.parkingcontrol.services;

import com.api.parkingcontrol.models.UserModel;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}") //Variavel de ambiente para o secret de geração de token
    private String secret;

    public String generateToken(UserModel user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("parkingcontrol-api") //Diz a aplicação que esta criando
                    .withSubject(user.getLogin()) //diz o usuário que esta criando
                    .withExpiresAt(generateExpirationDate()) //define um tempo de expiração
                    .sign(algorithm); //faz a assinatura e geração do token
            return token;
        }catch (JWTCreationException exception){
            throw new RuntimeException("Erro while generate token", exception);
        }
    }

    //Metodo de validação do tokem
    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("parkingcontrol-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            return "Token Invalid or Expired";
        }
    }

    //retorna o tempo de apiração do token de acesso
    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
