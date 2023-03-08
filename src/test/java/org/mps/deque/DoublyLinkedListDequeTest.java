package org.mps.deque;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * @author Javier Leiva Dueñas
 * @author Pablo Fernández Serrano
 */

/**
 * A class for testing the DoublyLinkedListDeque class.
 */
@DisplayName("Test of class DoublyLinkedListDeque")
public class DoublyLinkedListDequeTest {
    // The deque to be used in the tests
    private DoublyLinkedListDeque doublyLinkedListDeque;

    // Initiates the deque to be used in the tests
    @BeforeEach
    void init() {
        doublyLinkedListDeque = new DoublyLinkedListDeque();
    }

    // Tear down the deque after each test
    @AfterEach
    void terminate() {
        doublyLinkedListDeque = null;
    }

    // Tests for inserting elements into the deque
    @Nested
    @DisplayName("Inputs of DoublyLinkedListDeque")
    class InputsOfDoublyLinkedListDeque {

        // Test inserting null at the beginning of the deque
        @Test
        void prependNull() {
            assertThrows(DoubleEndedQueueException.class, () -> {
                doublyLinkedListDeque.prepend(null);
            });
        }

        // Test inserting null at the end of the deque
        @Test
        void appendNull() {
            assertThrows(DoubleEndedQueueException.class, () -> {
                doublyLinkedListDeque.append(null);
            });
        }

        // Test inserting elements at the beginning of the deque
        @Test
        void prependValue() {
            Double expectedValue1 = 12.7;
            Double expectedValue2 = 83.9;
            doublyLinkedListDeque.prepend(expectedValue2);
            doublyLinkedListDeque.prepend(expectedValue1);
            Double obtainedValue1 = (Double) doublyLinkedListDeque.first();
            Double obtainedValue2 = (Double) doublyLinkedListDeque.last();
            Integer size = doublyLinkedListDeque.size();
            assertEquals(expectedValue1, obtainedValue1);
            assertEquals(expectedValue2, obtainedValue2);
            assertEquals(2, size);
        }

        // Test inserting elements at the end of the deque
        @Test
        void appendValue() {
            Double expectedValue1 = 12.7;
            Double expectedValue2 = 83.9;
            doublyLinkedListDeque.append(expectedValue1);
            doublyLinkedListDeque.append(expectedValue2);
            Double obtainedValue2 = (Double) doublyLinkedListDeque.last();
            Double obtainedValue1 = (Double) doublyLinkedListDeque.first();
            Integer size = doublyLinkedListDeque.size();
            assertEquals(expectedValue1, obtainedValue1);
            assertEquals(expectedValue2, obtainedValue2);
            assertEquals(2, size);
        }

        // Test inserting a single element at the beginning of the deque
        @Test
        void prependValueWithOneValuePrepended() {
            Double expectedValue = 12.7;
            doublyLinkedListDeque.prepend(expectedValue);
            Double obtainedValue1 = (Double) doublyLinkedListDeque.first();
            Double obtainedValue2 = (Double) doublyLinkedListDeque.last();
            Integer size = doublyLinkedListDeque.size();
            assertEquals(expectedValue, obtainedValue1);
            assertEquals(expectedValue, obtainedValue2);
            assertEquals(1, size);
        }

        // Test inserting a single element at the end of the deque
        @Test
        void appendValueWithOneValueAppended() {
            Double expectedValue = 12.7;
            doublyLinkedListDeque.append(expectedValue);
            Double obtainedValue1 = (Double) doublyLinkedListDeque.last();
            Double obtainedValue2 = (Double) doublyLinkedListDeque.first();
            Integer size = doublyLinkedListDeque.size();
            assertEquals(expectedValue, obtainedValue1);
            assertEquals(expectedValue, obtainedValue2);
            assertEquals(1, size);
        }

        @Nested
        @DisplayName("Delete of DoublyLinkedListDeque")
        class ValuesOfDoublyLinkedListDeque {

