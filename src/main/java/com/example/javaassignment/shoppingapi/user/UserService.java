package com.example.javaassignment.shoppingapi.user;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.KeySpec;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(String email, String password, String phone, String firstName, String lastName) {
        User user = new User();
        user.setEmail(email);
        user.setPhone(phone);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        try {
            byte[] salt = new byte[8];
            Random random = new Random();
            random.nextBytes(salt);
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 1000, 32);
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = f.generateSecret(spec).getEncoded();
            Base64.Encoder enc = Base64.getEncoder();
            user.setSalt(enc.encodeToString(salt));
            user.setPassword(enc.encodeToString(hash));

            user = userRepository.save(user);

            return user;
        } catch (Exception e) {
            throw new CreateUserException(e.getMessage());
        }
    }

    public String generateJsonWebToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        claims.put("firstName", user.getFirstName());
        claims.put("lastName", user.getLastName());
        return Jwts.builder().setClaims(claims)
                .setSubject("" + user.getId())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .signWith(SignatureAlgorithm.HS256, "SECRET")
                .compact();
    }
}
