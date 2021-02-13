package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class Parity {
  @EpiTest(testDataFile = "parity.tsv")

//  // O(n), n是字长
//  public static short parity(long x) {
//    short result = 0;
//    while (x != 0) {
//      result ^= (x & 1);
//      x >>>= 1;
//    }
//    return result;
//  }

//  // O(k), k是1出现的次数
//  public static short parity(long x) {
//    short result = 0;
//    while (x != 0) {
//      result ^= 1;
//      x &= (x - 1); // 清除最低位的1
//    }
//    return result;
//  }

//  public static short parity(long x) {
//    final int W0RD_SIZE = 16;
//    final int BIT_MASK = 0xFFFF;
//    return (short) (
//            precomputedParity[(int) ((x >>> (3 * W0RD_SIZE)) & BIT_MASK)]
//                    ^ precomputedParity[(int) ((x >>> (2 * W0RD_SIZE)) & BIT_MASK)]
//                    ^ precomputedParity[(int) ((x >>> W0RD_SIZE) & BIT_MASK)]
//                    ^ precomputedParity[(int) (x & BIT_MASK)]);
//  }

  public static short parity(long x) {
    x ^= x >>> 32;
    x ^= x >>> 16;
    x ^= x >>> 8;
    x ^= x >>> 4;
    x ^= x >>> 2;
    x ^= x >>> 1;
    return (short) (x & 0x1);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Parity.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
