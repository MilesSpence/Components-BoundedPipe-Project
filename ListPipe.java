package boundedpipe;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListPipe<E> extends AbstractPipe<E> {

    private final List<E> list;

    public ListPipe(int max) {
        super(max);
        list = new LinkedList<>();
    }

    @Override
    public void prepend(E element) {
        if (element == null) throw new IllegalArgumentException();
        if (this.isFull()) throw new IllegalStateException();
        list.add(0, element);
    }

    @Override
    public void append(E element) {
        if (element == null) throw new IllegalArgumentException();
        if (this.isFull()) throw new IllegalStateException();
        list.add(element);
    }

    @Override
    public E removeFirst() {
        if (this.isEmpty()) throw new IllegalStateException();
        return list.remove(0);
    }

    @Override
    public E removeLast() {
        if (this.isEmpty()) throw new IllegalStateException();
        return list.remove(list.size() - 1);
    }

    @Override
    public int length() {
        return list.size();
    }

    @Override
    public Pipe<E> newInstance() {
        return new ListPipe<>(capacity());
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public E first() {
        if (this.isEmpty()) return null;
        return list.get(0);
    }

    @Override
    public E last() {
        if (this.isEmpty()) return null;
        return list.get(list.size() - 1);
    }
}
