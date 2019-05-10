package com.javachallenges.Java.Challenge.Tree;

import org.junit.Test;

public class TreeTest {

  @Test
  public void preOrderShouldWorkOk() {
    // given
    Node r4 = new Node(4);
    Node l3 = new Node(3);
    l3.right = r4;
    Node r3 = new Node(6);
    Node r2 = new Node(5);
    r2.right = r3;
    r2.left = l3;
    Node r1 = new Node(2);
    r1.right = r2;
    Node root = new Node(1);
    root.right = r1;

    // then
    Tree.preOrder(root);
  }
}
