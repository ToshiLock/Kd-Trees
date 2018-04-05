package kDTrees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdOut;

public class KdtreeST<T> {

	private static final boolean HORIZONTAL = true;
    private static final boolean VERTICAL = false;
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
		
		/*recursive solution
		 *root = put(root, p, val, VERTICAL, 0); //VERTIAL and 0 are arbitrary 
		 *resize(root); 
		 */
		
		//intereative solution?
		boolean axis = VERTICAL;
		double comp = 0;
		Node current = root;
		
		if (root ==null)
		{
			root = new Node(p,val,axis);
		}
		
		else
		{
			while(true)
			{
				comp = compare(current,p);
				if(comp < 0)
				{
					if(current.left != null)
					{
						current = current.left;
						axis = !axis;
					}
					else
					{
						current.left = new Node(p,val, axis);
						break;
					}
				}
				else // if comp >=0
				{
					if(current.right != null)
					{
						current = current.left;
						axis = !axis;
					}
					else
					{
						current.right = new Node(p,val, axis);
						break;
					}
				}
			}
		}
		
		
	}
	
	/*
	 * recursive solution 
	 *
	
	//travels down the tree till it finds where to put the new node
	private Node put(Node current, Point2D p, T val, boolean horizontal, double comp) 
	{
		if (current == null)
	  		return new Node(p, val, horizontal);

  		if(current.point.equals(p))
  			current.value = val;
	  	  	
  		comp = compare(current, p);
	  	if (comp >= 0)
	  	{
	  		current.right = put(current.right, p, val, !horizontal, comp);
	  		resize(current);
	  		return current;
	  	}
	  	else
	  	{
	  		current.left = put(current.left, p, val, !horizontal, comp);
	  		resize(current);
	  		return current;
	  	}	  
	  }
	  */
	
	// value associated with point p 
	public T get(Point2D p)
	{
		if (p == null)
			throw new java.lang.NullPointerException("the point must not be null");
		
		// recursive solution
		//return get(p, root);
		
		double comp = 0;
		Node current = root;
		
		while(current!= null && !current.point.equals(p))
		{
			comp = compare(current,p);
			if(comp < 0)
				current = current.left;
			else
				current = current.right;
		}

		if(current == null)
			return null;
		
		return current.value;
	}
	
	/* recursive soltion
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
	*/
	  

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
		
		Queue<Point2D> points = new Queue<Point2D>();
		
		range(rect, points, root);
		
		return points;
	}
	
	private Iterable<Point2D> range(RectHV rect, Queue<Point2D> q, Node current)
	{
		//base case
		if(current == null)
		{
			return q;
		}
		
		//add point
		if(rect.contains(current.point))
			q.enqueue(current.point);
		
		//decide node orientaion 
		if(current.orientation == VERTICAL)
		{
			//left
			if (rect.xmin() < current.point.x())
				range(rect,q,current.left);
			
			//right
			if (rect.xmax() >= current.point.x())
				range(rect,q,current.right);
		}
		else // if horizontal
		{
			//down
			if(rect.ymin() < current.point.y())
				range(rect,q,current.left);
			
			//up
			if(rect.ymax() >= current.point.y())
				range(rect,q,current.right);
		}
		return q;
	}
	
	 
	// a nearest neighbor to point p; null if the symbol table is empty 
	public Point2D nearest(Point2D p)
	{
		if(p == null)
			throw new java.lang.NullPointerException("the point must not be null");
		
		if(isEmpty())
			return null;
		
		//create and pass to subsiquent calls so no recursive creation of variables
		return nearest(p, root, new Point2D(0,0), Integer.MAX_VALUE, 0);
	}
	
	private Point2D nearest(Point2D p, Node current, Point2D close, double distance, double cmp)
	{
		//base case
		if(current == null)
			return close;
		
		//add closer point
		if(current.point.distanceTo(p) < distance)
		{
			close = current.point;
			distance = current.point.distanceTo(p);
		}
		
		//prunning step neeeded 
		//read assighment and work out--------------------------------------------------------------------
		//if(current.point.distanceTo(p) < )
		
		cmp = compare(current, p);
		
		//if given point is closer to right node go right first
		if(cmp >= 0)
		{
			close = nearest(p,current.right, close,distance,cmp);
			close = nearest(p,current.left, close,distance,cmp);
		}
		else // go left first
		{
			close = nearest(p,current.left, close,distance,cmp);
			close = nearest(p,current.right, close,distance,cmp);
		}
		
		return close;
	}
	
	private void resize(Node n)
	{
		int r = (n.right != null)? n.right.size : 0; 
		int l = (n.left != null)? n.left.size: 0;
		n.size = r + l + 1;
	}
	
	//method that uses whether we are horizintal or vertical to decide what to do with the node.
	private double compare(Node current, Point2D that)
	{
		if(current.orientation == HORIZONTAL)
		{
			return that.y() - current.point.y();
		}
		
		return that.x()- current.point.x();
	}
	
	private class Node
	{		
	    Point2D point;
	    T value;
		Node right;
		Node left;
		boolean orientation;
		int size;
		
		private Node(Point2D p, T val, boolean orientation)
		{
			this.point = p;
			this.value = val;
			this.orientation = orientation;
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
		test.put(new Point2D(.5, 1), 40);
		test.put(new Point2D(4, 3), 40);
		
		StdOut.print("size() test");
		StdOut.println(test.size());

		StdOut.println("\nget() test");
		StdOut.println(test.get(new Point2D(.5,1)));
		
		
		StdOut.println("\nIn order points() test");
		for(Point2D p : test.points())
		{
			StdOut.println(p);
		}

		StdOut.println("\nrectangle test");
		
		for(Point2D p : test.range(new RectHV(1,0,3,0)))
		{
			StdOut.println(p);
		}
		
		StdOut.println("\nnearest() test");
		StdOut.println(test.nearest(new Point2D(0,3)));
		
	}
}
