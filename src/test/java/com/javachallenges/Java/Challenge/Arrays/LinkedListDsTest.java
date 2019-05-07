package com.javachallenges.Java.Challenge.Arrays;

import com.javachallenges.Java.Challenge.LinkedListDs;
import com.javachallenges.Java.Challenge.SinglyLinkedListNode;
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
}
