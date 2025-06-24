package com.example.DICOM.Security;

import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.Date;

public class JwtAuthFilter extends OncePerRequestFilter {

    private static final String SINGER_KEY = "cc3dbZhXgrUYMKqBhvEw13N8FUtQQEI7Rd9dw1dw2MghUzacDbCmh1oyVfKILKIJ";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                SignedJWT signedJWT = SignedJWT.parse(token);
                JWSVerifier verifier = new MACVerifier(SINGER_KEY.getBytes());

                if (signedJWT.verify(verifier)
                        && signedJWT.getJWTClaimsSet().getExpirationTime().after(new Date())) {

                    String username = signedJWT.getJWTClaimsSet().getSubject();
                    String role = (String) signedJWT.getJWTClaimsSet().getClaim("role");

                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);

                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            username, null, Collections.singleton(authority));

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (ParseException | com.nimbusds.jose.JOSEException e) {
                e.printStackTrace();
            }
        }

        filterChain.doFilter(request, response);
    }
}
