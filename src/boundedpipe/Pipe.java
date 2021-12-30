package boundedpipe;

/**
 * <p>
 * A bounded pipe. Elements in the pipe may not be null.</p>
 * <p>
 * A typical string representation of a bounded pipe is
 * <code>[e_1, e_2, ..., e_n-1, e_n]:c</code>,
 * where <code>e_1</code> is the first element of the pipe,
 * <code>e_n</code> is the last element,
 * and <code>c</code> is the capacity.
 * </p>
 * <p>
 * Implementations of this interface should have a one-argument constructor
 * that takes the desired capacity and creates an empty pipe.
 * The capacity must be strictly greater than zero.
 * </p>
 * <p>Example: <code>public Pipe(int max)</code></p>
 * <p>
 * A pipe iterator iterates through the pipe from first to last.</p>
 *
 * @param <E> the type of elements in this pipe
 * @author Miles Spence
 */
public interface Pipe<E> extends Iterable<E> {

    /**
     * <p>
     * Adds the specified element to the beginning of this pipe.</p>
     * <p>
     * Example:<br>
     * { <code>p = [A, B, C]:6</code> <em>and</em> <code>x = X</code> }<br>
     * <code>p.prepend(x)</code><br>
     * { <code>p = [X, A, B, C]:6</code> <em>and</em> <code>x = X</code> }<br>
     * </p>
     *
     * @param element the element to be prepended to this pipe
     * @throws IllegalArgumentException if the specified element is null
     * @throws IllegalStateException    if this pipe is full
     */
    void prepend(E element) throws IllegalArgumentException, IllegalStateException;

    /**
     * <p>
     * Adds the specified element to the end of this pipe.</p>
     * <p>
     * Example:<br>
     * { <code>p = [A, B, C]:6</code> <em>and</em> <code>x = X</code> }<br>
     * <code>p.append(x)</code><br>
     * { <code>p = [A, B, C, X]:6</code> <em>and</em> <code>x = X</code> }<br>
     * </p>
     *
     * @param element the element to be appended to this pipe
     * @throws IllegalArgumentException if the specified element is null
     * @throws IllegalStateException    if this pipe is full
     */
    void append(E element) throws IllegalArgumentException, IllegalStateException;

    /**
     * <p>
     * Removes and returns the first element from this pipe.</p>
     * <p>
     * Example:<br>
     * { <code>p = [A, B, C]:6</code> <em>and</em> <code>x = X</code> }<br>
     * <code>x = p.removeFirst()</code><br>
     * { <code>p = [B, C]:6</code> <em>and</em> <code>x = A</code> }<br>
     * </p>
     *
     * @return the first element from this pipe
     * @throws IllegalStateException if this pipe is empty
     */
    E removeFirst() throws IllegalStateException;

    /**
     * <p>
     * Removes and returns the last element from this pipe.</p>
     * <p>
     * Example:<br>
     * { <code>p = [A, B, C]:6</code> <em>and</em> <code>x = X</code> }<br>
     * <code>x = p.removeLast()</code><br>
     * { <code>p = [A, B]:6</code> <em>and</em> <code>x = C</code> }<br>
     * </p>
     *
     * @return the last element from this pipe
     * @throws IllegalStateException if this pipe is empty
     */
    E removeLast() throws IllegalStateException;

    /**
     * <p>
     * Returns the number of elements in this pipe.</p>
     * <p>
     * Example:<br>
     * { <code>p = [A, B, C]:6</code> <em>and</em> <code>x = X</code> }<br>
     * <code>x = p.length()</code><br>
     * { <code>p = [A, B, C]:6</code> <em>and</em> <code>x = 3</code> }<br>
     * </p>
     *
     * @return the number of elements in this pipe
     */
    int length();

    /**
     * <p>
     * Returns the number of elements that this pipe can hold.</p>
     * <p>
     * Example:<br>
     * { <code>p = [A, B, C]:6</code> <em>and</em> <code>x = X</code> }<br>
     * <code>x = p.capacity()</code><br>
     * { <code>p = [A, B, C]:6</code> <em>and</em> <code>x = 6</code> }<br>
     * </p>
     *
     * @return the number of elements that this pipe can hold
     */
    int capacity();

