// Arjenis Montenegro

/**
 * DinosaurPuzzle class represents a specific implementation of the Puzzle interface.
 * It provides functionality to generate answers and hints for a dinosaur-themed puzzle,
 * as well as methods to retrieve dinosaur names, prices, and ages.
 * 
 * This class includes a story setting the context for the puzzle and maintains correct 
 * answers, hints given, and other relevant puzzle data.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DinosaurPuzzle implements Puzzle {

    private List<String> dinosaurs = Arrays.asList("T-Rex", "Ankylosaurus", "Triceratops", "Brachiosaurus");
    private List<String> prices = Arrays.asList("$500", "$750", "$1000", "$1250");
    private List<String> ages = Arrays.asList("72 million", "75 million", "85 million", "90 million");
    private List<Boolean[][]> correctAnswers;
    private boolean[][] hintsGiven;
    private String story = "Mitch has a huge collection of dinosaur fossils, and has decided to list some for sale online. Match each fossil to its species, age, and country in which it was found, as well as the price Mitch is asking for it.";

    public List<String> getDinosaurs() {
        return this.dinosaurs;
    }

    public void setDinosaurs(List<String> dinosaurs) {
        this.dinosaurs = dinosaurs;
    }

    public List<String> getPrices() {
        return this.prices;
    }

    public void setPrices(List<String> prices) {
        this.prices = prices;
    }
    public void setAges(List<String> ages) {
        this.ages = ages;
    }
    public void setCorrectAnswers(List<Boolean[][]> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }
    public void setHintsGiven(boolean[][] hintsGiven) {
        this.hintsGiven = hintsGiven;
    }

    public String getStory() {
        return this.story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public DinosaurPuzzle() {
        generateAnswers();
        hintsGiven = new boolean[correctAnswers.size()][4];
    }

    @Override
    public List<Boolean[][]> generateAnswers() {
        correctAnswers = new ArrayList<>();
        // Left Grid
        correctAnswers.add(new Boolean[][] {
            {true, false, false, false},
            {false, false, false, true},
            {false, false, true, false},
            {false, true, false, false}
        });
        // Bottom Grid
        correctAnswers.add(new Boolean[][] {
            {false, false, false, true},
            {false, false, true, false},
            {true, false, false, false},
            {false, true, false, false}
        });
        // Right Grid
        correctAnswers.add(new Boolean[][] {
            {false, false, true, false},
            {true, false, false, false},
            {false, true, false, false},
            {false, false, false, true}
        });
        return correctAnswers;
    }

    @Override
    public List<String> generateHints() {
        return Arrays.asList(
            "1. The Triceratops costs more than the Brachiosaurus.",
            "2. The Brachiosaurus is either the $1250 fossil or the 72 million year old fossil.",
            "3. The $1000 fossil is either the T-Rex or the 75 million year old fossil.",
            "4. The 85 million year old fossil costs $250 less than the Brachiosaurus.",
            "5. The Ankylosaurus costs $1250."
        );
    }

    @Override
    public List<String> getNames() {
        return dinosaurs;
    }

    @Override
    public List<String> getNumbers() {
        return prices;
    }

    @Override
    public List<String> getAges() {
        return ages;
    }

    public List<Boolean[][]> getCorrectAnswers() {
        return correctAnswers;
    }

    public boolean[][] getHintsGiven() {
        return hintsGiven;
    }

    public void setHintGiven(int gridIndex, int rowIndex) {
        if(gridIndex < hintsGiven.length && rowIndex < hintsGiven[gridIndex].length) {
            hintsGiven[gridIndex][rowIndex] = true;
        }
    }

    public boolean isPositionCorrect(int gridIndex, int rowIndex, int colIndex) {
        if (gridIndex < 0 || gridIndex >= correctAnswers.size()) return false;
        Boolean[][] grid = correctAnswers.get(gridIndex);
        if (rowIndex < 0 || rowIndex >= grid.length) return false;
        if (colIndex < 0 || colIndex >= grid[rowIndex].length) return false;
        return Boolean.TRUE.equals(grid[rowIndex][colIndex]);
    }
}