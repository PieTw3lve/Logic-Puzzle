import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DinosaurPuzzle implements Puzzle {

    private List<String> dinosaurs = Arrays.asList("Archadon", "Mirasaurus", "Pilodontus", "Rotosaurus");
    private List<String> prices = Arrays.asList("$500", "$750", "$1000", "$1250");
    private List<String> ages = Arrays.asList("72 million", "75 million", "85 million", "90 million");
    private List<Boolean[][]> correctAnswers;

    public DinosaurPuzzle() {
        generateAnswers();
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
            "1. The Pilodontus costs more than the Rotosaurus.",
            "2. The Rotosaurus is either the $1250 fossil or the 72 million year old fossil.",
            "3. The $1000 fossil is either the Archadon or the 75 million year old fossil.",
            "4. The 85 million year old fossil costs $250 less than the Rotosaurus.",
            "5. The Mirasaurus costs $1250."
        );
    }

    @Override
    public List<String> getDinosaurs() {
        return dinosaurs;
    }

    @Override
    public List<String> getPrices() {
        return prices;
    }

    @Override
    public List<String> getAges() {
        return ages;
    }

    public boolean isPositionCorrect(int gridIndex, int rowIndex, int colIndex) {
        if (gridIndex < 0 || gridIndex >= correctAnswers.size()) return false;
        Boolean[][] grid = correctAnswers.get(gridIndex);
        if (rowIndex < 0 || rowIndex >= grid.length) return false;
        if (colIndex < 0 || colIndex >= grid[rowIndex].length) return false;
        return Boolean.TRUE.equals(grid[rowIndex][colIndex]);
    }
}