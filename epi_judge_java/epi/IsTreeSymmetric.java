package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeSymmetric {
  @EpiTest(testDataFile = "is_tree_symmetric.tsv")

  // 时间复杂度 O(n)，空间复杂度 O(h)
  public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
    return tree == null || checkSymmetric(tree.left, tree.right);
  }

  private static boolean checkSymmetric(BinaryTreeNode<Integer> tree1, BinaryTreeNode<Integer> tree2) {
    if (tree1 == null && tree2 == null) {
      return true;
    } else if (tree1 != null && tree2 != null) {
      return tree1.data == tree2.data
              && checkSymmetric(tree1.left, tree2.right)
              && checkSymmetric(tree1.right, tree2.left);
    }
    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeSymmetric.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
