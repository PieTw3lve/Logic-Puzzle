// Arjenis Montenegro
// Sukhdeep Singh

/**
 * Puzzle interface defines methods for generating answers and hints 
 * for a puzzle, as well as retrieving names, numbers, and ages.
 */

import java.util.List;

public interface Puzzle {
    List<Boolean[][]> generateAnswers();
    List<String> generateHints();
    List<String> getNames();
    List<String> getNumbers();
    List<String> getAges();
}