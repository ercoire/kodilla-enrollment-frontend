package com.example.kodillaenrollmentfrontend.views.teacher;

import com.example.kodillaenrollmentfrontend.dao.apiclient.TeacherApiClient;
import com.example.kodillaenrollmentfrontend.dao.dto.CourseDto;
import com.example.kodillaenrollmentfrontend.dao.dto.StudentDto;
import com.example.kodillaenrollmentfrontend.dao.dto.TeacherDto;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
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

import java.util.List;

@Route("teacher-details/:teacherId?")
public class TeacherView extends VerticalLayout implements BeforeEnterObserver {

    FormLayout teacherForm = new FormLayout();

    private String teacherId;

    @Autowired
    private TeacherApiClient teacherApiClient;

    TextField firstname = new TextField("First name");
    TextField lastname = new TextField("Last name");
    TextArea description = new TextArea("Bio");

    public TeacherView() {
        HorizontalLayout functions = new HorizontalLayout();

        add(new NativeLabel("Choose a button to edit, delete or export your course"));
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


        Button showCourses = new Button("Show showCourses"); //todo connection to endpoint?
        Button export = new Button("Open in Google Sheets");  //todo add export

        functions.add(edit, delete, create, showCourses, export);
        add(functions);

        teacherForm.add(firstname, lastname, description);
        teacherForm.setColspan(description, 3);

        teacherForm.setSizeFull();
        add(teacherForm);

        HorizontalLayout courses = new HorizontalLayout();
        Grid<CourseDto> courseGrid = new Grid<>(CourseDto.class);
        courseGrid.setVisible(false);
        courseGrid.setSizeFull();
        courseGrid.setColumns("title", "assignedTeachers", "startingDate", "endDate", "pricePerMonth", "description", "duration", "day", "time");
        courses.add(courseGrid);
        add(courses);

        create.addClickListener(event -> {
            UI.getCurrent().getPage().setLocation("/teacher_create");
        });

        showCourses.addClickListener(event -> {
            courseGrid.setVisible(true);
            Long id = Long.valueOf(teacherId);
            List<CourseDto> courseList = teacherApiClient.getCoursesByTeacherId(id);
            courseGrid.setItems(courseList);

        });
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        teacherApiClient.getTeacher(Long.valueOf(teacherId));
        TeacherDto teacherDto = teacherApiClient.getTeacher(Long.valueOf(teacherId));
        firstname.setValue(teacherDto.getFirstname());
        lastname.setValue(teacherDto.getLastname());
        description.setValue(teacherDto.getDescription());
    }

    private void deleteTeacherById(Long id) {
        teacherApiClient.deleteTeacher(id);
    }


    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        this.teacherId = event.getRouteParameters().get("teacherId").orElseThrow();
    }
}

