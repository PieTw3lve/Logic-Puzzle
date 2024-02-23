// Eric Lim

public class AnswerGrid extends Grid {

    public AnswerGrid() {
        super();
    }

    public AnswerGrid(int grids, int rows, int cols) {
        super(grids, rows, cols);
    }

    public void generateAnswers() {
        for (int i = 0; i < 3; i++) {
            int maxRow = getPuzzleBoard().get(i).length;
            int maxCol = getPuzzleBoard().get(i)[0].length;
            
            // Track which rows and columns already have a true value
            boolean[] rowsUsed = new boolean[maxRow];
            boolean[] colsUsed = new boolean[maxCol];
            
            // Set 4 true values randomly ensuring only one true value per row and column
            for (int count = 0; count < 4; count++) {
                int row, col;
                do {
                    row = (int) (Math.random() * maxRow);
                    col = (int) (Math.random() * maxCol);
                } while (getPuzzleBoard().get(i)[row][col] != null || rowsUsed[row] || colsUsed[col]);
                
                setValue(i, row, col, true);
                rowsUsed[row] = true;
                colsUsed[col] = true;
            }
        }
    }
}
