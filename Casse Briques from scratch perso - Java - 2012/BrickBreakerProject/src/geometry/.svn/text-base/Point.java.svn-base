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
import java.util.LinkedList;

import collision.CollisionInfo;


/**
 * representation of a point.
 * 
 * @author orelero
 *
 */
strictfp public class Point implements Cloneable,Serializable
{
	/**
	 * 
	 */
	static final long serialVersionUID = 42L;
	
	/**
	 * 
	 */
	private static LinkedList<Point> pointRecycler = new LinkedList<Point>();
	
	/**
	 * 
	 */
	private float x;
	/**
	 * 
	 */
	private float y;
	
	/**
	 * origin point (0,0).
	 */
	public static final Point ORIGIN = new Point(0, 0);
	
	
	public static Point getInstance()
	{
		if(pointRecycler.size() == 0) return new Point();
		
		else
		{
			Point point = pointRecycler.get(0);
			point.setXY(0, 0);
			pointRecycler.remove(0);
			return point;
		}
	}
	
	/**
	 * @param _x
	 * @param _y
	 */
	public static Point getInstance(float _x, float _y)
	{
		if(pointRecycler.size() == 0) return new Point(_x, _y);
		
		else
		{
			Point point = pointRecycler.get(0);
			point.setXY(_x, _y);
			pointRecycler.remove(0);
			return point;
		}
	}
	
	/**
	 * @param _collisionInfo called as much as possible to recycle Point objects.
	 */
	public static void dump(Point _point)
	{
		if(_point != null) pointRecycler.add(_point);
	}
	
	/**
	 * 
	 */
	private Point()
	{
		x = 0;
		y = 0;
	}
	
	
	private Point(float _x, float _y)
	{
		x = _x;
		y = _y;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Point clone()
	{
		return new Point(x,y);
	}
	
	/**
	 * @param _p
	 * @return the distance between this Point and _p.
	 */
	public float getDistance(Point _p)
	{
		return (float)Math.sqrt(Math.pow(_p.getX()-x,2)+Math.pow(_p.getY()-y,2));
	}
	
	/**
	 * @param _s
	 * @return the shortest distance between this Point and _s.
	 */
	public float getDistance(Segment _s)
	{
		Vector v1 = new Vector(_s.getP1().getX()-_s.getP2().getX() , _s.getP1().getY()-_s.getP2().getY());
		Vector v2 = new Vector(_s.getP1().getX()-x , _s.getP1().getY()-y);
		
		return (float)(Math.abs(v1.dotProduct(v2)/Math.sqrt(v1.getNorm()*v2.getNorm())));
	}
	
	/**
	 * @param _p2
	 * @param _weightMagnifier default is 1.
	 * @return the center point of the segment represented by this Point and _p. The bigger _weightMagnifier is, the more _p2 "attracts" the center.
	 */
	public Point getCenter(Point _p2, float _weightMagnifier)
	{
		return new Point( (_weightMagnifier*_p2.getX() - x) / (_weightMagnifier+1) + x, (_weightMagnifier*_p2.getY() - y) / (_weightMagnifier+1) + y );
	}
	
	public void copyTransferFrom(Point _point)
	{
		x = _point.getX();
		y = _point.getY();
	}
	
	//GETTERS SETTERS

	/**
	 * @return
	 */
	public float getX()
	{
		return x;
	}

	/**
	 * @param _x
	 */
	public void setX(float _x)
	{
		this.x = _x;
	}

	/**
	 * @return
	 */
	public float getY()
	{
		return y;
	}

	/**
	 * @param _y
	 */
	public void setY(float _y)
	{
		this.y = _y;
	}
	
	/**
	 * @param _x
	 * @param _y
	 */
	public void setXY(float _x, float _y)
	{
		x = _x;
		y = _y;
	}
}
