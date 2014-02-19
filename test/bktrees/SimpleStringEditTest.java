/**
 * The MIT License (MIT)
 * 
 * Copyright (c) 2014 Joshua Dickson
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package bktrees;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * jUnit tests for our comparator
 * @author Joshua Dickson
 * @version February 19, 2014
 */
public class SimpleStringEditTest {
	
	private Comparator<String> comparator;
	
	/**
	 * Instantiate the comparator
	 */
	@Before
	public void before() {
		comparator = new SimpleStringEdit();
	}

	/**
	 * Test string equality
	 */
	@Test
	public void test() {

		Node<String> n1 = new Node<String>("Hello");
		Node<String> n2 = new Node<String>("hello");
		
		assertEquals(comparator.getEditDistance(n1, n2), 0);
	}
	
	/**
	 * Test comparator results for a number of word pairs
	 */
	@Test
	public void testSomeEditDifferences() {
		
		Node<String> n1 = new Node<String>("tuck");
		Node<String> n2 = new Node<String>("duck");
		
		assertEquals(comparator.getEditDistance(n1, n2), 1);
		
		n1 = new Node<String>("tuck");
		n2 = new Node<String>("such");
		
		assertEquals(comparator.getEditDistance(n1, n2), 2);
		
		n1 = new Node<String>("tuck");
		n2 = new Node<String>("both");
		
		assertEquals(comparator.getEditDistance(n1, n2), 4);
		
	}

}
