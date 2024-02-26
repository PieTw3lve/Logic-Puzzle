import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;

import java.util.Arrays;
import java.util.List;

public class PuzzleController {

    private DinosaurPuzzle puzzle;

    @FXML
    private List<Text> answerPrices, answerDinosaurs, answerAges;

    
    private int hintCounter = 0;

    @FXML
    private Text cluesText;

    @FXML
    private GridPane grid1, grid2, grid3;

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
            setupGrid(grid1);
            setupGrid(grid2);
            setupGrid(grid3);
        }
    }

    private void displayHints() {
        List<String> hints = puzzle.generateHints();
        cluesText.setText(String.join("\n", hints));
    }

    private void updateTextFields() {
        List<String> dinosaurs = puzzle.getDinosaurs();
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
        List<String> hints = puzzle.generateHints();
        if (hintCounter < hints.size()) {
            cluesText.setText(hints.get(hintCounter));
            hintCounter++;
        } else {
            cluesText.setText("No more hints available.");
        }   
    }
    
    @FXML
    void clearErrors(ActionEvent event) {
        clearIncorrectAnswers(grid1, 0);
        clearIncorrectAnswers(grid2, 1);
        clearIncorrectAnswers(grid3, 2);
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
        resetGrid(grid1);
        resetGrid(grid2);
        resetGrid(grid3);
        displayHints();
    }

    private void resetGrid(GridPane grid) {
        grid.getChildren().forEach(node -> {
            if (node instanceof Button) {
                ((Button) node).setText("");
            }
        });
    }

    @FXML
    void submitAnswers(ActionEvent event) {
        switch (data.getGridSize()) {
            case "3x4 Grid":
                userInput = Arrays.asList(grid1, grid2, grid3);
                userGrid = new UserGrid(3, 4, 4);
                break;
        }
        userGrid.convertUserInputToGrid(userInput);
        System.out.println(userGrid.toString());
    }
}