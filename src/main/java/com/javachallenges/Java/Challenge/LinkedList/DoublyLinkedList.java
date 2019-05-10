package com.javachallenges.Java.Challenge.LinkedList;

public class DoublyLinkedList {

  public static DoublyLinkedListNode sortedInsert(DoublyLinkedListNode head, int data) {
    DoublyLinkedListNode newNode = new DoublyLinkedListNode(data);
    if (head == null) return newNode;
    if (head.data > data) {
      head.prev = newNode;
      newNode.next = head;
      return newNode;
    }
    DoublyLinkedListNode current = head;
    DoublyLinkedListNode next = current.next;
    while (current != null) {
      if (next == null) {
        current.next = newNode;
        newNode.prev = current;
      } else {
        if (next.data > data) {
          current.next = newNode;
          newNode.prev = current;
          newNode.next = next;
          next.prev = newNode;
          break;
        }
      }
      current = next;
      next = current != null ? current.next : null;
    }
    return head;
  }

  public static DoublyLinkedListNode reverse(DoublyLinkedListNode head) {
    if (head == null || head.next == null) return head;
    DoublyLinkedListNode newHead = null;
    while (head.next != null) {
      newHead = reverseNode(head, newHead);
      head = head.next;
    }
    return newHead;
  }

  private static DoublyLinkedListNode reverseNode(
      DoublyLinkedListNode head, DoublyLinkedListNode lastHead) {
    DoublyLinkedListNode tail = null;
    if (lastHead != null) {
      tail = lastHead;
    } else {
      tail = new DoublyLinkedListNode(head.data);
    }
    DoublyLinkedListNode newHead = new DoublyLinkedListNode(head.next.data);
    newHead.next = tail;
    tail.prev = newHead;
    return newHead;
  }
}
