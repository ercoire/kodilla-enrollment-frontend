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
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Route("course_edit/:courseId?")
public class CourseEditView extends VerticalLayout implements BeforeEnterObserver {

    FormLayout courseForm = new FormLayout();

    private String courseId;

    @Autowired
    private CourseApiClient courseApiClient;


    @Autowired
    private TeacherApiClient teacherApiClient;

    TextField title = new TextField("Title");
    ComboBox<TeacherDto> assignedTeacher1 = new ComboBox<>("Teacher1");
    ComboBox<TeacherDto> assignedTeacher2 = new ComboBox<>("Teacher2");
    DatePicker startingDate = new DatePicker("Starting Date");
    DatePicker endDate = new DatePicker("End Date");
    TextArea description = new TextArea("Description");
    TextField pricePerMonth = new TextField("Price");
    TextField duration = new TextField("Duration");
    ComboBox<String> day = new ComboBox<>("Day");
    TimePicker time = new TimePicker("Time");

    private List<TeacherDto> allTeachers() {
        return teacherApiClient.getTeachers();
    }

    public CourseEditView() {

        assignedTeacher1.setAllowCustomValue(false);
        assignedTeacher2.setAllowCustomValue(false);
        assignedTeacher1.setItemLabelGenerator(TeacherDto::getName);
        assignedTeacher2.setItemLabelGenerator(TeacherDto::getName);

        pricePerMonth.setSuffixComponent(new Span("PLN"));
        description.setWidthFull();
        duration.setSuffixComponent(new Span("min"));

        day.setItems(Arrays.stream(DayOfWeek.values())
                .map(DayOfWeek::name)
                .toList());

        HorizontalLayout mainContent = createCourseForm();
        HorizontalLayout saveChanges = submitCourseForm();


        add(mainContent);
        add(courseForm);
        add(saveChanges);
    }

    private HorizontalLayout submitCourseForm() {
        HorizontalLayout submitChanges = new HorizontalLayout();
        Button submit = new Button("Submit changes");

        submit.addClickListener(event -> {
            String courseTitle = title.getValue();
            TeacherDto teacher1 = assignedTeacher1.getValue();
            TeacherDto teacher2 = assignedTeacher2.getValue();
            List<TeacherDto> teachers = new ArrayList<>();
            if (teacher1 != null) {
                teachers.add(teacher1);
            }
            if (teacher2 != null) {
                teachers.add(teacher2);
            }
            LocalDate start = startingDate.getValue();
            LocalDate end = endDate.getValue();
            int price = Integer.parseInt(pricePerMonth.getValue());
            String desc = description.getValue();
            int dur = Integer.parseInt(duration.getValue());
            String d = day.getValue();
            LocalTime hour = time.getValue();
            updateCourseFromForm(courseTitle, teachers, start, end, price, desc, dur, d, hour);

            Notification n = new Notification("Course updated successfully");
            n.setPosition(Notification.Position.TOP_CENTER);
            n.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            n.open(); //todo not working
            UI.getCurrent().getPage().setLocation("/courses");
        });

        submitChanges.add(submit);
        return submitChanges;
    }

    private HorizontalLayout createCourseForm() {
        HorizontalLayout mainContent = new HorizontalLayout(courseForm);
        mainContent.setSizeFull();
        courseForm.add(title, startingDate, endDate, assignedTeacher1, assignedTeacher2, day, time, duration, pricePerMonth, description);
        courseForm.setColspan(title, 3);
        courseForm.setColspan(description, 3);

        courseForm.setSizeFull();
        return mainContent;
    }

    private void updateCourseFromForm(String courseTitle, List<TeacherDto> teachers, LocalDate start, LocalDate end,
                                      int price, String desc, int dur, String d, LocalTime hour) {
        CourseDto dto = new CourseDto(Long.parseLong(courseId), courseTitle, teachers, start, end, price, desc, dur, d, hour);
        courseApiClient.updateCourse(dto);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        Long id = Long.parseLong(courseId);
        CourseDto course = courseApiClient.getCourse(id);

        assignedTeacher1.setItems(allTeachers());
        assignedTeacher2.setItems(allTeachers());
        if (course.getAssignedTeachers().size() > 0) {
            assignedTeacher1.setValue(course.getAssignedTeachers().get(0));
        }
        if (course.getAssignedTeachers().size() > 1) {
            assignedTeacher2.setValue(course.getAssignedTeachers().get(1));
        }


        title.setValue(course.getTitle());
        startingDate.setValue(course.getStartingDate());
        endDate.setValue(course.getEndDate());
        pricePerMonth.setValue(String.valueOf(course.getPricePerMonth()));
        description.setValue(course.getDescription());
        duration.setValue(String.valueOf(course.getDuration()));
        day.setValue(course.getDay());
        time.setValue(course.getTime());

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        this.courseId = event.getRouteParameters().get("courseId")
                .orElseThrow();
    }
}
