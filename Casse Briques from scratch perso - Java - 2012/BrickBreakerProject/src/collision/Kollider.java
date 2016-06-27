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
import geometry.Segment;

import java.util.ArrayList;
import java.util.HashMap;

import transformation.Transformers;



/**
 * handles all active and inactive MultiPolygons, checks for overlapping edges of each of their active TriggeringPolygons.
 * Has to be updated ( kollider.process() ) after geometries updates ( Transformation.Util.updateMultiPolygonGeometry(MultiPolygon) ).
 * 
 * @author orelero
 *
 */
strictfp public class Kollider
{	
	/**
	 * 
	 */
	private HashMap<Integer,MultiPolygon> idHandler = null;
	
	/**
	 * 
	 */
	private ArrayList<MultiPolygon> dynamicMultiPolygons = null;
	/**
	 * 
	 */
	private ArrayList<MultiPolygon> staticMultiPolygons = null;
	
	/**
	 * 
	 */
	private boolean isDebugMode;
	
	
	/**
	 * 
	 * @param _debugMode true if drawing polygons (for debugging purpose for instance) is needed.
	 */
	public Kollider(boolean _isDebugMode)
	{
		idHandler = new HashMap<Integer,MultiPolygon>();
		dynamicMultiPolygons = new ArrayList<MultiPolygon>();
		staticMultiPolygons = new ArrayList<MultiPolygon>();
		isDebugMode = _isDebugMode;
	}
	
	/**
	 * @param _multiPolygon
	 * @return
	 */
	protected int registerMultiPolygon(MultiPolygon _multiPolygon)
	{
		Integer i;
		
		for(i = 0 ; i < idHandler.size() ; i++)
		{
			if(idHandler.get(i) == null)
			{
				idHandler.put(i,_multiPolygon);
				return i;
			}
		}
		
		idHandler.put(i,_multiPolygon);
		if(_multiPolygon.isDynamic()) dynamicMultiPolygons.add(_multiPolygon);
		else staticMultiPolygons.add(_multiPolygon);
		
		return i;
	}
	
	/**
	 * remove safely _multiPolygon from this collision system.
	 * 
	 * @param _multiPolygon
	 * @return false if failed.
	 */
	public boolean unregisterMultiPolygon(MultiPolygon _multiPolygon)
	{
		if(idHandler.remove(_multiPolygon.getId()) == null) return false;
		
		dynamicMultiPolygons.remove(_multiPolygon);
		staticMultiPolygons.remove(_multiPolygon);
		
		return true;
	}
	
	//GETTERS
	
	/**
	 * @return
	 */
	public ArrayList<MultiPolygon> getDynamicMultiPolygons()
	{
		return dynamicMultiPolygons;
	}
	
	/**
	 * @return
	 */
	public ArrayList<MultiPolygon> getStaticMultiPolygons()
	{
		return staticMultiPolygons;
	}
	
	/**
	 * get a MultiPolygon managed by this Kollider given its _id.
	 * 
	 * @param _id
	 * @return
	 */
	public MultiPolygon getMultiPolygon(int _id)
	{
		return idHandler.get(_id);
	}
	
	/**
	 * @return
	 */
	public boolean isDebugMode()
	{
		return isDebugMode;
	}
	
	/**
	 * @param _isDebugMode
	 */
	public void setIsDebugMode(boolean _isDebugMode)
	{
		isDebugMode = _isDebugMode;
	}
	
	//PROCESSING (LOOP DECOMPOSITION FOR A CLEAR UNDERSTANDING)
	
	/**
	 * update CollisionInfos.
	 */
	public void process()
	{
		for(MultiPolygon mp : dynamicMultiPolygons)
		{
			mp.updateBeforeProcessing(isDebugMode);
		}
		for(MultiPolygon mp : staticMultiPolygons)
		{
			mp.updateBeforeProcessing(isDebugMode);
		}
		
		
		//DYNA VS STA
		test(dynamicMultiPolygons,staticMultiPolygons);
				
		//DYNA VS DYNA
		test(dynamicMultiPolygons,dynamicMultiPolygons);
		
		
		for(MultiPolygon mp : dynamicMultiPolygons)
		{
			mp.updateAfterProcessing();
		}
		for(MultiPolygon mp : staticMultiPolygons)
		{
			mp.updateAfterProcessing();
		}
	}
	
	/**
	 * @param _multiPolygonsI1
	 * @param _multiPolygonsI2
	 */
	private void test(ArrayList<MultiPolygon> _multiPolygonsI1, ArrayList<MultiPolygon> _multiPolygonsI2)
	{
		int i1 = 0;
		int i2 = 0;
		
		for(MultiPolygon mp1 : _multiPolygonsI1)
		{
			if(i1 < _multiPolygonsI1.size()-1)
			{
				i2 = 0;
					int skipper = i1 + 1;
					
						for(MultiPolygon mp2 : _multiPolygonsI2)
						{
							if(skipper <= i2 && i1 < _multiPolygonsI2.size())
							{
								boolean hasBoundariesCollided = mp1.boundariesCollide(mp2);
								
								if(hasBoundariesCollided)
								{
									if(mp1.getTransformation().isUpdated()) Transformers.updateMultiPolygonGeometry(mp1);
									if(mp2.getTransformation().isUpdated()) Transformers.updateMultiPolygonGeometry(mp2);
								}
								
								if(mp1.isActive() && mp2.isActive() && hasBoundariesCollided) test(mp1,mp2);
							}
							
							i2++;
						}
			}
			
			i1++;
		}
	}
	
	/**
	 * @param _mp1
	 * @param _mp2
	 */
	private void test(MultiPolygon _mp1, MultiPolygon _mp2)
	{
		for(TriggeringPolygon tp2 : _mp2.getTriggeringPolygons())
		{
			if(tp2.isActive()) test(_mp1, tp2);
		}
	}
	
	/**
	 * @param _mp1
	 * @param _tp
	 */
	private void test(MultiPolygon _mp1, TriggeringPolygon _tp)
	{
		for(TriggeringPolygon tp1 : _mp1.getTriggeringPolygons())
		{
			if(tp1.isActive()) test(tp1, _tp);
		}
	}
	
	/**
	 * @param _tp1
	 * @param _tp2
	 */
	private void test(TriggeringPolygon _tp1, TriggeringPolygon _tp2)
	{
		for(int i1 = 0 ; i1 < _tp1.getPoints().size()-1 ; i1++)
		{
			Segment segment1 = _tp1.getSegment(i1);
			
			for(int i2 = 0 ; i2 < _tp2.getPoints().size()-1 ; i2++)
			{
				Segment segment2 = _tp2.getSegment(i2);
				
				Point contactPoint = collision.Algorithms.segment2segment(segment1, segment2);
				
				if(contactPoint != null)
				{
					fireCollisionEvent(contactPoint, i1, i2, _tp1, _tp2);
					return;
				}
			}
		}
	}
	
	private void fireCollisionEvent(Point _rawContactPoint, int i1, int i2, TriggeringPolygon _tp1, TriggeringPolygon _tp2)
	{
		Integer tp1RealContactPointIndex = getRealContactPointIndex(i1, _tp1, _tp2.getSegment(i2));
		if(tp1RealContactPointIndex == null) tp1RealContactPointIndex = getRealContactPointIndex(i1, _tp1, getLowerSegment(i2,_tp2));
		if(tp1RealContactPointIndex == null) tp1RealContactPointIndex = getRealContactPointIndex(i1, _tp1, getUpperSegment(i2,_tp2));
		
		Integer tp2RealContactPointIndex = getRealContactPointIndex(i2, _tp2, _tp1.getSegment(i1));
		if(tp2RealContactPointIndex == null) tp2RealContactPointIndex = getRealContactPointIndex(i2, _tp2, getLowerSegment(i1,_tp1));
		if(tp2RealContactPointIndex == null) tp2RealContactPointIndex = getRealContactPointIndex(i2, _tp2, getUpperSegment(i1,_tp1));
		
		Integer tp1VertexContactIndex = tp1RealContactPointIndex;
		Integer tp1EdgeContactIndex = null;
		Point tp1RealContactPoint = null;
		Integer tp2VertexContactIndex = tp2RealContactPointIndex;
		Integer tp2EdgeContactIndex = null;
		Point tp2RealContactPoint = null;

		if(tp1VertexContactIndex == null)
		{
			tp1EdgeContactIndex = new Integer(i1);
			tp1RealContactPoint = _rawContactPoint;
		}
		else tp1RealContactPoint = _tp1.getPoint(tp1VertexContactIndex);
		
		if(tp2VertexContactIndex == null)
		{
			tp2EdgeContactIndex = new Integer(i2);
			tp2RealContactPoint = _rawContactPoint;
		}
		else tp2RealContactPoint = _tp2.getPoint(tp2VertexContactIndex);
		
		CollisionInfo collisionInfo1 = CollisionInfo.getInstance(_tp2, tp1VertexContactIndex,
				tp1EdgeContactIndex, tp1RealContactPoint, tp2VertexContactIndex,
				tp2EdgeContactIndex, tp2RealContactPoint);
		_tp1.addCollisionInfo(collisionInfo1);
		if(_tp1.getCollisionEventHandler() != null) _tp1.getCollisionEventHandler().onCollision(collisionInfo1);
		
		CollisionInfo collisionInfo2 = CollisionInfo.getInstance(_tp1, tp2VertexContactIndex,
				tp2EdgeContactIndex, tp2RealContactPoint, tp1VertexContactIndex,
				tp1EdgeContactIndex, tp1RealContactPoint);
		_tp2.addCollisionInfo(collisionInfo2);
		if(_tp2.getCollisionEventHandler() != null) _tp2.getCollisionEventHandler().onCollision(collisionInfo2);
	}
	
	private Integer getRealContactPointIndex(int _segmentIndex, TriggeringPolygon _tp, Segment _segmentObstructer)
	{
		Segment lowerSegment = getLowerSegment(_segmentIndex, _tp);
		
		Segment upperSegment = getUpperSegment(_segmentIndex, _tp);
		
		Point lowerTestPoint = collision.Algorithms.segment2segment(lowerSegment, _segmentObstructer);
		Point upperTestPoint = collision.Algorithms.segment2segment(upperSegment, _segmentObstructer);
		
		if(lowerTestPoint != null) return new Integer(_segmentIndex);
		if(upperTestPoint != null) return new Integer(_segmentIndex+1);
		
		return null;
	}
	
	private Segment getLowerSegment(int _segmentIndex, TriggeringPolygon _tp)
	{
		Segment lowerSegment;
		if(_segmentIndex == 0) lowerSegment = _tp.getSegment(_tp.getPoints().size()-2);
		else lowerSegment = _tp.getSegment(_segmentIndex-1);
		return lowerSegment;
	}
	
	private Segment getUpperSegment(int _segmentIndex, TriggeringPolygon _tp)
	{
		Segment upperSegment;
		if(_segmentIndex == _tp.getPoints().size()-2) upperSegment = _tp.getSegment(0);
		else upperSegment = _tp.getSegment(_segmentIndex+1);
		return upperSegment;
	}
}
