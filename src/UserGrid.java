// Eric Lim
// Sukhdeep Singh

import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class UserGrid extends Grid {

    public UserGrid() {
        super();
    }

    public UserGrid(int grids, int rows, int cols) {
        super(grids, rows, cols);
    }

    public void convertUserInputToGrid(List<GridPane> gridPanes) {
        int gridIndex = 0;
        for (GridPane gridPane : gridPanes) {
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
                            break;
                        case "O":
                            setValue(gridIndex, rowIndex, columnIndex, true);
                            break;
                        case "X":
                            setValue(gridIndex, rowIndex, columnIndex, false);
                            break;
                    }
                } catch (Exception e) {
                    break;
                }
            }
            gridIndex += 1;
        }
    }
}
