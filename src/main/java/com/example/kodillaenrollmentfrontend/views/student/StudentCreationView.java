package com.example.kodillaenrollmentfrontend.views.student;

import com.example.kodillaenrollmentfrontend.dao.apiclient.StudentApiClient;
import com.example.kodillaenrollmentfrontend.dao.dto.StudentDto;
import com.example.kodillaenrollmentfrontend.views.MainView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("student_create")
public class StudentCreationView extends VerticalLayout {

    FormLayout studentForm = new FormLayout();

    @Autowired
    StudentApiClient studentApiClient;

    TextField firstname = new TextField("First name");
    TextField lastname = new TextField("Last name");
    EmailField email = new EmailField("Email");

    public StudentCreationView() {


        firstname.setRequired(true);
        lastname.setRequired(true);
        email.setRequired(true);

        studentForm.add(firstname, lastname , email);
        studentForm.setSizeFull();

        HorizontalLayout buttons = createButtonsLayout();

        add(studentForm);
        add(buttons);

    }

    private HorizontalLayout createButtonsLayout() {
        HorizontalLayout functions = new HorizontalLayout();
        Button create = new Button("Create ");

        create.addClickListener(event -> {
            String first = firstname.getValue();
            String last = lastname.getValue();
            String mail = email.getValue();

            if (first == null || last == null || first.equals("") || last.equals("") || mail.equals("")) {
                Notification fail = new Notification();
                fail.setText("Please fill in all required fields");
                fail.setDuration(3000);
                fail.setPosition(Notification.Position.TOP_CENTER);
                fail.addThemeVariants(NotificationVariant.LUMO_WARNING);
                fail.open();
            } else {
                createStudent(first, last, mail);
                UI.getCurrent().getPage().setLocation("/students");
            }
        });

        Button backToMain = new Button("Main menu");
        backToMain.addClickListener(event -> UI.getCurrent().navigate(MainView.class));

        functions.add(create, backToMain);
        return functions;
    }

    private void createStudent(String firstname, String lastname, String mail) {
        StudentDto dto = new StudentDto(null, firstname, lastname, mail);
        studentApiClient.createStudent(dto);
    }
}
