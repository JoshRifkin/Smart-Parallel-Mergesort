package sorting;

import java.util.Arrays;
import java.util.Random;

public class MergesortParallelSmart {
	
	static Long S;
	
	private MergesortParallelSmart() {
		
	}
	
	public static void main(String[] args) {
		S = findFaster();
		Comparable[] sampleArray = createTestArray(S*10);
		sort(sampleArray);
	}
	
	public static long sort(Comparable[] a)
    {
    	long currTime = System.nanoTime();
   		Comparable[] aux = new Comparable[a.length];
    	sort(a, aux, 0, a.length - 1);
    	long total = System.nanoTime() - currTime;
    	System.out.println("Runtime in nanoseconds: " + total);
    	return total;
    }

    // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi)
    {
    	if (hi <= lo)
    		return;
    	int mid = lo + (hi - lo) / 2;
    	//recursively: reduce sub-arrays to length 1, merge up 
    	if(a.length >= S) {
    		Thread t = newThread(a, aux, lo, mid); 
    		t.start();
    		try{
    			t.join();
    		} catch (Exception e) {
    			System.out.println("Error.");
    		}
    	}
    	else {
    		sort(a, aux, lo, mid);
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

	private static Long findFaster() {
		long unthreadedTime = 0;
		long threadedTime = 0;
		long n = 10;
		
		while(threadedTime < unthreadedTime) {
			Comparable[] testArray = createTestArray(n);
			Comparable[] testArrayCopy = Arrays.copyOf(testArray, testArray.length);
			
			unthreadedTime = Mergesort.sort(testArray);
			threadedTime = MergeSortParallelNaive.sort(testArrayCopy);
			
			n *= 10;
		}
		
		return n;
	}

	private static Comparable[] createTestArray(long length) {
		Comparable[] a = new Comparable[(int) length];
		Random rand = new Random();
		for(int i = 0; i < a.length; i++) {
			a[i] = rand.nextLong();
		}
		
		return a;
	}
}
