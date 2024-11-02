package es.ull.esit.utilities;

import java.util.BitSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

// Sirve para calcular todos los subconjuntos de un conjunto dado
/**
 * @class PowerSet
 * @brief Class to calculate the power set of a given set.
 */
public class PowerSet<E> implements Iterator<Set<E>>, Iterable<Set<E>> {

    private E[] arr = null;
    private BitSet bset = null;

    /**
     * @brief Constructor of the class.
     * @param set
     */
    @SuppressWarnings("unchecked")
    public PowerSet(Set<E> set) {
        this.arr = (E[]) set.toArray();
        this.bset = new BitSet(this.arr.length + 1);
    }

    /**
     * @brief Method to calculate the edges of the graph.
     */
    @Override
    public boolean hasNext() {
        return !this.bset.get(this.arr.length);
    }

    /**
     * @brief Method to calculate the edges of the graph.
     */
    @Override
    public Set<E> next() {
        Set<E> returnSet = new TreeSet<>();
        for (int i = 0; i < this.arr.length; i++) {
            if (this.bset.get(i)) {
                returnSet.add(this.arr[i]);
            }
        }
        for (int i = 0; i < this.bset.size(); i++) {
            if (!this.bset.get(i)) {
                this.bset.set(i);
                break;
            } else {
                this.bset.clear(i);
            }
        }
        return returnSet;
    }

    /**
     * @brief Method to calculate the edges of the graph.
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not Supported!");
    }

    /**
     * @brief Method to calculate the edges of the graph.
     */
    @Override
    public Iterator<Set<E>> iterator() {
        return this;
    }
}