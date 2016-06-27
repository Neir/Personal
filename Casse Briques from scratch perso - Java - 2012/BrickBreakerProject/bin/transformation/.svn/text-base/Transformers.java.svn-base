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

package transformation;

import collision.MultiPolygon;
import geometry.Point;
import geometry.Polygon;
import geometry.Vector;

/**
 * static methods used (and only these ones if possible) to transform MultiPolygons (MultiPolygons only if possible).
 * 
 * @author orelero
 *
 */
strictfp public class Transformers
{
	/**
	 * updates _multiPolygon its geometry/BoundingBox based on its current Transformation.
	 * 
	 * @param _multiPolygon
	 */
	public static void updateMultiPolygonGeometry(MultiPolygon _multiPolygon)
	{
		Transformation transformation = _multiPolygon.getTransformation();
		
		if(transformation.isUpdated())
		{
			for(int i = 0 ; i < _multiPolygon.getTriggeringPolygons().size() ; i++)
			{
				rotatePolygon(_multiPolygon.getTriggeringPolygons().get(i), 
						_multiPolygon.getTriggeringPolygonsSave().get(i),transformation.getCenterPosition(),
						transformation.getAngle());
			}

			for(int i = 0 ; i < _multiPolygon.getTriggeringPolygons().size() ; i++)
			{
				translatePolygon(_multiPolygon.getTriggeringPolygons().get(i), 
						_multiPolygon.getTriggeringPolygons().get(i),
						transformation.getTranslation().getX(), transformation.getTranslation().getY());
			}
			
			transformation.setUpdated(false);
			
			if(_multiPolygon.isBoxBounded()) _multiPolygon.updateBoundingBox();
			else _multiPolygon.getBoundingCircle().setCenter(transformation.getTranslatedCenterPosition());
		}
	}
	
	/**
	 * @param _result
	 * @param _polygon
	 * @param _deltaX
	 * @param _deltaY
	 */
	public static void translatePolygon(Polygon _result, Polygon _polygon, float _deltaX, float _deltaY)
	{
		for(int i = 0 ; i < _polygon.getPoints().size() ; i++)
		{
			Point p = _polygon.getPoints().get(i);
			_result.getPoints().get(i).setXY(p.getX() + _deltaX, p.getY() + _deltaY);
		}
	}
	
	
	public static Point translate(Point _point, Vector _vector)
	{
		_point.setXY(_point.getX()+_vector.getX(), _point.getY()+_vector.getY());
		return _point;
	}
	
	/**
	 * @param _result
	 * @param _point
	 * @param _anchorPoint
	 * @param _angle
	 */
	public static void rotatePoint(Point _result, Point _point, Point _anchorPoint, double _angle)
	{
		float xTransl = _point.getX()-_anchorPoint.getX();
		float yTransl = _point.getY()-_anchorPoint.getY();
		float xa = _anchorPoint.getX();
		float ya = _anchorPoint.getY();
		
		float newX = (float)(xTransl * Math.cos(_angle) + yTransl * Math.sin(_angle)) + xa;
		float newY = (float)(- xTransl * Math.sin(_angle) + yTransl * Math.cos(_angle)) + ya;
		
		_result.setXY(newX, newY);
	}
	
	/**
	 * @param _result
	 * @param _polygon
	 * @param _anchorPoint
	 * @param _angle
	 */
	public static void rotatePolygon(Polygon _result, Polygon _polygon, Point _anchorPoint, double _angle)
	{
		for(int i = 0 ; i < _polygon.getPoints().size() ; i++)
		{
			rotatePoint(_result.getPoints().get(i), _polygon.getPoints().get(i),_anchorPoint, _angle);
		}
	}
	
	/**
	 * to be called each time an _angle is updated by in/decrementation in order to avoid weird results.
	 * 
	 * @param _angle
	 * @return
	 */
	public static double correctDirtyAngle(double _angle)
	{
		if(_angle > 2*Math.PI) return _angle - 2*Math.PI;
		if(_angle <= 0) return 2*Math.PI + _angle;
		return _angle;
	}
}
