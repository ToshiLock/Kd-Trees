package kDTrees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdOut;

public class PointST<T> {

	private RedBlackBST<Point2D, T> symTable;
	

	// construct an empty symbol table of points
	public PointST()
	{
		symTable = new RedBlackBST<Point2D,T>(); 
	}

	// is the symbol table empty? 
	public boolean isEmpty()
	{
		return symTable.isEmpty();
	}

	// number of points 
	public int size()
	{
		return symTable.size();
	}

	// associate the value val with point p
	public void put(Point2D p, T val)
	{
		if (p == null)
			throw new java.lang.NullPointerException("the point must not be null");
		if (val == null)
			throw new java.lang.NullPointerException("the value must not be null");
		
		symTable.put(p, val);
	}

	// value associated with point p 
	public T get(Point2D p)
	{
		if (p == null)
			throw new java.lang.NullPointerException("the point must not be null");
		
		return symTable.get(p);
	}

	// does the symbol table contain point p? 
	public boolean contains(Point2D p)
	{
		if (p == null)
			throw new java.lang.NullPointerException("the point must not be null");
		
		return symTable.contains(p);
	}
	  

	// all points in the symbol table
	public Iterable<Point2D> points()
	{
		Queue<Point2D> points = new Queue<Point2D>(); 
		int size =  symTable.size();
		
		for(int i = 0; i < size ; i++)
		{
			points.enqueue(symTable.select(i));
		}
		
		return points;
	}

	// all points that are inside the rectangle 
	public Iterable<Point2D> range(RectHV rect)
	{
		if(rect == null)
			throw new java.lang.NullPointerException("the rectangle must not be null");
		
		Queue<Point2D> points = new Queue<Point2D>();
		int size =  symTable.size();
		
		
		for(int i = 0; i < size ; i++)
		{
			if(rect.contains(symTable.select(i)))
			{
				points.enqueue(symTable.select(i));
			}
		}
		
		return points;
	}
	 
	// a nearest neighbor to point p; null if the symbol table is empty 
	public Point2D nearest(Point2D p)
	{
		if(p == null)
			throw new java.lang.NullPointerException("the point must not be null");
		
		if(isEmpty())
			return null;
		
		// already done empty check so this is safe
		double shortDistance = symTable.max().distanceTo(p); //something to compare to
		Point2D shortPoint = symTable.max();
		
		int size =  symTable.size();
		
		for(int i = 0; i < size ; i++)
		{
			if(symTable.select(i).distanceTo(p) < shortDistance)
			{
				shortPoint = symTable.select(i);
				shortDistance = shortPoint.distanceTo(p);
			}
		}
		
		return shortPoint;
	}
	  
	public static void main(String[] args)    
	{
		// unit testing of the methods (not graded) 
		PointST<Integer> test = new PointST<Integer>(); 
	
		
		for(Point2D p: test.points())
		{
			StdOut.println(p);
		}
		
		for(Point2D p : test.range(new RectHV(0, -1, 1, 4)))
		{
			StdOut.println(p);
		}
	}
}
