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

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Set;

/**
 * An implementation of a BK (Burkhard-Keller) tree
 * @author Joshua Dickson
 * @version February 19, 2014
 *
 * @param <T>
 */
public class BKTree<T> {
	
	private Node<T> root;
	private int treeSize;
	private final Comparator<T> comparator;
	
	/**
	 * Construct a BKTree instance
	 * @param comparator the comparator for finding the edit distance between nodes
	 */
	public BKTree(Comparator<T> comparator) {
		this.root = null;
		this.comparator = comparator;
		this.treeSize = 0;
	}
	
	/**
	 * Get the list of matching nodes
	 * @param matchNode the node to match for
	 * @param editDistance the maximum edit distance to the match node
	 * @return the set of matching nodes
	 */
	public AbstractMap.SimpleEntry<Set<Node<T>>, Integer> getMatchList(
			Node<T> matchNode, int editDistance) {
		AbstractMap.SimpleEntry<Set<Node<T>>, Integer> logger = new 
				AbstractMap.SimpleEntry<Set<Node<T>>, Integer>(new HashSet<Node<T>>(0), 0);
		return getMatches(root, matchNode, editDistance, logger);
	}
	
	/**
	 * A function to allow a recursive search through the tree
	 * @param root the root at which to begin searching
	 * @param matchNode the node to match
	 * @param editDistance the maximum allowed edit distance
	 * @param logger the set and query logger
	 * @return the set of matching nodes in the subtree anchored by the root node
	 */
	private AbstractMap.SimpleEntry<Set<Node<T>>, Integer> getMatches(Node<T> root, 
			Node<T> matchNode, int editDistance, AbstractMap.SimpleEntry<Set<Node<T>>, 
			Integer> logger) {
		
		// note that we have visited a node
		logger.setValue(logger.getValue() + 1);
		
		// get the edit distance to the node
		int edit = comparator.getEditDistance(root, matchNode);
		
		// build the upper and lower edit bounds
		int editUpperBound = edit + editDistance;
		int editLowerBound = edit - editDistance;
						
		// add the node to the match set if it's within the edit distance of our local root
		if(edit <= editDistance) {
			logger.getKey().add(root);
		}
		
		// look at all of the children
		for(Node<T> child : root.getChildMap().keySet()) {
			
			int editToChild = comparator.getEditDistance(child, root);
						
			if(editLowerBound <= editToChild && editToChild <= editUpperBound) {
				logger = getMatches(child, matchNode, editDistance, logger);
			}
		}
		
		return logger;
	}
	
	/**
	 * Add a node to the tree structure
	 * @param node
	 */
	public void add(Node<T> node) {
			if(root == null) {
				root = node;
				treeSize++;
			} else 
				addToTree(node, root);
	}
	
	/**
	 * Get the number of nodes in the tree
	 * @return
	 */
	public int getTreeSize() {
		return treeSize;
	}
	
	/**
	 * Add a node to the tree
	 * @param node the node to add
	 * @param root the root to attempt to add the node to
	 */
	private void addToTree(Node<T> node, Node<T> root) {
		
		// get the edit distance from our root to the node to add
		int edit = comparator.getEditDistance(root, node);

		// add the new node to a child if necessary
		for(Node<T> childNode : root.getChildMap().keySet()) {
			if(root.getChildMap().get(childNode) == edit) {
				addToTree(node, childNode);
				return;
			}
		}

		// there was no child that matched the edit distance, so we'll create a new entry
		root.getChildMap().put(node, edit);
		treeSize++;
		
	}

}
