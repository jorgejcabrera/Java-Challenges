package com.javachallenges.Java.Challenge.LinkedList;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class LinkedListDsTest {

  @Test
  public void insertNodeAtPositionShouldWorkOk() {
    // given
    SinglyLinkedListNode second = new SinglyLinkedListNode(7);
    second.next = null;
    SinglyLinkedListNode first = new SinglyLinkedListNode(13);
    first.next = second;
    SinglyLinkedListNode head = new SinglyLinkedListNode(16);
    head.next = first;

    // when
    head = LinkedListDs.insertNodeAtPosition(head, 1, 2);

    // then
    assertThat(head.data, equalTo(16));
    assertThat(head.next.data, equalTo(13));
    assertThat(head.next.next.data, equalTo(1));
    assertThat(head.next.next.next.data, equalTo(7));
  }

  @Test
  public void insertNodeAtLastPositionShouldWorkOk() {
    // given
    SinglyLinkedListNode second = new SinglyLinkedListNode(7);
    second.next = null;
    SinglyLinkedListNode first = new SinglyLinkedListNode(13);
    first.next = second;
    SinglyLinkedListNode head = new SinglyLinkedListNode(16);
    head.next = first;

    // when
    head = LinkedListDs.insertNodeAtPosition(head, 1, 3);

    // then
    assertThat(head.data, equalTo(16));
    assertThat(head.next.data, equalTo(13));
    assertThat(head.next.next.data, equalTo(7));
    assertThat(head.next.next.next.data, equalTo(1));
  }

  @Test
  public void insertNodeAtFirstPositionShouldWorkOk() {
    // given
    SinglyLinkedListNode second = new SinglyLinkedListNode(7);
    second.next = null;
    SinglyLinkedListNode first = new SinglyLinkedListNode(13);
    first.next = second;
    SinglyLinkedListNode head = new SinglyLinkedListNode(16);
    head.next = first;

    // when
    head = LinkedListDs.insertNodeAtPosition(head, 1, 0);

    // then
    assertThat(head.data, equalTo(1));
    assertThat(head.next.data, equalTo(16));
    assertThat(head.next.next.data, equalTo(13));
    assertThat(head.next.next.next.data, equalTo(7));
  }

  @Test
  public void insertNodeAtEmptyListShouldWorkOk() {
    // given
    SinglyLinkedListNode head = null;

    // when
    head = LinkedListDs.insertNodeAtPosition(head, 1, 0);

    // then
    assertThat(head.data, equalTo(1));
    assertThat(head.next, equalTo(null));
  }

  @Test
  public void deleteNodeAtPositionShouldWorkOk() {
    // given
    SinglyLinkedListNode second = new SinglyLinkedListNode(2);
    second.next = null;
    SinglyLinkedListNode first = new SinglyLinkedListNode(6);
    first.next = second;
    SinglyLinkedListNode head = new SinglyLinkedListNode(20);
    head.next = first;

    // when
    head = LinkedListDs.deleteNode(head, 1);

    // then
    assertThat(head.data, equalTo(20));
    assertThat(head.next.data, equalTo(2));
  }

  @Test
  public void deleteNodeAtFirstPositionShouldWorkOk() {
    // given
    SinglyLinkedListNode second = new SinglyLinkedListNode(2);
    second.next = null;
    SinglyLinkedListNode first = new SinglyLinkedListNode(6);
    first.next = second;
    SinglyLinkedListNode head = new SinglyLinkedListNode(20);
    head.next = first;

    // when
    head = LinkedListDs.deleteNode(head, 0);

    // then
    assertThat(head.data, equalTo(6));
    assertThat(head.next.data, equalTo(2));
  }

  @Test
  public void deleteNodeAtLastPositionShouldWorkOk() {
    // given
    SinglyLinkedListNode second = new SinglyLinkedListNode(2);
    second.next = null;
    SinglyLinkedListNode first = new SinglyLinkedListNode(6);
    first.next = second;
    SinglyLinkedListNode head = new SinglyLinkedListNode(20);
    head.next = first;

    // when
    head = LinkedListDs.deleteNode(head, 2);
    // then
    assertThat(head.data, equalTo(20));
    assertThat(head.next.data, equalTo(6));
    assertThat(head.next.next, equalTo(null));
  }

  @Test
  public void printReverse() {
    // given
    SinglyLinkedListNode second = new SinglyLinkedListNode(2);
    second.next = null;
    SinglyLinkedListNode first = new SinglyLinkedListNode(6);
    first.next = second;
    SinglyLinkedListNode head = new SinglyLinkedListNode(20);
    head.next = first;

    LinkedListDs.reversePrint(head);
  }

  @Test
  public void reverseLinkedList() {
    // given
    SinglyLinkedListNode second = new SinglyLinkedListNode(2);
    second.next = null;
    SinglyLinkedListNode first = new SinglyLinkedListNode(6);
    first.next = second;
    SinglyLinkedListNode head = new SinglyLinkedListNode(20);
    head.next = first;

    // when
    head = LinkedListDs.reverse(head);

    // then
    assertThat(head.data, equalTo(2));
    assertThat(head.next.data, equalTo(6));
    assertThat(head.next.next.data, equalTo(20));
  }

  @Test
  public void compareLinkedListShouldWorkOk() {
    // given
    SinglyLinkedListNode second = new SinglyLinkedListNode(2);
    second.next = null;
    SinglyLinkedListNode first = new SinglyLinkedListNode(6);
    first.next = second;
    SinglyLinkedListNode head = new SinglyLinkedListNode(20);
    head.next = first;

    // given
    SinglyLinkedListNode secondAux = new SinglyLinkedListNode(2);
    secondAux.next = null;
    SinglyLinkedListNode firstAux = new SinglyLinkedListNode(6);
    firstAux.next = secondAux;
    SinglyLinkedListNode headAux = new SinglyLinkedListNode(20);
    headAux.next = firstAux;

    assertThat(LinkedListDs.compareLists(head, headAux), equalTo(true));
    assertThat(LinkedListDs.compareLists(headAux, head), equalTo(true));
  }

  @Test
  public void compareLinkedListShouldWorkOkWhenTwoListAreNotEquals() {
    // given
    SinglyLinkedListNode first = new SinglyLinkedListNode(6);
    first.next = null;
    SinglyLinkedListNode head = new SinglyLinkedListNode(20);
    head.next = first;

    // given
    SinglyLinkedListNode secondAux = new SinglyLinkedListNode(2);
    secondAux.next = null;
    SinglyLinkedListNode firstAux = new SinglyLinkedListNode(6);
    firstAux.next = secondAux;
    SinglyLinkedListNode headAux = new SinglyLinkedListNode(20);
    headAux.next = firstAux;

    assertThat(LinkedListDs.compareLists(head, headAux), equalTo(false));
    assertThat(LinkedListDs.compareLists(headAux, head), equalTo(false));
  }

  @Test
  public void compareLinkedListShouldWorkOkWhenTwoListAreSimilar() {
    // given
    SinglyLinkedListNode second = new SinglyLinkedListNode(2);
    second.next = null;
    SinglyLinkedListNode first = new SinglyLinkedListNode(6);
    first.next = second;
    SinglyLinkedListNode head = new SinglyLinkedListNode(20);
    head.next = first;

    // given
    SinglyLinkedListNode secondAux = new SinglyLinkedListNode(2);
    secondAux.next = null;
    SinglyLinkedListNode firstAux = new SinglyLinkedListNode(6);
    firstAux.next = secondAux;
    SinglyLinkedListNode headAux = new SinglyLinkedListNode(2);
    headAux.next = firstAux;

    assertThat(LinkedListDs.compareLists(head, headAux), equalTo(false));
    assertThat(LinkedListDs.compareLists(headAux, head), equalTo(false));
  }

  @Test
  public void mergeSortedLinkedListShouldWorkOk() {
    // given
    SinglyLinkedListNode second = new SinglyLinkedListNode(6);
    second.next = null;
    SinglyLinkedListNode first = new SinglyLinkedListNode(5);
    first.next = second;
    SinglyLinkedListNode head = new SinglyLinkedListNode(4);
    head.next = first;

    SinglyLinkedListNode secondAux = new SinglyLinkedListNode(10);
    secondAux.next = null;
    SinglyLinkedListNode firstAux = new SinglyLinkedListNode(2);
    firstAux.next = secondAux;
    SinglyLinkedListNode headAux = new SinglyLinkedListNode(1);
    headAux.next = firstAux;

    // when
    SinglyLinkedListNode mergeLists = LinkedListDs.mergeLists(head, headAux);

    // then
    assertThat(mergeLists.data, equalTo(1));
    assertThat(mergeLists.next.next.next.next.next.data, equalTo(10));
  }

  @Test
  public void mergeSortedLinkedListWithDifferentSizesShouldWorkOk() {
    // given
    SinglyLinkedListNode second = new SinglyLinkedListNode(6);
    second.next = null;
    SinglyLinkedListNode first = new SinglyLinkedListNode(5);
    first.next = second;
    SinglyLinkedListNode head = new SinglyLinkedListNode(4);
    head.next = first;

    SinglyLinkedListNode headAux = new SinglyLinkedListNode(1);

    // when
    SinglyLinkedListNode mergeLists = LinkedListDs.mergeLists(head, headAux);

    // then
    assertThat(mergeLists.data, equalTo(1));
    assertThat(mergeLists.next.next.data, equalTo(5));
    assertThat(mergeLists.next.next.next.data, equalTo(6));
  }

  @Test
  public void anotherMergeSortedLinkedListWithDifferentSizesShouldWorkOk() {
    // given
    SinglyLinkedListNode second = new SinglyLinkedListNode(5);
    second.next = null;
    SinglyLinkedListNode first = new SinglyLinkedListNode(3);
    first.next = second;
    SinglyLinkedListNode head = new SinglyLinkedListNode(1);
    head.next = first;

    SinglyLinkedListNode firstAux = new SinglyLinkedListNode(4);
    firstAux.next = null;
    SinglyLinkedListNode headAux = new SinglyLinkedListNode(2);
    headAux.next = firstAux;

    // when
    SinglyLinkedListNode mergeLists = LinkedListDs.mergeLists(headAux, head);

    // then
    assertThat(mergeLists.data, equalTo(1));
    assertThat(mergeLists.next.next.data, equalTo(3));
    assertThat(mergeLists.next.next.next.next.data, equalTo(5));
  }
}
