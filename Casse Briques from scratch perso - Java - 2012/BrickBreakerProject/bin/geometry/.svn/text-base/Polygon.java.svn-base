/* Kollider2D 2D collision engine for 2D games
    Copyright (C) 2008  Aurelien Boubennec

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.*/

package geometry;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * representation of a polygon as a succession of point.
 * When closed, its first point and its last point has the same values but don't share the same refrence.
 * 
 * @author orelero
 *
 */
strictfp public class Polygon implements Cloneable,Serializable
{
	/**
	 * 
	 */
	static final long serialVersionUID = 43L;
	
	/**
	 * 
	 */
	private ArrayList<Point> pointList = null;
	
	/**
	 * 
	 */
	private boolean isClosed;
	
	
	/**
	 * @param _startingPoint
	 */
	public Polygon(Point _startingPoint)
	{
		pointList = new ArrayList<Point>();
		pointList.add(_startingPoint);
		isClosed= false;
	}
	
	/**
	 * @param _pointList
	 */
	public Polygon(ArrayList<Point> _pointList)
	{
		pointList = _pointList;
		isClosed = false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Polygon clone()
	{
		ArrayList<Point> clonedPointList = new ArrayList<Point>();
		for(Point p : pointList)
		{
			clonedPointList.add(p.clone());
		}
		Polygon polygon = new Polygon(clonedPointList);
		polygon.forceClosure(isClosed);
		return polygon;
	}
	
	
	public void copyTransferFrom(Polygon _polygon)
	{
		for(int i = 0 ; i <_polygon.getPoints().size() ; i++)
		{
			pointList.set(i, _polygon.getPoints().get(i));
			isClosed = _polygon.isClosed();
		}
	}
	
	/**
	 * @param _point
	 */
	public void addPoint(Point _point)
	{
		pointList.add(_point);
	}
	
	/**
	 * 
	 */
	public void removePoint()
	{
		pointList.remove(pointList.size()-1);
		if(isClosed) isClosed = false;
	}
	
	/**
	 * 
	 */
	public void open()
	{
		if(!isClosed)
		{
			isClosed = false;
			removePoint();
		}
	}
	
	/**
	 * 
	 */
	public void close()
	{
		if(!isClosed)
		{
			isClosed = true;
			pointList.add(pointList.get(0).clone());
		}
	}
	
	/**
	 * @param _index
	 * @return
	 */
	public Point getPoint(int _index)
	{
		return pointList.get(_index);
	}
	
	/**
	 * @param _index
	 * @return
	 */
	public Segment getSegment(int _index)
	{
		List<Point> segmentPoints = pointList.subList(_index, _index+2);
		return new Segment(segmentPoints.get(0),segmentPoints.get(1));
	}
	
	/**
	 * @param _p
	 * @return the maximum distance from _p to one of the points of this Polygon.
	 */
	public float maxDistanceFrom(Point _p)
	{
		float maxDistance = 0;
		
		for(Point p : pointList)
		{
			float distance = p.getDistance(_p);
			if(distance > maxDistance) maxDistance = distance;
		}
		
		return maxDistance;
	}
	
	/**
	 * @return
	 */
	public Point getCenterOfGravity()
	{
		float xCOG = 0;
		float yCOG = 0;
		
		for(Point p : pointList)
		{
			xCOG = p.getX() + xCOG;
			yCOG = p.getY() + yCOG;
		}
		
		if(isClosed)
		{
			Point overmuchPoint = pointList.get(0);
			xCOG = (xCOG - overmuchPoint.getX()) / (pointList.size()-1);
			yCOG = (yCOG - overmuchPoint.getY()) / (pointList.size()-1);
		}
		else
		{
			xCOG = xCOG / pointList.size();
			yCOG = yCOG / pointList.size();
		}
		
		return Point.getInstance(xCOG, yCOG);
	}
	
	//GETTERS SETTERS
	
	/**
	 * @return
	 */
	public boolean isClosed()
	{
		return isClosed;
	}
	
	/**
	 * @param _isClosed
	 */
	public void forceClosure(boolean _isClosed)
	{
		isClosed = _isClosed;
	}
	
	/**
	 * @return
	 */
	public ArrayList<Point> getPoints()
	{
		return pointList;
	}
	
	public void setPointList(ArrayList<Point> _pointList)
	{
		pointList = _pointList;
	}
}
