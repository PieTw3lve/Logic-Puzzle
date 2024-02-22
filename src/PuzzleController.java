// Eric Lim

import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class PuzzleController {
    
    PuzzleData data = PuzzleData.getInstance();
    
    private String gridSize = data.getGridSize();
    private String difficulty = data.getDifficulty();
    private int time = 0;
    private int score = 0;
//edhgrthetwhrthrhrwerthrewth
    @FXML
    private GridPane grid1;

    @FXML
    private GridPane grid2;

    @FXML
    private GridPane grid3;


    @FXML
    public void initialize() {
        Grid answerGrid = new Grid();
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
        return;
    }

    @FXML
    void clearErrors(ActionEvent event) {
        return;
    }

    @FXML
    void startOver(ActionEvent event) {
        return;
    }

    @FXML
    void submitAnswers(ActionEvent event) {
        List<GridPane> gridPanes = Arrays.asList(grid1, grid2, grid3);
        Grid grid = new Grid(gridPanes);
    }
}
