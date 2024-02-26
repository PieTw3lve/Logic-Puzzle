// Arjenis Montenegro
// Sukhdeep Singh

import java.util.List;

public interface Puzzle {
    List<Boolean[][]> generateAnswers();
    List<String> generateHints();
    List<String> getNames();
    List<String> getNumbers();
    List<String> getAges();
}