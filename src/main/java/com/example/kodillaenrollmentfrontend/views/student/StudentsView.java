package com.example.kodillaenrollmentfrontend.views.student;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("students")
public class StudentsView extends VerticalLayout {

    //add grid, add .setItems from StudentDto
    public StudentsView(){
        add(new NativeLabel("Your students here!"));
        HorizontalLayout functions = new HorizontalLayout();
        Button create = new Button("Create new");
        Button export = new Button("Export to GoogleSheets");  //todo

        functions.add(create, export);
        add(functions);

        add(new NativeLabel("There will be a grid here soon"));
    }
}
