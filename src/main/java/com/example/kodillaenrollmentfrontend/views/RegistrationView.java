package com.example.kodillaenrollmentfrontend.views;

import com.example.kodillaenrollmentfrontend.domain.RegistrationForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("register") //todo as Student
public class RegistrationView extends VerticalLayout {


    private RegistrationForm registrationForm;

    @Autowired
    public RegistrationView(RegistrationForm registrationForm) {
        this.registrationForm = registrationForm;
        // Create a button to submit the form
        Button submitButton = new Button("Register", event -> registerUser());

        // Add the form and button to the view
        add(registrationForm, submitButton);
    }

    private void registerUser() {
        // Call the submit method of the RegistrationForm
        // registrationForm.submit();
        Notification.show("Registration successful");
    }
}