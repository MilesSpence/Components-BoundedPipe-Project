package boundedpipe;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class LinkedPipeTest {

    private Pipe<String> pipe_abc_6;

    @org.junit.Before
    public void setUp() throws Exception {
        pipe_abc_6 = new LinkedPipe<>(6);
        pipe_abc_6.append("A");
        pipe_abc_6.append("B");
        pipe_abc_6.append("C");
    }

    @org.junit.Test
    public void littlePrepend() {
        Pipe<String> littlePrependPipe = new LinkedPipe<>(1);
        assertEquals(0, littlePrependPipe.length());
        assertEquals(1, littlePrependPipe.capacity());
        littlePrependPipe.prepend("A");
        assertEquals(1, littlePrependPipe.length());
        assertEquals("A", littlePrependPipe.first());
    }

    @org.junit.Test
    public void prepend() {
        assertEquals(3, pipe_abc_6.length());
        pipe_abc_6.prepend("D");
        assertEquals(4, pipe_abc_6.length());
        assertEquals("D", pipe_abc_6.first());
    }

    @org.junit.Test
    public void append() {
        assertEquals(3, pipe_abc_6.length());
        pipe_abc_6.append("D");
        assertEquals(4, pipe_abc_6.length());
        assertEquals("A", pipe_abc_6.first());
        assertEquals("D", pipe_abc_6.last());
    }

    @org.junit.Test
    public void removeFirst() {
        assertEquals(3, pipe_abc_6.length());
        String first = pipe_abc_6.removeFirst();
        assertEquals(2, pipe_abc_6.length());
        assertEquals("A", first);
        assertEquals("B", pipe_abc_6.first());
    }

    @org.junit.Test
    public void removeLast() {
        assertEquals(3, pipe_abc_6.length());
        String last = pipe_abc_6.removeLast();
        assertEquals(2, pipe_abc_6.length());
        assertEquals("C", last);
        assertEquals("B", pipe_abc_6.last());
    }

    @org.junit.Test
    public void length() {
        assertEquals(3, pipe_abc_6.length());

        Pipe<String> length_pipe = new LinkedPipe<>(5);
        assertEquals(0, length_pipe.length());

        length_pipe.prepend("A");
        assertEquals(1, length_pipe.length());
        length_pipe.prepend("B");
        assertEquals(2, length_pipe.length());
        length_pipe.append("C");
        assertEquals(3, length_pipe.length());
        length_pipe.prepend("D");
        assertEquals(4, length_pipe.length());
        length_pipe.append("E");
        assertEquals(5, length_pipe.length());
        length_pipe.removeFirst();
        assertEquals(4, length_pipe.length());
        length_pipe.removeFirst();
        assertEquals(3, length_pipe.length());
        length_pipe.removeLast();
        assertEquals(2, length_pipe.length());
        length_pipe.append("a");
        assertEquals(3, length_pipe.length());
        length_pipe.removeFirst();
        assertEquals(2, length_pipe.length());
        length_pipe.prepend("b");
        assertEquals(3, length_pipe.length());
    }

    @org.junit.Test
    public void capacity() {
        Pipe<String> capcity_pipe = new LinkedPipe<>(2);
        assertEquals(6, pipe_abc_6.capacity());
        assertEquals(2, capcity_pipe.capacity());
    }

    @org.junit.Test
    public void newInstance() {
        Pipe<String> newInstancePipe = pipe_abc_6.newInstance();
        assertEquals(6, newInstancePipe.capacity());
        assertEquals(0, newInstancePipe.length());
    }

    @org.junit.Test
    public void clear() {
        assertEquals(3, pipe_abc_6.length());
        pipe_abc_6.clear();
        assertEquals(0, pipe_abc_6.length());
        assertEquals(6, pipe_abc_6.capacity());
        assertNull(pipe_abc_6.first());
        assertEquals("[]:6", pipe_abc_6.toString());
    }

    @org.junit.Test
    public void iterator() {
        String result = "";
        for (String s : pipe_abc_6) {
            result += s;
        }
        assertEquals("ABC", result);
    }

    @org.junit.Test
    public void appendAll() {
        Pipe<String> appendAll_pipe = new LinkedPipe<>(2);
        appendAll_pipe.append("D");
        appendAll_pipe.append("E");
        assertEquals(2, appendAll_pipe.length());
        assertEquals("D", appendAll_pipe.first());
        assertEquals("E", appendAll_pipe.last());

        assertEquals(3, pipe_abc_6.length());
        assertEquals(6, pipe_abc_6.capacity());
        pipe_abc_6.appendAll(appendAll_pipe);
        assertEquals(5, pipe_abc_6.length());
        assertEquals("E", pipe_abc_6.last());
        assertEquals(0, appendAll_pipe.length());
    }

    @org.junit.Test
    public void appendAllEmpty() {
        Pipe<String> appendAllEmpty_pipe = new LinkedPipe<>(2);
        assertEquals(0, appendAllEmpty_pipe.length());
        pipe_abc_6.appendAll(appendAllEmpty_pipe);
        assertEquals(3, pipe_abc_6.length());
        assertEquals("C", pipe_abc_6.last());
        assertEquals(0, appendAllEmpty_pipe.length());
    }

    @org.junit.Test
    public void copy() {
        Pipe<String> copy = pipe_abc_6.copy();
        assertEquals(copy.length(), pipe_abc_6.length());
        assertEquals(copy.capacity(), pipe_abc_6.capacity());
        assertEquals(copy.first(), pipe_abc_6.first());
        assertEquals(copy.last(), pipe_abc_6.last());
    }

    @org.junit.Test
    public void testABCEqualsNUll() {
        Pipe<String> s = null;
        assertFalse(pipe_abc_6.equals(s));
    }

    @org.junit.Test
    public void testABCEqualsSelf() {
        assertTrue(pipe_abc_6.equals(pipe_abc_6));
    }

    @org.junit.Test
    public void testABCEqualsNonPipe() {
        assertFalse(pipe_abc_6.equals("[A, B, C]:6"));
    }

    @org.junit.Test
    public void testABC6EqualsDifferentABC6() {
        Pipe<String> diff_pipe_abc_6 = new LinkedPipe<>(6);
        diff_pipe_abc_6.append("A");
        diff_pipe_abc_6.append("B");
        diff_pipe_abc_6.append("C");
        assertTrue(pipe_abc_6.equals(diff_pipe_abc_6));
    }

    @org.junit.Test
    public void testABC6EqualsABC10() {
        Pipe<String> pipe_abc_10 = new LinkedPipe<>(10);
        pipe_abc_10.append("A");
        pipe_abc_10.append("B");
        pipe_abc_10.append("C");
        assertFalse(pipe_abc_6.equals(pipe_abc_10));
    }

    @org.junit.Test
    public void testABC6EqualsAB6() {
        Pipe<String> pipe_ab_6 = new LinkedPipe<>(6);
        pipe_ab_6.append("A");
        pipe_ab_6.append("B");
        assertFalse(pipe_abc_6.equals(pipe_ab_6));
    }

    @org.junit.Test
    public void testEmpty3EqualsDifferentEmpty3() {
        Pipe<String> empty_3 = new LinkedPipe<>(3);
        Pipe<String> empty_3_dup = new LinkedPipe<>(3);
        assertTrue(empty_3.equals(empty_3_dup));
    }

    @org.junit.Test
    public void testEmpty3EqualsDifferentEmpty5() {
        Pipe<String> empty_5 = new LinkedPipe<>(5);
        Pipe<String> empty_3 = new LinkedPipe<>(3);
        assertFalse(empty_3.equals(empty_5));
    }

    @org.junit.Test
    public void testABC6EqualsDEF6() {
        Pipe<String> pipe_def_6 = new LinkedPipe<>(6);
        pipe_def_6.append("D");
        pipe_def_6.append("E");
        pipe_def_6.append("F");
        assertEquals(3, pipe_def_6.length());
        assertEquals("D", pipe_def_6.first());
        assertEquals("F", pipe_def_6.last());
        assertFalse(pipe_abc_6.equals(pipe_def_6));
    }

    @org.junit.Test
    public void testHashCodeABC6AndAB6() {
        Pipe<String> pipe_ab_6 = new LinkedPipe<>(6);
        pipe_ab_6.append("A");
        pipe_ab_6.append("B");
        assertNotEquals(pipe_abc_6.hashCode(), pipe_ab_6.hashCode());
    }

    @org.junit.Test
    public void testHashCodeABC6EqualsDifferentABC6() {
        Pipe<String> diff_pipe_abc_6 = new LinkedPipe<>(6);
        diff_pipe_abc_6.append("A");
        diff_pipe_abc_6.append("B");
        diff_pipe_abc_6.append("C");
        assertEquals(pipe_abc_6.hashCode(), diff_pipe_abc_6.hashCode());
    }

    @org.junit.Test
    public void isEmpty() {
        assertFalse(pipe_abc_6.isEmpty());
        Pipe<String> pipe_1 = new LinkedPipe<>(1);
        assertTrue(pipe_1.isEmpty());
    }

    @org.junit.Test
    public void isFull() {
        assertFalse(pipe_abc_6.isFull());
        Pipe<String> pipe_a_1 = new LinkedPipe<>(1);
        pipe_a_1.append("A");
        assertTrue(pipe_a_1.isFull());
    }

    @org.junit.Test(expected = IllegalStateException.class)
    public void testIllegalStateExceptionPrepend() {
        Pipe<String> pipe_a_1 = new LinkedPipe<>(1);
        pipe_a_1.prepend("A");
        pipe_a_1.prepend("B");
    }

    @org.junit.Test(expected = IllegalStateException.class)
    public void testIllegalStateExceptionAppend() {
        Pipe<String> pipe_a_1 = new LinkedPipe<>(1);
        pipe_a_1.prepend("A");
        pipe_a_1.append("A");
    }

    @org.junit.Test(expected = IllegalStateException.class)
    public void testIllegalStateExceptionRemoveFirst() {
        Pipe<String> pipe_1 = new LinkedPipe<>(1);
        pipe_1.removeFirst();
    }

    @org.junit.Test(expected = IllegalStateException.class)
    public void testIllegalStateExceptionRemoveLast() {
        Pipe<String> pipe_1 = new LinkedPipe<>(1);
        pipe_1.removeLast();
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentExceptionPrepend() {
        Pipe<String> pipe_1 = new LinkedPipe<>(1);
        pipe_1.prepend(null);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentExceptionAppend() {
        Pipe<String> pipe_1 = new LinkedPipe<>(1);
        pipe_1.append(null);
    }

    @org.junit.Test
    public void first() {
        assertEquals("A", pipe_abc_6.first());
    }

    @org.junit.Test
    public void last() {
        assertEquals("C", pipe_abc_6.last());
    }

    @org.junit.Test
    public void testNullFirst() {
        Pipe<String> pipe_0 = new LinkedPipe<>(0);
        assertEquals(null, pipe_0.first());
    }

    @org.junit.Test
    public void testNullLast() {
        Pipe<String> pipe_0 = new LinkedPipe<>(0);
        assertEquals(null, pipe_0.last());
    }

    @org.junit.Test
    public void toStringABC() {
        assertEquals("[A, B, C]:6", pipe_abc_6.toString());
    }

    @org.junit.Test
    public void toStringEmpty() {
        Pipe<String> pipe_5 = new LinkedPipe<>(5);
        assertEquals("[]:5", pipe_5.toString());
    }

    @org.junit.Test
    public void removeFirstEmpty() {
        Pipe<String> pipe_5 = new LinkedPipe<>(1);
        pipe_5.append("A");
        pipe_5.removeFirst();
        assertEquals("[]:1", pipe_5.toString());
    }

    @org.junit.Test
    public void removeLastEmpty() {
        Pipe<String> pipe_5 = new LinkedPipe<>(1);
        pipe_5.prepend("A");
        pipe_5.removeLast();
        assertEquals("[]:1", pipe_5.toString());
    }

    @org.junit.Test
    public void anotherLengthTest() {
        Pipe<String> pipe_1 = new LinkedPipe<>(1);
        assertEquals(0, pipe_1.length());
    }

    @org.junit.Test
    public void testABCD6EqualsABCD6() {
        Pipe<String> pipe_def_6 = pipe_abc_6.newInstance();
        pipe_def_6.append("A");
        pipe_def_6.append("B");
        pipe_def_6.append("C");
        pipe_def_6.append("D");

        assertEquals(6, pipe_def_6.capacity());
        assertEquals(4, pipe_def_6.length());
        assertEquals("A", pipe_def_6.first());
        assertEquals("D", pipe_def_6.last());

        pipe_abc_6.append("D");

        assertTrue(pipe_abc_6.equals(pipe_def_6));
    }

    @org.junit.Test
    public void testABCD6EqualsABCD4() {
        Pipe<String> pipe_abcd_6 = new LinkedPipe<>(4);
        pipe_abcd_6.append("A");
        pipe_abcd_6.append("B");
        pipe_abcd_6.append("C");
        pipe_abcd_6.append("D");


        pipe_abc_6.append("D");
        assertEquals(4, pipe_abcd_6.length());
        assertEquals("A", pipe_abcd_6.first());
        assertEquals("D", pipe_abcd_6.last());
        assertEquals(4, pipe_abcd_6.capacity());

        assertFalse(pipe_abc_6.equals(pipe_abcd_6));
    }

    @org.junit.Test
    public void testABCAndDiffABC() {
        Pipe<String> pipe_ABC_6 = new LinkedPipe<>(6);
        pipe_ABC_6.append("C");
        pipe_ABC_6.prepend("B");
        pipe_ABC_6.prepend("A");
        assertEquals(pipe_abc_6, pipe_ABC_6);
    }

    @org.junit.Test(expected = NoSuchElementException.class)
    public void fullIterator() {
        pipe_abc_6.append("A");
        pipe_abc_6.append("A");
        pipe_abc_6.append("A");
        String result = "";
        for (String s : pipe_abc_6) {
            result += s;
        }

        Pipe<String> pipe_6 = new LinkedPipe<>(6);
        String otherResult = "";

        Iterator<String> thisIter = pipe_6.iterator();
        String test = thisIter.next();

        for (String s : pipe_6) {
            otherResult += s;
        }
        assertEquals("ABCAAA", result);
        assertEquals("", otherResult);
    }
}
