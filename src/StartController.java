// Eric Lim

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;

public class StartController {

    // Puzzle Parameter Vars

    PuzzleData data = PuzzleData.getInstance();

    @FXML
    private ChoiceBox<String> gridSizeOption;
    private List<String> gridSizes = Arrays.asList("3x4 Grid");

    @FXML
    private ChoiceBox<String> difficultyRangeOption;
    private List<String> difficulties = Arrays.asList("Easy");

    // Methods

    @FXML
    public void initialize() {
        gridSizeOption.getItems().addAll(gridSizes);
        difficultyRangeOption.getItems().addAll(difficulties);
    }

    @FXML
    void startGameAction(ActionEvent event) throws IOException {
        String gridSize = gridSizeOption.getValue();
        String difficulty = difficultyRangeOption.getValue();
    
        if (gridSize != null && difficulty != null) {
            handleGrid(event, gridSize, difficulty);
        }
    }

    private void handleGrid(ActionEvent event, String gridSize, String difficulty) throws IOException {
        switch (gridSize) {
            case "3x4 Grid":
                data.setDifficulty(difficulty);
                switchScene(event, "fxml/Puzzle3x4.fxml");
            // Handle other grid sizes here if needed
        }
    }

    private void switchScene(ActionEvent event, String sceneName) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(sceneName));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
