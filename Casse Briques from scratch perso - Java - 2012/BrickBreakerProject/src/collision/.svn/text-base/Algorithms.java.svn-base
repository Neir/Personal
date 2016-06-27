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

package collision;

import geometry.Point;
import geometry.Polygon;
import geometry.Segment;

/**
 * base methods used by Kollider.
 * 
 * @author orelero
 *
 */
strictfp public class Algorithms
{
	
	/**
	 * @param _AB
	 * @param _CD
	 * @return
	 */
	public static Point segment2segment(Segment _AB, Segment _CD)
	{
	      double Ax = _AB.getP1().getX();
	      double Ay = _AB.getP1().getY();
	      double Bx = _AB.getP2().getX();
	      double By = _AB.getP2().getY();
	      
	      double Cx = _CD.getP1().getX();
	      double Cy = _CD.getP1().getY();
	      double Dx = _CD.getP2().getX();
	      double Dy = _CD.getP2().getY();
	      
	      double Sx;
	      double Sy;

	      if(Ax==Bx)
	      {
	         if(Cx==Dx)
	         {
	        	 if(Ax!=Cx) return null; //TODO: add tolerance for the Bullet pass, ie: Math.abs(Ax-Cx)>tolerance .
	        	 
	        	 Sx = (Ax+Bx+Cx+Dx)/4;
		         Sy = (Ay+By+Cy+Dy)/4;
	         }
	         else
	         {
	            double pCD = (Cy-Dy)/(Cx-Dx);
	            Sx = Ax;
	            Sy = pCD*(Ax-Cx)+Cy;
	         }
	      }
	      else
	      {
	         if(Cx==Dx)
	         {
	            double pAB = (Ay-By)/(Ax-Bx);
	            Sx = Cx;
	            Sy = pAB*(Cx-Ax)+Ay;
	         }
	         else
	         {
	            double pCD = (Cy-Dy)/(Cx-Dx);
	            double pAB = (Ay-By)/(Ax-Bx);
	            double oCD = Cy-pCD*Cx;
	            double oAB = Ay-pAB*Ax;
	            Sx = (oAB-oCD)/(pCD-pAB);
	            Sy = pCD*Sx+oCD;
	         }
	      }
	      if((Sx<Ax && Sx<Bx)|(Sx>Ax && Sx>Bx) | (Sx<Cx && Sx<Dx)|(Sx>Cx && Sx>Dx)
	            | (Sy<Ay && Sy<By)|(Sy>Ay && Sy>By) | (Sy<Cy && Sy<Dy)|(Sy>Cy && Sy>Dy)) return null;
	        return Point.getInstance((float)Sx, (float)Sy);
	}
	
	/**
	 * @param _AB
	 * @param _CT circle's center.
	 * @param _radius
	 * @return true if the segment _AB and the circle (_CT,_radius) collide.
	 */
	public static boolean circle2segment(Segment _AB, Point _CT, float _radius)
	{
           float Ax = _AB.getP1().getX();
           float Ay = _AB.getP1().getY();
           float Bx = _AB.getP2().getX();
           float By = _AB.getP2().getY();
           float xCt = _CT.getX();
           float yCt = _CT.getY();
           
           double distance;
           double xv = Bx-Ax;
           double yv = By-Ay;
           double xw = xCt-Ax;
           double yw = yCt-Ay;
           double c1 = xw*xv+yw*yv;
            if ( c1<= 0 ) distance = _CT.getDistance(_AB.getP1());
            else
            {
            	double c2 = xv*xv+yv*yv;
               if ( c2 <= c1 ) distance = _CT.getDistance(_AB.getP2());
               else
               {
            	   double b = c1 / c2;
            	   double xPb = Ax + b*xv;
            	   double yPb = Ay + b*yv;
                  distance = (float)Math.sqrt(Math.pow(xCt-xPb,2)+Math.pow(yCt-yPb,2));
               }
            }
            if(distance<_radius)
            {
              return true;
            }
            return false;
	}
	
	/**
	 * @param _p
	 * @param _CT
	 * @param _radius
	 * @return
	 */
	public static boolean circle2Polygon(Polygon _p, Point _CT, float _radius)
	{
		for(int i = 0 ; i < _p.getPoints().size()-1 ; i++)
		{
			if(circle2segment(_p.getSegment(i), _CT, _radius)) return true;
		}
		
		return false;
	}
	
	
	/**
	 * @param _CT1
	 * @param _radius1
	 * @param _CT2
	 * @param _radius2
	 * @return
	 */
	public static boolean circle2Circle(Point _CT1, float _radius1, Point _CT2, float _radius2)
	{
		if(_CT1.getDistance(_CT2)> _radius1+_radius2) return false;
		
		return true;
	}
	     
}
