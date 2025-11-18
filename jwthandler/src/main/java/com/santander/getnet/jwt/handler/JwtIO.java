package com.santander.getnet.jwt.handler;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class JwtIO {

    public static void main(String[] args) {
        var jwtHandler = new JwtIO();

        var jwt = jwtHandler.createJWTAndEncrypt();
        System.out.println("Jwe: " + jwt);

        var jwt2 = jwtHandler.createJWTAndSign();
        System.out.println("Jws: " + jwt2);
    }

    public String createJWTAndSign() {
        String out = null;
        KeyPairGenerator keyPairGenerator;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");

            keyPairGenerator.initialize(2048);

            // generate the key pair
            KeyPair keyPair = keyPairGenerator.genKeyPair();

            // create KeyFactory and RSA Keys Specs
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPublicKeySpec publicKeySpec = keyFactory.getKeySpec(keyPair.getPublic(), RSAPublicKeySpec.class);
            RSAPrivateKeySpec privateKeySpec = keyFactory.getKeySpec(keyPair.getPrivate(), RSAPrivateKeySpec.class);

            // generate (and retrieve) RSA Keys from the KeyFactory using Keys Specs
            RSAPublicKey publicRsaKey = (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);
            RSAPrivateKey privateRsaKey  = (RSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);

            var header = new JWSHeader.Builder(JWSAlgorithm.RS256)
                .type(JOSEObjectType.JWT)
                .keyID(UUID.randomUUID().toString())
                .build();

            var payload = buildClaimSet();

            var signedJWT = new SignedJWT(header, payload);
            signedJWT.sign(new RSASSASigner(privateRsaKey));

            out = signedJWT.serialize();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException| JOSEException e) {
            System.out.println(e.getMessage());
        }

        return out;
    }

    //Sample method to construct a JWT
    public String createJWTAndEncrypt() {

        String out = null;
        KeyPairGenerator keyPairGenerator;

        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");

            keyPairGenerator.initialize(2048);

            // generate the key pair
            KeyPair keyPair = keyPairGenerator.genKeyPair();

            // create KeyFactory and RSA Keys Specs
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPublicKeySpec publicKeySpec = keyFactory.getKeySpec(keyPair.getPublic(), RSAPublicKeySpec.class);
            RSAPrivateKeySpec privateKeySpec = keyFactory.getKeySpec(keyPair.getPrivate(), RSAPrivateKeySpec.class);

            // generate (and retrieve) RSA Keys from the KeyFactory using Keys Specs
            RSAPublicKey publicRsaKey = (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);
            RSAPrivateKey privateRsaKey  = (RSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);

            JWTClaimsSet claimsSet = buildClaimSet();

            System.out.println("--------------------------");
            System.out.println("Claim Set : \n"+claimsSet);

            // create the JWT header and specify:
            //  RSA-OAEP as the encryption algorithm
            //  128-bit AES/GCM as the encryption method
            JWEHeader header = new JWEHeader(JWEAlgorithm.RSA_OAEP_512, EncryptionMethod.A256GCM);

            // create the EncryptedJWT object
            EncryptedJWT jwt = new EncryptedJWT(header, claimsSet);

            // create an RSA encrypter with the specified public RSA key
            RSAEncrypter encrypter = new RSAEncrypter(publicRsaKey);

            // do the actual encryption
            jwt.encrypt(encrypter);

            // serialize to JWT compact form
            String jwtString = jwt.serialize();
            System.out.println("\nJwt Compact Form : "+jwtString);

            // in order to read back the data from the token using your private RSA key:
            // parse the JWT text string using EncryptedJWT object
            jwt = EncryptedJWT.parse(jwtString);

            // create a decrypter with the specified private RSA key
            RSADecrypter decrypter = new RSADecrypter(privateRsaKey);

            // do the decryption
            jwt.decrypt(decrypter);

            // print out the claims

            System.out.println("===========================================================");
            System.out.println("Issuer: [ " + jwt.getJWTClaimsSet().getIssuer() + "]");
            System.out.println("Subject: [" + jwt.getJWTClaimsSet().getSubject()+ "]");
            System.out.println("Audience size: [" + jwt.getJWTClaimsSet().getAudience().size()+ "]");
            System.out.println("Expiration Time: [" + jwt.getJWTClaimsSet().getExpirationTime()+ "]");
            System.out.println("Not Before Time: [" + jwt.getJWTClaimsSet().getNotBeforeTime()+ "]");
            System.out.println("Issue At: [" + jwt.getJWTClaimsSet().getIssueTime()+ "]");
            System.out.println("JWT ID: [" + jwt.getJWTClaimsSet().getJWTID()+ "]");
            System.out.println("===========================================================");

            out = jwt.getParsedString();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | JOSEException | ParseException e) {
            System.out.println(e.getMessage());
        }

        return out;
    }

    private JWTClaimsSet buildClaimSet() {
        JWTClaimsSet.Builder claimsSet = new JWTClaimsSet.Builder();
        claimsSet.issuer("https://my-auth-server.com");
        claimsSet.subject("John Kerr");
        claimsSet.audience(List.of("https://my-web-app.com", "https://your-web-app.com"));
        claimsSet.expirationTime(Date.from(Instant.now().plusSeconds(120)));
        claimsSet.notBeforeTime(new Date());
        claimsSet.jwtID(UUID.randomUUID().toString());

        return claimsSet.build();
    }
}
