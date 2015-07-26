package isketch.objets;

import java.awt.Color;
import java.awt.Point;

public class Segment {
	private Point p1, p2;
	private Color c;
	private int size;
	
	
	public Segment(Point p1, Point p2, Color c, int size) {
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.c = c;
		this.size = size;
	}

	
	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Point getP1() {
		return p1;
	}

	public void setP1(Point p1) {
		this.p1 = p1;
	}

	public Point getP2() {
		return p2;
	}

	public void setP2(Point p2) {
		this.p2 = p2;
	}
	
	
}
