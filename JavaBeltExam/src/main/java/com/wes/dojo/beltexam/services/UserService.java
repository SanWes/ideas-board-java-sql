package com.wes.dojo.beltexam.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.wes.dojo.beltexam.models.LoginUser;
import com.wes.dojo.beltexam.models.User;
import com.wes.dojo.beltexam.repositories.UserRepository;


@Service
public class UserService {
			
			@Autowired
			private final UserRepository userRepository;
		    
		    public UserRepository getUserRepository() {
				return userRepository;
			}



			public UserService(UserRepository userRepository) {
		        this.userRepository = userRepository;
		    }
		    

		    
		    // find user by email
		    public Optional<User> findByEmail(String email) {
		        return userRepository.findByEmail(email);
		    }
		    
		    // find user by id
		    public User findUserById(Long id) {
		    	Optional<User> u = userRepository.findById(id);
		    	
		    	if(u.isPresent()) {
		            return u.get();
		    	} else {
		    	    return null;
		    	}
		    }

		    
		    public User register(User newUser, BindingResult result) {
		        if(userRepository.findByEmail(newUser.getEmail()).isPresent()) {
		            result.rejectValue("email", "Unique", "This email is already in use!");
		        }
		        if(!newUser.getPassword().equals(newUser.getPasswordConfirmation())) {
		            result.rejectValue("passwordConfirmation", "Matches", "The Confirm Password must match Password!");
		        }
		        if(result.hasErrors()) {
		            return null;
		        } else {
		            String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		            newUser.setPassword(hashed);
		            return userRepository.save(newUser);
		        }
		    }
		    
		    public User login(LoginUser newLogin, BindingResult result) {
		        if(result.hasErrors()) {
		            return null;
		        }
		        Optional<User> potentialUser = userRepository.findByEmail(newLogin.getEmail());
		        if(!potentialUser.isPresent()) {
		            result.rejectValue("email", "Unique", "Unknown email!");
		            return null;
		        }
		        User user = potentialUser.get();
		        if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
		            result.rejectValue("password", "Matches", "Invalid Password!");
		        }
		        if(result.hasErrors()) {
		            return null;
		        } else {
		            return user;
		        }
		    }
		    
} // end of service
