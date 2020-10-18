package au.com.apathak.hackathon.leedcode;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 * <p>
 * <p>
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
 * <p>
 * Example:
 * <p>
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 */
public class TrappingRainWater {

  public static void main(String[] args) {
    int sum = trap(new int[] { 0,1,0,1,3,0,2,0,4  });
    System.out.println(sum);
  }

  public static int trap(int[] trap) {

    int maxIdx = 0, max = Integer.MIN_VALUE;

    for (int i = 0; i < trap.length; ++i) {
      if (max < trap[i]) {
        max = trap[i];
        maxIdx = i;
      }
    }

    int left = trap[0], right = trap[trap.length - 1], sum = 0;
    for (int i = 1; i < maxIdx; ++i) {

      if (left < trap[i]) {
        left = trap[i];
      } else {
        sum += left - trap[i];
      }
    }

    for (int i = trap.length - 2; i > maxIdx; i--) {
      if (right < trap[i]) {
        right = trap[i];
      } else {
        sum += right - trap[i];
      }
    }

    return sum;

  }

}




