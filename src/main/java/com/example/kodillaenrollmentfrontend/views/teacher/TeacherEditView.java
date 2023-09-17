package com.example.kodillaenrollmentfrontend.views.teacher;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("teacher_edit")
public class TeacherEditView extends VerticalLayout {

    FormLayout teacherForm = new FormLayout();

    //    @Autowired
//    private TeacherApiClient teacherApiClient;


    public TeacherEditView() {

        //todo preset with data
        TextField firstname = new TextField("First name");
        TextField lastname = new TextField("Last name");
        TextArea description = new TextArea("Bio");

        teacherForm.add(firstname, lastname, description);
        teacherForm.setColspan(description, 3);

        teacherForm.setSizeFull();

        add(teacherForm);
        add(new Button("Submit changes"));
    }
}


