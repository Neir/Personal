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



/**
 * vector2D implementation.
 * 
 * @author orelero
 *
 */
strictfp public class Vector implements Cloneable, Serializable
{
	static final long serialVersionUID = 49L;
	
	/**
	 * 
	 */
	private float x;
	/**
	 * 
	 */
	private float y;
	
	/**
	 * 
	 */
	public final static Vector X_UNIT =  new Vector(1,0);
	/**
	 * 
	 */
	public final static Vector Y_UNIT =  new Vector(0,1);
	
	
	/**
	 * 
	 */
	public Vector()
	{
		x = 0;
		y = 0;
	}
	
	/**
	 * @param _x
	 * @param _y
	 */
	public Vector(float _x, float _y)
	{
		x = _x;
		y = _y;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Vector clone()
	{
		return new Vector(x,y);
	}
	
	/**
	 * normalize this vector.
	 * 
	 * @return this vector.
	 */
	public Vector normalize()
	{
		float norm = getNorm();
		x = (float)((1/norm)*x);
		y = (float)((1/norm)*y);
		
		return this;
	}
	
	/**
	 * @param _vector
	 * @return
	 */
	public Vector copyTransferFrom(Vector _vector)
	{
		x = _vector.getY();
		y = _vector.getY();
		
		return this;
	}
	
	/**
	 * @return a new version of this vector but normalized.
	 */
	public Vector getNormalized()
	{
		float newX = (1/getNorm())*x;
		float newY = (1/getNorm())*y;
		
		return new Vector(newX, newY);
	}
	
	/**
	 * @return
	 */
	public float getNorm()
	{
		return (float)(Math.sqrt(Math.pow(x,2)+Math.pow(y,2)));
	}
	
	/**
	 * @param _v add _v to this Vector.
	 * @return this Vector.
	 */
	public Vector addUp(Vector _v)
	{
		x = _v.getX() + x;
		y = _v.getY() + y;
		
		return this;
	}
	
	
	/**
	 * @param _x
	 * @param _y
	 * @return
	 */
	public Vector addUp(float _x, float _y)
	{
		x = _x + x;
		y = _y + y;
		
		return this;
	}
	
	/**
	 * @return this Vector but "negated".
	 */
	public Vector negate()
	{
		x = -x;
		y = -y;
		
		return this;
	}
	
	/**
	 * @param _scalar
	 * @return this Vector but multiplied by _scalar.
	 */
	public Vector mult(float _scalar)
	{
		x = x*_scalar;
		y = y*_scalar;
		
		return this;
	}
	
	/**
	 * @param _v
	 * @return the dot product between this and _v.
	 */
	public float dotProduct(Vector _v)
	{
		return x*_v.getX()+y*_v.getY();
	}
	
	/**
	 * @param _v
	 * @return the direct angle between this and _v.
	 */
	public float getAngle(Vector _v)
	{
		return (float)Math.acos( this.dotProduct(_v)/(this.getNorm()*_v.getNorm()) );
	}
	
	/**
	 * @param _v
	 * @return counter-clock wise depending on the sign.
	 */
	public int crossProductSign(Vector _v)
	{
		float temp = x*_v.getY()-y*_v.getX();
		
		return (int)(temp/Math.abs(temp));
	}
	
	/**
	 * useful if a vector need to be drawn at a certain anchor point.
	 * 
	 * @param _anchorPoint
	 * @return
	 */
	public Segment getSegmentRepresentation(Point _anchorPoint)
	{
		return new Segment(_anchorPoint.getX(),_anchorPoint.getY(),_anchorPoint.getX()+x,_anchorPoint.getY()+y);
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
	 * @param x
	 */
	public void setX(float x)
	{
		this.x = x;
	}

	/**
	 * @return
	 */
	public float getY()
	{
		return y;
	}

	/**
	 * @param y
	 */
	public void setY(float y)
	{
		this.y = y;
	}
	
	
	/**
	 * @param _x
	 * @param _y
	 * @return
	 */
	public Vector setXY(float _x, float _y)
	{
		x = _x;
		y = _y;
		return this;
	}
}
