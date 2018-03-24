package com.dinero.app.account.service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

public class JWT {




    private Key key = MacProvider.generateKey(SignatureAlgorithm.HS256);
    //long ttlMillis
    protected String createJWT (String login, String role ){



        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();

        Date now = new Date(nowMillis);

        byte[] keydata = key.getEncoded();

        Key signingKey = new SecretKeySpec(keydata, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(now)
                .setSubject(role).claim("login", login)
                .signWith(signatureAlgorithm, signingKey);


        /** czas ważności tokenu */
        /* if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        */



        return builder.compact();
    }

    public void parseJWT(String jwt) {
        Claims claims = Jwts.parser().
                setSigningKey((key.getEncoded()))
                .parseClaimsJws(jwt)
                .getBody();



    }
}
