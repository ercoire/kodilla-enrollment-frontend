package com.example.kodillaenrollmentfrontend.views.teacher;

import com.example.kodillaenrollmentfrontend.dao.dto.TeacherDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route("teachers")
public class TeachersView extends VerticalLayout {
    private final Grid<TeacherDto> grid = new Grid<>(TeacherDto.class);
//    Grid<Person> grid = new Grid<>(Person.class, false);
//        grid.addColumn(Person::getFirstName).setHeader("First name");
//        grid.addColumn(Person::getLastName).setHeader("Last name");
//        grid.addColumn(Person::getEmail).setHeader("Email");
//        grid.addColumn(Person::getProfession).setHeader("Profession");

    public TeachersView() {
        add(new NativeLabel("See all teachers here. Happy clicking!"));
        HorizontalLayout functions = new HorizontalLayout();
        Button create = new Button("Create new");
        Button export = new Button("Export to GoogleSheets");  //todo

        functions.add(create, export);
        add(functions);

//        HorizontalLayout layout = new HorizontalLayout();
//        layout.setSizeFull();
//        layout.add(grid);
//        grid.setColumns("firstname", "lastname", "assignedCourses", "description");
//        grid.setSizeFull();

        add(new NativeLabel("There will be a grid here soon"));
     //   add(layout);
    }


}
