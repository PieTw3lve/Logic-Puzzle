// Eric Lim

import java.util.ArrayList;
import java.util.List;

public class Grid {
    
    private List<Boolean[][]> grids;

    public Grid() {
        grids = new ArrayList<>();
    }

    public void setValue(int gridIndex, int row, int col, boolean value) {
        try {
            this.grids.get(gridIndex)[row][col] = value;
        } catch (Exception e) {
            return;
        }
    }

    public Boolean getValue(int gridIndex, int row, int col) {
        try {
            return this.grids.get(gridIndex)[row][col];
        } catch (Exception e) {
            return null;
        }
    }

    public List<Boolean[][]> getGrid() {
        return this.grids;
    }

    public void setGrid(List<Boolean[][]> grids) {
        this.grids = grids;
    }

    public void ensureCapacity(int gridIndex, int row, int col) {
        // Ensure the grids list has enough capacity
        while (grids.size() <= gridIndex) {
            grids.add(new Boolean[1][1]);
        }
        
        Boolean[][] grid = grids.get(gridIndex);
        
        // Ensure the row has enough capacity
        if (row >= grid.length) {
            Boolean[][] newRows = new Boolean[row + 1][];
            System.arraycopy(grid, 0, newRows, 0, grid.length);
            for (int i = grid.length; i <= row; i++) {
                newRows[i] = new Boolean[1];
            }
            grid = newRows;
            grids.set(gridIndex, grid);
        }
        
        // Ensure the column has enough capacity
        if (col >= grid[row].length) {
            Boolean[] newCols = new Boolean[col + 1];
            System.arraycopy(grid[row], 0, newCols, 0, grid[row].length);
            grid[row] = newCols;
        }
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
