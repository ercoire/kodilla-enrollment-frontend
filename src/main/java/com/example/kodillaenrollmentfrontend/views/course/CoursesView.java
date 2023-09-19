package com.example.kodillaenrollmentfrontend.views.course;

import com.example.kodillaenrollmentfrontend.dao.apiclient.CourseApiClient;
import com.example.kodillaenrollmentfrontend.dao.dto.CourseDto;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("courses")
public class CoursesView extends VerticalLayout {  //todo fix to be created from actual bean

    @Autowired
    private CourseApiClient courseApiClient;

    private final Grid<CourseDto> grid = new Grid<>(CourseDto.class);

    public CoursesView() {
        add(new NativeLabel("Your courses here!"));
        HorizontalLayout functions = new HorizontalLayout();
        Button create = new Button("Create new");
        Button export = new Button("Export to GoogleSheets");  //todo

        functions.add(create, export);
        add(functions);

        HorizontalLayout gridView = new HorizontalLayout();
        gridView.setSizeFull();
        gridView.add(grid);
        grid.setColumns("title", "assignedTeachers", "startingDate", "endDate", "pricePerMonth", "description", "duration", "day", "time");

        add(gridView);

        create.addClickListener(event -> {
            UI.getCurrent().getPage().setLocation("/course_create");
        });

    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        grid.setItems(courseApiClient.getAllCourses());
    }
}
