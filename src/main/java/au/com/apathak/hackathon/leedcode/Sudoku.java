package au.com.apathak.hackathon.leedcode;

public class Sudoku {

  public static boolean solve(int i, int j, int[][] cells) {
    if (i == 9) {
      i = 0;
      if (++j == 9)
        return true;
    }
    if (cells[i][j] != 0)  // skip filled cells
      return solve(i + 1, j, cells);

    for (int val = 1; val <= 9; ++val) {
      if (legal(i, j, val, cells)) {
        cells[i][j] = val;
        if (solve(i + 1, j, cells))
          return true;
      }
    }
    cells[i][j] = 0; // reset on backtrack
    return false;
  }

  static boolean legal(int i, int j, int val, int[][] cells) {
    for (int k = 0; k < 9; ++k)  // row
      if (val == cells[k][j] || val == cells[i][k])
        return false;

    //      for (int k = 0; k < 9; ++k) // col
    //        if (val == cells[i][k])
    //          return false;

    int boxRowOffset = (i / 3) * 3;
    int boxColOffset = (j / 3) * 3;
    for (int k = 0; k < 3; ++k) // box
      for (int m = 0; m < 3; ++m)
        if (val == cells[boxRowOffset + k][boxColOffset + m])
          return false;

    return true; // no violations, so it's legal
  }

  //  public static boolean isValid(int i, int j, int val, int[][] board) {
  //
  //    for (int idx = 0; idx < 9; ++idx) {
  //      if (val == board[idx][j])
  //        return false;
  //      if (val == board[i][idx])
  //        return false;
  //    }
  //
  //    //    for(int col=0;col<9; ++col ){
  //    //    }
  //
  //    int row = (i / 3) * 3;
  //    int col = (j / 3) * 3;
  //    for (int m = 0; m < 3; m++) {
  //      for (int n = 0; n < 3; n++) {
  //        if (val == board[m + row][n + col])
  //          return false;
  //
  //      }
  //    }
  //    return true;
  //  }
  //
  //  public static boolean evaluateExpression(int i, int j, int[][] board) {
  //
  //    if (i == 9) {
  //      i = 0;
  //      if (++j == 9) {
  //        return true;
  //      }
  //    }
  //    if (board[i][j] != 0) {
  //      return evaluateExpression(i + i, j, board);
  //    }
  //
  //    for (int k = 1; k <= 9; ++k) {
  //      if (isValid(i, j, k, board)) {
  //        board[i][j] = k;
  //        if (evaluateExpression(i + 1, j, board)) {
  //          return true;
  //        }
  //      }
  //
  //    }
  //    board[i][j] = 0;
  //
  //    return false;
  //
  //  }

  public static void main(String[] args) {
    Sudoku sudoku = new Sudoku();

    //@formatter:off
    int[][] board = new int[][] {
          { 0, 8, 0, 4, 0, 2, 0, 6, 0 },
          { 0, 3, 4, 0, 0, 0, 9, 1, 0 },
          { 9, 6, 0, 0, 0, 0, 0, 8, 4 },
          { 0, 0, 0, 2, 1, 6, 0, 0, 0 },
          { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
          { 0, 0, 0, 3, 5, 7, 0, 0, 0 },
          { 8, 4, 0, 0, 0, 0, 0, 7, 5 },
          { 0, 2, 6, 0, 0, 0, 1, 3, 0 },
          { 0, 9, 0, 7, 0, 1, 0, 4, 0 }
    };
    //@formatter:on

    Sudoku.solve(0, 0, board);

    for (int i = 0; i < board.length; ++i) {
      for (int j = 0; j < board[0].length; ++j) {
        System.out.format(" %d ", board[i][j]);

      }
      System.out.println("");
    }
  }
}
/**
 * <pre>
 * -----------------------
 * |   8   | 4   2 |   6   |
 * |   3 4 |       | 9 1   |
 * | 9 6   |       |   8 4 |
 *  -----------------------
 * |       | 2 1 6 |       |
 * |       |       |       |
 * |       | 3 5 7 |       |
 *  -----------------------
 * | 8 4   |       |   7 5 |
 * |   2 6 |       | 1 3   |
 * |   9   | 7   1 |   4   |
 *  -----------------------
 * </pre>
 */