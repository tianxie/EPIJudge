package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import jdk.jshell.Snippet;

public class LowestCommonAncestor {

  // 后序遍历
  // 时间复杂度 O(n)，空间复杂度 O(h)
  public static BinaryTreeNode<Integer> lca(BinaryTreeNode<Integer> tree,
                                            BinaryTreeNode<Integer> node0,
                                            BinaryTreeNode<Integer> node1) {
    return LCAHelper(tree, node0, node1).ancestor;
  }

  private static Status LCAHelper(BinaryTreeNode<Integer> tree,
                                  BinaryTreeNode<Integer> node0,
                                  BinaryTreeNode<Integer> node1) {
    if (tree == null) {
      return new Status(0, null);
    }

    Status leftStatus = LCAHelper(tree.left, node0, node1);
    if (leftStatus.numTargetNodes == 2) {
      // 两个节点都在左子树
      return leftStatus;
    }

    Status rightStatus = LCAHelper(tree.right, node0, node1);
    if (rightStatus.numTargetNodes == 2) {
      // 两个节点都在右子树
      return rightStatus;
    }

    int numTargetNodes = leftStatus.numTargetNodes + rightStatus.numTargetNodes
            + ((tree == node0) ? 1 : 0) + ((tree == node1) ? 1 : 0);
    return new Status(numTargetNodes, numTargetNodes == 2 ? tree : null);
  }

  private static class Status {
    public int numTargetNodes;
    public BinaryTreeNode<Integer> ancestor;

    public Status(int numTargetNodes, BinaryTreeNode<Integer> ancestor) {
      this.numTargetNodes = numTargetNodes;
      this.ancestor = ancestor;
    }
  }

  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor,
                               BinaryTreeNode<Integer> tree, Integer key0,
                               Integer key1) throws Exception {
    BinaryTreeNode<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTreeNode<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTreeNode<Integer> result =
        executor.run(() -> lca(tree, node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestor.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
