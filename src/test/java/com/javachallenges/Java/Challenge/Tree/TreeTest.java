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

  @Test
  public void topViewShouldWorkOk() {
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
    Tree.topView(root);
  }

  @Test
  public void topLevelOrderShouldWorkOk() {
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
    Tree.levelOrder(root);
  }

  @Test
  public void insertShouldWorkOk() {
    // given
    Node nodeLR2 = new Node(3);
    Node nodeLL2 = new Node(1);
    Node nodeR1 = new Node(7);
    Node nodeL1 = new Node(2);
    Node root = new Node(4);

    nodeL1.right = nodeLR2;
    nodeL1.left = nodeLL2;
    root.left = nodeL1;
    root.right = nodeR1;

    // when
    Tree.insert(root, 6);

    //
    assertThat(root.left.data, equalTo(2));
    assertThat(root.left.left.data, equalTo(1));
    assertThat(root.left.right.data, equalTo(3));
    assertThat(root.right.data, equalTo(7));
    assertThat(root.right.left.data, equalTo(6));  }

  @Test
  public void manyInsertionsShouldWorkOk() {
    // given
    Node root = null;

    // when
    root = Tree.insert(root, 4);
    root = Tree.insert(root, 2);
    root = Tree.insert(root, 3);
    root = Tree.insert(root, 1);
    root = Tree.insert(root, 7);
    root = Tree.insert(root, 6);

    // then
    assertThat(root.left.data, equalTo(2));
    assertThat(root.left.left.data, equalTo(1));
    assertThat(root.left.right.data, equalTo(3));
    assertThat(root.right.data, equalTo(7));
    assertThat(root.right.left.data, equalTo(6));
  }
}
