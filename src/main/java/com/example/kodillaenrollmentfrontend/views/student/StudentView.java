package com.example.kodillaenrollmentfrontend.views.student;

import com.example.kodillaenrollmentfrontend.dao.apiclient.StudentApiClient;
import com.example.kodillaenrollmentfrontend.dao.dto.CourseDto;
import com.example.kodillaenrollmentfrontend.dao.dto.PaymentDto;
import com.example.kodillaenrollmentfrontend.dao.dto.StudentDto;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;
import org.springframework.beans.factory.annotation.Autowired;

@Route("student-details/:studentId")
public class StudentView extends VerticalLayout implements BeforeEnterObserver { //todo add actions to buttons

    @Autowired
    StudentApiClient studentApiClient;

    FormLayout studentForm = new FormLayout();
    private String studentId;

    TextField firstname = new TextField("First name");
    TextField lastname = new TextField("Last name");
    EmailField email = new EmailField("Email");

    private final Grid<CourseDto> courseGrid = new Grid<>(CourseDto.class);
    private final Grid<PaymentDto> paymentGrid = new Grid<>(PaymentDto.class);

    public StudentView() {
        HorizontalLayout functions = new HorizontalLayout();

        add(new NativeLabel("Choose a button to edit, delete or export your student"));
        Button create = new Button("Create new");
        create.addClickListener(event -> {
            UI.getCurrent().getPage().setLocation("/student_create");
        });

        Button edit = new Button("Edit");
        edit.addClickListener(event -> {
            UI.getCurrent().navigate(StudentEditView.class, new RouteParameters("studentId", studentId));
        });

        Button delete = new Button("Delete");
        delete.addClickListener(event -> {
            deleteStudentById(Long.valueOf(studentId));
            add(Notification.show("Student deleted successfully!"));
            //todo notification not working
            UI.getCurrent().getPage().setLocation("/students");
        });

        Button showCourses = new Button("Show courses");
        Button showPayments = new Button("Show payments");

        functions.add(edit, delete, create, showCourses, showPayments);


        studentForm.add(firstname, lastname/*, email*/);

        add(functions);
        add(studentForm);
    }

    private void deleteStudentById(Long id) {
        studentApiClient.deleteStudent(id);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        Long id = Long.parseLong(studentId);
        StudentDto retrieved = studentApiClient.getStudent(id);
        firstname.setValue(retrieved.getFirstname());
        lastname.setValue(retrieved.getLastname());
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        this.studentId = event.getRouteParameters().get("studentId")
                .orElseThrow();
    }
}
