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


/**
 * a segment representation.
 * 
 * P1: starting point, P2: end point.
 * 
 * @author orelero
 *
 */
strictfp public class Segment implements Cloneable
{
	/**
	 * 
	 */
	private Point p1;
	/**
	 * 
	 */
	private Point p2;
	/**
	 * 
	 */
	private Segment normal;
	
	/**
	 * 
	 */
	public Segment()
	{
		p1 = Point.getInstance(new Float(0),new Float(0));
		p2 = Point.getInstance(new Float(0),new Float(1));
	}
	
	/**
	 * @param _x1
	 * @param _y1
	 * @param _x2
	 * @param _y2
	 */
	public Segment(float _x1, float _y1, float _x2, float _y2)
	{
		p1 = Point.getInstance(new Float(_x1),new Float(_y1));
		p2 = Point.getInstance(new Float(_x2),new Float(_y2));
		normal = null;
	}
	
	/**
	 * @param _p1
	 * @param _p2
	 */
	public Segment(Point _p1, Point _p2)
	{
		p1 = _p1;
		p2 = _p2;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Segment clone()
	{
		Segment s = new Segment(p1.clone(),p2.clone());
		s.updateNormal();
		return s;
	}
	
	/**
	 * get a Vectorial representation of this segment.
	 * The direction depends of the construction of this Segment.
	 * 
	 * @return
	 */
	public Vector getVectorRepresentation()
	{
		return new Vector(p2.getX()-p1.getX() , p2.getY()-p1.getY());
	}
	
	/**
	 * the normal can be upside down depending on this Segment construction.
	 * 
	 * @return a vector orthogonal to the direction of this Segment.
	 */
	public Vector getNormal()
	{
		Vector v = this.getVectorRepresentation();
		
		return (   v.setXY( v.getY(), -v.getX() )    ).normalize().mult(4.0F);
	}
	
	/**
	 * @return
	 */
	public Point getCenter()
	{
		return Point.getInstance( (p2.getX() + p1.getX()) / 2, (p2.getY() + p1.getY()) / 2 );
	}
	
	/**
	 * update the normal of this Segment.
	 * 
	 * @return the segment representation of this Segment.
	 */
	public Segment updateNormal()
	{
		if(normal == null) normal = new Segment();
		Point center = getCenter();
		normal.setP1(center);
		normal.setP2( transformation.Transformers.translate(center.clone(),getNormal()) );
		return normal;
	}
	
	/**
	 * @return the segment representation of this segment.
	 */
	public Segment getNormalSegment()
	{
		if(normal == null) updateNormal();
		return normal;
	}
	
	//GETTERS SETTERS
	
	/**
	 * @return
	 */
	public Point getP1()
	{
		return p1;
	}

	/**
	 * @param p1
	 */
	public void setP1(Point p1)
	{
		this.p1 = p1;
	}

	/**
	 * @return
	 */
	public Point getP2()
	{
		return p2;
	}

	/**
	 * @param p2
	 */
	public void setP2(Point p2)
	{
		this.p2 = p2;
	}
}
