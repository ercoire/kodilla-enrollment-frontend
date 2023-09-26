package com.example.kodillaenrollmentfrontend.views.teacher;

import com.example.kodillaenrollmentfrontend.dao.apiclient.TeacherApiClient;
import com.example.kodillaenrollmentfrontend.dao.dto.TeacherDto;
import com.example.kodillaenrollmentfrontend.views.MainView;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("teacher_edit/:teacherId?")
public class TeacherEditView extends VerticalLayout implements BeforeEnterObserver {

    FormLayout teacherForm = new FormLayout();

    private String teacherId;


    @Autowired
    private TeacherApiClient teacherApiClient;

    TextField firstname = new TextField("First name");
    TextField lastname = new TextField("Last name");
    TextArea description = new TextArea("Bio");

    public TeacherEditView() {

        teacherForm.add(firstname, lastname, description);
        teacherForm.setColspan(description, 3);

        teacherForm.setSizeFull();

        HorizontalLayout save = createSubmitLayout();

        add(teacherForm);
        add(save);

    }

    private HorizontalLayout createSubmitLayout() {
        HorizontalLayout save = new HorizontalLayout();
        Button submit = new Button("Submit changes");

        submit.addClickListener(event -> {
            String first = firstname.getValue();
            String last = lastname.getValue();
            String bio = description.getValue();
            updateTeacherFromForm(first, last, bio);

            Notification n = new Notification("Teacher updated successfully");
            n.setDuration(5000);
            n.setPosition(Notification.Position.TOP_CENTER);
            n.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            n.open();
            UI.getCurrent().getPage().setLocation("/teachers");
        });

        Button backToMain = new Button("Main menu");
        backToMain.addClickListener(event -> UI.getCurrent().navigate(MainView.class));

        save.add(submit, backToMain);
        return save;
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        Long id = Long.parseLong(teacherId);
        TeacherDto teacherDto = teacherApiClient.getTeacher(id);
        firstname.setValue(teacherDto.getFirstname());
        lastname.setValue(teacherDto.getLastname());
        description.setValue(teacherDto.getDescription());
    }

    private void updateTeacherFromForm(String first, String last, String bio) {
        TeacherDto dto = new TeacherDto(Long.parseLong(teacherId), first, last, bio);
        teacherApiClient.updateTeacher(dto);

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        this.teacherId = event.getRouteParameters().get("teacherId").orElseThrow();
    }
}


