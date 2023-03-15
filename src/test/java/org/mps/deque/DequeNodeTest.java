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

    // Nested class to test the inputs of DequeNode constructor
    @Nested
    @DisplayName("Insert new values")
    class InputsOfDequeNode {
        // Test that a node with a number item can be constructed without throwing an exception@Test
        @Test
        @DisplayName("It create a unconnected node with double value")
        void numberNodeConstruction() {
            assertDoesNotThrow(() -> {
                new DequeNode<Double>(2.03, null, null);
            });
        }

        // Test that a node with a string item can be constructed without throwing an exception
        @Test
        @DisplayName("It create a unconnected node with string value")
        void stringNodeConstruction() {
            assertDoesNotThrow(() -> {
                new DequeNode<String>("example", null, null);
            });
        }

        // Test that a node with a boolean item can be constructed without throwing an exception
        @Test
        @DisplayName("It create a unconnected node with boolean value")
        void booleanNodeConstruction() {
            assertDoesNotThrow(() -> {
                new DequeNode<Boolean>(true, null, null);
            });
        }

        // Test that a node with a null item can be constructed without throwing an exception
        @Test
        @DisplayName("It create a unconnected node with null value")
        void nullNodeConstruction() {
            assertDoesNotThrow(() -> {
                new DequeNode<>(null, null, null);
            });
        }
    }

    // Nested class to test the values of the item and the links of the node
    @Nested
    @DisplayName("Treating values")
    class ValuesOfDequeNode {

        // Test that the getItem method returns the correct value of the item stored in the node
        @Test
        @DisplayName("It returns the item contained")
        void getsItem() {
            Double expectedValue = 2.93;
            DequeNode<Double> node = new DequeNode<>(expectedValue, null, null);
            Double obtainedValue = node.getItem();
            assertEquals(expectedValue, obtainedValue);
        }

        // Test that the setItem method changes the value of the item stored in the node
        @Test
        @DisplayName("It sets the value as specified")
        void setsItem() {
            Double expectedValue = 2.93;
            DequeNode<Double> node = new DequeNode<>(9.134, null, null);
            node.setItem(expectedValue);
            Double obtainedValue = node.getItem();
            assertEquals(expectedValue, obtainedValue);
        }


        // Test that the setPrevious method changes the reference to the previous node in the deque
        @Test
        @DisplayName("It returns the previous node")
        void previousDequeNode() {
            DequeNode<Double> node = new DequeNode<>(2.93, null, null);
            DequeNode<Double> expectedValue = new DequeNode<>(8.46, null, node);
            node.setPrevious(expectedValue);
            DequeNode<Double> obtainedValue = node.getPrevious();
            assertEquals(expectedValue, obtainedValue);
        }

        // Test that the setNext method changes the reference to the next node in the deque
        @Test
        @DisplayName("It returns the next node")
        void nextDequeNode() {
            DequeNode<Double> node = new DequeNode<>(2.93, null, null);
            DequeNode<Double> expectedValue = new DequeNode<>(8.46, null, node);
            node.setNext(expectedValue);
            DequeNode<Double> obtainedValue = node.getNext();
            assertEquals(expectedValue, obtainedValue);
        }
    }

    // Nested class to test if a node is terminal (first or last) in the deque
    @Nested
    @DisplayName("Detection of terminals")
    class TerminalDequeNode {
        // Test that a node with no previous or next nodes is considered as first in the deque
        @Test
        @DisplayName("It can tell it is the first value when unconnected")
        void isFirstWithOneValue() {
            DequeNode<Double> node = new DequeNode<>(12.8, null, null);
            assertTrue(node.isFirstNode());
        }

        // Test that a node with no previous or next nodes is considered as last in the deque
        @Test
        @DisplayName("It can tell it is the last value when unconnected")
        void isLastWithOneValue() {
            DequeNode<Double> node = new DequeNode<>(12.8, null, null);
            assertTrue(node.isLastNode());
        }

        // Test that a node with a previous node is not considered as first in the deque
        @Test
        @DisplayName("It can tell it is the first value when connected")
        void isNotFirstWithOneValue() {
            DequeNode<Double> node = new DequeNode<>(12.8, null, null);
            DequeNode<Double> firstNode = new DequeNode<>(9.123, null, node);
            node.setPrevious(firstNode);
            assertFalse(node.isFirstNode());
        }

        // Test that a node with a next node is not considered as last in the deque
        @Test
        @DisplayName("It can tell it is the last value when connected")
        void isNotLastWithOneValue() {
            DequeNode<Double> node = new DequeNode<>(12.8, null, null);
            DequeNode<Double> lastNode = new DequeNode<>(9.123, node, null);
            node.setNext(lastNode);
            assertFalse(node.isLastNode());
        }

        // Test that a node with both previous and next nodes is not considered as terminal in the deque
        @Test
        @DisplayName("It can tell if it is a terminal node or not")
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

        // Test that a node with no previous or next nodes is considered as terminal in the deque
        @Test
        @DisplayName("It can tell it is a terminal node when alone")
        void isTerminalWithOneValue() {
            DequeNode<Double> node = new DequeNode<>(2.1, null, null);
            assertFalse(node.isNotATerminalNode());
        }
    }
}
