package com.example.kodillaenrollmentfrontend.views.teacher;

import com.example.kodillaenrollmentfrontend.dao.apiclient.TeacherApiClient;
import com.example.kodillaenrollmentfrontend.dao.dto.CourseDto;
import com.example.kodillaenrollmentfrontend.dao.dto.TeacherDto;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnRendering;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;
import org.springframework.beans.factory.annotation.Autowired;

@Route("teacher-details/:teacherId?")
public class TeacherView extends VerticalLayout implements BeforeEnterObserver {

    FormLayout teacherForm = new FormLayout();

    private String teacherId;

    @Autowired
    private TeacherApiClient teacherApiClient;

    TextField firstname = new TextField("First name");
    TextField lastname = new TextField("Last name");
    TextArea description = new TextArea("Bio");

    Grid<CourseDto> courseGrid = new Grid<>(CourseDto.class, false);

    public TeacherView() {

        add(new NativeLabel("Choose a button to edit, delete or export your course"));

        HorizontalLayout buttons = createButtonsLayout();
        HorizontalLayout teacherViewForm = createTeacherLayout();
        HorizontalLayout courseLayout = createCourseLayout();

        add(buttons);
        add(teacherViewForm);
        add(courseLayout);
    }

    private HorizontalLayout createTeacherLayout() {
        HorizontalLayout teacherLayout = new HorizontalLayout(teacherForm);
        firstname.setReadOnly(true);
        lastname.setReadOnly(true);
        description.setReadOnly(true);
        teacherForm.add(firstname, lastname, description);
        teacherForm.setColspan(description, 3);
        teacherForm.setSizeFull();
        teacherLayout.setSizeFull();
        return teacherLayout;
    }

    private HorizontalLayout createButtonsLayout() {
        HorizontalLayout functions = new HorizontalLayout();

        Button create = new Button("Create new");
        create.addClickListener(event -> {
            UI.getCurrent().getPage().setLocation("/teacher_create");
        });

        Button edit = new Button("Edit");
        edit.addClickListener(event -> {
            UI.getCurrent().navigate(TeacherEditView.class, new RouteParameters("teacherId", teacherId));
        });

        Button delete = new Button("Delete");
        delete.addClickListener(event -> {
            deleteTeacherById(Long.valueOf(teacherId));
            add(Notification.show("Teacher deleted successfully!"));
            UI.getCurrent().getPage().setLocation("/teachers");
        });

        Button showCourses = new Button("Show Courses");
        showCourses.addClickListener(event -> {
        courseGrid.setVisible(!courseGrid.isVisible());
        });

        Button export = new Button("Open in Google Sheets");  //todo add export
        create.addClickListener(event -> {
            UI.getCurrent().getPage().setLocation("/teacher_create");
        });


        functions.add(edit, delete, create, showCourses, export);
        return functions;
    }

    private HorizontalLayout createCourseLayout() {
        HorizontalLayout courses = new HorizontalLayout();
        courses.setSizeFull();
        courseGrid.addColumn(CourseDto::getTitle).setHeader("Course title").setSortable(true);
        courseGrid.addColumn(CourseDto::getStartingDate).setHeader("Starting date").setSortable(true);
        courseGrid.addColumn(CourseDto::getEndDate).setHeader("End date").setSortable(true);
        courseGrid.addColumn(CourseDto::getPricePerMonth).setHeader("Price").setSortable(true);
        courseGrid.addColumn(CourseDto::getDuration).setHeader("Duration").setSortable(true);
        courseGrid.addColumn(CourseDto::getDay).setHeader("Day").setSortable(true);
        courseGrid.addColumn(CourseDto::getTime).setHeader("Time").setSortable(true);
        courseGrid.addColumn(CourseDto::getTeacherNames).setHeader("Teachers").setSortable(true);
        courseGrid.setSizeFull();
        courseGrid.setColumnRendering(ColumnRendering.LAZY);
        courseGrid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);
        courseGrid.setAllRowsVisible(true);
        courseGrid.setVisible(false);
        courses.add(courseGrid);
        return courses;
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        teacherApiClient.getTeacher(Long.valueOf(teacherId));
        TeacherDto teacherDto = teacherApiClient.getTeacher(Long.valueOf(teacherId));

        firstname.setValue(teacherDto.getFirstname());
        lastname.setValue(teacherDto.getLastname());
        description.setValue(teacherDto.getDescription());

        courseGrid.setItems(teacherApiClient.getCoursesByTeacherId(Long.valueOf(teacherId)));
    }

    private void deleteTeacherById(Long id) {
        teacherApiClient.deleteTeacher(id);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        this.teacherId = event.getRouteParameters().get("teacherId").orElseThrow();
    }
}

