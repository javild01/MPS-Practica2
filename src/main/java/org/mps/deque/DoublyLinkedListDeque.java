package org.mps.deque;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
        if (size == 0)
            throw new DoubleEndedQueueException("Empty List");
        return first.getItem();
    }

    @Override
    public T last() {
        if (size == 0)
            throw new DoubleEndedQueueException("Empty List");
        return last.getItem();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        if (index < 0)
            throw new DoubleEndedQueueException("NegativeIndex");
        if (size < index)
            throw new DoubleEndedQueueException("IndexOutOfBound");
        DequeNode node = first;
        while (index > 0 && node != null) {
            node = node.getNext();
            index--;
        }
        return (T)node.getItem();
    }

    @Override
    public boolean contains(T value) {
        DequeNode node = first;
        while (node != null) {
            if (node.getItem().equals(value))
                return true;
            node = node.getNext();
        }
        return false;
    }

    @Override
    public void remove(T value) {
        DequeNode node = first;
        while (node != null) {
            if (node.getItem().equals(value)) {
                if (node.getNext() != null) {
                    if (node.getPrevious() != null) {
                        node.getNext().setPrevious(node.getPrevious());
                        node.getPrevious().setNext(node.getNext());
                    } else {
                        node.getNext().setPrevious(null);
                        first = node.getNext();
                    }
                } else {
                    if (node.getPrevious() != null) {
                        last = node.getPrevious();
                        node.getPrevious().setNext(null);
                    } else {
                        first = null;
                        last = null;
                    }
                }
                size--;
                break;
            }
            node = node.getNext();
        }
    }

    @Override
    public void sort(Comparator<? super T> comparator) {
        List<T> list = new ArrayList<T>();
        while (first != null) {
            list.add(this.first.getItem());
            this.deleteFirst();
        }
        first = null;
        last = null;
        list.sort(comparator);
        while (!list.isEmpty()) {
            this.append(list.get(0));
            list.remove(0);
        }
    }
}
