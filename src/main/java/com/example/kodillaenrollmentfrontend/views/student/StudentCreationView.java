package com.example.kodillaenrollmentfrontend.views.student;

import com.example.kodillaenrollmentfrontend.dao.apiclient.StudentApiClient;
import com.example.kodillaenrollmentfrontend.dao.dto.StudentDto;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
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

    public StudentCreationView() {

        TextField firstname = new TextField("First name");
        firstname.setRequired(true);
        TextField lastname = new TextField("Last name");
        lastname.setRequired(true);
        EmailField email = new EmailField("Email");


        studentForm.add(firstname, lastname /*, email*/);
        studentForm.setSizeFull();

        add(studentForm);
        Button create = new Button("Create ");
        add(create);

        create.addClickListener(event -> {
            String first = firstname.getValue();
            String last = lastname.getValue();
         //   String mail = email.getValue();


            if(first == null || last == null || first.equals("") || last.equals("")){
                Notification fail = new Notification("Please fill in all required fields");
                fail.setDuration(3000);
                fail.setPosition(Notification.Position.TOP_CENTER);
                fail.addThemeVariants(NotificationVariant.LUMO_WARNING);
                fail.open();
            } else {
                createStudent(first, last/*, mail*/);
                UI.getCurrent().getPage().setLocation("/students");
            }
        });
    }

    private void createStudent(String firstname, String lastname /*, String mail*/) {
        StudentDto dto = new StudentDto(null, firstname, lastname/*, mail*/);
        studentApiClient.createStudent(dto);
    }
}
