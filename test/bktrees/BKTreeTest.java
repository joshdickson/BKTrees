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
import static org.junit.Assert.assertTrue;

import java.util.AbstractMap;
import java.util.Set;

import org.junit.Test;

/**
 * jUnit tests for our BKTree class
 * @author Joshua Dickson
 * @version February 19, 2014
 */
public class BKTreeTest {

	/**
	 * Test functionality of the tree
	 */
	@Test
	public void testTree() {
		BKTree<String> tree = new BKTree<String>(new SimpleStringEdit());
		
		// set up a number of nodes and load into our tree
		tree.add(new Node<String>("tuck"));
		tree.add(new Node<String>("buck"));
		tree.add(new Node<String>("muck"));
		tree.add(new Node<String>("such"));
		tree.add(new Node<String>("sees"));
		tree.add(new Node<String>("more"));
		tree.add(new Node<String>("your"));
		tree.add(new Node<String>("gets"));
		tree.add(new Node<String>("that"));
		tree.add(new Node<String>("bird"));
		tree.add(new Node<String>("dogs"));
		tree.add(new Node<String>("cats"));
		tree.add(new Node<String>("rain"));
		tree.add(new Node<String>("toll"));
		
		assertEquals(tree.getTreeSize(), 14);
		
		AbstractMap.SimpleEntry<Set<Node<String>>, Integer> query = 
				tree.getMatchList(new Node<String>("plus"), 0);
		
		assertTrue(query.getKey().isEmpty());
		
		
		query = tree.getMatchList(new Node<String>("thas"), 1);
		
		assertEquals(query.getKey().size(), 1);
		assertTrue(testHelper(query.getKey(), "that"));
		

		query = tree.getMatchList(new Node<String>("yuck"), 1);
		
		assertEquals(query.getKey().size(), 3);
		assertTrue(testHelper(query.getKey(), "tuck"));
		assertTrue(testHelper(query.getKey(), "buck"));
		assertTrue(testHelper(query.getKey(), "muck"));
		
		assertTrue(query.getValue() < 5);
		
	}
	
	/**
	 * Check if the node set contains an entry for the given value
	 * @param nodeSet the set of nodes
	 * @param val the value to check
	 * @return true if the set contains an entry for the given value, false otherwise
	 */
	private boolean testHelper(Set<Node<String>> nodeSet, String val) {
		for(Node<String> n : nodeSet) {
			if (n.getNodeContent().equalsIgnoreCase(val)) 
				return true;
		}
		return false;
	}

}
