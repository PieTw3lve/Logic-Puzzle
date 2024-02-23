// Eric Lim

import java.util.List;

/**
 * The PuzzleData class represents data related to a logic puzzle, including its information.
 * This class follows the Singleton design pattern, ensuring only one instance of PuzzleData exists
 * throughout the application.
 */

public class PuzzleData {
    
    private static final PuzzleData instance = new PuzzleData();

    private String gridSize;
    private String difficulty;
    private String story;
    private List<String> clues;
    private List<Boolean[][]> answer; // Not entirely sure if this should be here

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

    public String getStory() {
        return this.story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public List<String> getClues() {
        return this.clues;
    }

    public void setClues(List<String> clues) {
        this.clues = clues;
    }

    @Override
    public String toString() {
        return "{" +
            "gridSize='" + getGridSize() + "'" +
            ", difficulty='" + getDifficulty() + "'" +
            ", story='" + getStory() + "'" +
            ", clues='" + getClues() + "'" +
            "}";
    }
}
