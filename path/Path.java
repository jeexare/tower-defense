/**
 * A path object represents points that together trace a path.
 * 
 * 
 * @author Heather Rubio
 * @version 11.14.2022
 */

package path;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;

public class Path {

	private ArrayList<Point> pointCount;

	/**
	 * Constructor.
	 * 
	 * Creates an empty path.
	 */

	public Path() {
		pointCount = new ArrayList<Point>();
	}

	/**
	 * A constructor that takes a Scanner as a parameter.
	 * 
	 * 
	 * @param in an already opened scanner
	 */

	public Path(Scanner in) {
		pointCount = new ArrayList<Point>();
		int size = in.nextInt();
		for (int i = 0; i < size; i++) {
			Point n = new Point(in.nextInt(), in.nextInt());
			pointCount.add(n);
		}
	}

	/**
	 * Returns the size of the path
	 * 
	 */

	public int getPointCount() {
		return pointCount.size();
	}

	/**
	 * Returns the nth X coordinate in the path.
	 * 
	 * @param n the nth Point
	 * @return the X coordinate of n
	 */

	public int getX(int n) {
		Point thisPoint = pointCount.get(n);
		return (int) thisPoint.getX();
	}

	/**
	 * Returns the nth Y coordinate in the path.
	 * 
	 * @param n the nth Point
	 * @return the Y coordinate of n
	 */
 
	public int getY(int n){
		Point thisPoint = pointCount.get(n);
		return (int) thisPoint.getY();
	}

	/**
	 * Adds the specified (x, y) coordinate to the end of the path.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */

	public void add(int x, int y) {
		Point n = new Point(x, y);
		pointCount.add(n);
	}

	/**
	 * Returns a String representation of the path. Has a count, then the x and y
	 * coordinates of each point.
	 * 
	 * 
	 * @return String s, a String representation of the path
	 */

	public String toString() {
		String s = pointCount.size() + "\n";

		for (int i = 0; i < pointCount.size(); i++) // Goes through every single point
		{
			s += getX(i) + " " + getY(i) + "\n";
		}

		return s;
	}

	
	/*Draw method draws the path cool cool*/
	
	public void draw(Graphics g) {
	g.setColor(Color.RED);
	for(int i = 0; i < getPointCount() - 1; i++) {
         g.drawLine(getX(i), 
        		 getY(i), 
        		 getX(i + 1), 
        		 getY(i + 1));
         }
	}
	
	/**
	 * Get the length of the segment between points
	 * n-1 and n.
	 * 
	 * @param n the segment between n-1 and n
	 * @return the length of segment n
	 */
	public double getSegmentLength(int n) {
		double ourY = getY(n) - getY(n - 1); // Did this so it looks cleaner, it just stores each part of the equation in a variable
		double ourX = getX(n) - getX(n - 1);
		double length = Math.sqrt((ourY * ourY) + (ourX * ourX)); // Plug in the distance formula
		
		return length;
	}
	
	/**
	 * Get the total length of the path.
	 * 
	 * @param n the segment between n and n-1
	 * @return the length of segment n
	 */
	public double getLength() {
		double totalLength = 0;
		 for (int x = 1; x < getPointCount(); x++)  // Loops through every point
			 totalLength += getSegmentLength(x); // Gets every segment length and adds it to our total
		 
		return totalLength;
	}
	
	/** 
	 * Given a percentage between 0% and 100%, this method calculates
	 * the location along the path that is exactly this percentage
	 * along the path. The location is returned in a Point object
	 * (integer x and y), and the location is a screen coordinate.
	 * 
	 * If the percentage is less than 0%, the starting position is
	 * returned. If the percentage is greater than 100%, the final
	 * position is returned.
	 * 
	 * Callers must not change the x or y coordinates of any returned
	 * point object (or the caller could be changing the path).
	 * 
	 * @param percentTraveled a distance along the path
	 * @return the screen coordinate of this position along the path
	 */
	 public Point convertToCoordinates(double percentTraveled) {
		 if (percentTraveled <= 0) return new Point(getX(0), getY(0));
		 if (percentTraveled >= 1) return new Point(getX(getPointCount() - 1), getY(getPointCount() - 1));
		 
			double totalPixels = getLength() * percentTraveled;
			double traveledInSegment = totalPixels;
			int currentSegment = 0;
			for (int x = 1; x < getPointCount(); x++) {
				traveledInSegment = traveledInSegment - getSegmentLength(x);
				if (traveledInSegment < 0) {
					traveledInSegment += getSegmentLength(x);
					currentSegment = x;
					break;
				}
			}

		 double percentageAcross = traveledInSegment / getSegmentLength(currentSegment); // 'percentage across' the segment. 
		 
		 // Weighted average of each coordinate
		 
		 int x = (int) ((getX(currentSegment) * percentageAcross   +   (getX(currentSegment - 1) * (1 - percentageAcross))));
		 int y = (int) ((getY(currentSegment) * percentageAcross   +   (getY(currentSegment - 1) * (1 - percentageAcross))));
		 
		 Point n = new Point(x, y);
		 return n;
	 }
}
