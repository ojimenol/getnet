package com.santander.getnet.jwt.handler;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwe;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import jakarta.xml.bind.DatatypeConverter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

public class JwtIO {

    private static final String SECRET_KEY =
        "oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w";

    public static void main(String[] args) {
        var jwtHandler = new JwtIO();

        var keyString = jwtHandler.generateHmacShaKey();

        var jwt = jwtHandler.createJWT(keyString,"1", "me", "subject", 300000L);
        System.out.println("Jwt: " + jwt);
    }

    public String generateHmacShaKey() {
        //Generating a safe HS256 Secret key
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        String secretString = Encoders.BASE64.encode(key.getEncoded());
        System.out.println("Secret key: " + secretString);
        return secretString;
    }

    //Sample method to construct a JWT
    public String createJWT(String secret, String id, String issuer, String subject, long ttlMillis) {

        //The JWT signature algorithm we will be using to sign the token
        var signatureAlgorithm = Jwts.SIG.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getId());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
            .setId(id)
            .setIssuedAt(now)
            .setSubject(subject)
            .setIssuer(issuer)
            .signWith(signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public Jwe<Claims> decodeJWT(String jwt) {

        var signatureAlgorithm = Jwts.SIG.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        SecretKey signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getId());

        //This line will throw an exception if it is not a signed JWS (as expected)
        var jwe = Jwts.parser()
            .decryptWith(signingKey)
            .build()
            .parseEncryptedClaims(jwt);
        return jwe;
    }

}
