import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PuzzleController {

    private DinosaurPuzzle puzzle;

    @FXML
    private List<Text> answerNumbers, answerNames, answerAges;

    @FXML
    private Label HintL;

    @FXML
    private Text cluesText;

    @FXML
    private GridPane LeftGrid, BottomGrid, RightGrid;

    PuzzleData data = PuzzleData.getInstance();

    private UserGrid userGrid;
    private List<GridPane> userInput;

    @FXML
    private Text NameOne, NameTwo, NameThree, NameFour;

    public PuzzleController() {
        puzzle = new DinosaurPuzzle();
    }

    @FXML
    public void initialize() {
        displayHints();
        updateTextFields();

        if (data.getGridSize().equals("3x4 Grid")) {
            setupGrid(LeftGrid);
            setupGrid(BottomGrid);
            setupGrid(RightGrid);
        }
    }

    private void displayHints() {
        List<String> hints = puzzle.generateHints();
        cluesText.setText(String.join("\n", hints));
    }

    private void updateTextFields() {
        List<String> dinosaurs = puzzle.getNames();
        NameOne.setText(dinosaurs.get(0));
        NameTwo.setText(dinosaurs.get(1));
        NameThree.setText(dinosaurs.get(2));
        NameFour.setText(dinosaurs.get(3));
    }

    private void setupGrid(GridPane grid) {
        grid.getChildren().forEach(node -> {
            if (node instanceof Button) {
                Button button = (Button) node;
                button.setOnAction(this::onBoxClicked);
            }
        });
    }

    @FXML
    void onBoxClicked(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        GridPane grid = (GridPane) clickedButton.getParent();
        Integer clickedRow = GridPane.getRowIndex(clickedButton);
        Integer clickedColumn = GridPane.getColumnIndex(clickedButton);
        clickedRow = clickedRow != null ? clickedRow : 0;
        clickedColumn = clickedColumn != null ? clickedColumn : 0;

        switch (clickedButton.getText()) {
            case "":
                clickedButton.setText("O");
                markAdjacentCells(grid, clickedRow, clickedColumn);
                break;
            case "O":
                clickedButton.setText("");
                break;
            case "X":
                clickedButton.setText("");
                break;
        }
    }

    private void markAdjacentCells(GridPane grid, int row, int col) {
        for (Node node : grid.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                int buttonRow = GridPane.getRowIndex(button) != null ? GridPane.getRowIndex(button) : 0;
                int buttonCol = GridPane.getColumnIndex(button) != null ? GridPane.getColumnIndex(button) : 0;

                if (buttonRow == row && buttonCol != col) {
                    button.setText("X");
                }
                if (buttonCol == col && buttonRow != row) {
                    button.setText("X");
                }
            }
        }
    }

    @FXML
    void requestHint(ActionEvent event) {
        String hintMessage = getNextHint();
        HintL.setText(hintMessage);
    }
    
    private String getNextHint() {
        List<Boolean[][]> correctAnswers = puzzle.getCorrectAnswers();
        boolean[][] hintsGiven = puzzle.getHintsGiven();
    
        for (int gridIndex = 0; gridIndex < correctAnswers.size(); gridIndex++) {
            for (int rowIndex = 0; rowIndex < correctAnswers.get(gridIndex).length; rowIndex++) {
                for (int colIndex = 0; colIndex < correctAnswers.get(gridIndex)[rowIndex].length; colIndex++) {
                    if (correctAnswers.get(gridIndex)[rowIndex][colIndex] != null && correctAnswers.get(gridIndex)[rowIndex][colIndex] && !hintsGiven[gridIndex][rowIndex]) {
                        // Mark hint as given
                        puzzle.setHintGiven(gridIndex, rowIndex);
    
                        String gridName = getGridNameByIndex(gridIndex);
                        return String.format("Correct answer at the %s, row %d, column %d", gridName, rowIndex + 1, colIndex + 1);
                    }
                }
            }
        }
        return "No more hints available.";
    }
    
    private String getGridNameByIndex(int index) {
        switch (index) {
            case 0: return "left grid";
            case 1: return "bottom grid";
            case 2: return "right grid";
            default: return "unknown grid"; // should not happen
        }
    }

    @FXML
    void clearErrors(ActionEvent event) {
        clearIncorrectAnswers(LeftGrid, 0);
        clearIncorrectAnswers(BottomGrid, 1);
        clearIncorrectAnswers(RightGrid, 2);
    }

    private void clearIncorrectAnswers(GridPane grid, int gridIndex) {
        for (Node node : grid.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                int row = GridPane.getRowIndex(button) != null ? GridPane.getRowIndex(button) : 0;
                int col = GridPane.getColumnIndex(button) != null ? GridPane.getColumnIndex(button) : 0;
    
                // Clear the button if it's incorrectly marked as "O"
                if ("O".equals(button.getText()) && !puzzle.isPositionCorrect(gridIndex, row, col)) {
                    button.setText(""); // Clear the incorrect "O"
                    // After clearing an incorrect "O", clear related "X" marks
                    clearRelatedX(grid, row, col);
                }
            }
        }
    }

    private void clearRelatedX(GridPane grid, int clearedRow, int clearedCol) {
        for (Node node : grid.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                int buttonRow = GridPane.getRowIndex(button) != null ? GridPane.getRowIndex(button) : 0;
                int buttonCol = GridPane.getColumnIndex(button) != null ? GridPane.getColumnIndex(button) : 0;
    
                // Check if the button is in the same row or column as the cleared "O"
                // And if it is marked with "X", then clear it
                if ((buttonRow == clearedRow || buttonCol == clearedCol) && "X".equals(button.getText())) {
                    // Before clearing the "X", check if there's another "O" in the same row or column
                    // that would justify keeping the "X". If not, clear the "X".
                    if (!hasValidO(grid, buttonRow, buttonCol, clearedRow, clearedCol)) {
                        button.setText("");
                    }
                }
            }
        }
    }

    // Helper method to check if there's a valid "O" in the same row or column
    private boolean hasValidO(GridPane grid, int row, int col, int excludedRow, int excludedCol) {
        for (Node node : grid.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                int buttonRow = GridPane.getRowIndex(button) != null ? GridPane.getRowIndex(button) : 0;
                int buttonCol = GridPane.getColumnIndex(button) != null ? GridPane.getColumnIndex(button) : 0;

                if ("O".equals(button.getText()) && !(buttonRow == excludedRow && buttonCol == excludedCol)) {
                    if (buttonRow == row || buttonCol == col) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @FXML
    void startOver(ActionEvent event) {
        switchScene(event, "fxml/Puzzle3x4.fxml");
    }

    private void switchScene(ActionEvent event, String sceneName) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(sceneName));
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void submitAnswers(ActionEvent event) {
        switch (data.getGridSize()) {
            case "3x4 Grid":
                userInput = Arrays.asList(LeftGrid, BottomGrid, RightGrid);
                userGrid = new UserGrid(3, 4, 4);
                break;
            // Handle other grid sizes here if needed
        }
        userGrid.convertUserInputToGrid(userInput);

        // Retrieve correct answers from DinosaurPuzzle
        List<Boolean[][]> correctAnswers = puzzle.generateAnswers();

        // Compare user input to correct answers
        boolean allCorrect = true;
        for (int i = 0; i < correctAnswers.size(); i++) {
            Boolean[][] userGridValues = userGrid.getPuzzleBoard().get(i);
            Boolean[][] correctValues = correctAnswers.get(i);

            for (int row = 0; row < userGridValues.length; row++) {
                for (int col = 0; col < userGridValues[row].length; col++) {
                    if (userGridValues[row][col] != correctValues[row][col]) {
                        allCorrect = false;
                        // Optionally, you can mark the incorrect cell in the UI
                        // For example, change the style of the Button
                    }
                }
            }
        }

        // Display result in a JavaFX Alert
        Alert alert = new Alert(allCorrect ? AlertType.INFORMATION : AlertType.ERROR);
        alert.setTitle("Submission Result");
        alert.setHeaderText(null);

        if (allCorrect) {
            alert.setContentText("Congratulations! All answers are correct.");
        } else {
            alert.setContentText("Some answers are incorrect. Please review your answers.");
        }

        // Show the alert and wait for the user to close it
        alert.showAndWait();
    }
}