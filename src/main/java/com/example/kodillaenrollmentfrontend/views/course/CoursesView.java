package com.example.kodillaenrollmentfrontend.views.course;

import com.example.kodillaenrollmentfrontend.dao.apiclient.CourseApiClient;
import com.example.kodillaenrollmentfrontend.dao.dto.CourseDto;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H1;
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
        H1 title = new H1("See all courses here");
        title.getStyle().set("font-size", "var(--lumo-font-size-m)")
                .set("margin", "0");

        HorizontalLayout functions = createButtonsLayout();
        HorizontalLayout gridView = createGridLayout();

        add(title);
        add(functions);
        add(gridView);
    }

    private HorizontalLayout createButtonsLayout() {
        HorizontalLayout buttons = new HorizontalLayout();
        Button create = new Button("Create new");
        create.addClickListener(event -> {
            UI.getCurrent().getPage().setLocation("/course_create");

        });
        Button export = new Button("Export to GoogleSheets");  //todo

        buttons.add(create, export);
        return buttons;
    }

    private HorizontalLayout createGridLayout() {
        HorizontalLayout gridView = new HorizontalLayout();
        gridView.setSizeFull();
        grid.setColumns("title", "teacherNames", "startingDate", "endDate", "pricePerMonth",
                "description", "duration", "day", "time");

        grid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);
        grid.setAllRowsVisible(true);

        grid.addItemClickListener(event -> {
            Long clickedCourseId = event.getItem().getId();
            UI.getCurrent().navigate(CourseView.class, new RouteParameters(Map.of(
                    "courseId", clickedCourseId.toString()
            )));
        });
        gridView.add(grid);
        return gridView;
    }


    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        grid.setItems(courseApiClient.getCourses());
    }
}
