package com.example.kodillaenrollmentfrontend.views.payment;

import com.example.kodillaenrollmentfrontend.dao.apiclient.CourseApiClient;
import com.example.kodillaenrollmentfrontend.dao.apiclient.PaymentApiClient;
import com.example.kodillaenrollmentfrontend.dao.apiclient.StudentApiClient;
import com.example.kodillaenrollmentfrontend.dao.dto.CourseDto;
import com.example.kodillaenrollmentfrontend.dao.dto.PaymentCreationDto;
import com.example.kodillaenrollmentfrontend.dao.dto.StudentDto;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;


@Route("payment_create")
public class PaymentCreationView extends VerticalLayout {

    @Autowired
    private PaymentApiClient paymentApiClient;

    @Autowired
    private StudentApiClient studentApiClient;

    @Autowired
    private CourseApiClient courseApiClient;

    FormLayout paymentLayout = new FormLayout();

    private List<StudentDto> allStudents() {
        return studentApiClient.getStudents();
    }

    private List<CourseDto> allCourses() {
        return courseApiClient.getAllCourses();
    }


    ComboBox<StudentDto> student = new ComboBox<>("Student name"); //todo name+lastname as String
    ComboBox<CourseDto> course = new ComboBox<>("Course title");

    public PaymentCreationView() {

        DatePicker paymentDate = new DatePicker("Payment date");

        student.setAutoOpen(false);

        TextField amount = new TextField("Amount");
        amount.setSuffixComponent(new Span("PLN"));


        paymentLayout.add(paymentDate, student, amount, course);

        add(paymentLayout);

        Button create = new Button("Create ");
        add(create);


        create.addClickListener(event -> {
            LocalDate date = paymentDate.getValue();
            StudentDto person = student.getValue();
            String amt = amount.getValue();
            CourseDto dto = course.getValue();

            if (date == null || person == null || amt.isEmpty() || dto == null) {
                // Check if any required fields are empty and show an error notification.
                Notification.show("Please fill in all required fields", 3000, Notification.Position.TOP_CENTER);
            } else {
                // All required fields are filled, proceed with payment creation.
                createPaymentFromForm(date, person, amt, dto);
                // Optionally, show a success notification.
                Notification.show("Payment created successfully", 3000, Notification.Position.TOP_CENTER);
            }

        });
    }

    private void createPaymentFromForm(LocalDate date, StudentDto person, String amt, CourseDto courseDto) {
        Long studentId = person.getId();
        int amount = Integer.parseInt(amt);
        Long courseId = courseDto.getId();
        PaymentCreationDto paymentDto = new PaymentCreationDto(null, date, studentId, amount, courseId);
        paymentApiClient.createPayment(paymentDto);
    }


    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        ComboBox.ItemFilter<StudentDto> studentFilter = (studentName, filterString) -> studentName
                .toString().toLowerCase().startsWith(filterString.toLowerCase());
        course.setItemLabelGenerator(CourseDto::getTitle);
        student.setItems(studentFilter, allStudents());
        ComboBox.ItemFilter<CourseDto> courseFilter = (courseTitle, filterString) -> courseTitle
                .getTitle().toLowerCase().startsWith(filterString.toLowerCase());
        course.setItems(courseFilter, allCourses());
    }
}


