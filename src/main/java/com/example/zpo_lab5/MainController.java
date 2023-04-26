package com.example.zpo_lab5;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class MainController {

    Object object;
    Class<?> clazz;

    @FXML
    Button button;

    @FXML
    public void initialize() {
        button.setDisable(true);
        object = new Person();
        clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        button.setOnAction(e -> onHelloButtonClick());
        for (Field field : fields) {
            VinputText vinputText = new VinputText(this, field);
            vBox.getChildren().add(vinputText);

            if (field.isAnnotationPresent(MyPatterns.class)) {
                System.out.println(field.getAnnotation(MyPatterns.class));
                MyPatterns annotations = field.getAnnotation(MyPatterns.class);
                for (MyPattern mp : annotations.value()) {
                    vinputText.registerValidator(new MyPatternValidator(mp));
                }

            } else if (field.isAnnotationPresent(MyPattern.class)) {
                vinputText.registerValidator(new MyPatternValidator(field.getAnnotation(MyPattern.class)));
            }
        }
    }

    @FXML
    private VBox vBox;

    protected void onHelloButtonClick() {
        vBox.getChildren().forEach(v -> System.out.println(((VinputText)v).textInputControl.getText()));
    }

    public void allCorrect() {
        ObservableList<Node> children = vBox.getChildren();
        for (Node c : children) {
            if (c instanceof VinputText) {
                VinputText v = (VinputText) c;
                if (!v.isGood()) {
                    button.setDisable(true);
                    return;
                }
            }
        }
        button.setDisable(false);
    }
}