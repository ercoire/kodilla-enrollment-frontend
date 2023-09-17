package com.example.kodillaenrollmentfrontend.views.student;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("student_create")
public class StudentCreationView extends VerticalLayout {

    FormLayout teacherForm = new FormLayout();

    public StudentCreationView() {


        TextField firstname = new TextField("First name");
        TextField lastname = new TextField("Last name");
        //todo add payment
        //todo add course

        teacherForm.add(firstname, lastname);
        teacherForm.setSizeFull();

        add(teacherForm);
        add(new Button("Create ")); //todo createStudent on click
//        save.addClickListener(event -> save());
//        delete.addClickListener(event -> delete());
    }
}
