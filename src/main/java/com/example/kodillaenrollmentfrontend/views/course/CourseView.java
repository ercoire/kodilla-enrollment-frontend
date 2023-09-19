package com.example.kodillaenrollmentfrontend.views.course;

import com.example.kodillaenrollmentfrontend.dao.apiclient.CourseApiClient;
import com.example.kodillaenrollmentfrontend.dao.apiclient.TeacherApiClient;
import com.example.kodillaenrollmentfrontend.dao.dto.CourseDto;
import com.example.kodillaenrollmentfrontend.dao.dto.TeacherDto;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.DayOfWeek;

@Route("course-details/:id")
public class CourseView extends VerticalLayout{

    private CourseDto courseDto;

    @Autowired
    private CourseApiClient courseApiClient;

    @Autowired
    private TeacherApiClient teacherApiClient;

    FormLayout courseForm = new FormLayout();

    ComboBox<DayOfWeek> day = new ComboBox<>("Day");
    ComboBox<TeacherDto> assignedTeacher1 = new ComboBox<>("Teacher1");
    ComboBox<TeacherDto> assignedTeacher2 = new ComboBox<>("Teacher2");


    public CourseView() {
        add(new NativeLabel("Choose a button to edit, delete or export your course"));
        HorizontalLayout functions = new HorizontalLayout();
        add(functions);

        TextField title = new TextField("Title");

        TextField assignedTeacher1 = new TextField("Teacher1");
        TextField assignedTeacher2 = new TextField("Teacher2");
        //assignedTeacher1.setItems(teacherList); //todo
        //assignedTeacher2.setItems(teacherList);

        DatePicker startingDate = new DatePicker("Starting Date");
        DatePicker endDate = new DatePicker("End Date");

        TextField pricePerMonth = new TextField("Price");
        pricePerMonth.setSuffixComponent(new Span("PLN"));

        TextArea description = new TextArea("Description");
        TextField duration = new TextField("Duration");
        duration.setSuffixComponent(new Span("min"));

        ComboBox<String> day = new ComboBox<>("Day");
        TimePicker time = new TimePicker("Time");
        //description.setWidthFull();

        Button create = new Button("Create new");
        Button edit = new Button("Edit");
        Button delete = new Button("Delete");
        Button payments = new Button("Show payment list");
        Button addStudent = new Button("Add Student");
        addStudent.addClickListener(event -> {
            UI.getCurrent().getPage().setLocation("/course_edit");
        });
        Button export = new Button("Export to Google Sheets"); //todo
        functions.add(edit, delete, create, payments, addStudent, export);


        HorizontalLayout mainContent = new HorizontalLayout(courseForm);
        mainContent.setSizeFull();
        courseForm.add(title, startingDate, endDate, assignedTeacher1, assignedTeacher2, pricePerMonth, day, time, duration, description);
        courseForm.setColspan(title, 3);
        courseForm.setColspan(description, 3);

        courseForm.setSizeFull();

        add(functions);
        add(mainContent);
        add(courseForm);
//
//        Button button = new Button("text");
//        button.addClickListener(event -> {
//
//        });
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        CourseDto courseById = courseApiClient.getCourse();

        System.out.println(courseById);
    }
}
