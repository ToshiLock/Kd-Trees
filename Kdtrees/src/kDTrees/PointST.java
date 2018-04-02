package kDTrees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.RedBlackBST;

public class PointST<T> {

	private RedBlackBST<Point2D, T> symTable;
	
	public PointST()
	{
		// construct an empty symbol table of points
		symTable = new RedBlackBST<Point2D,T>(); 
	}
	 
	public boolean isEmpty()
	{
		// is the symbol table empty? 
		return symTable.isEmpty();
	}
	  
	public int size()
	{
		// number of points 
		return symTable.size();
	}
	  
	public void put(Point2D p, T val)
	{
		if (p == null)
			throw new java.lang.NullPointerException("the point must not be null");
		if (val == null)
			throw new java.lang.NullPointerException("the value must not be null");
		
		// associate the value val with point p
		symTable.put(p, val);
	}
	  
	public T get(Point2D p)
	{
		if (p == null)
			throw new java.lang.NullPointerException("the point must not be null");
		
		// value associated with point p 
		return symTable.get(p);
	}
	  
	public boolean contains(Point2D p)
	{
		if (p == null)
			throw new java.lang.NullPointerException("the point must not be null");
		
		// does the symbol table contain point p? 
		return symTable.contains(p);
	}
	  
	public Iterable<Point2D> points()
	{
		// all points in the symbol table
		Queue<Point2D> points = new Queue<Point2D>(); 
		int size =  symTable.size();
		
		for(int i = 0; i < size ; i++)
		{
			points.enqueue(symTable.select(i));
		}
		
		return points;
	}
	  
	public Iterable<Point2D> range(RectHV rect)
	{
	 // all points that are inside the rectangle 
	}
	 
	public Point2D nearest(Point2D p)
	{
	 // a nearest neighbor to point p; null if the symbol table is empty 
	}
	  
	public static void main(String[] args)    
	{
	 // unit testing of the methods (not graded) 
	}
}
