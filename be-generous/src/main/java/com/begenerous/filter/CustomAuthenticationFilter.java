package com.begenerous.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.begenerous.repository.UserRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final UserRepo userRepo;
    private String line;
    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, UserRepo userRepo) {
        this.authenticationManager = authenticationManager;
        this.userRepo = userRepo;
    }

    /**
     * When the user tries to log in this method is called
     */
    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        line = request.getReader().readLine();
        String[] splitLine = line.replaceAll("[{}]","").split(",");
        String email = splitLine[0].split(":")[1].replace("\"","");
        String password = splitLine[1].split(":")[1].replace("\"","");

        log.info("Email: {}", email);
        log.info("Password: {}", password);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        /** The user gets authenticated by the authenticationManager with his credentials */
        return authenticationManager.authenticate(authenticationToken);
    }

    /**
     * If the auth is successful then this function is called
     * this is where the token is sent
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        com.begenerous.model.User userFromRepo = userRepo.findByEmail(user.getUsername());
        // TODO Utils maybe?
        Algorithm algorithm = Algorithm.HMAC256("ThisShouldBeEncrypted".getBytes()); // Internal algorithm and secret key, so the token can't be changed
        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1440 * 60 * 1000)) // 24 hours
                .withIssuer(request.getRequestURI())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())) // The roles that the user has
                .sign(algorithm);


        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "Login successful!");
        responseBody.put("access_token", access_token);
        responseBody.put("id", userFromRepo.getUserId().toString());

        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), responseBody);
    }

    /**
     * If the auth is unsuccessful then this function is called
     * error is included in response body
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Map<String, String> responseBody = new HashMap<>();

        String[] splitLine = line.replaceAll("[{}]","").split(",");
        String email = splitLine[0].split(":")[1].replace("\"","");
        com.begenerous.model.User user = userRepo.findByEmail(email);
        String errorMessage = "Bad credentials: " + (user == null ? "no account is registered with this email!" : "wrong password!");

        responseBody.put("error", errorMessage);
        responseBody.put("message", "Login unsuccessful!");

        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        new ObjectMapper().writeValue(response.getOutputStream(), responseBody);
    }
}
