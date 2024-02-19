import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PuzzleController {
    
    PuzzleData data = PuzzleData.getInstance();
    
    private String difficulty;
    private int time;
    private int score;

    @FXML
    public void initialize() {
        difficulty = data.getDifficulty();
        time = 0;
        score = 0;
    }

    @FXML
    void onBoxClicked(ActionEvent event) {
        Button button = (Button) event.getSource();
        List<String> labels = Arrays.asList("", "O", "X");
        int currentIndex = labels.indexOf(button.getText());
        button.setText(labels.get((currentIndex + 1) % labels.size()));
    }
}
