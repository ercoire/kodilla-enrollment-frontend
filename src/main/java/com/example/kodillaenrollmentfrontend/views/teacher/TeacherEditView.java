package com.example.kodillaenrollmentfrontend.views.teacher;

import com.example.kodillaenrollmentfrontend.dao.apiclient.TeacherApiClient;
import com.example.kodillaenrollmentfrontend.dao.dto.CourseDto;
import com.example.kodillaenrollmentfrontend.dao.dto.TeacherDto;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Route("teacher_edit")
public class TeacherEditView extends VerticalLayout {

    FormLayout teacherForm = new FormLayout();

    @Autowired
    private TeacherApiClient teacherApiClient;


    public TeacherEditView() {

        //todo preset with data
        TextField firstname = new TextField("First name");
        TextField lastname = new TextField("Last name");
        TextArea description = new TextArea("Bio");

        teacherForm.add(firstname, lastname, description);
        teacherForm.setColspan(description, 3);

        teacherForm.setSizeFull();

        add(teacherForm);
        Button submit = new Button("Submit changes");

        submit.addClickListener(event -> {
            String first = firstname.getValue();
            String last = lastname.getValue();
            String bio = description.getValue();
            updateTeacherFromForm(first, last, bio);

            Notification n = new Notification("Teacher updated successfully");
            n.setPosition(Notification.Position.TOP_CENTER);
            n.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            n.setDuration(5000);
            n.open();

            UI.getCurrent().getPage().setLocation("/teachers");
        });
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        Long id = null;
        teacherApiClient.getTeacher(id);  //todo pass Id value
    }

    private void updateTeacherFromForm(String first, String last, String bio) {
        TeacherDto dto = new TeacherDto(null, first, last, bio);
        //todo pass dto value
        teacherApiClient.updateTeacher(dto);
    }
}


