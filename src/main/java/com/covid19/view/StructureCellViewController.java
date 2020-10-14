package com.covid19.view;

import javafx.fxml.FXML;
import com.covid19.model.Structure;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Rating;

import java.io.IOException;



public class StructureCellViewController extends ListCell<Structure> {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label nameStructureLabel;

    @FXML
    private ImageView structureImg;

    @FXML
    private Label addressLabelValue;

    @FXML
    private Label stateLabelValue;

    @FXML
    private Label cityLabelValue;

    @FXML
    private Rating starRating;


    private FXMLLoader mLLoader;


    public StructureCellViewController(){

        if (mLLoader == null) {
            mLLoader = new FXMLLoader(getClass().getResource("/StructureCell.fxml"));
            mLLoader.setController(this);
            try {
                mLLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void updateItem(Structure structure, boolean empty) {
        super.updateItem(structure, empty);

        if(empty || structure == null) {

            setText(null);
            setGraphic(null);

        } else {
            nameStructureLabel.setText(structure.getName());
            addressLabelValue.setText(structure.getAddress());
            stateLabelValue.setText(structure.getState());
            cityLabelValue.setText(structure.getCity());
            structureImg.setImage(new Image(structure.getImageLink()));
            starRating.setRating(structure.getAverageRating());
            setText(null);
            setGraphic(anchorPane);
        }
    }

}
