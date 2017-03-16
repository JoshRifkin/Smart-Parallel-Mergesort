package sorting;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

public class MergeSortParallelNaiveTest {

	@Test
	public void test() {
		Random rand = new Random();
		
		Long[] randomNums = new Long[10000];
		Long[] randomNums2 = new Long[100000];
		Long[] randomNums3 = new Long[1000000];
//		Long[] randomNums4 = new Long[10000000];
//		Long[] randomNums5 = new Long[100000000];
//		Long[] randomNums6 = new Long[100000000];
		
		for(int i = 0; i < randomNums.length; i++){
			randomNums[i] = rand.nextLong();
		}
		for(int i = 0; i < randomNums2.length; i++){
			randomNums2[i] = rand.nextLong();
		}
		for(int i = 0; i < randomNums3.length; i++){
			randomNums3[i] = rand.nextLong();
		}
//		for(int i = 0; i < randomNums4.length; i++){
//			randomNums4[i] = rand.nextLong();
//		}
//		for(int i = 0; i < randomNums5.length; i++){
//			randomNums5[i] = rand.nextLong();
//		}
//		for(int i = 0; i < randomNums6.length; i++){
//			randomNums6[i] = rand.nextLong();
//		}
		
		Long[] randomNumsCopy = Arrays.copyOf(randomNums, randomNums.length);
		Long[] randomNums2Copy = Arrays.copyOf(randomNums2, randomNums2.length);
		Long[] randomNums3Copy = Arrays.copyOf(randomNums3, randomNums3.length);
//		Long[] randomNums4Copy = Arrays.copyOf(randomNums4, randomNums4.length);
//		Long[] randomNums5Copy = Arrays.copyOf(randomNums5, randomNums5.length);
//		Long[] randomNums6Copy = Arrays.copyOf(randomNums6, randomNums6.length);
		
		Mergesort.sort(randomNums);
		MergeSortParallelNaive.sort(randomNumsCopy);
		Mergesort.sort(randomNums2);
		MergeSortParallelNaive.sort(randomNums2Copy);
		Mergesort.sort(randomNums3);
		MergeSortParallelNaive.sort(randomNums3Copy);
//		Mergesort.sort(randomNums4);
//		MergeSortParallelNaive.sort(randomNums4Copy);
//		Mergesort.sort(randomNums5);
//		MergeSortParallelNaive.sort(randomNums5Copy);
//		Mergesort.sort(randomNums6);
//		MergeSortParallelNaive.sort(randomNums6Copy);
	}

}
