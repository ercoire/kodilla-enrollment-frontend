package com.example.kodillaenrollmentfrontend.views.course;

import com.example.kodillaenrollmentfrontend.dao.apiclient.CourseApiClient;
import com.example.kodillaenrollmentfrontend.dao.apiclient.StudentApiClient;
import com.example.kodillaenrollmentfrontend.dao.dto.CourseDto;
import com.example.kodillaenrollmentfrontend.dao.dto.StudentDto;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnRendering;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Route("add-student/:courseId")
public class StudentToCourseView extends VerticalLayout implements BeforeEnterObserver {

    @Autowired
    private CourseApiClient courseApiClient;

    @Autowired
    private StudentApiClient studentApiClient;

    FormLayout addStudentForm = new FormLayout();
    TextField courseTitle = new TextField("Course title");
    ComboBox<StudentDto> students = new ComboBox<>("Students");

    private String courseId;
    private CourseDto selected;
    private final Grid<StudentDto> studentGrid = new Grid<>(StudentDto.class, false);

    public StudentToCourseView() {

        HorizontalLayout buttons = createButtonsLayout();
        HorizontalLayout main = createMainLayout();
        HorizontalLayout studentLayout = createStudentLayout();

        studentGrid.setAllRowsVisible(true);
        studentGrid.setVisible(true);
        add(main);
        add(buttons);
        add(studentLayout);
        add(studentGrid);
    }

    private HorizontalLayout createMainLayout() {
        HorizontalLayout mainContent = new HorizontalLayout(addStudentForm);
        courseTitle.setReadOnly(true);

        mainContent.add(courseTitle, students);
        return mainContent;
    }

    private HorizontalLayout createButtonsLayout() {
        HorizontalLayout functions = new HorizontalLayout();

        Button repeat = new Button("Add one more");
        repeat.addClickListener(event -> {
            addStudentToCourse();
            Notification.show("Student added to the list", 3000, Notification.Position.TOP_CENTER);
            students.clear();
            refreshTable();
            studentGrid.setAllRowsVisible(true);
            studentGrid.setVisible(true);
        });

        Button submit = new Button("Add and back to course");
        submit.addClickListener(event -> {
            addStudentToCourse();
            Notification.show("Student added to the list", 3000, Notification.Position.TOP_CENTER);
            UI.getCurrent().navigate(CourseView.class, new RouteParameters("courseId", courseId));
             studentGrid.isVisible();
        });

        Button toCourse = new Button("Back to course");
        toCourse.addClickListener(event -> {
            UI.getCurrent().navigate(CourseView.class, new RouteParameters("courseId", courseId));
        });

        functions.add(repeat);
        functions.add(submit);
        functions.add(toCourse);
        return functions;
    }

    private void addStudentToCourse(){
        students.getValue();
        courseApiClient.addStudentToCourse(Long.valueOf(courseId), students.getValue().getId());
    }

    private void refreshTable() {
        List<StudentDto> updatedStudents = courseApiClient.getStudentsByCourse(Long.valueOf(courseId));
        studentGrid.setItems(updatedStudents);
    }


    private HorizontalLayout createStudentLayout() {
        HorizontalLayout gridView = new HorizontalLayout();
        gridView.setSizeFull();

        studentGrid.addColumn(StudentDto::getFirstname).setHeader("First name").setSortable(true);
        studentGrid.addColumn(StudentDto::getLastname).setHeader("Last name").setSortable(true);
        studentGrid.setSizeFull();
        studentGrid.setColumnRendering(ColumnRendering.LAZY);
        studentGrid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);
        studentGrid.setAllRowsVisible(true);
        studentGrid.setVisible(false);
        gridView.add(studentGrid);
        return gridView;
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        Long id = Long.parseLong(courseId);
        selected = courseApiClient.getCourse(id);
        courseTitle.setValue(selected.getTitle());
        studentGrid.setItems(courseApiClient.getStudentsByCourse(id));
        students.setItems(studentApiClient.getStudents());
        students.setItemLabelGenerator(StudentDto::getStudentName);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        this.courseId = event.getRouteParameters().get("courseId")
                .orElseThrow();
    }
}