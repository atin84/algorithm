package atin84.dovelet.stairs3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import atin84.dovelet.stairs3.Center1;

public class Center1Test {
	
	private String O = "O";
	private String X = "X";
	
	@Test
	public void test1() {
		Center1 center = new Center1();
		assertEquals(X, center.getResult(4));	
		assertEquals(O, center.getResult(6));
		assertEquals(O, center.getResult(35));
	}
	
}
