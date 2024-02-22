import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Grid {
    
    private List<List<Boolean>> grid;

    public Grid() {}

    public Grid(List<GridPane> grid) {
        // Converts user's input into a readable grid
        int gridIndex = 0;
        for (GridPane gridPane : grid) {
            ObservableList<Node> buttons = gridPane.getChildren();
            for (Node node : buttons) {
                try {
                    Button button = (Button) node;
                    String text = button.getText();
                    int rowIndex = (GridPane.getRowIndex(node) == null) ? 0 : GridPane.getRowIndex(node);
                    int columnIndex = (GridPane.getColumnIndex(node) == null) ? 0 : GridPane.getColumnIndex(node);
                    switch (text) {
                        case "":
                            setValue(gridIndex, rowIndex, columnIndex, false);
                        case "O":
                            setValue(gridIndex, rowIndex, columnIndex, true);
                        case "X":
                            setValue(gridIndex, rowIndex, columnIndex, false);
                    }
                } catch (Exception e) {
                    break;
                }
            }
            gridIndex += 1;
        }
    }

    public void setValue(int box, int row, int col, boolean value) {
        return;
    }

    public void generateAnswers() {
        return;
    }

    public List<List<Boolean>> getGrid() {
        return this.grid;
    }

    public void setGrid(List<List<Boolean>> grid) {
        this.grid = grid;
    }

    @Override
    public String toString() {
        return "grid = " + getGrid();
    }
}
