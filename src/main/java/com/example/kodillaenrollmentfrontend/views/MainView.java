package com.example.kodillaenrollmentfrontend.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {

    public MainView() {

        H1 title = new H1("Welcome to HAPPY STEPS!");
        title.getStyle().set("font-size", "var(--lumo-font-size-xl)")
                .set("margin", "0");


        Button courses = new Button("Courses");
        courses.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        courses.addClickListener(event -> UI.getCurrent().getPage().setLocation("courses"));

        Button students = new Button("Students");
        students.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        students.addClickListener(event -> UI.getCurrent().getPage().setLocation("students"));

        Button teachers = new Button("Teachers");
        teachers.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        teachers.addClickListener(event -> UI.getCurrent().getPage().setLocation("teachers"));


        Button payments = new Button("Payments");
        payments.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        payments.addClickListener(event -> UI.getCurrent().getPage().setLocation("payment_create"));

        HorizontalLayout main = new HorizontalLayout();
        main.add(teachers, courses, students, payments);

        Accordion accordion = new Accordion();
        Span name = new Span("Katarzyna Gierasimczuk");
        Span email = new Span("k.gierasimczuk@gmail.com");
        VerticalLayout personalInformationLayout = new VerticalLayout(name,
                email);
        personalInformationLayout.setSpacing(false);
        personalInformationLayout.setPadding(false);

        Span disc = new Span("This project has been created for Kodilla Java Developer Bootcamp, 09.2023");
        VerticalLayout disclaimer = new VerticalLayout(disc);
        disclaimer.setSpacing(false);
        disclaimer.setPadding(false);

        accordion.add("Personal information", personalInformationLayout);
        accordion.add("Disclaimer", disclaimer);


        add(title);
        add(main);
        add(accordion);
    }
}
