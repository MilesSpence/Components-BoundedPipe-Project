package boundedpipe;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircArrayPipe<E> extends AbstractPipe<E> {

    private final E[] elements;
    private int first;
    private int last;

    @SuppressWarnings("unchecked")
    public CircArrayPipe(int max) {
        super(max);
        elements = (E[]) new Object[max];
        first = -1;
        last = -1;
    }

    @Override
    public void prepend(E element) {
        if (element == null) throw new IllegalArgumentException();
        if (this.isFull()) throw new IllegalStateException();
        if (this.isEmpty()) {
            elements[0] = element;
            first = 0;
            last = 0;
        } else {
            first = (first - 1 + capacity()) % capacity();
            elements[first] = element;
        }
    }

    @Override
    public void append(E element) {
        if (element == null) throw new IllegalArgumentException();
        if (this.isFull()) throw new IllegalStateException();
        if (this.isEmpty()) {
            elements[0] = element;
            first = 0;
            last = 0;
        } else {
            last = (last + 1 + capacity()) % capacity();
            elements[last] = element;
        }
    }

    @Override
    public E removeFirst() {
        if (this.isEmpty()) throw new IllegalStateException();
        E firstElement = elements[first];
        if (first == last) {
            first = -1;
            last = -1;
            return firstElement;
        }
        first = (first + 1 + capacity()) % capacity();
        return firstElement;
    }

    @Override
    public E removeLast() {
        if (this.isEmpty()) throw new IllegalStateException();
        E lastElement = elements[last];
        if (first == last) {
            first = -1;
            last = -1;
            return lastElement;
        }
        last = (last - 1 + capacity()) % capacity();
        return lastElement;
    }

    @Override
    public int length() {
        if (first == -1) return 0;
        if ((last - (first - capacity()) + 1) % capacity() == 0) return capacity();
        return (last - (first - capacity()) + 1) % capacity();
    }

    @Override
    public Pipe<E> newInstance() {
        return new CircArrayPipe<>(capacity());
    }

    @Override
    public void clear() {
        first = -1;
        last = -1;
    }

    @Override
    public E first() {
        if (this.isEmpty()) return null;
        return elements[first];
    }

    @Override
    public E last() {
        if (this.isEmpty()) return null;
        return elements[last];
    }

    @Override
    public Iterator<E> iterator() {
        return new PipeIterator<E>();
    }

    private class PipeIterator<E> implements Iterator<E> {

        private int indexed = 0;
        private int indexOfNext = first;

        @Override
        public boolean hasNext() {
            return indexed < length();
        }

        @Override
        public E next() {
            if (!this.hasNext()) throw new NoSuchElementException();
            E result = (E) elements[indexOfNext];
            indexed++;
            indexOfNext = (indexOfNext + 1 + capacity()) % capacity();
            return result;
        }
    }
}
