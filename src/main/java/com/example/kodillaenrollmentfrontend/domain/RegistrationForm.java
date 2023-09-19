
package com.example.kodillaenrollmentfrontend.domain;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.i18n.I18NProvider;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class RegistrationForm extends FormLayout {
/*

    private final TextField username = new TextField("Username");
    private final TextField email = new TextField("Email");
    private final PasswordField password = new PasswordField("Password");
    private final PasswordField confirmPassword = new PasswordField("Confirm Password");

    private final BeanValidationBinder<UserStub> binder = new BeanValidationBinder<>(UserStub.class);

    @Autowired
    public RegistrationForm(I18NProvider i18NProvider) {
         // Configure the form fields
        username.setRequired(true);
        email.setRequired(true);
        password.setRequired(true);
        confirmPassword.setRequired(true);

        // Add the fields to the form
        add(username, email, password, confirmPassword);

        // Bind the form fields to a data model class (User in this case)
        binder.bindInstanceFields(this);
        binder.forField(confirmPassword)
                .withValidator(conf -> conf.equals(password.getValue()), "Passwords must match")
                .bind("password");

        // Set an error message for the email field
        email.setErrorMessage("Invalid email address");

        // Set the label for the confirm password field
        confirmPassword.setLabel("Confirm Password");

        // Set the form's localized title
        //setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));
        setMaxWidth("400px");
        setMinWidth("300px");
        setWidthFull();
        add(new NativeLabel("Registration Form"));
    }

    // Method to handle form submission and validation
//    public void submit() {
//        try {
//            User user = new User();
//            binder.writeBean(user);
//            // Perform registration logic here
//            // e.g., save the user to a database
//        } catch (ValidationException e) {
//            // Handle validation errors
//            e.printStackTrace();
//        }
//    }
*/
}