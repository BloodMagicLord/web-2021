package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.form.CommentCredentials;
import ru.itmo.wp.form.UserCredentials;

@Component
public class CommentCredentialsValidator implements Validator {
    public boolean supports(Class<?> clazz) {
        return CommentCredentials.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            CommentCredentials commentForm = (CommentCredentials) target;
            if (commentForm.getText().trim().isEmpty()) {
                errors.rejectValue("text", "text", "Must contain at least 1 letter");
            }
        }
    }
}
