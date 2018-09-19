package pl.coderslab.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckEmailValidator implements ConstraintValidator<CheckEmail, String> {
    @Autowired
    UserRepository userRepository;

    public void initialize(CheckEmail constraint) {
    }

    public boolean isValid(String email, ConstraintValidatorContext context) {
            User user = userRepository.findFirstByEmail(email);
        if (user != null) {
            return false;
        }
        return true;
    }

}
