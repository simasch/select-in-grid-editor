package com.example.application.views.helloworld;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Hello World")
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HelloWorldView extends HorizontalLayout {

    public HelloWorldView() {
        Binder<Bean> binder = new Binder<>(Bean.class);
        Select<String> select = new Select<>();
        select.setItems("foo", "bar");

        Grid<Bean> grid = new Grid<>();
        grid.addColumn(Bean::getValue).setEditorComponent(select);

        grid.addItemClickListener(ev -> grid.getEditor().editItem(ev.getItem()));
        binder.forField(select).bind(Bean::getValue, Bean::setValue);
        grid.getEditor().setBinder(binder);
        grid.setItems(new Bean());

        add(grid);
    }

    public static class Bean {

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
