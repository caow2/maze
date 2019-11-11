import java.util.Arrays;

/*
 * A class to store pairs of grid coordinates for a maze (based on JavaFX Pair)
 * Note that order does not matter for Pair.
 * i.e. [x1, x2] and [x2, x1] are treated as the same pair to reduce redundancy.
 */
public class CoordinatePair {
  private int[] key, val; // coordinates are [x,y]

  public CoordinatePair(int[] k, int[] v) {
    this.key = k;
    this.val = v;
  }

  public int[] getKey() {
    return key;
  }

  public int[] getVal() {
    return val;
  }

  @Override
  public boolean equals(Object other) {
    if(! (other instanceof CoordinatePair))
      return false;
    CoordinatePair otherPair = (CoordinatePair) other;
    return Arrays.equals(key, otherPair.getKey()) && Arrays.equals(val, otherPair.getVal()) ||
           Arrays.equals(key, otherPair.getVal()) && Arrays.equals(val, otherPair.getKey());
  }
}
