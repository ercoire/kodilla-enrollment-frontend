package com.example.kodillaenrollmentfrontend.views.teacher;

import com.example.kodillaenrollmentfrontend.dao.apiclient.TeacherApiClient;
import com.example.kodillaenrollmentfrontend.dao.dto.TeacherDto;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Route("teachers")
public class TeachersView extends VerticalLayout {

    @Autowired
    TeacherApiClient teacherApiClient;

    private final Grid<TeacherDto> grid = new Grid<>(TeacherDto.class, false);

    public TeachersView() {
        add(new NativeLabel("See all teachers here. Happy clicking!"));

        HorizontalLayout functions = new HorizontalLayout();
        Button create = new Button("Create new");
        Button export = new Button("Export to GoogleSheets");  //todo

        functions.add(create, export);
        add(functions);

        grid.addColumn(TeacherDto::getFirstname).setHeader("First name").setSortable(true);
        grid.addColumn(TeacherDto::getLastname).setHeader("Last name").setSortable(true);
        grid.addColumn(TeacherDto::getDescription).setHeader("Bio").setSortable(true);


        Div gridContainer = new Div(grid);
        gridContainer.setSizeFull();
        add(gridContainer);

        create.addClickListener(event -> {
            UI.getCurrent().getPage().setLocation("/teacher_create");
        });


    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        grid.setItems(teacherApiClient.getTeachers());

    }
}
