package com.example.kodillaenrollmentfrontend.views.teacher;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("teacher")
public class TeacherView extends VerticalLayout {
    FormLayout teacherForm = new FormLayout();

    //    @Autowired
//    private TeacherApiClient teacherApiClient;


    public TeacherView() {

        HorizontalLayout functions = new HorizontalLayout();

        add(new NativeLabel("Choose a button to edit, delete or export your course"));
        Button create = new Button("Create new");
        Button edit = new Button("Edit");
        Button delete = new Button("Delete");
        Button showCourses = new Button("Show showCourses"); //todo connection to endpoint?
        Button export = new Button("Open in Google Sheets");  //todo add export

        functions.add(edit, delete, create, showCourses, export);
        add(functions);

        TextField firstname = new TextField("First name");
        TextField lastname = new TextField("Last name");
        ComboBox<String> courseList = new ComboBox<>("Courses");  //todo add showCourses
        TextArea description = new TextArea("Bio");

        teacherForm.add(firstname, lastname, courseList, description);
        teacherForm.setColspan(description, 3);

        teacherForm.setSizeFull();

        add(teacherForm);
    }
}

