package org.mps.deque;

/**
 * @author Javier Leiva Dueñas
 * @author Pablo Fernández Serrano
 */

public class DoublyLinkedListDeque<T> implements DoubleEndedQueue<T> {

    private DequeNode<T> first;
    private DequeNode<T> last;
    private int size;

    public DoublyLinkedListDeque() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public void prepend(T value) {
        if(value==null){
            throw new DoubleEndedQueueException("Value of node cannot be null");
        }
        DequeNode<T> node = new DequeNode<T>(value, null, first);
        if (first != null) {
            first.setPrevious(node);
        } else {
            last = node;
        }
        first = node;
        size++;
    }

    @Override
    public void append(T value) {
        if(value==null){
            throw new DoubleEndedQueueException("Value of node cannot be null");
        }
        DequeNode<T> node = new DequeNode<T>(value, last, null);
        if (last != null) {
            last.setNext(node);
        } else {
            first = node;
        }
        last = node;
        size++;
    }

    @Override
    public void deleteFirst() {
        if(size==0){
            throw new DoubleEndedQueueException("List is empty");
        }
        if (first.getNext() != null) {
            first.getNext().setPrevious(null);
        } else {
            last = null;
        }
        first = first.getNext();
        size--;
    }

    @Override
    public void deleteLast() {
        if(size==0){
            throw new DoubleEndedQueueException("List is empty");
        }
        if (last.getPrevious() != null) {
            last.getPrevious().setNext(null);
        } else {
            first = null;
        }
        last = last.getPrevious();
        size--;
    }

    @Override
    public T first() {
        if (first == null) {
            throw new DoubleEndedQueueException("List is empty");
        }
        return first.getItem();
    }

    @Override
    public T last() {
        if (last == null) {
            throw new DoubleEndedQueueException("List is empty");
        }
        return last.getItem();
    }

    @Override
    public int size() {
        return size;
    }
}
