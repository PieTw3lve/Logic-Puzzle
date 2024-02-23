// Eric Lim

import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class PuzzleController {
    
    PuzzleData data = PuzzleData.getInstance();
    
    private UserGrid userGrid;
    private AnswerGrid answerGrid;
    private List<GridPane> userInput;
    private String gridSize = data.getGridSize();
    private String difficulty = data.getDifficulty();
    private int time = 0;
    private int score = 0;

    @FXML
    private GridPane grid1;

    @FXML
    private GridPane grid2;

    @FXML
    private GridPane grid3;


    @FXML
    public void initialize() {
        switch (data.getGridSize()) {
            case "3x4 Grid":
                answerGrid = new AnswerGrid(3, 4, 4);
        }
        answerGrid.generateAnswers();
        data.setAnswer(answerGrid.getPuzzleBoard());
        System.out.println(answerGrid.toString());
    }

    @FXML
    void onBoxClicked(ActionEvent event) {
        Button button = (Button) event.getSource();
        List<String> labels = Arrays.asList("", "O", "X");
        int currentIndex = labels.indexOf(button.getText());
        button.setText(labels.get((currentIndex + 1) % labels.size()));
    }

    @FXML
    void requestHint(ActionEvent event) {
        // requestHint() code here
    }

    @FXML
    void clearErrors(ActionEvent event) {
        // clearErrors() code here
    }

    @FXML
    void startOver(ActionEvent event) {
        // startOver() code here
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
