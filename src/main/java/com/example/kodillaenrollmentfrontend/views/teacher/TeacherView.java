package com.example.kodillaenrollmentfrontend.views.teacher;

import com.example.kodillaenrollmentfrontend.dao.apiclient.CourseApiClient;
import com.example.kodillaenrollmentfrontend.dao.apiclient.TeacherApiClient;
import com.example.kodillaenrollmentfrontend.dao.dto.CourseDto;
import com.example.kodillaenrollmentfrontend.dao.dto.TeacherDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.NativeLabel;
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

    @Autowired
    private CourseApiClient courseApiClient;

    public TeacherView() {
        HorizontalLayout functions = new HorizontalLayout();

        add(new NativeLabel("Choose a button to edit, delete or export your course"));
        Button create = new Button("Create new");
        Button edit = new Button("Edit");
        Button delete = new Button("Delete");
        Button showCourses = new Button("Show showCourses"); //todo connection to endpoint?
        Button export = new Button("Open in Google Sheets");  //todo add export

        functions.add(edit, delete, create, showCourses, export);
        add(functions);

        TextField firstname = new TextField("First name");
        TextField lastname = new TextField("Last name");
      //  ComboBox<String> courseList = new ComboBox<>("Courses");  //todo add showCourses
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
        showCourses.addClickListener(event -> {
            courseGrid.setVisible(true);
            //todo getId from the presented teacher
            List<CourseDto> courseList = teacherApiClient.getCoursesByTeacherId();
            courseGrid.setItems(courseList);

        });
    }


}

