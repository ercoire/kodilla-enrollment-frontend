package com.example.kodillaenrollmentfrontend.views.course;

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

@Route("course_create")
public class CourseCreationView extends VerticalLayout {

    FormLayout courseForm = new FormLayout();

    //    @Autowired
//    private CourseApiClient courseApiClient;
    //preset with values

    public CourseCreationView() {

        TextField title = new TextField("Title");

        ComboBox<String> assignedTeacher1 = new ComboBox<>("Teacher1");
        ComboBox<String> assignedTeacher2 = new ComboBox<>("Teacher2");
        assignedTeacher1.setAllowCustomValue(false);
        assignedTeacher2.setAllowCustomValue(false);
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



        HorizontalLayout mainContent = new HorizontalLayout(courseForm);
        mainContent.setSizeFull();
        courseForm.add(title, startingDate, endDate, assignedTeacher1, assignedTeacher2, pricePerMonth, day, time, duration, description);
        courseForm.setColspan(title, 3);
        courseForm.setColspan(description, 3);

        courseForm.setSizeFull();

        add(mainContent);
        add(courseForm);
        add(new Button("Create ")); //todo createCourse on Submit
     //   save.addClickListener(event -> save());

//
//        Button button = new Button("text");
//        button.addClickListener(event -> {
//
//        });

    }

//    @Override
//    protected void onAttach(AttachEvent attachEvent) {
//        super.onAttach(attachEvent);
//        CourseDto courseById = courseApiClient.getCourse();
//
//        System.out.println(courseById);
//    }
}
