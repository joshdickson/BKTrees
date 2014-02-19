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

import java.util.HashMap;
import java.util.Map;


/**
 * A Node for our BKTree
 * @author Joshua Dickson
 * @version February 19, 2014
 *
 * @param <T>
 */
public class Node<T> {
		
	private final T nodeContent;
	private final Map<Node<T>, Integer> childrenEditMap;
	
	/**
	 * Construct a node
	 * @param nodeContent the contents of the node
	 */
	public Node(T nodeContent) {
		this.nodeContent = nodeContent;
		childrenEditMap = new HashMap<Node<T>, Integer>();
	}
	
	/**
	 * Get the node content
	 * @return
	 */
	public T getNodeContent() {
		return nodeContent;
	}

	/**
	 * Get the child nodes of this node
	 * @return the children
	 */
	public Map<Node<T>, Integer> getChildMap() {
		return childrenEditMap;
	}

	/**
	 * Add a child node entry to the child node map
	 * @param children the children to set
	 */
	public void addChild(Node<T> child, int editDistance) {
		childrenEditMap.put(child, editDistance);
	}

}
