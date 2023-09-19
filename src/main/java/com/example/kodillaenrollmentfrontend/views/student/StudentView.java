package com.example.kodillaenrollmentfrontend.views.student;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("student")
public class StudentView extends VerticalLayout { //todo add actions to buttons

    FormLayout studentForm = new FormLayout();

    public StudentView() {
        HorizontalLayout functions = new HorizontalLayout();

        add(new NativeLabel("Choose a button to edit, delete or export your student"));
        Button create = new Button("Create new");
        Button edit = new Button("Edit");
        Button delete = new Button("Delete");
        Button showCourses = new Button("Show courses");
        Button showPayments = new Button("Show payments");

        functions.add(edit, delete, create, showCourses, showPayments);

        TextField firstname = new TextField("First name");
        TextField lastname = new TextField("Last name");
        EmailField email = new EmailField("Email");
        studentForm.add(firstname, lastname/*, email*/);

        add(functions);
        add(studentForm);
    }
}
