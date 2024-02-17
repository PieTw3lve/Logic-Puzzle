// Eric Lim

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;

public class Controller {
    
    // Menus Vars
    
    @FXML
    private AnchorPane startMenu;

    @FXML
    private AnchorPane puzzle3x4Menu;

    // Puzzle Parameter Vars

    @FXML
    private ChoiceBox<String> gridSizeOption;
    private String[] sizes = {"3x4 Grid"};

    @FXML
    private ChoiceBox<String> difficultyRangeOption;
    private String[] difficulties = {"Easy"};

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
            handleGridSize(gridSize);
            handleDifficulty(difficulty);
        }
    }

    private void handleGridSize(String gridSize) {
        switch (gridSize) {
            case "3x4 Grid":
                // Generate puzzle code or something
                puzzle3x4Menu.setVisible(true);
                break;
            // Handle other grid sizes here if needed
        }
    }
    
    private void handleDifficulty(String difficulty) {
        switch (difficulty) {
            case "Easy":
                // I have no clue what difficulty changes
                break;
            // Handle other difficulty levels here if needed
        }
    }
}
