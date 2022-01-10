package boundedpipe;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedPipe<E> extends AbstractPipe<E> {

    private class Node {
        public E contents;
        public Node prev;
        public Node next;

        public Node(E contents) {
            this.contents = contents;
            this.prev = null;
            this.next = null;
        }
    }

    private Node first;
    private Node last;
    private int length;

    public LinkedPipe(int max) {
        super(max);
        first = null;
        last = null;
        length = 0;
    }

    @Override
    public void prepend(E element) {
        if (element == null) throw new IllegalArgumentException();
        if (this.isFull()) throw new IllegalStateException();
        Node newNode = new Node(element);
        if (this.isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        }
        length++;
    }

    @Override
    public void append(E element) {
        if (element == null) throw new IllegalArgumentException();
        if (this.isFull()) throw new IllegalStateException();
        Node newNode = new Node(element);
        newNode.next = null;
        if (this.isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
        length++;
    }

    @Override
    public E removeFirst() {
        if (this.isEmpty()) throw new IllegalStateException();
        E nodeContents = first.contents;
        if (length() == 1) {
            first = null;
            last = null;
        } else {
            Node secondNode = first.next;
            first.next = null;
            secondNode.prev = null;
            first = secondNode;
        }
        length--;
        return nodeContents;
    }

    @Override
    public E removeLast() {
        if (this.isEmpty()) throw new IllegalStateException();
        E nodeContents = last.contents;
        if (length() == 1) {
            first = null;
            last = null;
        } else {
            Node secondToLastNode = last.prev;
            last.prev = null;
            secondToLastNode.next = null;
            last = secondToLastNode;
        }
        length--;
        return nodeContents;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public Pipe<E> newInstance() {
        return new LinkedPipe<>(capacity());
    }

    @Override
    public void clear() {
        first.next = null;
        last.prev = null;
        first = null;
        last = null;
        length = 0;
    }

    @Override
    public E first() {
        if (this.isEmpty()) return null;
        return first.contents;
    }

    @Override
    public E last() {
        if (this.isEmpty()) return null;
        return last.contents;
    }

    @Override
    public Iterator<E> iterator() {
        return new PipeIterator<E>();
    }

    private class PipeIterator<E> implements Iterator<E> {

        private int indexOfNext = 0;

        @Override
        public boolean hasNext() {
            return indexOfNext < length();
        }

        @Override
        public E next() {
            if (!this.hasNext()) throw new NoSuchElementException();
            Node temp = first;
            for (int i = 0; i < indexOfNext; i++) {
                temp = temp.next;
            }
            E result = (E) temp.contents;
            indexOfNext++;
            return result;
        }
    }
}
