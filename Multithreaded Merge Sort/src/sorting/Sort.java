package sorting;

public abstract class Sort
{
    /**
     * is v < w ?
     * @param v
     * @param w
     * @return
     */
    @SuppressWarnings("unchecked")
    protected static boolean less(Comparable v, Comparable w)
    {
	return v.compareTo(w) < 0;
    }
    
    /**
     * exchange a[i] and a[j]
     * @param a
     * @param i
     * @param j
     */
    protected static void exch(Object[] a, int i, int j)
    {
	Object swap = a[i];
	a[i] = a[j];
	a[j] = swap;
    }
}
