package com.example.kodillaenrollmentfrontend.views.teacher;

import com.example.kodillaenrollmentfrontend.dao.apiclient.TeacherApiClient;
import com.example.kodillaenrollmentfrontend.dao.dto.TeacherDto;
import com.example.kodillaenrollmentfrontend.views.MainView;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;


@Route("teachers")
public class TeachersView extends VerticalLayout {

    @Autowired
    TeacherApiClient teacherApiClient;

    private final Grid<TeacherDto> grid = new Grid<>(TeacherDto.class, false);

    public TeachersView() {
        H1 title = new H1("See all teachers here");
        title.getStyle().set("font-size", "var(--lumo-font-size-m)")
                .set("margin", "0");

        HorizontalLayout functions = new HorizontalLayout();
        Button create = new Button("Create new");
        Button backToMain = new Button("Main menu");
        backToMain.addClickListener(event -> UI.getCurrent().navigate(MainView.class));

        functions.add(create, backToMain);

        grid.addColumn(TeacherDto::getFirstname).setHeader("First name").setSortable(true);
        grid.addColumn(TeacherDto::getLastname).setHeader("Last name").setSortable(true);
        grid.addColumn(TeacherDto::getDescription).setHeader("Bio").setSortable(true);
        grid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);
        grid.setAllRowsVisible(true);
        Div gridContainer = new Div(grid);
        gridContainer.setSizeFull();


        create.addClickListener(event -> UI.getCurrent().getPage().setLocation("/teacher_create"));

        grid.addItemClickListener(event -> {
            Long clickedTeacherId = event.getItem().getId();

            UI.getCurrent().navigate(TeacherView.class, new RouteParameters(Map.of(
                    "teacherId", clickedTeacherId.toString())));
        });

        add(title);
        add(functions);
        add(gridContainer);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        grid.setItems(teacherApiClient.getTeachers());

    }


}
