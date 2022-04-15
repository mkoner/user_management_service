package MkonerLivraison.GestionUtilisateurs.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import  com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import static MkonerLivraison.GestionUtilisateurs.constant.SecurityConstante.*;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import MkonerLivraison.GestionUtilisateurs.entity.Client;

import static java.util.Arrays.*;

@Component
public class JWTTokenProvider {
	
	@Value("{jwt.secret}")
	private String secret;

	public String generateJwtToken (UserDetails userPrincipal) {
		String[] claims = getClaimsFromUser(userPrincipal);
		
		if (userPrincipal instanceof Client)
			return JWT.create().withAudience("CLIENT").withIssuer(TOKEN_PROVIDER).withArrayClaim(AUTHORITIES,
					claims).withIssuedAt(new Date()).withSubject(userPrincipal.getUsername())
					.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME*5)).sign(HMAC512(secret.getBytes()));
		
		return null;
	}
	
    public List<GrantedAuthority> getAuthorities(String token) {
        String[] claims = getClaimsFromToken(token);
        return stream(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public Authentication getAuthentication(String username, List<GrantedAuthority> authorities, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken userPasswordAuthToken = new
                UsernamePasswordAuthenticationToken(username, null, authorities);
        userPasswordAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return userPasswordAuthToken;
    }

    public boolean isTokenValid(String username, String token) {
        JWTVerifier verifier = getJWTVerifier();
        return StringUtils.isNotEmpty(username) && !isTokenExpired(verifier, token);
    }

    public String getSubject(String token) {
        JWTVerifier verifier = getJWTVerifier();
        return verifier.verify(token).getSubject();
    }

    private boolean isTokenExpired(JWTVerifier verifier, String token) {
        Date expiration = verifier.verify(token).getExpiresAt();
        return expiration.before(new Date());
    }

    private String[] getClaimsFromToken(String token) {
        JWTVerifier verifier = getJWTVerifier();
        return verifier.verify(token).getClaim(AUTHORITIES).asArray(String.class);
    }

    private JWTVerifier getJWTVerifier() {
        JWTVerifier verifier;
        try {
            Algorithm algorithm = HMAC512(secret);
            verifier = JWT.require(algorithm).withIssuer(TOKEN_PROVIDER).build();
        }catch (JWTVerificationException exception) {
            throw new JWTVerificationException(TOKEN_CANNOT_BE_VERIFIED);
        }
        return verifier;
    }



	private String[] getClaimsFromUser(UserDetails userPrincipal) {
		List<String> authorities = new ArrayList<>();
		for(GrantedAuthority grantedAuthority : userPrincipal.getAuthorities()) {
			authorities.add(grantedAuthority.getAuthority());
		}
		return authorities.toArray(new String[0]);
	}
}
