// Eric Lim
// Stores data so it can be stored and accessed across JavaFX scenes.

public class PuzzleData {
    
    private static final PuzzleData instance = new PuzzleData();

    private String difficulty;

    private PuzzleData(){}

    public static PuzzleData getInstance() {
        return instance;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

}
