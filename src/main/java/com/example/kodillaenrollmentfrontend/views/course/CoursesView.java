package com.example.kodillaenrollmentfrontend.views.course;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("courses")
public class CoursesView extends VerticalLayout {  //todo fix to be created from actual bean
    /* ##### ERROR DETAILS #####
    There was an exception while trying to navigate to 'courses' with the root cause
    'java.lang.UnsupportedOperationException: This method can't be used for a Grid that isn't constructed from
    a bean type. To construct Grid from a bean type, please provide a beanType argumentto the constructor:
    Grid<Person> grid = new Grid<>(Person.class)'
    ##############
     */

  //  private final Grid<CourseDto> grid = new Grid<>(CourseDto.class);
//    @Autowired
//    private CourseApiClient courseApiClient;

    public CoursesView() {
        add(new NativeLabel("Your courses here!"));
        HorizontalLayout functions = new HorizontalLayout();
        Button create = new Button("Create new");
        Button export = new Button("Export to GoogleSheets");  //todo

        functions.add(create, export);
        add(functions);

        add(new NativeLabel("There will be a grid here soon"));
 /*       add(new NativeLabel("See all courses here. Happy clicking!"));
        add(new Button("Create new")); //todo
Grid <String> grid = new Grid<>();
        grid.setColumns("title", "assignedTeachers", "startingDate", "endDate", "pricePerMonth", "description", "duration", "day", "time");
        HorizontalLayout mainContent = new HorizontalLayout(grid);
        mainContent.setSizeFull();
        grid.setSizeFull();*/
//        Button button = new Button("text");
//        button.addClickListener(event -> {
//
//        });

    }

//    @Override
//    protected void onAttach(AttachEvent attachEvent) {
//        super.onAttach(attachEvent);
//        List<CourseDto> allCourses = courseApiClient.getAllCourses();
//        grid.setItems(allCourses);
//        System.out.println(allCourses);
//    }
}
