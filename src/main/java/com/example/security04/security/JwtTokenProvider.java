package com.example.security04.security;

import com.example.security04.security.exeption.CustomException;
import com.example.security04.entity.JwtClaims;
import com.example.security04.util.Constante;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Autowired
    private UserProvider userProvider;

    private long validityInMilliseconds;
    private String secretKey;

    public JwtTokenProvider() {
        validityInMilliseconds = Constante.JWT_TIEMPO_EXPIRACION_MS;
        secretKey = Constante.JWT_SECRET_KEY;
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {

        /*List<JPAToken> list = InitCommandLineRunner.listTokens;

        JPAToken appToken = list.stream()
                .filter(x -> token.equals(x.getValor()))
                .findFirst()
                .orElse(null);

        if (appToken == null) {
            throw new CustomException("Invalid token", HttpStatus.UNAUTHORIZED);
        }

        return true;*/

        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new CustomException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        /*} catch (SignatureException ex) {
            System.out.println("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty");
        }*/
    }

    public Authentication getAuthentication(String token) {

        UserDetails userDetails = userProvider.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        //return Base64.base64ToString(token);
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String createToken(JwtClaims jwtClaims) {
        Claims claims = Jwts.claims().setSubject(jwtClaims.getUsername());
        claims.put("id", jwtClaims.getId());

        //Claims claims = Jwts.claims().setSubject(username);
        //List<AppUserRole> appUserRoles
        //claims.put("auth", appUserRoles.stream().map(s -> new SimpleGrantedAuthority(s.getAuthority())).filter(Objects::nonNull).collect(Collectors.toList()));

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
