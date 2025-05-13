package umbcs680.hw10;




public interface Comparator<T> {
    /**
     * Compares two objects.
     * @param o1 First object
     * @param o2 Second object
     * @return a negative integer, zero, or a positive integer
     *         if o1 is less than, equal to, or greater than o2
     */
    int compare(T o1, T o2);
}
