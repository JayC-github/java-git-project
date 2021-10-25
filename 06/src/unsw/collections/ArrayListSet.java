/**
 *
 */
package unsw.collections;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * An implementation of Set that uses an ArrayList to store the elements.
 *
 * @invariant All e in elements occur only once
 *
 * @author Robert Clifton-Everest
 *
 */
public class ArrayListSet<E> implements Set<E> {

    private ArrayList<E> elements;

    public ArrayListSet() {
        elements = new ArrayList<>();
    }

    @Override
    public void add(E e) {
        if (!elements.contains(e)) {
            elements.add(e);
        }
    }

    @Override
    public void remove(E e) {
        elements.remove(e);
    }

    @Override
    public boolean contains(Object e) {
        return elements.contains(e);
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean subsetOf(Set<?> other) {
        for (E element: elements) {
            if (!other.contains(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return elements.iterator();
    }

    @Override
    public Set<E> union(Set<? extends E> other) {
        Set<E> unionSet = new ArrayListSet<>();
        unionSet = (Set<E>) this;
        for (E elements: other) {
            unionSet.add(elements);
        }

        return unionSet;
    }

    @Override
    public Set<E> intersection(Set<? extends E> other) {
        Set<E> interSet = new ArrayListSet<>();
        for (E element: elements) {
            if (other.contains(element)) {
                interSet.add(element);
            }
        }
        
        return interSet;
    }

    /**
     * For this method, it should be possible to compare all other possible sets
     * for equality with this set. For example, if an ArrayListSet<Fruit> and a
     * LinkedListSet<Fruit> both contain the same elements they are equal.
     * Similarly, if a Set<Apple> contains the same elements as a Set<Fruit>
     * they are also equal.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        // if two set are equal
        if (elements.equals(other)) {
            return true;
        }

        // if it's the other set
        if (other instanceof Set<?>) {
            Set<?> othersSet = (Set<?>) other;
            if (size() != othersSet.size()) {
                return false;
            }

            for (E e: elements) {
                if (!othersSet.contains(e)) {
                    return false;
                }
            }
            
            return true;
        }

        return true;
    }

}
