package org.mps.deque;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import java.util.Deque;

import static org.junit.jupiter.api.Assertions.*;

/**
* @author Javier Leiva Dueñas
* @author Pablo Fernández Serrano
*/

@DisplayName("Test of class DequeNode")
public class DequeNodeTest {

    @Nested
    @DisplayName("Inputs of DequeNode")
    class InputsOfDequeNode {
        @Test
        void numberNodeConstruction() {
            assertDoesNotThrow(() -> {
                new DequeNode<Double>(2.03, null, null);
            });
        }

        @Test
        void stringNodeConstruction() {
            assertDoesNotThrow(() -> {
                new DequeNode<String>("example", null, null);
            });
        }

        @Test
        void booleanNodeConstruction() {
            assertDoesNotThrow(() -> {
                new DequeNode<Boolean>(true, null, null);
            });
        }

        @Test
        void nullNodeConstruction() {
            assertDoesNotThrow(() -> {
                new DequeNode<>(null, null, null);
            });
        }

        @Nested
        @DisplayName("Values of DequeNode")
        class ValuesOfDequeNode {
            @Test
            void getsItemCorrectly() {
                Double expectedValue = 2.93;
                DequeNode<Double> node = new DequeNode<>(expectedValue, null, null);
                Double obtainedValue = node.getItem();
                assertEquals(expectedValue, obtainedValue);
            }

            @Test
            void setsItemCorrectly() {
                Double expectedValue = 2.93;
                DequeNode<Double> node = new DequeNode<>(9.134, null, null);
                node.setItem(expectedValue);
                Double obtainedValue = node.getItem();
                assertEquals(expectedValue, obtainedValue);
            }

            @Test
            void previousDequeNodeCorrectly() {
                DequeNode<Double> node = new DequeNode<>(2.93, null, null);
                DequeNode<Double> expectedValue = new DequeNode<>(8.46, null, node);
                node.setPrevious(expectedValue);
                DequeNode<Double> obtainedValue = node.getPrevious();
                assertEquals(expectedValue, obtainedValue);
            }

            @Test
            void nextDequeNodeCorrectly() {
                DequeNode<Double> node = new DequeNode<>(2.93, null, null);
                DequeNode<Double> expectedValue = new DequeNode<>(8.46, null, node);
                node.setNext(expectedValue);
                DequeNode<Double> obtainedValue = node.getNext();
                assertEquals(expectedValue, obtainedValue);
            }

            @Nested
            @DisplayName("Terminals of DequeNode")
            class TerminalDequeNode {
                @Test
                void isFirstAlone() {
                    DequeNode<Double> node = new DequeNode<>(12.8, null, null);
                    Boolean obtainedValue = node.isFirstNode();
                    assertTrue(obtainedValue);
                }

                @Test
                void isLastAlone() {
                    DequeNode<Double> node = new DequeNode<>(12.8, null, null);
                    assertTrue(node.isLastNode());
                }

                @Test
                void isNotFirstAlone() {
                    DequeNode<Double> node = new DequeNode<>(12.8, null, null);
                    DequeNode<Double> firstNode = new DequeNode<>(9.123, null, node);
                    node.setPrevious(firstNode);
                    assertFalse(node.isFirstNode());
                }

                @Test
                void isNotLastAlone() {
                    DequeNode<Double> node = new DequeNode<>(12.8, null, null);
                    DequeNode<Double> lastNode = new DequeNode<>(9.123, node, null);
                    node.setNext(lastNode);
                    assertFalse(node.isLastNode());
                }

                @Test
                void isTerminalNode() {
                    DequeNode<Double> mediumNode = new DequeNode<>(2.1, null, null);
                    DequeNode<Double> firstNode = new DequeNode<>(2.8, null, mediumNode);
                    DequeNode<Double> lastNode = new DequeNode<>(15.18, mediumNode, null);
                    mediumNode.setPrevious(firstNode);
                    mediumNode.setNext(lastNode);
                    assertTrue(mediumNode.isNotATerminalNode());
                    assertFalse(firstNode.isNotATerminalNode());
                    assertFalse(lastNode.isNotATerminalNode());
                }

                @Test
                void isTerminalAlone() {
                    DequeNode<Double> node = new DequeNode<>(2.1, null, null);
                    assertFalse(node.isNotATerminalNode());
                }
            }
        }
    }
}
