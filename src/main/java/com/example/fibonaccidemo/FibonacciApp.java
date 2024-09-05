package com.example.fibonaccidemo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FibonacciApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create Fibonacci slider
        Slider slider = new Slider(0, 20, 0);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(5);
        slider.setBlockIncrement(1);

        // Create text field to display Fibonacci numbers
        TextField fibField = new TextField();
        fibField.setEditable(false);

        // Layout setup
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(new Label("Select the number of Fibonacci numbers:"), slider, fibField);

        // Event handler for slider value change
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int n = newValue.intValue();
            Fibonacci fib = new FibonacciRecursive();
            fib.calculateFibonacci(n);
            fibField.setText(fib.toString());
        });

        // Scene setup
        Scene scene = new Scene(root, 300, 150);
        primaryStage.setTitle("Fibonacci Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

abstract class Fibonacci {
    protected int[] fibNumbers;

    public abstract void calculateFibonacci(int n);

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fibNumbers.length; i++) {
            sb.append(fibNumbers[i]);
            if (i < fibNumbers.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}

class FibonacciRecursive extends Fibonacci {

    @Override
    public void calculateFibonacci(int n) {
        fibNumbers = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            fibNumbers[i] = fib(i);
        }
    }

    private int fib(int n) {
        if (n <= 1) {
            return n;
        }

        return fib(n - 1) + fib(n - 2);
    }
}
