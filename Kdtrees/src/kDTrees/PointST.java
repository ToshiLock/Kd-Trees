package kDTrees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class PointST {

	 public PointST()
	 {
		 // construct an empty symbol table of points 
	 }
	  
	 public boolean isEmpty()
	 {
		 // is the symbol table empty? 
	 }
	   
	 public int size()
	 {
		 // number of points 
	 }
	   
	 public void put(Point2D p, Value val)
	 {
		 // associate the value val with point p
	 }
	   
	 public Value get(Point2D p)
	 {
		 // value associated with point p 
	 }
	   
	 public boolean contains(Point2D p)
	 {
		 // does the symbol table contain point p? 
	 }
	   
	 public Iterable<Point2D> points()
	 {
		 // all points in the symbol table 
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
