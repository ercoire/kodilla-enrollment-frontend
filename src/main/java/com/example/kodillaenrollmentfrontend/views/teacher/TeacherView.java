package com.example.kodillaenrollmentfrontend.views.teacher;

import com.example.kodillaenrollmentfrontend.dao.apiclient.CourseApiClient;
import com.example.kodillaenrollmentfrontend.dao.apiclient.TeacherApiClient;
import com.example.kodillaenrollmentfrontend.dao.dto.CourseDto;
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
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("teacher")
public class TeacherView extends VerticalLayout {
    FormLayout teacherForm = new FormLayout();

    @Autowired
    private TeacherApiClient teacherApiClient;


    public TeacherView() {
        HorizontalLayout functions = new HorizontalLayout();

        add(new NativeLabel("Choose a button to edit, delete or export your course"));
        Button create = new Button("Create new");
        create.addClickListener(event -> {
            UI.getCurrent().getPage().setLocation("/teacher_create");
        });

        Button edit = new Button("Edit");
        edit.addClickListener(event -> {
            UI.getCurrent().getPage().setLocation("/teacher_edit");
        });

        Button delete = new Button("Delete");
        delete.addClickListener(event -> {
            //  deleteTeacherById(id);   //todo
            add(Notification.show("Teacher deleted successfully!"));
            UI.getCurrent().getPage().setLocation("/teachers");
        });


        Button showCourses = new Button("Show showCourses"); //todo connection to endpoint?
        Button export = new Button("Open in Google Sheets");  //todo add export

        functions.add(edit, delete, create, showCourses, export);
        add(functions);

        TextField firstname = new TextField("First name");
        TextField lastname = new TextField("Last name");
        TextArea description = new TextArea("Bio");

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
            //todo getId from the presented teacher
            Long id = null;
            List<CourseDto> courseList = teacherApiClient.getCoursesByTeacherId(id);
            courseGrid.setItems(courseList);

        });
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        //todo pass teacher id
        Long id = null;
        teacherApiClient.getTeacher(id);
    }

    private void deleteTeacherById(Long id) {
        teacherApiClient.deleteTeacher(id);
    }
}

