// Eric Lim

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Grid {
    
    private List<Boolean[][]> grids;

    public Grid() {
        grids = new ArrayList<>();
    }

    public Grid(List<GridPane> grid) {
        // Converts user's input into a readable grid
        grids = new ArrayList<>();
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

    public void setValue(int gridIndex, int row, int col, boolean value) {
        // Checks if the grids list has enough capacity
        // If not add another grid
        while (grids.size() <= gridIndex) {
            grids.add(new Boolean[1][1]);
        }
        
        Boolean[][] grid = grids.get(gridIndex);
        
        // Checks if the row has enough capacity
        // If not create another row
        if (row >= grid.length) {
            Boolean[][] newRows = new Boolean[row + 1][];
            System.arraycopy(grid, 0, newRows, 0, grid.length);
            for (int i = grid.length; i <= row; i++) {
                newRows[i] = new Boolean[1];
            }
            grid = newRows;
            grids.set(gridIndex, grid);
        }
        
        // Checks if the column has enough capacity
        // If not create another column
        if (col >= grid[row].length) {
            Boolean[] newCols = new Boolean[col + 1];
            System.arraycopy(grid[row], 0, newCols, 0, grid[row].length);
            grid[row] = newCols;
        }
        
        // Set the value at the specified position
        this.grids.get(gridIndex)[row][col] = value;
    }

    public Boolean getValue(int gridIndex, int row, int col) {
        try {
            return this.grids.get(gridIndex)[row][col];
        } catch (Exception e) {
            return null;
        }
    }

    public void generateAnswers() {
        // generateAnswers() code here
    }

    public List<Boolean[][]> getGrid() {
        return this.grids;
    }

    public void setGrid(List<Boolean[][]> grids) {
        this.grids = grids;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < grids.size(); i++) {
            sb.append("Grid ").append(i).append(":\n");
            Boolean[][] grid = grids.get(i);
            for (int j = 0; j < grid.length; j++) {
                for (int k = 0; k < grid[j].length; k++) {
                    if (grid[j][k] != null && grid[j][k]) {
                        sb.append("O");
                    } else {
                        sb.append("X");
                    }
                    sb.append(" ");
                }
                sb.append("\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