            // Test for deleting from an empty deque from the start
            @Test
            void deleteFirstValueOfEmptyList() {
                assertThrows(DoubleEndedQueueException.class, () -> {
                    doublyLinkedListDeque.deleteFirst();
                });
            }

            // Test for deleting from an empty deque from the end
            @Test
            void deleteLastValueOfEmptyList() {
                assertThrows(DoubleEndedQueueException.class, () -> {
                    doublyLinkedListDeque.deleteLast();
                });
            }

            // Test for deleting from the start of a deque with multiple elements
            @Test
            void deleteFirstValue() {
                Double expectedValue = 12.7;
                doublyLinkedListDeque.append(83.9);
                doublyLinkedListDeque.append(expectedValue);
                doublyLinkedListDeque.append(8.9);
                doublyLinkedListDeque.append(89);
                System.out.println(doublyLinkedListDeque.first());
                doublyLinkedListDeque.deleteFirst();
                Double obtainedValue = (Double) doublyLinkedListDeque.first();
                Integer size = doublyLinkedListDeque.size();
                assertEquals(expectedValue, obtainedValue);
                assertEquals(3, size);
            }

            // Test for deleting from the end of a deque with multiple elements
            @Test
            void deleteLastValue() {
                Double expectedValue = 12.7;
                doublyLinkedListDeque.append(83.9);
                doublyLinkedListDeque.append(3.9);
                doublyLinkedListDeque.append(expectedValue);
                doublyLinkedListDeque.append(73.9);
                doublyLinkedListDeque.deleteLast();
                Double obtainedValue = (Double) doublyLinkedListDeque.last();
                Integer size = doublyLinkedListDeque.size();
                assertEquals(expectedValue, obtainedValue);
                assertEquals(3, size);
            }

            // Test for deleting the only element in the deque from the start
            @Test
            void deleteFirstValueWithOneNode() {
                Integer expectedValue = 0;
                doublyLinkedListDeque.prepend(expectedValue);
                doublyLinkedListDeque.deleteFirst();
                Integer obtainedValue = doublyLinkedListDeque.size();
                assertEquals(expectedValue, obtainedValue);
            }

            // The following function tests the deletion of the last element in a
            // DoublyLinkedListDeque that has only one element.
            @Test
            void deleteLastValueWithOneNode() {
                Integer expectedValue = 0;
                doublyLinkedListDeque.prepend(expectedValue);
                doublyLinkedListDeque.deleteLast();
                Integer obtainedValue = doublyLinkedListDeque.size();
                assertEquals(expectedValue, obtainedValue);
            }

            // The following nested class contains tests for special cases involving terminals
            // in a DoublyLinkedListDeque.
            @Nested
            @DisplayName("Terminals in special cases of DoublyLinkedListDeque")
            class TerminalDoublyLinkedListDeque {
                // Test that a node with no previous or next nodes is considered as first in the deque
                @Test
                void isFirstWithOneNode() {
                    assertThrows(DoubleEndedQueueException.class, () -> {
                        doublyLinkedListDeque.first();
                    });
                }

                // The following function tests the behavior of the last() function when called on a deque with only one element.
                @Test
                void isLastWithLastNode() {
                    assertThrows(DoubleEndedQueueException.class, () -> {
                        doublyLinkedListDeque.last();
                    });
                }

                // The following function tests the behavior of the first() function when called on a deque with only one element.
                @Test
                void getFirst() {
                    Double expectedValue = 12.7;
                    doublyLinkedListDeque.append(expectedValue);
                    Double obtainedValue = (Double) doublyLinkedListDeque.first();
                    assertEquals(expectedValue, obtainedValue);
                }

                // The following function tests the behavior of the last() function when called on a deque with only one element.
                @Test
                void getLast() {
                    Double expectedValue = 12.7;
                    doublyLinkedListDeque.append(expectedValue);
                    Double obtainedValue = (Double) doublyLinkedListDeque.last();
                    assertEquals(expectedValue, obtainedValue);
                }
            }
        }
    }
}
