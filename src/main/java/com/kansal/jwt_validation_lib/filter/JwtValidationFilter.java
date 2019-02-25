package com.kansal.jwt_validation_lib.filter;

import com.kansal.jwt_validation_lib.connector.ValidatingServiceConnector;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtValidationFilter extends OncePerRequestFilter {

    private static final String JWT_SECRET = "jwtSecret@123";
    private static final String BEARER = "Bearer ";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String PARTNER_ID_CLAIM = "partnerId";
    private static final String CUSTOMER_ID_CLAIM = "customerId";
    private final ValidatingServiceConnector validatingServiceConnector;

    public JwtValidationFilter(ValidatingServiceConnector validatingServiceConnector) {
        this.validatingServiceConnector = validatingServiceConnector;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("filter called.");

        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);

        if (!StringUtils.isEmpty(authorizationHeader)) {
            System.out.println("authorizationHeader = " + authorizationHeader);
            if (!validateToken(authorizationHeader)) {
                response.sendError(401, "request not authorized.");
            } else {
                filterChain.doFilter(request, response);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private boolean validateToken(String authorizationHeader) {
        Claims claims = getClaims(authorizationHeader);
        System.out.println("claims = " + claims);

        String partnerId = claims.get(PARTNER_ID_CLAIM, String.class);
        String customerId = claims.get(CUSTOMER_ID_CLAIM, String.class);
        return validatingServiceConnector.isValid(partnerId, customerId);
    }

    private Claims getClaims(String authorizationHeader) {
        String token = authorizationHeader.replace(BEARER, "");
        return Jwts.parser()
                .setSigningKey(JWT_SECRET.getBytes())
                .parseClaimsJws(token).getBody();
    }
}
