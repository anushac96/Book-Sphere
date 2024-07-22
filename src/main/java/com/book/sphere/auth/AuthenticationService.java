package com.book.sphere.auth;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.book.sphere.email.EmailService;
import com.book.sphere.email.EmailTemplateName;
import com.book.sphere.role.RoleRepository;
import com.book.sphere.user.Token;
import com.book.sphere.user.TokenRepository;
import com.book.sphere.user.User;
import com.book.sphere.user.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final EmailService emailService;
	private final TokenRepository tokenRepository;
	
	@Value("${mailing.frontend.activation-url}")
    private String activationUrl;
	
	public void register(@Valid RegistrationRequest request) throws MessagingException {
	
		var userRole = roleRepository.findByName("USER")
                // todo - better exception handling
                .orElseThrow(() -> new IllegalStateException("ROLE USER was not initiated"));
        
		var user = new User.Builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();
		
        userRepository.save(user);
        sendValidationEmail(user); 
	}

	private void sendValidationEmail(User user) throws MessagingException {
        var newToken = generateAndSaveActivationToken(user);

        emailService.sendEmail(
                user.getEmail(),
                user.fullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                newToken,
                "Account activation"
                );
    }
	
	private String generateAndSaveActivationToken(User user) {
        // Generate a token
        String generatedToken = generateActivationCode(6);
        var token = new Token.Builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);

        return generatedToken;
    }
	
	private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();

        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }

        return codeBuilder.toString();
    }

}
