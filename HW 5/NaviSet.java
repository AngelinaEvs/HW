import java.util.*;

/**
 * @author Angelina Evsikova
 */


public class NaviSet<T> extends AbstractSet<T> implements NavigableSet<T> {
    /**Array field*/
    private ArrayList<T> data;//vector
    /**Comparator field*/
    private Comparator<? super T> c;
    /**Collection field*/
    private Collection<? extends T> col;
    /**Flag field*/
    private boolean flag = false;
    /**Constructor-creating a new instance*/
    public NaviSet() {
        data = new ArrayList<>();
        c = null;
    }
    /**Constructs a new instance sorted according to the null comparator
     * @param data - the array of elements*/
    public NaviSet(ArrayList<T> data) {
        this.data = data;
        data.sort(null);
    }
    /**Constructs a new instance
     * @param data - the array of elements
     * @param flag - boolean value*/
    public NaviSet(ArrayList<T> data, boolean flag) {
        this.data = data;
        c = null;
        if (!flag) data.sort(c);
    }
    /**Constructs a new instance sorted according to the specified comparator
     * @param c - Comparator*/
    public NaviSet(Comparator<? super T> c) {
        if (c != null) {
            this.data = new ArrayList<>();
            this.c = c;
            data.sort(c);
        }
        else {
            throw new NullPointerException();
        }
    }
    /**Creates a new instance containing items in the specified collection
     * sorted according to the specified comparator
     * @param col - Collection
     * @param c - Comparator*/
    public NaviSet(Collection<? extends T> col, Comparator<? super T> c) {
        if (col != null && c != null) {
            this.data = new ArrayList<>();
            this.c = c;
            this.addAll(col);
        }
        else {
            throw new NullPointerException();
        }
    }
    /**Creates a new instance containing the same elements
     *  sorted  in ascending order
     *  @param s - SortedSet*/
    public NaviSet(SortedSet<T> s) {
        if (s != null) {
            data = new ArrayList<>();
            c = null;
            java.util.Iterator<T> it = s.iterator();
            while (it.hasNext()) {
                data.add(it.next());
            }
        }
        else throw new NullPointerException();
    }

