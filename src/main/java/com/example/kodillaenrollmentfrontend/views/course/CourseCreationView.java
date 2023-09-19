package com.example.kodillaenrollmentfrontend.views.course;

import com.example.kodillaenrollmentfrontend.dao.apiclient.CourseApiClient;
import com.example.kodillaenrollmentfrontend.dao.apiclient.TeacherApiClient;
import com.example.kodillaenrollmentfrontend.dao.dto.CourseDto;
import com.example.kodillaenrollmentfrontend.dao.dto.TeacherDto;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Route("course_create")
public class CourseCreationView extends VerticalLayout {

    FormLayout courseForm = new FormLayout();

    @Autowired
    private CourseApiClient courseApiClient;

    @Autowired
    private TeacherApiClient teacherApiClient;

    ComboBox<DayOfWeek> day = new ComboBox<>("Day");
    ComboBox<TeacherDto> assignedTeacher1 = new ComboBox<>("Teacher1");
    ComboBox<TeacherDto> assignedTeacher2 = new ComboBox<>("Teacher2");

    private List<TeacherDto> allTeachers() {
        return teacherApiClient.getTeachers();
    }

    public CourseCreationView() {

        TextField title = new TextField("Title");

        assignedTeacher1.setAllowCustomValue(false);
        assignedTeacher2.setAllowCustomValue(false);

        DatePicker startingDate = new DatePicker("Starting Date");
        DatePicker endDate = new DatePicker("End Date");

        TextField pricePerMonth = new TextField("Price");
        pricePerMonth.setSuffixComponent(new Span("PLN"));

        TextArea description = new TextArea("Description");
        TextField duration = new TextField("Duration");
        duration.setSuffixComponent(new Span("min"));


        TimePicker time = new TimePicker("Time");


        HorizontalLayout mainContent = new HorizontalLayout(courseForm);
        mainContent.setSizeFull();
        courseForm.add(title, startingDate, endDate, assignedTeacher1, assignedTeacher2, pricePerMonth, day, time, duration, description);
        courseForm.setColspan(title, 3);
        courseForm.setColspan(description, 3);

        courseForm.setSizeFull();

        add(mainContent);
        add(courseForm);

        Button create = new Button("Create ");  //todo createCourse on Submit
        add(create);

        create.addClickListener(event -> {
            String courseTitle = title.getValue();
            LocalDate start = startingDate.getValue();
            LocalDate end = endDate.getValue();
//            TeacherDto teacher1 = assignedTeacher1.getValue();
//            TeacherDto teacher2 = assignedTeacher2.getValue();
            String dayOfWeek = String.valueOf(day.getValue());
            int price = Integer.parseInt(pricePerMonth.getValue());
            LocalTime t = time.getValue();
            int durValue = Integer.parseInt(duration.getValue());
            String desc = description.getValue();

            createCourseFromForm(courseTitle, start, end,/* teacher1, teacher2, */dayOfWeek, price, t, durValue, desc);
        });

    }

    private void createCourseFromForm(String courseTitle, LocalDate start, LocalDate end,/* TeacherDto teacher1,
                                      TeacherDto teacher2,*/ String dayOfWeek, int price, LocalTime t,
                                      int durValue, String desc) {
        List<TeacherDto> teachers = new ArrayList<>();
//        teachers.add(teacher1);
//        teachers.add(teacher2);
        CourseDto dto = new CourseDto(null, courseTitle,new ArrayList<>(), start, end, price, desc, durValue, dayOfWeek, t);
        courseApiClient.createCourse(dto);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);

        day.setItems(DayOfWeek.values());
//        ComboBox.ItemFilter<TeacherDto> teacher1Filter = (teacherName, filterString) -> teacherName
//                .toString().toLowerCase().startsWith(filterString.toLowerCase());
//        assignedTeacher1.setItemLabelGenerator(TeacherDto::getName);
//        assignedTeacher1.setItems(teacher1Filter, allTeachers());
//
//        ComboBox.ItemFilter<TeacherDto> teacher2Filter = (teacherName, filterString) -> teacherName
//                .toString().toLowerCase().startsWith(filterString.toLowerCase());
//        assignedTeacher2.setItemLabelGenerator(TeacherDto::getName);
//        assignedTeacher2.setItems(teacher2Filter, allTeachers());
//        assignedTeacher2.setRequired(false);
    }
}
