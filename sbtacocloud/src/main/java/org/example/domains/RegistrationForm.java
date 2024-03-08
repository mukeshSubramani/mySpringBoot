package org.example.domains;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {

    private final String username;
    private final String password;
    private final String fullName;
    private final String address;
    private final String zip;
    private final String phoneNumber;

    public User toUser(PasswordEncoder passwordEncoder){
        return new User(username,passwordEncoder.encode(password),
                fullName,address,zip,phoneNumber);
    }
}
