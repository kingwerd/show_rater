package com.edward.beltexam.validations;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.edward.beltexam.models.User;
import com.edward.beltexam.services.UserService;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    public static Pattern EMAIL_REGEX = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]+");
    private UserService userService;

    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    public HashMap<String, Object> validate(Map<String, String> body) {
        HashMap<String, Object> response = new HashMap<String, Object>();
        if (body.get("name").length() < 1) {
            response.put("name", "Name is required");
        }
        if (body.get("email").length() < 1) {
            response.put("email", "Email is required");
        } else if (!EMAIL_REGEX.matcher(body.get("email")).matches()) {
            response.put("email", "Invalid email");
        } else {
            User user = this.userService.findByEmail(body.get("email"));
            if (user != null) {
                response.put("email", "Email is already in use");
            }
        }
        if (body.get("password").length() < 8) {
            response.put("password", "Password must be more than 8 characters");
        }
        if (!body.get("password").equals(body.get("confirm"))) {
            response.put("confirm", "Passwords do not match");
        }
        if (response.isEmpty()) {
            response.put("valid", true);
        } else {
            response.put("valid", false);
        }
        return response;
    }

    public HashMap<String, Object> authenticate(Map<String, String> body) {
        HashMap<String, Object> response = new HashMap<String, Object>();
        User user = null;
        if (body.get("loginEmail").length() < 1) {
            response.put("loginEmail", "Email is required");
        } else if (!EMAIL_REGEX.matcher(body.get("loginEmail")).matches()) {
            response.put("loginEmail", "Invalid email");
        } else {
            user = userService.findByEmail(body.get("loginEmail"));
            if (user == null) {
                response.put("loginEmail", "Unknown email");
            }
        }
        if (body.get("loginPassword").length() < 8) {
			response.put("loginPassword", "Password must be 8 characters or longer");
		} else if (user != null && !BCrypt.checkpw(body.get("loginPassword"), user.getPassword())) {
			response.put("loginPassword", "Incorrect password");
        }
        if (response.isEmpty()) {
			response.put("valid", true);
			response.put("user", user);
		} else {
			response.put("valid", false);
		}
		
		return response;
    }
}