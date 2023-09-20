package com.example.kodillaenrollmentfrontend.views.student;

import com.example.kodillaenrollmentfrontend.dao.apiclient.StudentApiClient;
import com.example.kodillaenrollmentfrontend.dao.dto.StudentDto;
import com.example.kodillaenrollmentfrontend.dao.dto.TeacherDto;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("student_edit/:studentId?")
public class StudentEditView extends VerticalLayout {

    FormLayout studentForm = new FormLayout();

        @Autowired
    private StudentApiClient studentApiClient;


    public StudentEditView() {

        //todo preset with data
        TextField firstname = new TextField("First name");
        TextField lastname = new TextField("Last name");
        EmailField email = new EmailField("Email");
        ComboBox<String> courseList = new ComboBox<>("Courses"); //todo String to Course?
        ComboBox<String> payments = new ComboBox<>("Payments"); //todo String to Payment?
        //todo add payment
        //todo add course

        studentForm.add(firstname, lastname,/*email,*/ courseList, payments);

        studentForm.setSizeFull();

        add(studentForm);
        Button submit = new Button("Submit changes");
        submit.addClickListener(event -> {
            String first = firstname.getValue();
            String last = lastname.getValue();
          //  String mail = description.getValue();
            updateStudentFromForm(first, last);

            Notification n = new Notification("Student updated successfully");
            n.setPosition(Notification.Position.TOP_CENTER);
            n.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            n.setDuration(5000);
            n.open();

            UI.getCurrent().getPage().setLocation("/Students");
        });
    }

    private void updateStudentFromForm(String first, String last) {
        StudentDto dto = new StudentDto(null, first, last); //todo
        studentApiClient.updateStudent(dto);
    }
}

