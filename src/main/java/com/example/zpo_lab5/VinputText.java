package com.example.zpo_lab5;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class VinputText extends HBox {

    public TextInputControl textInputControl;
    private Label label;
    private ImageView imageView;
    private Validator validator;

    private List<Validator> validators = new ArrayList<>();

    private Field field;

    private MainController mainController;

    public VinputText(MainController mainController, Field field) {
        this.mainController = mainController;
        this.field = field;
        //this.validator = new MyPatternValidator(field.getAnnotation(MyPattern.class));
        imageView = new ImageView();
        imageView.setFitHeight(20.);
        imageView.setFitWidth(20.);
        textInputControl = new TextField();
        textInputControl.setOnKeyTyped(e -> onKeyTyped());
        label = new Label();
        label.setText(field.getName());
        this.getChildren().add(imageView);
        this.getChildren().add(textInputControl);
        this.getChildren().add(label);
    }

    public void registerValidator(Validator v) {
        validator = v;
        validators.add(v);
    }

    public boolean isGood(){
        for(Validator v : validators){
            if (!v.isValid())
                return false;
        }
        return true;
    }
    Tooltip tooltip = new Tooltip();

    private void validatorsCheck(Validator v){
        v.validate(textInputControl.getText());
        if (!v.isValid()){
            toolTipText += ('\n' + v.getMessage());
        }
    }
    private String toolTipText;
    private void onKeyTyped() {
        toolTipText = "";

        validators.forEach(this::validatorsCheck);
        if (toolTipText != "")
        {
            tooltip.setText(toolTipText);
            Tooltip.install(imageView,tooltip);
            try {
                    imageView.setImage(new Image(new FileInputStream("src\\wrong.png")));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
        }else {
            Tooltip.uninstall(imageView,tooltip);
            try {
                imageView.setImage(new Image(new FileInputStream("src\\ok.png")));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
//        if (validator != null){
//            validator.validate(textInputControl.getText());
//            Tooltip.install(imageView,tooltip);
//            if (!validator.isValid()){
//
//                tooltip.setText(validator.getMessage());
//
//                System.out.println(validator.getMessage());
//                try {
//                    imageView.setImage(new Image(new FileInputStream("src\\wrong.png")));
//                } catch (FileNotFoundException e) {
//                    throw new RuntimeException(e);
//                }
//            }else {
//                tooltip.setText("");
//                try {
//                    imageView.setImage(new Image(new FileInputStream("src\\ok.png")));
//                } catch (FileNotFoundException e) {
//                    throw new RuntimeException(e);
//                }
//            }
            mainController.allCorrect();

    }


}
