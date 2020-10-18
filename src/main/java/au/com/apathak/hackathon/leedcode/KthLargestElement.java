package au.com.apathak.hackathon.leedcode;

import java.util.Arrays;

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * <p>
 * Example 2:
 * <p>
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 * <p>
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class KthLargestElement {

  /**
   * Lame approach.
   *
   * @param input
   * @param k
   * @return
   */
  public static int findKthElementBasic(int[] input, int k) {

    Arrays.sort(input);
    return input[input.length - k];
  }

  /**
   * Almost sorting the array, but pushing the numbers in array.
   * @param item
   * @param top
   */
  public static void arrangeItem(int item, int[] top) {
    for (int i = 0; i < top.length; ++i) {
      if (top[i] <= item) {
        for (int j = top.length - 1; j >= i + 1; --j) {
          top[j] = top[j - 1];
        }
        top[i] = item;
        break;
      }
    }
  }

  /**
   *
   * @param input
   * @param k
   * @return
   */
  public static int findKthElement(int[] input, int k) {

    int[] top = new int[k];
    Arrays.fill(top, Integer.MIN_VALUE);
    for (int i : input) {
      arrangeItem(i, top);
    }

    return top[top.length - 1];
  }

  public static void main(String[] args) {
    int k = 1;
    System.out.println(findKthElement(new int[] { 3, 2, 3, 1, 2, 4, 5, 5, 6 }, 4));


  }
}
