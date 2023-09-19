package com.example.kodillaenrollmentfrontend.views.teacher;

import com.example.kodillaenrollmentfrontend.dao.apiclient.TeacherApiClient;
import com.example.kodillaenrollmentfrontend.dao.dto.TeacherDto;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("teacher_create")
public class TeacherCreationView extends VerticalLayout {

    @Autowired
    private TeacherApiClient teacherApiClient;

    FormLayout teacherForm = new FormLayout();

    public TeacherCreationView() {


        TextField firstname = new TextField("First name");
        TextField lastname = new TextField("Last name");
        TextArea description = new TextArea("Bio");


        teacherForm.add(firstname, lastname, description);
        teacherForm.setColspan(description, 3);

        teacherForm.setSizeFull();

        add(teacherForm);
        Button create = new Button("Create "); //todo createTeacher on click
        add(create);

        create.addClickListener(event -> {
            String first = firstname.getValue();
            String last = lastname.getValue();
            String desc = description.getValue();

            if (first == null || last == null || desc == null) {
                // Check if any required fields are empty and show an error notification.
                Notification.show("Please fill in all required fields", 3000, Notification.Position.TOP_CENTER);
            } else {
                // All required fields are filled, proceed with payment creation.
                createTeacher(first, last, desc);
                // Optionally, show a success notification.
                Notification.show("Payment created successfully", 3000, Notification.Position.TOP_CENTER);
            }
            UI.getCurrent().getPage().setLocation("/teachers");
        });

    }

    private void createTeacher(String firstname, String lastname, String description) {
        TeacherDto dto = new TeacherDto(null, firstname, lastname, description);
        teacherApiClient.createTeacher(dto);
    }

}
