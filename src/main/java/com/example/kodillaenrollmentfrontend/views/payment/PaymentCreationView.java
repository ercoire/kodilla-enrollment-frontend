package com.example.kodillaenrollmentfrontend.views.payment;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;


@Route("payment_create")
public class PaymentCreationView extends VerticalLayout {

    FormLayout paymentLayout = new FormLayout();

    public PaymentCreationView(){

        DatePicker paymentDate = new DatePicker("Payment date");
        TextField student = new TextField("Student name"); //todo name+lastname as String
        TextField amount = new TextField("Amount");
        amount.setSuffixComponent(new Span("PLN"));
        TextField course = new TextField("Course"); //todo get course title
        paymentLayout.add(paymentDate, student, amount, course);

        add(paymentLayout);
        add(new Button("Create ")); //todo createPayment on Submit
        //save.addClickListener(event -> save());
        //delete.addClickListener(event -> delete());
    }
}
