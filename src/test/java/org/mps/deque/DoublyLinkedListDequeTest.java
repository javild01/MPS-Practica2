package org.mps.deque;

import org.junit.jupiter.api.*;

import java.util.Comparator;
import java.util.Objects;

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
    @DisplayName("Simple methods")
    class SimpleMethods {
        @Nested
        @DisplayName("Append and prepend")
        class AppendAndPrepend {

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
                Double expectedFirstValue = 12.7;
                Double expectedLastValue = 83.9;
                doublyLinkedListDeque.prepend(expectedLastValue);
                doublyLinkedListDeque.prepend(expectedFirstValue);
                Double obtainedFirstValue = (Double) doublyLinkedListDeque.first();
                Double obtainedLastValue = (Double) doublyLinkedListDeque.last();
                Integer size = doublyLinkedListDeque.size();
                assertEquals(expectedFirstValue, obtainedFirstValue);
                assertEquals(expectedLastValue, obtainedLastValue);
                assertEquals(2, size);
            }

            // Test inserting elements at the end of the deque
            @Test
            void appendValue() {
                Double expectedFirstValue = 12.7;
                Double expectedLastValue = 83.9;
                doublyLinkedListDeque.append(expectedFirstValue);
                doublyLinkedListDeque.append(expectedLastValue);
                Double obtainedLastValue = (Double) doublyLinkedListDeque.last();
                Double obtainedFirstValue = (Double) doublyLinkedListDeque.first();
                Integer size = doublyLinkedListDeque.size();
                assertEquals(expectedFirstValue, obtainedFirstValue);
                assertEquals(expectedLastValue, obtainedLastValue);
                assertEquals(2, size);
            }

            // Test inserting a single element at the beginning of the deque
            @Test
            void prependValueWithOneValuePrepended() {
                Double expectedValue = 12.7;
                doublyLinkedListDeque.prepend(expectedValue);
                Double obtainedFirstValue = (Double) doublyLinkedListDeque.first();
                Double obtainedLastValue = (Double) doublyLinkedListDeque.last();
                Integer size = doublyLinkedListDeque.size();
                assertEquals(expectedValue, obtainedFirstValue);
                assertEquals(expectedValue, obtainedLastValue);
                assertEquals(1, size);
            }

            // Test inserting a single element at the end of the deque
            @Test
            void appendValueWithOneValueAppended() {
                Double expectedValue = 12.7;
                doublyLinkedListDeque.append(expectedValue);
                Double obtainedLastValue = (Double) doublyLinkedListDeque.last();
                Double obtainedFirstValue = (Double) doublyLinkedListDeque.first();
                Integer size = doublyLinkedListDeque.size();
                assertEquals(expectedValue, obtainedLastValue);
                assertEquals(expectedValue, obtainedFirstValue);
                assertEquals(1, size);
            }
        }

        @Nested
        @DisplayName("Deletion")
        class Deletion {

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
        }

        // The following nested class contains tests for special cases involving terminals
        // in a DoublyLinkedListDeque.
        @Nested
        @DisplayName("Terminal checks")
        class TerminalChecks {
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

    @Nested
// This test class groups the methods related to complex operations on the list    @DisplayName("Complex methods")
    class ComplexMethods {
        @Nested
        @DisplayName("Get Method")
        class GetMethod {

             // This test checks that an exception is thrown when trying to access a negative index with the get method
             @Test
             void getNegativeIndexThrowsException(){
                 assertThrows(DoubleEndedQueueException.class,()->{
                     doublyLinkedListDeque.get(-5);
                 });
             }

            // This test verifies if an exception is thrown when trying to get an element from an empty list
            @Test
            void getElementByIndexWithEmptyList() {
                assertThrows(DoubleEndedQueueException.class, () -> {
                    assertNull(doublyLinkedListDeque.get(5));
                });
            }

            // This test verifies if the correct value is obtained when getting an element from a list with a single element
            @Test
            void getElementByIndexInList() {
                doublyLinkedListDeque.append(0);
                int expectedValue = 0;
                int obtainedValue = (int) doublyLinkedListDeque.get(0);
                assertEquals(expectedValue, obtainedValue);
            }

            // This test verifies if an exception is thrown when trying to get an element that is not in the list
            @Test
            void getElementByIndexNotInList() {
                doublyLinkedListDeque.append(8);
                doublyLinkedListDeque.append(0);
                assertThrows(DoubleEndedQueueException.class, () -> {
                    assertNull(doublyLinkedListDeque.get(5));
                });
            }
        }

        @Nested
        @DisplayName("Remove Method")
        class RemoveMethod {

            // This test checks that the first element is removed
            @Test
            void removesFirstElement(){
                doublyLinkedListDeque.append(9);
                doublyLinkedListDeque.append(2.3);
                doublyLinkedListDeque.append(15.4);
                doublyLinkedListDeque.append(4.7);

                double expectedFirstElement = 2.3;
                double expectedLastElement = 4.7;
                doublyLinkedListDeque.remove(9);
                assertEquals(expectedFirstElement,doublyLinkedListDeque.first());
                assertEquals(expectedLastElement,doublyLinkedListDeque.last());
            }

            // This test verifies that an element that is the last but not the first is correctly removed
            // (if it were a list with a single element, then that element would be first and last at the same time)
            @Test
            void removesLastButNotFirstElement(){
                doublyLinkedListDeque.append(9.0);
                doublyLinkedListDeque.append(2.3);
                doublyLinkedListDeque.append(15.4);
                doublyLinkedListDeque.append(4.7);

                doublyLinkedListDeque.remove(4.7);
                double expectedFirstElement = 9;
                double expectedLastElement = 15.4;

                assertEquals(expectedFirstElement,doublyLinkedListDeque.first());
                assertEquals(expectedLastElement,doublyLinkedListDeque.last());
            }

            // This test verifies if no exception is thrown when trying to remove an element from an empty list
            @Test
            void removeElementInEmptyList() {
                assertDoesNotThrow(() -> {
                    doublyLinkedListDeque.remove(1);
                });
            }

            // This test verifies if the only element of a list is correctly removed and
            // if an exception is thrown when trying to access the first element of the empty list
            @Test
            void removeElementInListWithOneElement() {
                doublyLinkedListDeque.append(1);
                doublyLinkedListDeque.remove(1);
                assertThrows(DoubleEndedQueueException.class, () -> {
                    doublyLinkedListDeque.first();
                });
            }

            // This test verifies if it does not modify the list when trying to remove an element that is not in the list with a single element and if the size of the list remains unchanged
            @Test
            void removeElementNotContainedInListWithOneElement() {
                doublyLinkedListDeque.append(3);
                doublyLinkedListDeque.remove(1);
                int expectedValue = 1;
                int obtainedValue = doublyLinkedListDeque.size();
                assertEquals(expectedValue, obtainedValue);
            }

            // This test verifies if it correctly removes an element that is in the list and if it updates correctly both size and values of nodes
            @Test
            void removeElementInList() {
                doublyLinkedListDeque.append(3);
                doublyLinkedListDeque.append(2);
                doublyLinkedListDeque.append(7);
                doublyLinkedListDeque.append(9);
                doublyLinkedListDeque.remove(7);
                int expectedValueOfSize = 3;
                int obtainedValueOfSize = doublyLinkedListDeque.size();
                assertEquals(expectedValueOfSize, obtainedValueOfSize);
                int expectedValueOfNode = 9;
                int obtainedValueOfNode = (int)doublyLinkedListDeque.get(2);
                assertEquals(expectedValueOfNode, obtainedValueOfNode);
            }

            // This test verifies if the list is not modified when trying to delete an element which isn´t on the list and if the size and values of the list
            //remain the same
            @Test
            void removeElementNotContainedInList() {
                doublyLinkedListDeque.append(3);
                doublyLinkedListDeque.append(2);
                doublyLinkedListDeque.append(7);
                doublyLinkedListDeque.append(9);
                doublyLinkedListDeque.remove(8);
                int expectedValueOfSize = 4;
                int obtainedValueOfSize = doublyLinkedListDeque.size();
                assertEquals(expectedValueOfSize, obtainedValueOfSize);
                int expectedValueOfNode = 7;
                int obtainedValueOfNode = (int)doublyLinkedListDeque.get(2);
                assertEquals(expectedValueOfNode, obtainedValueOfNode);
            }
        }

        @Nested
        @DisplayName("Contains Method")
        class ContainsMethod {
            // Este test verifica si una lista vacía no contiene ningún valor
            @Test
            void emptyListDoesNotContainValue() {
                assertFalse(doublyLinkedListDeque.contains(9.3));
            }

            // Este test verifica si una lista con un solo elemento contiene ese elemento
            @Test
            void oneElementListDoesContainValue() {
                doublyLinkedListDeque.append(9.3);
                assertTrue(doublyLinkedListDeque.contains(9.3));
            }

            // Este test verifica si una lista con un solo elemento no contiene otro elemento distinto
            @Test
            void oneElementListDoesNotContainValue() {
                doublyLinkedListDeque.append(9.1);
                assertFalse(doublyLinkedListDeque.contains(9.3));
            }

            // Este test verifica si una lista con varios elementos iguales contiene ese elemento
            @Test
            void listDoesContainValue() {
                doublyLinkedListDeque.append(9.3);
                doublyLinkedListDeque.append(9.3);
                doublyLinkedListDeque.append(9.3);
                doublyLinkedListDeque.append(9.3);
                assertTrue(doublyLinkedListDeque.contains(9.3));
            }

            // Este test verifica que una lista no contiene un elemento que no se ha añadido
            @Test
            void listDoesNotContainValue() {
                doublyLinkedListDeque.append(9.1);
                doublyLinkedListDeque.append(9.3);
                doublyLinkedListDeque.append(9.3);
                doublyLinkedListDeque.append(9.3);
                doublyLinkedListDeque.append(9.3);
                doublyLinkedListDeque.append(9.3);
                assertFalse(doublyLinkedListDeque.contains(5.7));
            }
        }

        @Nested
        @DisplayName("Sort Method")
        class SortMethod {

            // This test verifies that after sorting an empty list, the size is still zero
            @Test
            void sortEmptyList() {
                doublyLinkedListDeque.sort(Comparator.comparingInt(Integer::intValue));
                assertEquals(doublyLinkedListDeque.size(),0);
            }

            // This test verifies if you can sort a list with a single element without modifying it
            @Test
            void sortOneElementList() {
                doublyLinkedListDeque.append(8);
                doublyLinkedListDeque.sort(Comparator.comparingInt(Integer::intValue));
                int expectedValue = 8;
                int obtainedValue = (int) doublyLinkedListDeque.first();
                assertEquals(expectedValue, obtainedValue);
            }

            // This test verifies if you can sort a list with two elements in ascending order
            @Test
            void sortTwoElementList() {
                doublyLinkedListDeque.append(8);
                doublyLinkedListDeque.append(5);
                doublyLinkedListDeque.sort(Comparator.comparingInt(Integer::intValue));
                int expectedFirstValue = 5;
                int obtainedFirstValue = (int) doublyLinkedListDeque.first();
                assertEquals(expectedFirstValue, obtainedFirstValue);
                int expectedSecondValue = 8;
                int obtainedSecondValue = (int) doublyLinkedListDeque.last();
                assertEquals(expectedSecondValue, obtainedSecondValue);
            }

            // This test verifies if you can sort a list with several elements in ascending order and if the values of the list correspond to those expected
            @Test
            void sortList() {
                doublyLinkedListDeque.append(5);
                doublyLinkedListDeque.append(8);
                doublyLinkedListDeque.append(7);
                doublyLinkedListDeque.append(5);
                doublyLinkedListDeque.append(0);
                doublyLinkedListDeque.sort(Comparator.comparingInt(Integer::intValue));
                int expectedFirstValue = 0;
                int obtainedFirstValue = (int) doublyLinkedListDeque.get(0);
                assertEquals(expectedFirstValue, obtainedFirstValue);
                int expectedSecondValue = 5;
                int obtainedSecondValue = (int) doublyLinkedListDeque.get(1);
                assertEquals(expectedSecondValue, obtainedSecondValue);
                int expectedThirdValue = 5;
                int obtainedThirdValue = (int) doublyLinkedListDeque.get(2);
                assertEquals(expectedThirdValue, obtainedThirdValue);
                int expectedFourthValue = 7;
                int obtainedFourthValue = (int) doublyLinkedListDeque.get(3);
                assertEquals(expectedFourthValue, obtainedFourthValue);
                int expectedFifthValue = 8;
                int obtainedFifthValue = (int) doublyLinkedListDeque.get(4);
                assertEquals(expectedFifthValue, obtainedFifthValue);
            }
        }
    }
}