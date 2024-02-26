// Eric Lim

/**
 * The PuzzleData class represents data related to a logic puzzle, including its information.
 * This class follows the Singleton design pattern, ensuring only one instance of PuzzleData exists
 * throughout the application.
 */

public class PuzzleData {
    
    private static final PuzzleData instance = new PuzzleData();

    private String gridSize;
    private String difficulty;

    private PuzzleData(){}

    public static PuzzleData getInstance() {
        return instance;
    }

    public String getGridSize() {
        return this.gridSize;
    }

    public void setGridSize(String gridSize) {
        this.gridSize = gridSize;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "{" +
            "gridSize='" + getGridSize() + "'" +
            ", difficulty='" + getDifficulty() + "'" +
            "}";
    }
}