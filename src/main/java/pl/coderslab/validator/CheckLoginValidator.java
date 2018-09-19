package pl.coderslab.validator;

import org.springframework.beans.factory.annotation.Autowired;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckLoginValidator implements ConstraintValidator<CheckLogin, String> {
    @Autowired
    UserRepository userRepository;
    public void initialize(CheckLogin constraint) {
    }

    public boolean isValid(String login, ConstraintValidatorContext context) {
        User user = userRepository.findByUsername(login);

        if (user != null) {
            return false;
        }
        return true;
    }

}
