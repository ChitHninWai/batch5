package com.example.backend.service;

import com.example.backend.data.UserDto;
import com.example.backend.dto.CredentialsDto;
import com.example.backend.dto.SignupDto;
import com.example.backend.entities.User;
import com.example.backend.exception.AppException;
import com.example.backend.mappers.UserMapper;
import com.example.backend.reposity.UserReposity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserReposity userReposity;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserDto register(SignupDto signupDto){
        Optional<User> oUser = userReposity.findByLogin(signupDto.login());
        if(oUser.isPresent()){
            throw new AppException("Login user already exists.",
                    HttpStatus.BAD_REQUEST);
        }
        User user = toEntity(signupDto);
        user.setPassword(passwordEncoder.encode(
                CharBuffer.wrap(signupDto.password())
                )
        );

        User saveUser = userReposity.save(user);

       return toDto(saveUser);
    }

    public static UserDto toDto(User user){
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getLogin()
        );
    }

    private User toEntity(SignupDto signupDto){
        User user = new User();
        user.setFirstName(signupDto.firstName());
        user.setLastName(signupDto.lastName());
        user.setLogin(signupDto.login());
        return user;
    }

    public UserDto login(CredentialsDto credentialsDto){
     var user = userReposity.findByLogin(credentialsDto.login())
                .orElseThrow(() ->new AppException("Unknown User", HttpStatus.NOT_FOUND));

     if(passwordEncoder.matches(CharBuffer.wrap(credentialsDto.loginPassword()),
             user.getPassword()
             )){
         return toDto(user);
     }
     throw new AppException("Invalid Password",HttpStatus.BAD_REQUEST);
    }
}
