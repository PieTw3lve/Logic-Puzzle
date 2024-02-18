// Eric Lim

import javafx.fxml.FXML;

import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;

public class Controller {
    
    // Menu Vars
    
    @FXML
    private GridPane startMenu;

    @FXML
    private GridPane puzzle3x4Menu;

    // Puzzle Parameter Vars

    @FXML
    private ChoiceBox<String> gridSizeOption;
    private List<String> sizes = Arrays.asList("3x4 Grid");

    @FXML
    private ChoiceBox<String> difficultyRangeOption;
    private List<String> difficulties = Arrays.asList("Easy");

    // Methods

    @FXML
    public void initialize() {
        gridSizeOption.getItems().addAll(sizes);
        difficultyRangeOption.getItems().addAll(difficulties);
    }

    @FXML
    void startGameAction(ActionEvent event) {
        String gridSize = gridSizeOption.getValue();
        String difficulty = difficultyRangeOption.getValue();
    
        if (gridSize != null && difficulty != null) {
            startMenu.setVisible(false);
            handleGrid(gridSize, difficulty);
        }
    }

    private void handleGrid(String gridSize, String difficulty) {
        switch (gridSize) {
            case "3x4 Grid":
                // Generate puzzle code or something
                puzzle3x4Menu.setVisible(true);
                break;
            // Handle other grid sizes here if needed
        }
        switch (difficulty) {
            case "Easy":
                // Difficulty increases point multiplier code here
                break;
            // Handle other difficulty levels here if needed
        }
    }

    @FXML
    void onBoxClicked(ActionEvent event) {
        Button button = (Button) event.getSource();
        List<String> labels = Arrays.asList("", "O", "X");
        int currentIndex = labels.indexOf(button.getText());
        button.setText(labels.get((currentIndex + 1) % labels.size()));
    }
}
