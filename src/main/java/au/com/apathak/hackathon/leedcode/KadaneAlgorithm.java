package au.com.apathak.hackathon.leedcode;

public class KadaneAlgorithm {

  public static void main(String[] args) {

    int[] array = { 4, 5, -6, 1, -4, -8, 9, 6, -9, 9, 6, -4, 7, 2 };

    int max_ending_at = array[0];
    int max_so_far = max_ending_at;
    for (int i = 1; i < array.length; ++i) {
      max_ending_at = Math.max(array[i], max_ending_at + array[i]);
      max_so_far = Math.max(max_so_far, max_ending_at);
    }

    System.out.printf("%d", max_so_far);
  }
}
