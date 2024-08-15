package in.itkaran.bookmyshow_150824.services;

import in.itkaran.bookmyshow_150824.exceptions.UserAlreadyExistsException;
import in.itkaran.bookmyshow_150824.exceptions.UserNotFoundException;
import in.itkaran.bookmyshow_150824.models.User;
import in.itkaran.bookmyshow_150824.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User signUp(String name,
                       String email,
                       String password) throws UserAlreadyExistsException {

        Optional<User> optionalUser = userRepository.findByEmail(email);
        User savedUser = null;

        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistsException("User already exists with email: " + email);
            //Move to login workflow.
        } else {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            savedUser = userRepository.save(user);
        }
        return savedUser;
    }
}

//BCrypt Password Encoder
