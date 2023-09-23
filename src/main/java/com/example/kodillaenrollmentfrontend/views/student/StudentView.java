package com.example.kodillaenrollmentfrontend.views.student;

import com.example.kodillaenrollmentfrontend.dao.apiclient.CourseApiClient;
import com.example.kodillaenrollmentfrontend.dao.apiclient.StudentApiClient;
import com.example.kodillaenrollmentfrontend.dao.dto.CourseDto;
import com.example.kodillaenrollmentfrontend.dao.dto.PaymentDto;
import com.example.kodillaenrollmentfrontend.dao.dto.StudentDto;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.stream.Collectors;

@Route("student-details/:studentId")
public class StudentView extends VerticalLayout implements BeforeEnterObserver {

    @Autowired
    StudentApiClient studentApiClient;

    @Autowired
    CourseApiClient courseApiClient;

    FormLayout studentForm = new FormLayout();
    private String studentId;

    TextField firstname = new TextField("First name");
    TextField lastname = new TextField("Last name");
    //  EmailField email = new EmailField("Email");

    private final Grid<CourseDto> courseGrid = new Grid<>(CourseDto.class, false);
    private final Grid<PaymentDto> paymentGrid = new Grid<>(PaymentDto.class, false);

    private Map<Long, String> coursesToTitles;


    public StudentView() {
        HorizontalLayout buttons = createButtonsLayout();
        HorizontalLayout studentForm = createStudentForm();
        HorizontalLayout courses = createCourseLayout();
        HorizontalLayout payments = createPaymentLayout();

        add(buttons);
        add(studentForm);
        add(courses);
        add(payments);
    }

    private HorizontalLayout createButtonsLayout() {
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

        Button delete = new Button("Delete");  //todo not working at all!
        delete.addClickListener(event -> {
            deleteStudentById(Long.valueOf(studentId));
            add(Notification.show("Student deleted successfully!"));
            //todo notification not working
            UI.getCurrent().getPage().setLocation("/students");
        });

        Button showCourses = new Button("Show courses");
        showCourses.addClickListener(event -> {
            courseGrid.setVisible(!courseGrid.isVisible());
        });

        Button showPayments = new Button("Show payment list");
        showPayments.addClickListener(event -> {
            paymentGrid.setVisible(!paymentGrid.isVisible());
        });

        functions.add(edit, delete, create, showCourses, showPayments);
        return functions;
    }

    private HorizontalLayout createStudentForm() {
        HorizontalLayout mainContent = new HorizontalLayout(studentForm);
        mainContent.setSizeFull();
        firstname.setReadOnly(true);
        lastname.setReadOnly(true);
        //   email.setReadOnly(true);
        studentForm.add(firstname, lastname/*, email*/);
        studentForm.setSizeFull();
        return mainContent;
    }

    public HorizontalLayout createCourseLayout() {
        HorizontalLayout gridView = new HorizontalLayout();
        gridView.setSizeFull();

        courseGrid.addColumn(CourseDto::getTitle).setHeader("Course title").setSortable(true);
        courseGrid.addColumn(CourseDto::getStartingDate).setHeader("Starting date").setSortable(true);
        courseGrid.addColumn(CourseDto::getEndDate).setHeader("End date").setSortable(true);
        courseGrid.addColumn(CourseDto::getPricePerMonth).setHeader("Price").setSortable(true);
        courseGrid.addColumn(CourseDto::getDuration).setHeader("Duration").setSortable(true);
        courseGrid.addColumn(CourseDto::getDay).setHeader("Day").setSortable(true);
        courseGrid.addColumn(CourseDto::getTime).setHeader("Time").setSortable(true);
        courseGrid.addColumn(CourseDto::getTeacherNames).setHeader("Teachers").setSortable(true);
        courseGrid.setSizeFull();
        //    courseGrid.setColumnRendering(ColumnRendering.LAZY);
        courseGrid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);
        courseGrid.setAllRowsVisible(true);
        courseGrid.setVisible(false);
        gridView.add(courseGrid);
        return gridView;
    }

    private HorizontalLayout createPaymentLayout() {
        HorizontalLayout paymentLayout = new HorizontalLayout();
        paymentLayout.setSizeFull();

        paymentGrid.addColumn(paymentDto -> fetchCourseTitleFromId(paymentDto.getCourseId())).setHeader("Course").setSortable(true);
        paymentGrid.addColumn(PaymentDto::getPaymentDate).setHeader("Date").setSortable(true);
        paymentGrid.addColumn(PaymentDto::getAmount).setHeader("Amount").setSortable(true);
        paymentGrid.setSizeFull();
        paymentGrid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);
        paymentGrid.setAllRowsVisible(true);
        paymentGrid.setVisible(false);
        paymentLayout.add(paymentGrid);
        return paymentLayout;
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

        coursesToTitles = courseApiClient.getCourses().stream()
                .collect(Collectors.toMap(CourseDto::getId, CourseDto::getTitle));

        courseGrid.setItems(studentApiClient.getCoursesByStudentId(id));
        paymentGrid.setItems(studentApiClient.getPaymentsByStudent(id));
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        this.studentId = event.getRouteParameters().get("studentId")
                .orElseThrow();
    }

    private String fetchCourseTitleFromId(Long courseId) {
        return coursesToTitles.get(courseId);
    }
}
