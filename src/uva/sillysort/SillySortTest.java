package uva.sillysort;

import static junit.framework.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SillySortTest {

	Main silly;
	@Before
	public void load() {
		silly = new Main();
	}
	
	@Test
	public void oneSwapOk() {
		Integer totalSwap = silly.sort(new Integer[] {3, 2, 1});
		assertEquals(new Integer(4), totalSwap);
	}
	
	@Test
	public void threeSwapsNotChangingPivot() {
		Integer totalSwap = silly.sort(new Integer[] {8, 1, 2, 4});
		assertEquals(new Integer(17), totalSwap);
	}
	
	@Test
	public void fiveSwapsPivotStartingInFinalPlace() {
		Integer totalSwap = silly.sort(new Integer[] {1, 8, 9, 7, 6});
		assertEquals(new Integer(41), totalSwap);
	}
	
	@Test
	public void fourSwapsChangingPivot() {
		Integer totalSwap = silly.sort(new Integer[] {8, 4, 5, 3, 2, 7});
		assertEquals(new Integer(34), totalSwap);
	}
	
	@Test
	public void swapWithElementInCorrectPosition() {
		Integer totalSwap = silly.sort(new Integer[] {1, 2, 8, 9, 7, 6});
		assertEquals(new Integer(41), totalSwap);
	}
	
	@Test
	public void noSwap() {
		Integer totalSwap = silly.sort(new Integer[] {1, 2, 8, 9});
		assertEquals(new Integer(0), totalSwap);
	}
	
	@Test
	public void twoSwapsNotMinor() {
		Integer totalSwap = silly.sort(new Integer[] {1, 2, 8, 7, 6, 9, 11, 10});
		assertEquals(new Integer(35), totalSwap);
	}
	
	@Test
	public void changePivotInOneSubSet() {
		Integer totalSwap = silly.sort(new Integer[] {1, 3, 2, 8, 9, 7, 6, 11, 10});
		assertEquals(new Integer(67), totalSwap);
	}
	
	@Test
	public void doNotSwapPivotBoundaryTest() {
		Integer totalSwap = silly.sort(new Integer[] {1, 2, 8, 9, 6});
		assertEquals(new Integer(29), totalSwap);
	}
	
	
}
