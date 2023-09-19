package com.example.kodillaenrollmentfrontend.views;

import com.example.kodillaenrollmentfrontend.views.course.CourseCreationView;
import com.example.kodillaenrollmentfrontend.views.course.CourseEditView;
import com.example.kodillaenrollmentfrontend.views.course.CourseView;
import com.example.kodillaenrollmentfrontend.views.course.CoursesView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route
public class MainView extends VerticalLayout {

    public MainView() {
        add(new Button("Click me", e -> Notification.show("You have been rockrolled!")));

        add(new NativeLabel("Welcome to the Main View"));
        //   add(new RouterLink("Admin", AdminView.class));


        VerticalLayout courses = new VerticalLayout();
        RouterLink coursesView = new RouterLink("CoursesView", CoursesView.class);
        RouterLink courseView = new RouterLink("CourseView", CourseView.class);
        RouterLink courseEditView = new RouterLink("CourseEditView", CourseEditView.class);
        RouterLink courseCreationView = new RouterLink("CourseCreationView", CourseCreationView.class);
        courses.add(coursesView, courseView, courseEditView, courseCreationView);
//
//        VerticalLayout teachers = new VerticalLayout();
//        RouterLink teachersView = new RouterLink("TeachersView", TeachersView.class);
//        RouterLink teacherView = new RouterLink("TeacherView", TeacherView.class);
//        RouterLink teacherEditView = new RouterLink("TeacherEditView", TeacherEditView.class);
//        RouterLink teacherCreationView = new RouterLink("TeacherCreationView", TeacherCreationView.class);
//        teachers.add(teachersView, teacherView, teacherEditView, teacherCreationView);
//
//        VerticalLayout students = new VerticalLayout();
//        RouterLink studentsView = new RouterLink("StudentsView", StudentsView.class);
//        RouterLink studentView = new RouterLink("StudentView", StudentView.class);
//        RouterLink studentEditView = new RouterLink("StudentEditView", StudentEditView.class);
//        RouterLink studentCreationView = new RouterLink("StudentCreationView", StudentCreationView.class);
//        students.add(studentsView, studentView, studentEditView, studentCreationView);
//
//        VerticalLayout payments = new VerticalLayout();
//        RouterLink paymentCreationView = new RouterLink("PaymentCreationView", PaymentCreationView.class);
//        payments.add(paymentCreationView);


//        HorizontalLayout main = new HorizontalLayout();
//        main.add(teachers, courses /*, students, payments*/);
//
//        add(main);
    }
}
