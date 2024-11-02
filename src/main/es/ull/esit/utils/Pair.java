package es.ull.esit.utils;
import java.util.Objects;

/**
 * @brief Class to create a pair of objects.
 * @param <F>
 * @param <S>
 */
public class Pair<F, S> {
    public final F first;
    public final S second;

    /**
     * @brief Constructor of the class.
     * @param first
     * @param second
     */
    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    /**
     * @brief Method to know if two objects are equal.
     * @return boolean value.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) {
            return false;
        }
        Pair<?, ?> p = (Pair<?, ?>) o;
        return Objects.equals(p.first, first) && Objects.equals(p.second, second);
    }

    /**
     * @brief Method to calculate the hash code of the object.
     * @return int value.
     */
    @Override
    public int hashCode() {
        return (first == null ? 0 : first.hashCode()) ^ (second == null ? 0 : second.hashCode());
    }

    /**
     * @brief Method to create a pair.
     * @param a
     * @param b
     * @return Pair object.
     */
    public static <A, B> Pair <A, B> create(A a, B b) {
        return new Pair<A, B>(a, b);
    }
}
