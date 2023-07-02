module alura_converter {
    requires javafx.controls;
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.swing;
    requires javafx.web;

    requires com.jfoenix;
    requires com.google.gson;


    opens com.alura.converter to javafx.fxml;
    exports com.alura.converter;
    exports com.alura.converter.controller;
    opens com.alura.converter.controller to javafx.fxml;
    exports com.alura.converter.dto;
    opens com.alura.converter.dto to javafx.fxml;
    exports com.alura.converter.util;
    opens com.alura.converter.util to javafx.fxml;
    exports com.alura.converter.enums;
    opens com.alura.converter.enums to javafx.fxml;
}