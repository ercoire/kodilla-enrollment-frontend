package com.example.kodillaenrollmentfrontend.views.course;

import com.example.kodillaenrollmentfrontend.dao.apiclient.CourseApiClient;
import com.example.kodillaenrollmentfrontend.dao.dto.CourseDto;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Route("courses")
public class CoursesView extends VerticalLayout {

    @Autowired
    private CourseApiClient courseApiClient;

    private final Grid<CourseDto> grid = new Grid<>(CourseDto.class);

    public CoursesView() {
        add(new NativeLabel("Your courses here!"));  //todo native label justified

        HorizontalLayout functions = new HorizontalLayout();
        Button create = new Button("Create new");
        Button export = new Button("Export to GoogleSheets");  //todo

        functions.add(create, export);
        add(functions);

        HorizontalLayout gridView = new HorizontalLayout();
        gridView.setSizeFull();
        gridView.add(grid);
        grid.setColumns("title", "assignedTeachers", "startingDate", "endDate", "pricePerMonth",
                "description", "duration", "day", "time");
        grid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);
        grid.setAllRowsVisible(true);
        add(gridView);

        create.addClickListener(event -> {
            UI.getCurrent().getPage().setLocation("/course_create");
            ;
        });

        grid.addItemClickListener(event -> {
            Long clickedCourseId = event.getItem().getId();
            UI.getCurrent().navigate(CourseView.class, new RouteParameters(Map.of(
                    "courseId", clickedCourseId.toString()
            )));
        });

    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        grid.setItems(courseApiClient.getCourses());
    }
}
