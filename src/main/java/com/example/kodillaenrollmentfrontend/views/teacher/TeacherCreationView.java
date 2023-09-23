package com.example.kodillaenrollmentfrontend.views.teacher;

import com.example.kodillaenrollmentfrontend.dao.apiclient.TeacherApiClient;
import com.example.kodillaenrollmentfrontend.dao.dto.TeacherDto;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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

    TextField firstname = new TextField("First name");
    TextField lastname = new TextField("Last name");
    TextArea description = new TextArea("Bio");

    Notification fail = new Notification("Please fill in all required fields!");
    Notification success = new Notification("Teacher created successfully!");

    public TeacherCreationView() {

        teacherForm.add(firstname, lastname, description);
        teacherForm.setColspan(description, 3);
        teacherForm.setSizeFull();
        add(teacherForm);

        fail.setPosition(Notification.Position.TOP_CENTER);
        fail.setDuration(3000);

        success.setPosition(Notification.Position.TOP_CENTER);
        success.setDuration(10000);
        success.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

        HorizontalLayout creation = submitTeacherForm();
        add(creation);
    }

    private HorizontalLayout submitTeacherForm() {
        HorizontalLayout submitForm = new HorizontalLayout();
        Button create = new Button("Create ");
        add(create);

        create.addClickListener(event -> {
            String first = firstname.getValue();
            String last = lastname.getValue();
            String desc = description.getValue();

            if (first.isEmpty() || last.isEmpty() || desc.isEmpty()) {
                fail.open();
            } else {
                createTeacher(first, last, desc);
                //   success.open();  //todo show notification
                UI.getCurrent().getPage().setLocation("/teachers");
            }
        });
        submitForm.add(create);
        return submitForm;
    }

    private void createTeacher(String firstname, String lastname, String description) {
        TeacherDto dto = new TeacherDto(null, firstname, lastname, description);
        teacherApiClient.createTeacher(dto);
    }

}
