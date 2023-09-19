package com.example.kodillaenrollmentfrontend.views.student;

import com.example.kodillaenrollmentfrontend.dao.apiclient.StudentApiClient;
import com.example.kodillaenrollmentfrontend.dao.dto.StudentDto;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("students")
public class StudentsView extends VerticalLayout {

    @Autowired
    private StudentApiClient studentApiClient;

    private final Grid<StudentDto> grid = new Grid<>(StudentDto.class);


    public StudentsView() {
        add(new NativeLabel("Your students here!"));
        HorizontalLayout functions = new HorizontalLayout();
        Button create = new Button("Create new");
        create.addClickListener(event -> UI.getCurrent().getPage().setLocation("/student_create"));
        Button export = new Button("Export to GoogleSheets");  //todo

        functions.add(create, export);
        add(functions);

        HorizontalLayout gridView = new HorizontalLayout();
        gridView.setSizeFull();
        gridView.add(grid);
        grid.setColumns("firstname", "lastname" /*, "email"*/);

        add(gridView);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        grid.setItems(studentApiClient.getStudents());
    }
}
