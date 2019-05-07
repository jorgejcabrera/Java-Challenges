package com.javachallenges.Java.Challenge;

public class LinkedListDs {

  public static SinglyLinkedListNode insertNodeAtPosition(
      SinglyLinkedListNode head, int data, int position) {
    SinglyLinkedListNode node = new SinglyLinkedListNode(data);
    SinglyLinkedListNode before = head;
    SinglyLinkedListNode current = head;

    if (position == 0) {
      node.next = head;
      return node;
    }
    int i = 0;
    boolean cont = true;
    while (before != null && cont) {
      if (i == position) {
        node.next = before.next;
        before.next = node;
        cont = false;
      } else {
        before = current;
        current = current.next;
        i = i + 1;
      }
    }
    return position == 1 ? current : head;
  }
}
