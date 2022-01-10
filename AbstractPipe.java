package boundedpipe;

import java.util.Iterator;

public abstract class AbstractPipe<E> implements Pipe<E> {

    private final int capacity;

    public AbstractPipe(int max) {
        capacity = max;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return length() == 0;
    }

    @Override
    public boolean isFull() {
        return length() == capacity();
    }

    @Override
    public void appendAll(Pipe<E> that) {
        if (that == null) throw new IllegalArgumentException();
        if (that.length() > 0) {
            this.append(that.removeFirst());
            appendAll(that);
        }
    }

    @Override
    public Pipe<E> copy() {
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Pipe)) {
            return false;
        }
        Pipe that = (Pipe) obj;
        if (this.capacity() != that.capacity()) {
            return false;
        }
        if (this.length() != that.length()) {
            return false;
        }
        Iterator<E> thisIter = this.iterator();
        Iterator<?> thatIter = that.iterator();
        while (thisIter.hasNext()) {
            E elem = thisIter.next();
            Object o = thatIter.next();
            if (!elem.equals(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 17;
        for (E element : this) {
            result = 31 * result + element.hashCode();
        }
        result = 31 * result + capacity();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<E> iterator = this.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]:");
        sb.append(capacity());
        return sb.toString();
    }
}
