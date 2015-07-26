package agent.laby.exception;

import java.awt.Point;

public class LabyErroneException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point point;
	
	public Point getPoint() {
		return point;
	}

	public LabyErroneException(Point p, String s){
		super(s);
		point = p;		
	}

}
