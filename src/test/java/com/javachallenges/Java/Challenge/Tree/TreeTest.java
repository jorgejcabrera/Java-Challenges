package com.javachallenges.Java.Challenge.Tree;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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

  @Test
  public void inOrderShouldWorkOk() {
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
    Tree.inOrder(root);
  }

  @Test
  public void heightShouldWorkOk() {
    // given
    Node noder31 = new Node(7);
    Node noder21 = new Node(6);
    Node nodel22 = new Node(4);
    Node nodel21 = new Node(1);
    Node noder1 = new Node(5);
    Node nodel1 = new Node(2);
    Node root = new Node(3);

    noder21.right = noder31;
    noder1.right = noder21;
    noder1.left = nodel22;
    nodel1.left = nodel21;
    root.right = noder1;
    root.left = nodel1;

    // then
    assertThat(Tree.height(root), equalTo(3));
  }

  @Test
  public void postOrderTraversalShouldWorkOk() {
    // given
    Node nodeLR4 = new Node(4);
    Node nodeL3 = new Node(3);
    Node nodeR3 = new Node(6);
    Node nodeR2 = new Node(5);
    Node nodeR1 = new Node(2);
    Node root = new Node(1);

    nodeL3.right = nodeLR4;
    nodeR2.left = nodeL3;
    nodeR2.right = nodeR3;
    nodeR1.right = nodeR2;
    root.right = nodeR1;

    // then
    Tree.postOrder(root);
  }
}
