package com.javachallenges.Java.Challenge.Tree;

public class Tree {

  public static void preOrder(Node node) {
    System.out.print(String.format("%s ",node.data));
    if (node.left != null) {
      preOrder(node.left);
    }
    if (node.right != null) {
      preOrder(node.right);
    }
  }
}
