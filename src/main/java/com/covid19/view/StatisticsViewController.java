package com.covid19.view;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

public class StatisticsViewController {

    @FXML
    private Label top3Label;

    @FXML
    private Label higherNumberLabel;

    @FXML
    private Label higherRatingLabel;

    @FXML
    private BarChart<?, ?> top3Chart;

    @FXML
    private PieChart higherNumberChart;

    @FXML
    private NumberAxis HigherRatingChart;
}
