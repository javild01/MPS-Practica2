package org.mps.deque;

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
        first.setPrevious(node);
        this.first = node;
        size++;
    }

    @Override
    public void append(T value) {
        if(value==null){
            throw new DoubleEndedQueueException("Value of node cannot be null");
        }
        DequeNode<T> node = new DequeNode<T>(value, last, null);
        last.setNext(node);
        this.last = node;
        size++;
    }

    @Override
    public void deleteFirst() {
        if(size==0){
            throw new DoubleEndedQueueException("List is empty");
        }
        first.getNext().setPrevious(null);
        first = first.getNext();
        size--;
    }

    @Override
    public void deleteLast() {
        if(size==0){
            throw new DoubleEndedQueueException("List is empty");
        }
        last.getPrevious().setNext(null);
        last = last.getPrevious();
        size--;
    }

    @Override
    public T first() {
        return first.getItem();
    }

    @Override
    public T last() {
        return last.getItem();
    }

    @Override
    public int size() {
        return size;
    }
}
