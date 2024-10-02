package com.Twitter_Clone.twitterc.controller;

import com.Twitter_Clone.twitterc.config.JwtProvider;
import com.Twitter_Clone.twitterc.model.User;
import com.Twitter_Clone.twitterc.model.Varification;
import com.Twitter_Clone.twitterc.repository.UserRepository;
import com.Twitter_Clone.twitterc.response.AuthResponse;
import com.Twitter_Clone.twitterc.service.impl.CustomUserDetailServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomUserDetailServiceImplementation customUserDetails;


    @PostMapping("/signup")
    public ResponseEntity <AuthResponse> createUserHandler(@RequestBody User user) throws Exception {
        String email= user.getEmail();
        String password= user.getPassword();
        String fullName= user.getFullName();
        String dob= user.getDob();

        User isEmailExist=userRepository.findByEmail(email);

        if(isEmailExist!=null){
            throw new Exception("email already exist with another account");
        }
        User createUser=new User();
        createUser.setEmail(email);
        createUser.setPassword(passwordEncoder.encode(password));
        createUser.setFullName(fullName);
        createUser.setDob(dob);
        createUser.setVarification(new Varification());

        User savedUser=userRepository.save(createUser);

        Authentication authentication=new UsernamePasswordAuthenticationToken(email,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token=jwtProvider.generateToken(authentication);

        AuthResponse response=new AuthResponse(token ,true);



        return new ResponseEntity<AuthResponse>(response, HttpStatus.CREATED);

    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody User user){
             String username=user.getEmail();
             String password=user.getPassword();


             Authentication authentication=authenticate(username,password);

             String token= jwtProvider.generateToken(authentication);
             AuthResponse response=new AuthResponse(token,true);


             return new ResponseEntity<AuthResponse>(response,HttpStatus.ACCEPTED);
    }


//    implementing the authentication coming from the above the code that is signin tokenization
    private Authentication authenticate(String username, String password) {
        UserDetails userDetails=customUserDetails.loadUserByUsername(username);


        if(userDetails==null){
            throw new BadCredentialsException("invalid username");

        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw  new BadCredentialsException("invalid user name of password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }


}
