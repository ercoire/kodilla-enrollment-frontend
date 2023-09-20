package com.example.kodillaenrollmentfrontend.views.course;

import com.example.kodillaenrollmentfrontend.dao.apiclient.CourseApiClient;
import com.example.kodillaenrollmentfrontend.dao.dto.CourseDto;
import com.example.kodillaenrollmentfrontend.dao.dto.PaymentDto;
import com.example.kodillaenrollmentfrontend.dao.dto.StudentDto;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnRendering;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Route("course-details/:courseId")
public class CourseView extends VerticalLayout implements BeforeEnterObserver {

    @Autowired
    private CourseApiClient courseApiClient;

    FormLayout courseForm = new FormLayout();
    private String courseId;

    TextField title = new TextField("Title");
    TextField assignedTeacher1 = new TextField("Teacher1");
    TextField assignedTeacher2 = new TextField("Teacher2");
    DatePicker startingDate = new DatePicker("Starting Date");
    DatePicker endDate = new DatePicker("End Date");
    TextField pricePerMonth = new TextField("Price");
    TextArea description = new TextArea("Description");
    TextField duration = new TextField("Duration");
    TextField day = new TextField("Day");
    TimePicker time = new TimePicker("Time");

    private final Grid<PaymentDto> paymentGrid = new Grid<>(PaymentDto.class, false);

    public CourseView() {
        add(new NativeLabel("Choose a button to edit, delete or export your course"));

        HorizontalLayout functions = new HorizontalLayout();
        Button create = new Button("Create new");
        create.addClickListener(event -> {
            UI.getCurrent().getPage().setLocation("/course_create");
        });

        Button edit = new Button("Edit");
        edit.addClickListener(event -> {
            UI.getCurrent().navigate(CourseEditView.class, new RouteParameters("courseId", courseId));
        });
        Button delete = new Button("Delete");
        delete.addClickListener(event -> {
            deleteCourseById(courseId);
            add(Notification.show("Course deleted successfully!"));
            UI.getCurrent().getPage().setLocation("/courses");
        });

        Button showPayments = new Button("Show payment list");
        showPayments.addClickListener(event -> {
            Long id = Long.parseLong(courseId);
            paymentGrid.setItems(courseApiClient.getPaymentsByCourseId(id));
            Div gridView = new Div();
            gridView.setSizeFull();

            //todo not working
   /*         //paymentGrid.setVisible(true);
            paymentGrid.addColumn(PaymentDto::getPaymentDate).setHeader("Payment date").setSortable(true);
            paymentGrid.addColumn(PaymentDto::fetchStudentName).setHeader("Student").setSortable(true);
            paymentGrid.addColumn(PaymentDto::getAmount).setHeader("Amount").setSortable(true);
            paymentGrid.setSizeFull();
            paymentGrid.setColumnRendering(ColumnRendering.LAZY);

            gridView.add(paymentGrid);
  */          add(gridView);
        });



        Button addStudent = new Button("Add Student");
        addStudent.addClickListener(event -> {
            UI.getCurrent().getPage().setLocation("/course_edit");
        });
        Button export = new Button("Export to Google Sheets"); //todo
        functions.add(edit, delete, create, showPayments, addStudent, export);
        add(functions);


        HorizontalLayout mainContent = new HorizontalLayout(courseForm);
        mainContent.setSizeFull();
        pricePerMonth.setSuffixComponent(new Span("PLN"));
        duration.setSuffixComponent(new Span("min"));
        description.setWidthFull();

        courseForm.add(title, startingDate, endDate, assignedTeacher1, assignedTeacher2, pricePerMonth,
                day, time, duration, description);
        courseForm.setColspan(title, 3);
        courseForm.setColspan(description, 3);

        courseForm.setSizeFull();

        add(functions);
        add(mainContent);
//        add(courseForm);


    }

    private void deleteCourseById(String courseId) {
        courseApiClient.deleteCourse(Long.parseLong(courseId));
    }


    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        Long id = Long.parseLong(courseId);
        CourseDto retrieved = courseApiClient.getCourse(id);
        title.setValue(retrieved.getTitle());
        if (retrieved.getAssignedTeachers().size() > 0) {
            assignedTeacher1.setValue(retrieved.getAssignedTeachers().get(0).toString());
        }
        if (retrieved.getAssignedTeachers().size() > 1) {
            assignedTeacher2.setValue(retrieved.getAssignedTeachers().get(1).toString());
        }

        startingDate.setValue(retrieved.getStartingDate());
        endDate.setValue(retrieved.getEndDate());
        pricePerMonth.setValue(String.valueOf(retrieved.getPricePerMonth()));
        description.setValue(retrieved.getDescription());
        duration.setValue(String.valueOf(retrieved.getDuration()));
        day.setValue(retrieved.getDay());
        time.setValue(retrieved.getTime());
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        this.courseId = event.getRouteParameters().get("courseId")
                .orElseThrow();
    }
}
