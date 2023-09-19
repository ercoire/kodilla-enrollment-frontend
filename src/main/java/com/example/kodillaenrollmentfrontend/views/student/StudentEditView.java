package com.example.kodillaenrollmentfrontend.views.student;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("student_edit")
public class StudentEditView extends VerticalLayout {

    FormLayout studentForm = new FormLayout();

    //    @Autowired
//    private TeacherApiClient teacherApiClient;


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
        add(new Button("Submit changes"));
    }
}

