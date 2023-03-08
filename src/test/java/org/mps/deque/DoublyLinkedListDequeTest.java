package org.mps.deque;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * @author Javier Leiva Dueñas
 * @author Pablo Fernández Serrano
 */

@DisplayName("Test of class DoublyLinkedListDeque")
public class DoublyLinkedListDequeTest {
    private DoublyLinkedListDeque doublyLinkedListDeque;

    @BeforeEach
    void init() {
        doublyLinkedListDeque = new DoublyLinkedListDeque();
    }

    @AfterEach
    void terminate() {
        doublyLinkedListDeque = null;
    }

    @Nested
    @DisplayName("Inputs")
    class InputsOfDoublyLinkedListDeque {

        @Test
        void insertStart() {
            Double expectedValue = 12.7;
            doublyLinkedListDeque.append(83.9);
            doublyLinkedListDeque.append(expectedValue);
            Double obtainedValue = (Double) doublyLinkedListDeque.last();
            Integer size = doublyLinkedListDeque.size();
            assertEquals(expectedValue, obtainedValue);
            assertEquals(2, size);
        }

        @Test
        void insertEnd() {
            Double expectedValue = 12.7;
            doublyLinkedListDeque.prepend(83.9);
            doublyLinkedListDeque.prepend(expectedValue);
            Double obtainedValue = (Double) doublyLinkedListDeque.first();
            Integer size = doublyLinkedListDeque.size();
            assertEquals(expectedValue, obtainedValue);
            assertEquals(2, size);
        }

        @Test
        void insertStartAlone() {
            Double expectedValue = 12.7;
            doublyLinkedListDeque.append(expectedValue);
            Double obtainedValue = (Double) doublyLinkedListDeque.last();
            Integer size = doublyLinkedListDeque.size();
            assertEquals(expectedValue, obtainedValue);
            assertEquals(1, size);
        }

        @Test
        void insertEndAlone() {
            Double expectedValue = 12.7;
            doublyLinkedListDeque.prepend(expectedValue);
            Double obtainedValue = (Double) doublyLinkedListDeque.first();
            Integer size = doublyLinkedListDeque.size();
            assertEquals(expectedValue, obtainedValue);
            assertEquals(1, size);
        }

        @Nested
        @DisplayName("Delete")
        class ValuesOfDoublyLinkedListDeque {

            @Test
            void deleteStartEmpty() {
                assertThrows(DoubleEndedQueueException.class, () -> {
                    doublyLinkedListDeque.deleteFirst();
                });
            }

            @Test
            void deleteEndEmpty() {
                assertThrows(DoubleEndedQueueException.class, () -> {
                    doublyLinkedListDeque.deleteLast();
                });
            }

            @Test
            void deleteStart() {
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

            @Test
            void deleteEnd() {
                Double expectedValue = 12.7;
                doublyLinkedListDeque.prepend(83.9);
                doublyLinkedListDeque.prepend(3.9);
                doublyLinkedListDeque.prepend(expectedValue);
                doublyLinkedListDeque.prepend(73.9);
                doublyLinkedListDeque.deleteLast();
                Double obtainedValue = (Double) doublyLinkedListDeque.last();
                Integer size = doublyLinkedListDeque.size();
                assertEquals(expectedValue, obtainedValue);
                assertEquals(3, size);
            }

            @Test
            void deleteStartAlone() {
                Integer expectedValue = 0;
                doublyLinkedListDeque.prepend(expectedValue);
                doublyLinkedListDeque.deleteFirst();
                Integer obtainedValue = doublyLinkedListDeque.size();
                assertEquals(expectedValue, obtainedValue);
            }

            @Test
            void deleteEndAlone() {
                Integer expectedValue = 0;
                doublyLinkedListDeque.prepend(expectedValue);
                doublyLinkedListDeque.deleteLast();
                Integer obtainedValue = doublyLinkedListDeque.size();
                assertEquals(expectedValue, obtainedValue);
            }

            @Nested
            @DisplayName("Terminals in special cases")
            class TerminalDoublyLinkedListDeque {
                @Test
                void isFirstAlone() {
                    assertThrows(DoubleEndedQueueException.class, () -> {
                        doublyLinkedListDeque.first();
                    });
                }

                @Test
                void isLastAlone() {
                    assertThrows(DoubleEndedQueueException.class, () -> {
                        doublyLinkedListDeque.last();
                    });
                }

                @Test
                void firstOne() {
                    Double expectedValue = 12.7;
                    doublyLinkedListDeque.append(expectedValue);
                    Double obtainedValue = (Double) doublyLinkedListDeque.first();
                    assertEquals(expectedValue, obtainedValue);
                }

                @Test
                void lastOne() {
                    Double expectedValue = 12.7;
                    doublyLinkedListDeque.append(expectedValue);
                    Double obtainedValue = (Double) doublyLinkedListDeque.last();
                    assertEquals(expectedValue, obtainedValue);
                }
            }
        }
    }
}
