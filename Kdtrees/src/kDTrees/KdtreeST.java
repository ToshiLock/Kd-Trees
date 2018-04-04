package kDTrees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdOut;

public class KdtreeST<T> {
	
	private Node root;
	
	// construct an empty symbol table of points
	public KdtreeST(){}

	// is the symbol table empty? 
	public boolean isEmpty()
	{
		return root == null;
	}

	// number of points 
	public int size()
	{
		if(isEmpty())
			return 0;
		
		return root.size;
	}

	// associate the value val with point p
	public void put(Point2D p, T val)
	{
		if (p == null)
			throw new java.lang.NullPointerException("the point must not be null");
		if (val == null)
			throw new java.lang.NullPointerException("the value must not be null");
		
		if(root == null)
		{
			root = new Node(p,val);
			return;
		}
		
		put(root, p, val);
		
	}
	//travels down the tree till it finds where to put the new node
	private void put(Node current, Point2D p, T val)
	{
		//if the new point is already in the tree
		if(current.point.equals(p))
		{
			current.value = val;
		}
		
		double comp = compare(current, p);
		if(comp >= 0)
		{			
			//can't use null base case like in video because we need the parent
			//to determin the horizontal or vertical; 
			if(current.right != null)
			{
				put(current.right, p, val);
				resize(current);
			}
			else
			{
				current.right = new Node(p,val,!current.horizontal);
				resize(current);
			}	
			return;
		}
		
		if (current.left != null)
		{
			put(current.left,p, val);
			resize(current);
		}
		else
		{
			current.left = new Node(p,val,!current.horizontal);	
			resize(current);
		}
	}
	
	/*
	 * possible second idea for put
	 * cleaner and closer to the video
	 * 
	 * private Node put(Node current, Point2D p, T val, boolean horizontal) 
	 * {
	 * 		if (current == null)
	 * 		{
	 * 			return new Node(p, val, horizontal);
	 * 		}
	 * 
	 * 		if(current.point.equals(p))
	 * 		{
	 * 			current.value = val;
	 * 		}
	 * 
	 * 		double comp = compare(current, p);
	 * 		if (comp >= 0)
	 * 		{
	 * 			current.right = put(current.right, p, val, !horizontal);
	 * 			return current;
	 * 		}
	 * 		else
	 * 		{
	 * 			current.left = put(current.left, p, val, !horizontal);
	 * 			return current;
	 * 		}
	 * 		
	 * 
	 * }
	 */
	
	//method that uses whether we are horizintal or vertical to decide what to do with the node.
	private double compare(Node current, Point2D that)
	{
		if(current.horizontal)
		{
			return current.point.y() - that.y();
		}
		
		return current.point.x() - that.x();
	}
	
	// value associated with point p 
	public T get(Point2D p)
	{
		if (p == null)
			throw new java.lang.NullPointerException("the point must not be null");
		
		return get(p, root);
	}
	
	private T get(Point2D p , Node n)
	{
		//base cases
		if (n == null)
			return null;
		if (n.point.equals(p))
			return n.value;
		
		//desides which path to go down
		double cmp = compare(n,p);
		if(cmp < 0)
			return get(p,n.left);
		else
			return get(p,n.right);
	}

	// does the symbol table contain point p? 
	public boolean contains(Point2D p)
	{
		if (p == null)
			throw new java.lang.NullPointerException("the point must not be null");
		
		return get(p, root) != null;
	}
	  

	// all points in the symbol table
	public Iterable<Point2D> points()
	{
		Queue<Point2D> points = new Queue<Point2D>(); 
		Queue<Node> temp = new Queue<Node>();
		Node current;
		
		temp.enqueue(root);
		
		while(!temp.isEmpty())
		{
			current = temp.dequeue();
			
			if(current.left != null)
				temp.enqueue(current.left);
			
			if(current.right != null)
				temp.enqueue(current.right);
			
			points.enqueue(current.point);
		}
		
		return points;
	}

	// all points that are inside the rectangle 
	public Iterable<Point2D> range(RectHV rect)
	{
		if(rect == null)
			throw new java.lang.NullPointerException("the rectangle must not be null");
		
		return null;
	}
	 
	// a nearest neighbor to point p; null if the symbol table is empty 
	public Point2D nearest(Point2D p)
	{
		if(p == null)
			throw new java.lang.NullPointerException("the point must not be null");
		
		if(isEmpty())
			return null;
		
		return null;
	}
	
	private void resize(Node n)
	{
		int r = (n.right != null)? n.right.size : 0; 
		int l = (n.left != null)? n.left.size: 0;
		
		n.size = r + l + 1;
	}
	
	private class Node
	{
		private static final boolean HORIZONTAL = true;
	    private static final boolean VERTICAL = false;
		
	    Point2D point;
	    T value;
		Node right;
		Node left;
		boolean horizontal;
		int size;
		
		private Node(Point2D p, T val)
		{
			this.point = p;
			this.value = val;
			this.right = null;
			this.left = null;
			this.horizontal = HORIZONTAL;
			this.size = 1;
		}
		
		private Node(Point2D p, T val, boolean horizontal)
		{
			this.point = p;
			this.value = val;
			this.horizontal = horizontal;
			this.right = null;
			this.left = null;
			this.size = 1;
		}
		
		@Override
		public String toString(){
			return point.toString() + " " + value.toString();
		}
	}
	  
	public static void main(String[] args)    
	{
		KdtreeST<Integer> test = new KdtreeST<Integer>();
		
		test.put(new Point2D(3, 0), 10);
		test.put(new Point2D(1, 0), 20);
		test.put(new Point2D(0, 2), 30);
		test.put(new Point2D(0, 0), 40);
		test.put(new Point2D(4, 3), 40);
		
		StdOut.println(test.size());
		
		for(Point2D p : test.points())
		{
			StdOut.println(p);
		}
	}


}
