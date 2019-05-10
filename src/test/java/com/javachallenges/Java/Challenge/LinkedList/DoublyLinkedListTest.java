package com.javachallenges.Java.Challenge.LinkedList;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DoublyLinkedListTest {

  @Test
  public void sortedInsertShouldWorkOk() {
    // given
    DoublyLinkedListNode third = new DoublyLinkedListNode(10);
    DoublyLinkedListNode second = new DoublyLinkedListNode(4);
    DoublyLinkedListNode first = new DoublyLinkedListNode(3);
    DoublyLinkedListNode head = new DoublyLinkedListNode(1);
    head.next = first;
    first.prev = head;
    first.next = second;
    second.prev = first;
    second.next = third;
    third.prev = second;

    // when
    head = DoublyLinkedList.sortedInsert(head, 5);

    // then
    assertThat(head.next.next.next.data, equalTo(5));
    assertThat(head.next.next.next.next.prev.data, equalTo(5));
  }

  @Test
  public void insertNodeAtTailShouldWorkOk() {
    // given
    DoublyLinkedListNode third = new DoublyLinkedListNode(10);
    DoublyLinkedListNode second = new DoublyLinkedListNode(4);
    DoublyLinkedListNode first = new DoublyLinkedListNode(3);
    DoublyLinkedListNode head = new DoublyLinkedListNode(1);
    head.next = first;
    first.prev = head;
    first.next = second;
    second.prev = first;
    second.next = third;
    third.prev = second;

    // when
    head = DoublyLinkedList.sortedInsert(head, 11);

    // then
    assertThat(head.next.next.next.data, equalTo(10));
    assertThat(head.next.next.next.next.prev.data, equalTo(10));
    assertThat(head.next.next.next.next.data, equalTo(11));
  }

  @Test
  public void insertNodeAtHeadShouldWorkOk() {
    // given
    DoublyLinkedListNode third = new DoublyLinkedListNode(10);
    DoublyLinkedListNode second = new DoublyLinkedListNode(4);
    DoublyLinkedListNode first = new DoublyLinkedListNode(3);
    DoublyLinkedListNode head = new DoublyLinkedListNode(2);
    head.next = first;
    first.prev = head;
    first.next = second;
    second.prev = first;
    second.next = third;
    third.prev = second;

    // when
    head = DoublyLinkedList.sortedInsert(head, 1);

    // then
    assertThat(head.data, equalTo(1));
    assertThat(head.next.data, equalTo(2));
    assertThat(head.next.next.next.next.data, equalTo(10));
  }

  @Test
  public void reverseDoublyLinkedListShouldWorkOk() {
    // given
    DoublyLinkedListNode third = new DoublyLinkedListNode(4);
    DoublyLinkedListNode second = new DoublyLinkedListNode(3);
    DoublyLinkedListNode first = new DoublyLinkedListNode(2);
    DoublyLinkedListNode head = new DoublyLinkedListNode(1);
    head.next = first;
    first.prev = head;
    first.next = second;
    second.prev = first;
    second.next = third;
    third.prev = second;

    // when
    head = DoublyLinkedList.reverse(head);

    // then
    assertThat(head.data, equalTo(4));
    assertThat(head.next.data, equalTo(3));
    assertThat(head.next.next.next.data, equalTo(1));
  }
}
