// Eric Lim

/**
 * Grid class represents a collection of boolean grids.
 * Each grid is a two-dimensional array of boolean values, 
 * where each cell represents either true or false.
 * 
 * This class provides functionality to manipulate the grids, 
 * such as setting and getting values, changing the grid capacity,
 * and generating a string representation of the grids.
 */

import java.util.ArrayList;
import java.util.List;

public class Grid {

    private List<Boolean[][]> puzzleBoard;

    public Grid() {
        this.puzzleBoard = new ArrayList<>();
    }

    public Grid(int grids, int rows, int cols) {
        this.puzzleBoard = new ArrayList<>();
        changeCapacity(grids, rows, cols);
    }

    public void setValue(int gridIndex, int row, int col, boolean value) {
        try {
            this.puzzleBoard.get(gridIndex)[row][col] = value;
        } catch (Exception e) {
            return;
        }
    }

    public Boolean getValue(int gridIndex, int row, int col) {
        try {
            return this.puzzleBoard.get(gridIndex)[row][col];
        } catch (Exception e) {
            return null;
        }
    }

    public List<Boolean[][]> getPuzzleBoard() {
        return this.puzzleBoard;
    }

    public void setPuzzleBoard(List<Boolean[][]> puzzleBoard) {
        this.puzzleBoard = puzzleBoard;
    }

    public void changeCapacity(int totalGrids, int numRows, int numCols) {
        // Changes grid amount to totalGrids
        while (puzzleBoard.size() < totalGrids) {
            puzzleBoard.add(new Boolean[numRows][numCols]);
        }
    
        // Changes grid rows and columns to specified amount
        for (int i = 0; i < puzzleBoard.size(); i++) {
            Boolean[][] grid = puzzleBoard.get(i);
    
            // Ensure the row has enough capacity
            if (numRows > grid.length) {
                Boolean[][] newRows = new Boolean[numRows][];
                System.arraycopy(grid, 0, newRows, 0, grid.length);
                for (int j = grid.length; j < numRows; j++) {
                    newRows[j] = new Boolean[numCols];
                }
                puzzleBoard.set(i, newRows);
            }
    
            // Ensure the column has enough capacity
            for (int row = 0; row < numRows; row++) {
                if (numCols > grid[row].length) {
                    Boolean[] newCols = new Boolean[numCols];
                    System.arraycopy(grid[row], 0, newCols, 0, grid[row].length);
                    grid[row] = newCols;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < puzzleBoard.size(); i++) {
            sb.append("Grid ").append(i).append(":\n");
            Boolean[][] grid = puzzleBoard.get(i);
            for (Boolean[] row : grid) {
                for (Boolean cell : row) {
                    sb.append(cell != null && cell ? "O " : "X ");
                }
                sb.append("\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}