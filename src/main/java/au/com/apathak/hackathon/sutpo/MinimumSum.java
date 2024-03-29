package au.com.apathak.hackathon.sutpo;

/**
 *
 */
public class MinimumSum {


  public static int minimum(Range ranges[]) {

    assert (ranges != null && ranges.length > 1);
    int min = 0;

    for (int counter = 1; counter < ranges.length; ++counter) {
      min += minimum(ranges[counter], ranges[counter - 1]);
    }

    return min;
  }

  public static int minimum(Range range1, Range range2) {

    boolean inRange = range2.inRange(range1.high) || range2.inRange(range1.low) || range1.inRange(range2.low) || range1.inRange(range2.high);
    if (inRange) {
      return 0;
    }

    return Math.min(Math.abs(range1.high - range2.low), Math.abs(range2.high - range1.low));

  }

//  [low,high] -- [low1,high1]--- [low2,high2]

  public static void main(String[] args) {

    Range range1 = new Range(-100, -10);
    Range range2 = new Range(-1, 7);
    Range range3 = new Range(10, 100);

//    Range range1 = new Range(201, 700);
//    Range range2 = new Range(-100, 200);
//    Range range3 = new Range(-2, 0);
    Range range4 = new Range(-10, -5);
    Range range5 = new Range(Integer.MIN_VALUE, Integer.MAX_VALUE);
    Range range6 = new Range(Integer.MIN_VALUE, 0);
    Range range7 = new Range(1, Integer.MAX_VALUE);

//    Range[] ranges = new Range[] { range1, range2, range3, range4, range5, range6,range7 };
    Range[] ranges = new Range[] { range1, range2, range3 };

    System.out.format("%n%d", minimum(ranges));
  }

  public static class Range {
    int low, high;

    public Range(int low, int high) {
      assert (low < high);
      this.high = high;
      this.low = low;
    }

    public int getLow() {
      return low;
    }

    public void setLow(int low) {
      this.low = low;
    }

    public int getHigh() {
      return high;
    }

    public void setHigh(int high) {
      this.high = high;
    }

    public boolean inRange(int value) {
      return low <= value && value <= high;
    }
  }
}
