package com.javachallenges.Java.Challenge.LinkedList;

public class LinkedListDs {
  static SinglyLinkedListNode head;

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

  public static SinglyLinkedListNode deleteNode(SinglyLinkedListNode head, int position) {
    SinglyLinkedListNode before = head;
    SinglyLinkedListNode current = head;
    int i = 0;
    boolean cont = true;

    if (position == 0) return head.next;

    while (before != null && cont) {
      if (i == position) {
        before.next = current.next;
        current = null;
        cont = false;
      } else {
        before = current;
        current = current.next;
        i = i + 1;
      }
    }
    return head;
  }

  public static void reversePrint(SinglyLinkedListNode head) {
    if (head.next != null) reversePrint(head.next);
    System.out.println(head.data);
  }

  public static SinglyLinkedListNode reverse(SinglyLinkedListNode head) {
    return recursiveReverse(head, null);
  }

  private static SinglyLinkedListNode recursiveReverse(
      SinglyLinkedListNode curr, SinglyLinkedListNode prev) {
    if (curr.next == null) {
      head = curr;
      curr.next = prev;
      return head;
    }
    SinglyLinkedListNode next1 = curr.next;
    curr.next = prev;
    recursiveReverse(next1, curr);
    return head;
  }

  public static boolean compareLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
    if (head1 == null && head2 == null) return true;

    while (head1 != null && head2 != null) {
      if ((head1.data != head2.data)
          || (head1.next == null && head2.next != null)
          || (head2.next == null && head1.next != null)) return false;
      head1 = head1.next;
      head2 = head2.next;
    }
    return true;
  }

  public static SinglyLinkedListNode mergeLists(
      SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
    if (head1 == null && head2 == null) return null;
    if (head1 == null && head2 != null) return head2;
    if (head2 == null && head1 != null) return head1;
    SinglyLinkedListNode next;

    SinglyLinkedListNode newHead = head1.data <= head2.data ? head1 : head2;
    if (newHead == head1) head1 = head1.next;
    else head2 = head2.next;
    if (head1 == null) {
      next = head2;
      newHead.next = next;
      return newHead;
    } else if (head2 == null) {
      next = head1;
      newHead.next = next;
      return newHead;
    } else {
      next = head1.data <= head2.data ? head1 : head2;
      newHead.next = next;
    }
    while (head1 != null && head2 != null) {
      if (next == head1) head1 = head1.next;
      else head2 = head2.next;
      if (head1 == null) {
        next.next = head2;
      } else if (head2 == null) {
        next.next = head1;
      } else {
        next.next = head1.data <= head2.data ? head1 : head2;
      }
      next = next.next;
    }
    return newHead;
  }
}
