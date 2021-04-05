package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeBalanced {

  @EpiTest(testDataFile = "is_tree_balanced.tsv")

  // 后序遍历
  // 时间复杂度 O(n)，空间复杂度 O(h)
  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    return checkBalanced(tree).balanced;
  }

  private static BalanceStatusWithHeight checkBalanced(BinaryTreeNode<Integer> tree) {
    if (tree == null) {
      return new BalanceStatusWithHeight(true, -1);
    }

    BalanceStatusWithHeight leftStatus = checkBalanced(tree.left);
    if (!leftStatus.balanced) {
      return leftStatus;
    }

    BalanceStatusWithHeight rightStatus = checkBalanced(tree.right);
    if (!rightStatus.balanced) {
      return rightStatus;
    }

    boolean isBalanced = Math.abs(leftStatus.height - rightStatus.height) <= 1;
    int height = Math.max(leftStatus.height, rightStatus.height) + 1;
    return new BalanceStatusWithHeight(isBalanced, height);
  }

  private static class BalanceStatusWithHeight {
    public boolean balanced;
    public int height;

    public BalanceStatusWithHeight(boolean balanced, int height) {
      this.balanced = balanced;
      this.height = height;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
