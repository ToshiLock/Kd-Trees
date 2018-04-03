package kDTrees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdOut;

public class KdtreeST<T> {
	
	private Node Root;
	
	// construct an empty symbol table of points
	public KdtreeST()
	{
		
	}

	// is the symbol table empty? 
	public boolean isEmpty()
	{
		return Root == null;
	}

	// number of points 
	public int size()
	{
		
	}

	// associate the value val with point p
	public void put(Point2D p, T val)
	{
		if (p == null)
			throw new java.lang.NullPointerException("the point must not be null");
		if (val == null)
			throw new java.lang.NullPointerException("the value must not be null");
		
		if(Root == null)
		{
			Root = new Node<T>(p,val);
			return;
		}
		
		put(Root, p, val);
		
	}
	//travels down the tree till it finds where to put the new node
	private void put(Node<T> current, Point2D p, T val)
	{
		double comp = compare(current, p, current.horizontal);
		
		if(comp > 0)
		{			
			if(current.right != null)
				put(current.right, p, val);
			else
				current.right = new Node<T>(p,val,!current.horizontal);
			return;
		}
		
		if (current.left != null)
			put(current.left,p, val);
		else
			current.left = new Node<T>(p,val,!current.horizontal);	
	}
	
	//method that uses whether we are horizintal or vertical to decide what to do with the node.
	private double compare(Node<T> current, Point2D that, boolean Horizontal)
	{
		if(Horizontal)
		{
			return current.point.x() - that.x();
		}
		
		return current.point.x() - that.y();
	}
	
	// value associated with point p 
	public T get(Point2D p)
	{
		if (p == null)
			throw new java.lang.NullPointerException("the point must not be null");
	}

	// does the symbol table contain point p? 
	public boolean contains(Point2D p)
	{
		if (p == null)
			throw new java.lang.NullPointerException("the point must not be null");
	}
	  

	// all points in the symbol table
	public Iterable<Point2D> points()
	{
		Queue<Point2D> points = new Queue<Point2D>(); 
	}

	// all points that are inside the rectangle 
	public Iterable<Point2D> range(RectHV rect)
	{
		if(rect == null)
			throw new java.lang.NullPointerException("the rectangle must not be null");\
	}
	 
	// a nearest neighbor to point p; null if the symbol table is empty 
	public Point2D nearest(Point2D p)
	{
		if(p == null)
			throw new java.lang.NullPointerException("the point must not be null");
		
		if(isEmpty())
			return null;
	}
	
	private class Node<U>
	{
		private static final boolean HORIZONTAL = true;
	    private static final boolean VERTICAL = false;
		
	    Point2D point;
	    U value;
	    
		Node right = null;
		Node left = null;
		boolean horizontal = HORIZONTAL;
		
		private Node(Point2D p, U val)
		{
			this.point = p;
			this.value = val;
		}
		
		private Node(Point2D p, U val, boolean horizontal)
		{
			this.point = p;
			this.value = val;
			this.horizontal = horizontal;
		}
		
	}
	  
	public static void main(String[] args)    
	{
		
	}


}
