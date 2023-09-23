package com.example.kodillaenrollmentfrontend.views.course;

import com.example.kodillaenrollmentfrontend.dao.apiclient.CourseApiClient;
import com.example.kodillaenrollmentfrontend.dao.dto.CourseDto;
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
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@Route("course_create")
public class CourseCreationView extends VerticalLayout {

    FormLayout courseForm = new FormLayout();

    @Autowired
    private CourseApiClient courseApiClient;

    TextField title = new TextField("Title");
    DatePicker startingDate = new DatePicker("Starting Date");
    DatePicker endDate = new DatePicker("End Date");
    TextField pricePerMonth = new TextField("Price");
    TextArea description = new TextArea("Description");
    TextField duration = new TextField("Duration");
    TimePicker time = new TimePicker("Time");
    ComboBox<DayOfWeek> day = new ComboBox<>("Day");

    public CourseCreationView() {

        HorizontalLayout mainContent = createCourseForm();
        HorizontalLayout buttons = createButtonsLayout();

        add(mainContent);
        add(buttons);
    }

    private HorizontalLayout createButtonsLayout() {
        HorizontalLayout buttons = new HorizontalLayout();

        Button create = new Button("Create ");

        create.addClickListener(event -> {
            String courseTitle = title.getValue();
            LocalDate start = startingDate.getValue();
            LocalDate end = endDate.getValue();

            String dayOfWeek = String.valueOf(day.getValue());
            int price = Integer.parseInt(pricePerMonth.getValue());
            LocalTime t = time.getValue();
            int durValue = Integer.parseInt(duration.getValue());
            String desc = description.getValue();

            createCourseFromForm(courseTitle, start, end, dayOfWeek, price, t, durValue, desc);
            Notification n = new Notification("Course created successfully");
            n.setPosition(Notification.Position.TOP_CENTER);
            n.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            n.setDuration(20000);
            n.open();  //todo doesn't work
            UI.getCurrent().getPage().setLocation("/courses");
        });
        buttons.add(create);
        return buttons;
    }

    private HorizontalLayout createCourseForm() {
        HorizontalLayout form = new HorizontalLayout(courseForm);
        form.setSizeFull();
        pricePerMonth.setSuffixComponent(new Span("PLN"));
        description.setWidthFull();
        duration.setSuffixComponent(new Span("min"));

        courseForm.add(title, startingDate, endDate, day, time, duration, pricePerMonth, description);
        courseForm.setColspan(title, 3);
        courseForm.setColspan(description, 3);

        courseForm.setSizeFull();
        form.add(courseForm);
        return form;
    }

    private void createCourseFromForm(String courseTitle, LocalDate start, LocalDate end, String dayOfWeek,
                                      int price, LocalTime t, int durValue, String desc) {

        CourseDto dto = new CourseDto(null, courseTitle, new ArrayList<>(), start, end, price, desc, durValue, dayOfWeek, t);
        courseApiClient.createCourse(dto);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);

        day.setItems(DayOfWeek.values());

    }
}