    /**
     * <p>
     * Creates a new, empty bounded pipe with the same capacity as this pipe.</p>
     * <p>
     * Example:<br>
     * { <code>p = [A, B, C]:6</code> <em>and</em> <code>x = X</code> }<br>
     * <code>x = p.newInstance()</code><br>
     * { <code>p = [A, B, C]:6</code> <em>and</em> <code>x = []:6</code> }<br>
     * </p>
     *
     * @return empty bounded pipe with the same capacity as this pipe
     */
    Pipe<E> newInstance();

    /**
     * <p>
     * Removes all of the elements of this pipe, without altering the capacity.</p>
     * <p>
     * Example:<br>
     * { <code>p = [A, B, C]:6</code> }<br>
     * <code>p.clear()</code><br>
     * { <code>p = []:6</code> }<br>
     * </p>
     */
    void clear();

    /**
     * <p>
     * Checks if this pipe has any elements.</p>
     * <p>
     * Example:<br>
     * { <code>p = [A, B, C]:6</code> <em>and</em> <code>x = X</code> }<br>
     * <code>x = p.isEmpty()</code><br>
     * { <code>p = [A, B, C]:6</code> <em>and</em> <code>x = false</code> }<br>
     * </p>
     *
     * @return true if this pipe has any elements and false otherwise
     */
    boolean isEmpty();

    /**
     * <p>
     * Checks if this pipe has any more space (basically if length == capacity).</p>
     * <p>
     * Example:<br>
     * { <code>p = [A, B, C]:6</code> <em>and</em> <code>x = X</code> }<br>
     * <code>x = p.isFull()</code><br>
     * { <code>p = [A, B, C]:6</code> <em>and</em> <code>x = false</code> }<br>
     * </p>
     *
     * @return true if this pipe is full and false otherwise
     */
    boolean isFull();

    /**
     * <p>
     * Takes another pipe and empties its elements into this pipe.</p>
     * <p>
     * Example:<br>
     * { <code>p = [A, B, C]:6</code> <em>and</em> <code>x = [D, E, F]:6</code> }<br>
     * <code>p.appendAll(x)</code><br>
     * { <code>p = [A, B, C, D, E, F]:6</code> <em>and</em> <code>x = []:6</code> }<br>
     * </p>
     *
     * @param that the pipe to be emptied
     */
    void appendAll(Pipe<E> that);

    /**
     * <p>
     * Creates a complete copy of this pipe.</p>
     * <p>
     * Example:<br>
     * { <code>p = [A, B, C]:6</code> <em>and</em> <code>x = X</code> }<br>
     * <code>x = p.copy()</code><br>
     * { <code>p = [A, B, C]:6</code> <em>and</em> <code>x = [A, B, C]:6</code> }<br>
     * </p>
     *
     * @return a complete copy of this pipe
     */
    Pipe<E> copy();

    /**
     * <p>
     * Returns the first element of this pipe, without removing the element.</p>
     * <p>
     * Example:<br>
     * { <code>p = [A, B, C]:6</code> <em>and</em> <code>x = X</code> }<br>
     * <code>x = p.first()</code><br>
     * { <code>p = [A, B, C]:6</code> <em>and</em> <code>x = A</code> }<br>
     * </p>
     *
     * @return the first element of this pipe
     */
    E first();

    /**
     * <p>
     * Returns the last element of this pipe, without removing the element.</p>
     * <p>
     * Example:<br>
     * { <code>p = [A, B, C]:6</code> <em>and</em> <code>x = X</code> }<br>
     * <code>x = p.last()</code><br>
     * { <code>p = [A, B, C]:6</code> <em>and</em> <code>x = C</code> }<br>
     * </p>
     *
     * @return the last element of this pipe
     */
    E last();
}
