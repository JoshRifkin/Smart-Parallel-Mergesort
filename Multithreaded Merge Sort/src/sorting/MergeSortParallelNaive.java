package sorting;




public class MergeSortParallelNaive extends Sort
{

	
	
    // This class should not be instantiated.
    private MergeSortParallelNaive()
    {
    }
    
    public static long sort(Comparable[] a)
    {
    	long currTime = System.nanoTime();
   		Comparable[] aux = new Comparable[a.length];
    	sort(a, aux, 0, a.length - 1);
    	long total = System.nanoTime() - currTime;
    	System.out.println("Runtime in nanoseconds for Parallel: " + total);
    	return total;
    }

    // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi)
    {
    	if (hi <= lo)
    		return;
    	int mid = lo + (hi - lo) / 2;
    	//recursively: reduce sub-arrays to length 1, merge up 
    	Thread t = newThread(a, aux, lo, mid); 
    	t.start();
    	try{
    		t.join();
    	} catch (Exception e) {
    		System.out.println("Error.");
    	}
    	sort(a, aux, mid + 1, hi);
    	merge(a, aux, lo, mid, hi);
    }
    private static Thread newThread(Comparable[] a, Comparable[] aux, int lo, int mid) {
		// TODO Auto-generated method stub
		return new Thread() {
			@Override
			public void run() {
				sort(a, aux, lo, mid);
			}
		};
		
	}

	// stably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo .. hi]
    // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi)
    {
	// copy to aux[]
	for (int k = lo; k <= hi; k++)
	    aux[k] = a[k];
	// merge back to a[]
	int left = lo, right = mid + 1;
	for (int current = lo; current <= hi; current++)
	{
	    if (left > mid) //left half exhausted
		a[current] = aux[right++]; //copy value from the right
	    else if (right > hi) //right half exhausted
		a[current] = aux[left++]; //copy value from the left
	    //neither exhausted - copy lower value
	    else if (less(aux[right], aux[left]))
		a[current] = aux[right++];
	    else
		a[current] = aux[left++];
	}
    }
    
}