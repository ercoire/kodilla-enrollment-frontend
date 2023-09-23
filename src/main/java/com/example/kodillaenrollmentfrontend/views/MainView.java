package com.example.kodillaenrollmentfrontend.views;

import com.example.kodillaenrollmentfrontend.views.course.CoursesView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import java.awt.*;

@Route
public class MainView extends VerticalLayout {

    public MainView() {

        H1 title = new H1("Welcome to HAPPY STEPS!");
        title.getStyle().set("font-size", "var(--lumo-font-size-xl)")
                .set("margin", "0");

        //   add(new RouterLink("Admin", AdminView.class));


        Button courses = new Button("Courses");
        courses.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        courses.addClickListener(event -> {
            UI.getCurrent().getPage().setLocation("courses");
        });

        Button students = new Button("Students");
        students.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        students.addClickListener(event -> {
            UI.getCurrent().getPage().setLocation("students");
        });

        Button teachers = new Button("Teachers");
        teachers.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        teachers.addClickListener(event -> {
            UI.getCurrent().getPage().setLocation("teachers");
        });


        Button payments = new Button("Payments");
        payments.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        payments.addClickListener(event -> {
            UI.getCurrent().getPage().setLocation("payment_create");
        });

        HorizontalLayout main = new HorizontalLayout();
        main.add(teachers, courses, students, payments);

        Span name = new Span("Sophia Williams");
        Span email = new Span("sophia.williams@company.com");
        Span copyrights = new Span("Ⓒ Katarzyna Gierasimczuk 2023");
        VerticalLayout footer = new VerticalLayout(name,
                email, copyrights);
        footer.setSpacing(false);
        footer.setPadding(false);


        add(title);
        add(main);
        add(footer);
       // add(new Button("Click me", e -> Notification.show("You have been rickrolled!")));
    }
}
