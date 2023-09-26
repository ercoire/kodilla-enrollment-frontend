package com.example.kodillaenrollmentfrontend.views.student;

import com.example.kodillaenrollmentfrontend.dao.apiclient.StudentApiClient;
import com.example.kodillaenrollmentfrontend.dao.dto.StudentDto;
import com.example.kodillaenrollmentfrontend.views.MainView;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Route("students")
public class StudentsView extends VerticalLayout {

    @Autowired
    private StudentApiClient studentApiClient;

    private final Grid<StudentDto> grid = new Grid<>(StudentDto.class);


    public StudentsView() {

        H1 title = new H1("See all teachers here");
        title.getStyle().set("font-size", "var(--lumo-font-size-m)")
                .set("margin", "0");

        HorizontalLayout buttons = createButtonsLayout();
        HorizontalLayout students = createStudentsGridLayout();

        add(title);
        add(buttons);
        add(students);
    }

    private HorizontalLayout createStudentsGridLayout() {
        HorizontalLayout gridView = new HorizontalLayout();
        gridView.setSizeFull();
        gridView.add(grid);
        grid.setColumns("firstname", "lastname", "email");
        grid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);
        grid.setAllRowsVisible(true);

        grid.addItemClickListener(event -> {
            Long clickedStudentId = event.getItem().getId();
            UI.getCurrent().navigate(StudentView.class, new RouteParameters(Map.of(
                    "studentId", clickedStudentId.toString()
            )));
        });

        return gridView;
    }

    private HorizontalLayout createButtonsLayout() {
        HorizontalLayout functions = new HorizontalLayout();
        Button create = new Button("Create new");
        create.addClickListener(event -> UI.getCurrent().navigate(StudentCreationView.class));

        Button backToMain = new Button("Main menu");
        backToMain.addClickListener(event -> UI.getCurrent().navigate(MainView.class));

        functions.add(create, backToMain);
        add(functions);
        return functions;
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        grid.setItems(studentApiClient.getStudents());
    }
}
