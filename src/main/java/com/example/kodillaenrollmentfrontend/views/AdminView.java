package com.example.kodillaenrollmentfrontend.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Route("main")
public class AdminView extends AppLayout {   //todo this class to be main view

    public AdminView() {
        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("Welcome to HAPPY STEPS!");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        Tabs tabs = getTabs();

        addToDrawer(tabs);
        addToNavbar(toggle, title);
    }

    private Tabs getTabs() {
        Tabs tabs = new Tabs();
      Tab ctab = new Tab();
        Tab customerTab = new Tab(new Icon(VaadinIcon.USER));
        customerTab.add("Customer");

        tabs.add(createTab(VaadinIcon.GROUP, "Courses"),
                createTab(VaadinIcon.ACADEMY_CAP, "Students"),
                createTab(VaadinIcon.OPEN_BOOK, "Teachers"),
                createTab(VaadinIcon.CASH, "Payments"), customerTab);

        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        return tabs;
    }

    private Tab createTab(VaadinIcon viewIcon, String viewName) {
        Icon icon = viewIcon.create();
        icon.getStyle().set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("margin-inline-start", "var(--lumo-space-xs)")
                .set("padding", "var(--lumo-space-xs)");

        RouterLink link = new RouterLink();
        link.add(icon, new Span(viewName));

        link.setTabIndex(-1);

        return new Tab(link);
    }

}

