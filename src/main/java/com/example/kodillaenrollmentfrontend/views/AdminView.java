package com.example.kodillaenrollmentfrontend.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("admin")
public class AdminView extends VerticalLayout {

    public AdminView() {
        NativeLabel title = new NativeLabel("Welcome to administration panel!");
        title.setWidthFull();
        add(title);
        HorizontalLayout layout = new HorizontalLayout(); //todo action to show all


        layout.setPadding(true);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
      //  layout.setAlignItems(Alignment.CENTER);
      //  layout.setSizeFull();
        layout.setSpacing(false);
        layout.getThemeList().add("spacing");
      //  layout.add(courses, students, teachers, payments, calendar);

        add(layout);

    }
}
