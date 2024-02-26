// Eric Lim
// Sukhdeep Singh

/**
 * UserGrid class represents a specialized grid for user input.
 * It extends the Grid class and provides functionality to convert 
 * user input represented by JavaFX GridPane objects into boolean grids.
 * 
 * This class inherits methods from the Grid class to manipulate the grids 
 * and adds a method to convert user input from JavaFX GridPane objects.
 */

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import java.util.List;

public class UserGrid extends Grid {

    public UserGrid() {
        super();
    }    
    
    public UserGrid(int grids, int rows, int cols) {
        super(grids, rows, cols);
    }

    public void convertUserInputToGrid(List<GridPane> gridPanes) {
        for (int gridIndex = 0; gridIndex < gridPanes.size(); gridIndex++) {
            GridPane gridPane = gridPanes.get(gridIndex);
            for (Node node : gridPane.getChildren()) {
                if (node instanceof Button) {
                    Button button = (Button) node;
                    int row = GridPane.getRowIndex(node) != null ? GridPane.getRowIndex(node) : 0;
                    int col = GridPane.getColumnIndex(node) != null ? GridPane.getColumnIndex(node) : 0;
                    boolean value = "O".equals(button.getText());
                    this.setValue(gridIndex, row, col, value);
                }
            }
        }
    }
}