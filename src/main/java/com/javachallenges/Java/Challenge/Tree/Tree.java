package com.javachallenges.Java.Challenge.Tree;

public class Tree {

  /**
   * It must print the values in the tree's preorder traversal as a single line of space-separated
   * values
   *
   * @param node
   */
  public static void preOrder(Node node) {
    System.out.print(String.format("%s ", node.data));
    if (node.left != null) {
      preOrder(node.left);
    }
    if (node.right != null) {
      preOrder(node.right);
    }
  }

  /**
   * It must print the values in the tree's inorder traversal as a single line of space-separated
   * values
   *
   * @param node
   */
  public static void inOrder(Node node) {
    if (node == null) {
      return;
    }
    inOrder(node.left);
    System.out.printf("%s ", node.data);
    inOrder(node.right);
  }

  /**
   * @param root of tree
   * @return The height of a binary tree is the number of edges between the tree's root and its
   *     furthest leaf
   */
  public static int height(Node root) {
    int left = 0;
    int right = 0;
    if (root.left != null) left = height(root.left, left + 1);
    if (root.right != null) right = height(root.right, right + 1);
    return left > right ? left : right;
  }

  /**
   * @param root It must print the values in the tree's postorder traversal as a single line of
   *     space-separated values.
   */
  public static void postOrder(Node root) {
    if (root.left != null) postOrder(root.left);
    if (root.right != null) postOrder(root.right);
    System.out.print(String.format("%d ", root.data));
  }

  public static void topView(Node root) {
    class QueueObj {
      Node node;
      int hd;

      QueueObj(Node node, int hd) {
        this.node = node;
        this.hd = hd;
      }
    }
    java.util.Queue<QueueObj> q = new java.util.LinkedList<QueueObj>();
    java.util.Map<Integer, Node> topViewMap = new java.util.TreeMap<Integer, Node>();
    if (root == null) {
      return;
    } else {
      q.add(new QueueObj(root, 0));
    }
    while (!q.isEmpty()) {
      QueueObj tmpNode = q.poll();
      if (!topViewMap.containsKey(tmpNode.hd)) {
        topViewMap.put(tmpNode.hd, tmpNode.node);
      }
      if (tmpNode.node.left != null) {
        q.add(new QueueObj(tmpNode.node.left, tmpNode.hd - 1));
      }
      if (tmpNode.node.right != null) {
        q.add(new QueueObj(tmpNode.node.right, tmpNode.hd + 1));
      }
    }
    for (java.util.Map.Entry<Integer, Node> entry : topViewMap.entrySet()) {
      System.out.print(String.format("%d ", entry.getValue().data));
    }
  }

  private static int height(Node node, int height) {
    int left = height;
    int right = height;
    if (node.left != null) {
      left = height(node.left, height + 1);
    }
    if (node.right != null) {
      right = height(node.right, height + 1);
    }
    return left > right ? left : right;
  }
}