    /**
     * {@inheritDoc}
     *
     * <p>This implementation always throws an
     * <tt>UnsupportedOperationException</tt>.
     *
     * @param t - the element passed in
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @throws IllegalArgumentException      {@inheritDoc}
     * @throws IllegalStateException         {@inheritDoc}
     */
    @Override
    public boolean add(T t) {
        try {
            if (t == null) {
                throw new NullPointerException();
            }
            if (data.size() == 0 || this.ceiling(t) == null) {
                data.add(t);
                return true;
            }
            if (data.lastIndexOf(t) == -1) {
                data.add(t);
                for (int j = data.size() - 1; j > data.lastIndexOf(this.ceiling(t)); j--) {
                    data.set(j, data.get(j - 1));
                }
                data.set(data.lastIndexOf(this.ceiling(t)) - 1, t);
                return true;
            }
        }
        catch (UnsupportedOperationException e) {
            System.out.println("the requested operation is not supported");
        }
        catch (ClassCastException e) {
            System.out.println("the object is not an instance of a subclass");
        }
        catch (IllegalArgumentException e) {
            System.out.println("an invalid or unsuitable argument was passed to the method");
        }
        catch (IllegalStateException e) {
            System.out.println("this method cannot be called at this time");
        }
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * <p>This implementation iterates over the specified collection, and adds
     * each object returned by the iterator to this collection, in turn.
     *
     * <p>Note that this implementation will throw an
     * <tt>UnsupportedOperationException</tt> unless <tt>add</tt> is
     * overridden (assuming the specified collection is non-empty).
     *
     * @param col - the сollection passed in
     * @throws ClassCastException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @see #add(Object)
     */
    @Override
    public boolean addAll(Collection<? extends T> col) {
        try {
            if (col == null) {
                throw new NullPointerException();
            }
            java.util.Iterator<? extends T> it = col.iterator();
            while (it.hasNext()) {
                this.add(it.next());
            }
        } catch (ClassCastException e) {
            System.out.println("the object is not an instance of a subclass");
        }
        return true;
    }

    /**
     * Returns an iterator over the elements contained in this collection.
     *
     * @return an iterator over the elements contained in this collection
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int cursor;
            @Override
            public boolean hasNext() {
                return data.size() > cursor;
            }

            @Override
            public T next() {
                cursor++;
                return data.get(cursor - 1);
            }
        };
    }

    /**Returns the number of elements in this set (its cardinality)
     * @return the number of elements in this set (its cardinality)*/
    @Override
    public int size() {
        return data.size();
    }

    /**
     * Returns the greatest element in this set strictly less than the
     * given element, or {@code null} if there is no such element.
     *
     * @param t the value to match
     * @return the greatest element less than {@code e},
     * or {@code null} if there is no such element
     * @throws ClassCastException   if the specified element cannot be
     *                              compared with the elements currently in the set
     * @throws NullPointerException if the specified element is null
     *                              and this set does not permit null elements
     */
    @Override
    public T lower(T t) {
        try {
            if (t == null) {
                throw new NullPointerException("element is null");
            }
            for (int i = 0; i < data.size(); i++) {
                if (data.get(0) == t) return null;
                if (c.compare(data.get(i), t) >= 0) {
                    return data.get(i - 1);
                }
            }
        } catch (ClassCastException e) {
            System.out.println("the object is not an instance of a subclass");
        }
        return null;
    }

    /**
     * Returns the greatest element in this set less than or equal to
     * the given element, or {@code null} if there is no such element.
     *
     * @param t the value to match
     * @return the greatest element less than or equal to {@code e},
     * or {@code null} if there is no such element
     * @throws ClassCastException   if the specified element cannot be
     *                              compared with the elements currently in the set
     * @throws NullPointerException if the specified element is null
     *                              and this set does not permit null elements
     */
    @Override
    public T floor(T t) {
        try {
            if (t == null) {
                throw new NullPointerException("element is null");
            }
            for (int i = 0; i < data.size(); i++) {
                if (c.compare(data.get(0), t) > 0) {
                    return null;
                }
                if (c.compare(data.get(i), t) == 0) {
                    return data.get(i);
                }
                if (c.compare(data.get(i), t) > 0) {
                    return data.get(i - 1);
                }
                if (c.compare(data.get(i), t) < 0 && i == data.size() - 1) {
                    return data.get(i);
                }
            }
        } catch (ClassCastException e) {
            System.out.println("the object is not an instance of a subclass");
        }
        return null;
    }

    /**
     * Returns the least element in this set greater than or equal to
     * the given element, or {@code null} if there is no such element.
     *
     * @param t the value to match
     * @return the least element greater than or equal to {@code e},
     * or {@code null} if there is no such element
     * @throws ClassCastException   if the specified element cannot be
     *                              compared with the elements currently in the set
     * @throws NullPointerException if the specified element is null
     *                              and this set does not permit null elements
     */
    @Override
    public T ceiling(T t) {
        try {
            if (t == null) {
                throw new NullPointerException("element is null");
            }
            for (int i = 0; i < data.size(); i++) {
                if (c.compare(data.get(i), t) >= 0) {
                    return data.get(i);
                }
            }
        } catch (ClassCastException e) {
            System.out.println("the object is not an instance of a subclass");
        }
        return null;
    }

    /**
     * Returns the least element in this set strictly greater than the
     * given element, or {@code null} if there is no such element.
     *
     * @param t the value to match
     * @return the least element greater than {@code e},
     * or {@code null} if there is no such element
     * @throws ClassCastException   if the specified element cannot be
     *                              compared with the elements currently in the set
     * @throws NullPointerException if the specified element is null
     *                              and this set does not permit null elements
     */
    @Override
    public T higher(T t) {
        try {
            if (t == null) {
                throw new NullPointerException("element is null");
            }
            for (int i = 0; i < data.size(); i++) {
                if (c.compare(data.get(i), t) >= 0 && i == data.size() - 1) return null;
                if (c.compare(data.get(i), t) > 0) {
                    return data.get(i - 1);
                }
                if (c.compare(data.get(i), t) == 0) {
                    return data.get(i + 1);
                }
            }
        } catch (ClassCastException e) {
            System.out.println("the object is not an instance of a subclass");
        }
        return null;
    }

    /**
     * Retrieves and removes the first (lowest) element,
     * or returns {@code null} if this set is empty.
     *
     * @return the first element, or {@code null} if this set is empty
     */
    @Override
    public T pollFirst() {
        if (data.isEmpty()) return null;
        T el = data.get(0);
        data.remove(0);
        return el;
    }

    /**
     * Retrieves and removes the last (highest) element,
     * or returns {@code null} if this set is empty.
     *
     * @return the last element, or {@code null} if this set is empty
     */
    @Override
    public T pollLast() {
        if (data.isEmpty()) return null;
        T el = data.get(data.size() - 1);
        data.remove(data.size() - 1);
        return el;
    }

    /**
     * Returns a reverse order view of the elements contained in this set.
     * The descending set is backed by this set, so changes to the set are
     * reflected in the descending set, and vice-versa.  If either set is
     * modified while an iteration over either set is in progress (except
     * through the iterator's own {@code remove} operation), the results of
     * the iteration are undefined.
     *
     * <p>The returned set has an ordering equivalent to
     * <tt>{@link Collections#reverseOrder(Comparator) Collections.reverseOrder}(comparator())</tt>.
     * The expression {@code s.descendingSet().descendingSet()} returns a
     * view of {@code s} essentially equivalent to {@code s}.
     *
     * @return a reverse order view of this set
     */
    @Override
    public NaviSet<T> descendingSet() {
        ArrayList<T> al = new ArrayList<>();
        Iterator<T> it = this.descendingIterator();
        while (it.hasNext()) {
            al.add(it.next());
        }
        NaviSet<T> naviSet = new NaviSet<T>(al, true);
        return naviSet;
    }

    /**
     * Returns an iterator over the elements in this set, in descending order.
     * Equivalent in effect to {@code descendingSet().iterator()}.
     *
     * @return an iterator over the elements in this set, in descending order
     */
    @Override
    public Iterator<T> descendingIterator() {
        return new Iterator<>();
    }

    private class Iterator<T> implements java.util.Iterator<T> {
        private int cursor;

        public Iterator () {
            this.cursor = data.size();
        }

        @Override
        public boolean hasNext() {
            return cursor > 0;
        }

        @Override
        public T next() {
            cursor--;
            return (T) data.get(cursor);
        }
    }

    /**
     * Returns a view of the portion of this set whose elements range from
     * {@code fromElement} to {@code toElement}.  If {@code fromElement} and
     * {@code toElement} are equal, the returned set is empty unless {@code
     * fromInclusive} and {@code toInclusive} are both true.  The returned set
     * is backed by this set, so changes in the returned set are reflected in
     * this set, and vice-versa.  The returned set supports all optional set
     * operations that this set supports.
     *
     * <p>The returned set will throw an {@code IllegalArgumentException}
     * on an attempt to insert an element outside its range.
     *
     * @param fromElement   low endpoint of the returned set
     * @param fromInclusive {@code true} if the low endpoint
     *                      is to be included in the returned view
     * @param toElement     high endpoint of the returned set
     * @param toInclusive   {@code true} if the high endpoint
     *                      is to be included in the returned view
     * @return a view of the portion of this set whose elements range from
     * {@code fromElement}, inclusive, to {@code toElement}, exclusive
     * @throws NullPointerException     if {@code fromElement} or
     *                                  {@code toElement} is null and this set does
     *                                  not permit null elements
     * @throws IllegalArgumentException if {@code fromElement} is
     *                                  greater than {@code toElement}; or if this set itself
     *                                  has a restricted range, and {@code fromElement} or
     *                                  {@code toElement} lies outside the bounds of the range.
     */
    @Override
    public NaviSet<T> subSet(T fromElement, boolean fromInclusive, T toElement, boolean toInclusive) {
        if (fromElement == null || toElement == null) {
            throw new NullPointerException();
        }
        if (c.compare(fromElement, data.get(data.size() - 1)) > 0 || (c.compare(fromElement, data.get(data.size() - 1)) == 0 && !fromInclusive)
            || ((c.compare(fromElement, data.get(0)) < 0) && (c.compare(toElement, data.get(0)) < 0))) {
            throw new IllegalArgumentException();
        }
        ArrayList<T> al = new ArrayList<>();
        NaviSet<T> naviSet = new NaviSet<>(al);
        if ((fromElement.equals(toElement)) && (fromInclusive != toInclusive)) return naviSet;
        for (int i = data.indexOf(this.ceiling(fromElement)); i <= data.indexOf(this.floor(toElement)); i++) {
            if (!(toInclusive) && i == data.indexOf(this.floor(toElement)) && c.compare(toElement, data.get(i)) == 0) break;
            if (!(fromInclusive) && i == data.indexOf(this.ceiling(fromElement)) && c.compare(fromElement, data.get(i)) == 0) i++;
            al.add(data.get(i));
        }
        return naviSet;
    }

    /**
     * Returns a view of the portion of this set whose elements are less than
     * (or equal to, if {@code inclusive} is true) {@code toElement}.  The
     * returned set is backed by this set, so changes in the returned set are
     * reflected in this set, and vice-versa.  The returned set supports all
     * optional set operations that this set supports.
     *
     * <p>The returned set will throw an {@code IllegalArgumentException}
     * on an attempt to insert an element outside its range.
     *
     * @param toElement high endpoint of the returned set
     * @param inclusive {@code true} if the high endpoint
     *                  is to be included in the returned view
     * @return a view of the portion of this set whose elements are less than
     * (or equal to, if {@code inclusive} is true) {@code toElement}
     * @throws NullPointerException     if {@code toElement} is null and
     *                                  this set does not permit null elements
     * @throws IllegalArgumentException if this set itself has a
     *                                  restricted range, and {@code toElement} lies outside the
     *                                  bounds of the range
     */
    @Override
    public NaviSet<T> headSet(T toElement, boolean inclusive) {
        if (toElement == null) {
            throw new NullPointerException();
        }
        if (c.compare(toElement, data.get(0)) < 0) {
            throw new IllegalArgumentException();
        }
        ArrayList<T> al = new ArrayList<>();
        NaviSet<T> naviSet = new NaviSet<>(al);
        for (int i = 0; i <= data.indexOf(this.floor(toElement)); i++) {
            if (!(inclusive) && i == data.indexOf(this.floor(toElement)) && toElement.equals(data.get(i))) break;
            al.add(data.get(i));
        }
        return naviSet;
    }

    /**
     * Returns a view of the portion of this set whose elements are greater
     * than (or equal to, if {@code inclusive} is true) {@code fromElement}.
     * The returned set is backed by this set, so changes in the returned set
     * are reflected in this set, and vice-versa.  The returned set supports
     * all optional set operations that this set supports.
     *
     * <p>The returned set will throw an {@code IllegalArgumentException}
     * on an attempt to insert an element outside its range.
     *
     * @param fromElement low endpoint of the returned set
     * @param inclusive   {@code true} if the low endpoint
     *                    is to be included in the returned view
     * @return a view of the portion of this set whose elements are greater
     * than or equal to {@code fromElement}
     * @throws NullPointerException     if {@code fromElement} is null
     *                                  and this set does not permit null elements
     * @throws IllegalArgumentException if this set itself has a
     *                                  restricted range, and {@code fromElement} lies outside the
     *                                  bounds of the range
     */
    @Override
    public NaviSet<T> tailSet(T fromElement, boolean inclusive) {
        if (fromElement == null) {
            throw new NullPointerException();
        }
        if (c.compare(fromElement, data.get(data.size() - 1)) > 0) {
            throw new IllegalArgumentException();
        }
        ArrayList<T> al = new ArrayList<>();
        NaviSet<T> naviSet = new NaviSet<>(al);
        if (c.compare(fromElement, data.get(data.size() - 1)) == 0 && !inclusive) return naviSet;
        for (int i = data.indexOf(this.ceiling(fromElement)); i < data.size(); i++) {
            if (!(inclusive) && i == data.indexOf(this.ceiling(fromElement))) i++;
            al.add(data.get(i));
        }
        return naviSet;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Equivalent to {@code subSet(fromElement, true, toElement, false)}.
     *
     * @param fromElement - low endpoint (inclusive) of the returned set
     * @param toElement - high endpoint (exclusive) of the returned set
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        if (fromElement == null || toElement == null) {
            throw new NullPointerException();
        }
        if (c.compare(fromElement, data.get(data.size() - 1)) > 0) {
            throw new IllegalArgumentException();
        }
        ArrayList<T> al = new ArrayList<>();
        SortedSet<T> sortedSet = new NaviSet<>(al);
        if ((fromElement.equals(toElement))) return sortedSet;
        for (int i = data.indexOf(this.ceiling(fromElement)); i <= data.indexOf(this.floor(toElement)); i++) {
            if (i == data.indexOf(this.floor(toElement)) && toElement.equals(data.get(data.size() - 1))) break;
            al.add(data.get(i));
        }
        return sortedSet;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Equivalent to {@code headSet(toElement, false)}.
     *
     * @param toElement - high endpoint (exclusive) of the returned set
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    @Override
    public SortedSet<T> headSet(T toElement) {
         if (toElement == null) {
            throw new NullPointerException();
        }
        if (c.compare(toElement, data.get(0)) < 0) {
            throw new IllegalArgumentException();
        }
        ArrayList<T> al = new ArrayList<>();
        SortedSet<T> sortedSet = new NaviSet<>(al);
        for (int i = 0; i <= data.indexOf(this.floor(toElement)); i++) {
            if (i == data.indexOf(this.floor(toElement)) && toElement.equals(data.get(i))) break;
            al.add(data.get(i));
        }
        return sortedSet;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Equivalent to {@code tailSet(fromElement, true)}.
     *
     * @param fromElement - low endpoint (inclusive) of the returned set
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        if (fromElement == null) {
            throw new NullPointerException();
        }
        if (c.compare(fromElement, data.get(data.size() - 1)) > 0) {
            throw new IllegalArgumentException();
        }
        ArrayList<T> al = new ArrayList<>();
        SortedSet<T> sortedSet = new NaviSet<T>(al);
        for (int i = data.indexOf(this.ceiling(fromElement)); i < data.size(); i++) {
            al.add(data.get(i));
        }
        return sortedSet;
    }

    /**
     * Returns the comparator used to order the elements in this set,
     * or <tt>null</tt> if this set uses the {@linkplain Comparable
     * natural ordering} of its elements.
     *
     * @return the comparator used to order the elements in this set,
     * or <tt>null</tt> if this set uses the natural ordering
     * of its elements
     */
    @Override
    public Comparator<? super T> comparator() {
        return c;
    }

    /**
     * Returns the first (lowest) element currently in this set.
     *
     * @return the first (lowest) element currently in this set
     * @throws NoSuchElementException if this set is empty
     */
    @Override
    public T first() throws NoSuchElementException {
        if (!(data.isEmpty())) {
            return data.get(0);
        }
        else {
            throw new NoSuchElementException("No such element!");
        }
    }

    /**
     * Returns the last (highest) element currently in this set.
     *
     * @return the last (highest) element currently in this set
     * @throws NoSuchElementException if this set is empty
     */
    @Override
    public T last() throws NoSuchElementException {
        if (!(data.isEmpty())) {
            return data.get(data.size() - 1);
        }
        else {
            throw new NoSuchElementException("No such element!");
        }
    }
    /**Removes the specified element from this set if it is present
     * @return the specified element from this set if it is present*/
    public boolean remove (Object el) {
        if (this.ceiling((T) el) == el) {
            data.remove(el);
            return true;
        }
        return false;
    }
    /**Returns true if this set contains the specified element
     * @return true if this set contains the specified element*/
    public boolean contains(Object el) {
        if (this.ceiling((T) el) == el) {
            return true;
        }
        return false;
    }
    /**Returns true if this set contains no elements
     * @return true if this set contains no elements*/
    public boolean isEmpty() {
        return data.isEmpty();
    }
    /**Returns whether instances of the class are equal to each other
     * @return whether instances of the class are equal to each other*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return false;
        if (o == null || getClass() != o.getClass()) return false;
        NaviSet<T> naviSet = (NaviSet<T>) o;
        if (this.size() != naviSet.size()) return false;
        for (int i = 0; i < this.size(); i++) {
            if (!(this.data.get(i).equals(naviSet.data.get(i)))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), data, c, col, flag);
    }
}