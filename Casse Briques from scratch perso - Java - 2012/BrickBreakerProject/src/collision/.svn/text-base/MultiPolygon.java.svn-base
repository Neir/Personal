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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

import geometry.Point;
import geometry.Polygon;
import geometry.Vector;
import transformation.*;

/**
 * Entity that can handle one or more TriggeringPolygons. Transformations are applied as a whole.
 * 
 * @author orelero
 *
 */
strictfp public class MultiPolygon implements Serializable, Cloneable
{
	/**
	 * 
	 */
	static final long serialVersionUID = 47L;
	
	/**
	 * 
	 */
	private int id;
	
	/**
	 * 
	 */
	private ArrayList<TriggeringPolygon> triggeringPolygons = null;
	/**
	 * 
	 */
	private ArrayList<TriggeringPolygon> triggeringPolygonsSave = null;
	
	/**
	 * 
	 */
	private Transformation transformation;

	/**
	 * 
	 */
	private Transformation transformationCopy;
	
	
	private BoundingBox boundingBox;
	private BoundingCircle boundingCircle;
	
	/**
	 * 
	 */
	private boolean isDynamic;
	
	/**
	 * 
	 */
	private boolean isActive;
	
	/**
	 * name of this polygon. Possibly used for debug purpose.
	 */
	public String name;
	
	/**
	 * 
	 */
	private boolean isBoxBounded;
	
	/**
	 * 
	 */
	private Kollider owner;
	

	/**
	 * @param _kollider  can be set as null. Call register(...) then.
	 * @param _isDynamic true if its geometry/position is variable.
	 */
	public MultiPolygon(Kollider _kollider, boolean _isDynamic, boolean _isBoxBounded)
	{
		isBoxBounded = _isBoxBounded;
		
		triggeringPolygons = new ArrayList<TriggeringPolygon>();
		triggeringPolygonsSave = new ArrayList<TriggeringPolygon>();
		
		isDynamic = _isDynamic;
		
		isActive = false;
		
		transformation = new Transformation(0, Point.getInstance(), new Vector(0,0));
		transformationCopy = new Transformation(0, Point.getInstance(), new Vector(0,0));
		
		if(isBoxBounded) boundingBox = new BoundingBox();
		else
		{
			boundingCircle = new BoundingCircle();
		}
		
		if(_kollider != null)
		{
			id = _kollider.registerMultiPolygon(this);
			owner = _kollider;
		}
			
	}
	
	public MultiPolygon clone()
	{
		MultiPolygon multiPolygon = new MultiPolygon(owner, isDynamic, isBoxBounded);
		
		multiPolygon.setActivity(isActive);
		
		ArrayList<TriggeringPolygon> tps = new ArrayList<TriggeringPolygon>();
		ArrayList<TriggeringPolygon> tpsSave = new ArrayList<TriggeringPolygon>();
		
		for(int i = 0 ; i < triggeringPolygons.size() ; i++)
		{
			tps.add(triggeringPolygons.get(i).clone());
			tpsSave.add(triggeringPolygonsSave.get(i).clone());
		}
		
		multiPolygon.setTransformation(transformation.clone());
		multiPolygon.setTransformationCopy(transformationCopy.clone());
		
		if(isBoxBounded)multiPolygon.updateBoundingBox();
		else multiPolygon.setBoundingCircle(boundingCircle.clone());
		
		multiPolygon.dumpCollisionInfoStacks();
		
		owner.registerMultiPolygon(multiPolygon);

		return multiPolygon;
	}
	
	/**
	 * @param _triggeringPolygon
	 * @param _generateWeightCenterPosition sets the position center as the center of gravity of all points belonging to this MultiPolygon.
	 * If false then you need to provide a center to the Transformation by calling MultiPolygon.setCenter(...). 
	 */
	public void addTriggeringPolygon(TriggeringPolygon _triggeringPolygon, boolean _generateWeightCenterPosition)
	{
		_triggeringPolygon.setOwner(this);
		triggeringPolygons.add(_triggeringPolygon);
		triggeringPolygonsSave.add(_triggeringPolygon.clone());
		
		if(_generateWeightCenterPosition)
		{
			int weight = 0;
			Point center = null;
			for(TriggeringPolygon tp : triggeringPolygons)
			{
				if(weight == 0)center = tp.getCenterOfGravity();
				else
				{
					center = center.getCenter(tp.getCenterOfGravity(), weight);
				}
				weight++;
			}
				
			transformation.setCenterPosition(center);
			
			if(!isBoxBounded) 
			{
				boundingCircle.setCenter(center);
				updateBoundingCircle();
			}
		}
	}
	
	/**
	 * 
	 * @param _triggeringPolygon
	 */
	public void removeTriggeringPolygon(TriggeringPolygon _triggeringPolygon)
	{
		_triggeringPolygon.setOwner(null);
		triggeringPolygons.remove(_triggeringPolygon);
		triggeringPolygonsSave.remove(_triggeringPolygon);
	}
	
	/**
	 * normally automatically called when using Transformation.Util.updateMultiPolygonGeometry(MultiPolygon _multiPolygon)
	 */
	public void updateBoundingBox()
	{
		if(!isBoxBounded) return;
		
		Point iniPoint = triggeringPolygons.get(0).getPoint(0);
		float leftBound = iniPoint.getX();
		float upBound = iniPoint.getY();
		float rightBound = iniPoint.getX();
		float downBound = iniPoint.getY();
		
		for(Polygon polygon : triggeringPolygons)
		{
			for(Point p : polygon.getPoints())
			{
				if(p.getX() < leftBound) leftBound = p.getX();
				else if(p.getX() > rightBound) rightBound = p.getX();
				if(p.getY() < downBound) downBound = p.getY();
				else if(p.getY() > upBound) upBound = p.getY();
			}
		}
		float width = rightBound - leftBound;
		float height = upBound - downBound;
		Point center = Point.getInstance(leftBound + width/2, downBound + height/2);
		
		boundingBox.leftBound = leftBound;
		boundingBox.rightBound = rightBound;
		boundingBox.downBound = downBound;
		boundingBox.upBound = upBound;
		boundingBox.width = width;
		boundingBox.height = height;
		Point.dump(boundingBox.center);
		boundingBox.center = center;
		
		boundingBox.updatePolygon();
	}
	
	public void updateBoundingCircle()
	{
		if(isBoxBounded) return;
		
		float maxDistance = 0;
		
		for(Polygon polygon : triggeringPolygons)
		{
			for(Point p : polygon.getPoints())
			{
				float distance = boundingCircle.center.getDistance(p);
				if(distance > maxDistance) maxDistance = distance;
			}
		}
		
		boundingCircle.radius = maxDistance;
	}
	
	/**
	 * 
	 */
	protected void updateBeforeProcessing(boolean _isDebugMode)
	{
		dumpCollisionInfoStacks();
		
		transformation.setChecked(true);
		
		if(transformation.isUpdated() && isBoxBounded())
		{
			Transformers.updateMultiPolygonGeometry(this);
		}
		
		if(!isBoxBounded())
		{
			if(owner.isDebugMode()) Transformers.updateMultiPolygonGeometry(this);
			else this.getBoundingCircle().setCenter(transformation.getTranslatedCenterPosition());
		}
	}
	
	/**
	 * 
	 */
	
	private void dumpCollisionInfoStacks()
	{
		for(TriggeringPolygon tp : triggeringPolygons)
		{
			LinkedList<CollisionInfo> cis = tp.getCollisionInfoStack();
			
			for(CollisionInfo ci : cis)
			{
				CollisionInfo.dump(ci);
			}
			
			tp.getCollisionInfoStack().clear();
		}
	}
	
	/**
	 * 
	 */
	protected void updateAfterProcessing()
	{
		
	}
	
	/**
	 * @return true if at least one of its TriggeringPolygon has collided.
	 */
	public boolean hasCollided()
	{
		boolean hasCollided = false;
		
		for(TriggeringPolygon tp : triggeringPolygons)
		{
			if(tp.getCollisionInfoStack().size() != 0) hasCollided = true;
		}
		
		return hasCollided;
	}
	
	/**
	 * @param _multiPolygon
	 * @return
	 */
	protected boolean boundariesCollide(MultiPolygon _multiPolygon)
	{
		if(isBoxBounded() && _multiPolygon.isBoxBounded())
		{
			BoundingBox bb1 = _multiPolygon.getBoundingBox();
			BoundingBox bb2 = this.getBoundingBox();
			
			if( bb2.leftBound > bb1.leftBound  &&  bb2.leftBound > bb1.rightBound) return false;
			if( bb2.rightBound < bb1.leftBound  &&  bb2.rightBound < bb1.rightBound) return false;
			if( bb2.upBound < bb1.upBound  &&  bb2.upBound < bb1.downBound) return false;
			if( bb2.downBound > bb1.upBound  &&  bb2.downBound > bb1.downBound) return false;
			
			return true;
		}
		else
			if(!isBoxBounded() && _multiPolygon.isBoxBounded())
			{
				return Algorithms.circle2Polygon(_multiPolygon.getBoundingBox().getPolygon(), getBoundingCircle().center, getBoundingCircle().radius);
			}
			else
				if(isBoxBounded() && !_multiPolygon.isBoxBounded())
				{
					return Algorithms.circle2Polygon(getBoundingBox().getPolygon(), _multiPolygon.getBoundingCircle().center, _multiPolygon.getBoundingCircle().radius);
				}
				else
					{
						return Algorithms.circle2Circle(_multiPolygon.getBoundingCircle().center, _multiPolygon.getBoundingCircle().radius, getBoundingCircle().center, getBoundingCircle().radius);
					}
	}
	
	/**
	 * sets this MultiPolygon its Transformation to the previous one.
	 * Gives the ability to stop a MultiPolygon moving when a collision occurs for example.
	 */
	public void backTrack()
	{
		transformation.copyTransferFrom(transformationCopy);
		transformation.setUpdated(true);
		//Transformers.updateMultiPolygonGeometry(this);
	}
	
	/**
	 * saves the current Transformation so an immediate backtrack() call has no effect till the next
	 * Kollider.process() call.
	 */
	public void saveTransformation()
	{
		transformationCopy.copyTransferFrom(transformation);
	}
	
	/**
	 * add this MultiPolygon to the collision system _kollider.
	 * 
	 * @param _kollider
	 */
	public void register(Kollider _kollider)
	{
		id = _kollider.registerMultiPolygon(this);
		owner = _kollider;
	}
	
	//GETTERS SETTERS
	
	/**
	 * @return
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @return
	 */
	public ArrayList<TriggeringPolygon> getTriggeringPolygons()
	{
		return triggeringPolygons;
	}

	/**
	 * All transformations are calculated from the "triggeringPolygonsSave" and saved 
	 * into the "triggeringPolygons" object in order to prevent from error accumulation.
	 * 
	 * @return
	 */
	public ArrayList<TriggeringPolygon> getTriggeringPolygonsSave()
	{
		return triggeringPolygonsSave;
	}

	/**
	 * @return
	 */
	public boolean isDynamic()
	{
		return isDynamic;
	}
	
	/**
	 * When inactive, a MultiPolygon is not taken in consideration by its Kollider.
	 * 
	 * @return
	 */
	public boolean isActive()
	{
		return isActive;
	}
	
	/**
	 * 
	 * @param _isActive
	 * @return
	 */
	public boolean setActivity(boolean _isActive)
	{
		if(_isActive && triggeringPolygons.size() == 0) return false;
		
		isActive = _isActive;
		return true;
	}
	
	/**
	 * @return
	 */
	public BoundingBox getBoundingBox()
	{
		return boundingBox;
	}
	
	/**
	 * @param _boundingBox
	 */
	public void setBoundingBox(BoundingBox _boundingBox)
	{
		isBoxBounded = true;
		setBoundingCircle(null);
		boundingBox = _boundingBox;
	}
	
	/**
	 * @return
	 */
	public BoundingCircle getBoundingCircle()
	{
		return boundingCircle;
	}
	
	/**
	 * @return true if this MultiPolygon is bounded by a Box but not a circle.
	 */
	public boolean isBoxBounded()
	{
		return isBoxBounded;
	}
	
	/**
	 * @param _boundingBox
	 */
	public void setBoundingCircle(BoundingCircle _boundingCircle)
	{
		isBoxBounded = false;
		setBoundingBox(null);
		boundingCircle = _boundingCircle;
	}
	
	/**
	 * @return the current transformation
	 */
	public Transformation getTransformation()
	{
		return transformation;
	}
	
	/**
	 * @param _transformation
	 */
	public void setTransformation(Transformation _transformation)
	{
		transformation = _transformation;
	}
	
	/**
	 * @param _transformationCopy
	 */
	public void setTransformationCopy(Transformation _transformationCopy)
	{
		transformationCopy = _transformationCopy;
	}
	
	/**
	 * the "transformationCopy" is unchanged until the next Kollider.process() call.
	 * 
	 * @return
	 */
	public Transformation getTransformationCopy()
	{
		return transformationCopy;
	}
	
	/**
	 * @return the Kollider this MultiPolygon belongs to.
	 */
	public Kollider getOwner()
	{
		return owner;
	}
	
	
	/**
	 * to be called before adding this MultiPolygon to the Kollider if no center has been supplied before.
	 * 
	 * @param _center
	 */
	public void setCenter(Point _center)
	{
		if(!isBoxBounded)boundingCircle.setCenter(_center.clone());
		transformation.setCenterPosition(_center);
	}
	
	//INNER CLASSES
	
	/**
	 * bounding box automatically used to reduce computation.
	 * Automatically update with its owner's geometry update call.
	 * 
	 * Primarily used for "very changeable" shapes.
	 * 
	 * @author orelero
	 *
	 */
	public class BoundingBox implements Serializable
	{
		static final long serialVersionUID = 46L;
		
		public float leftBound;
		public float upBound;
		public float rightBound;
		public float downBound;
		
		public float width;
		public float height;
		
		public Point center;
		
		private Polygon polygon;
		
		
		@Override
		public String toString()
		{
			return "lb: "+leftBound+"ub: "+upBound+" rb: "+rightBound+" db: "+downBound+" w: "+width+" h: "+height+" cX: "+center.getX()+" cY: "+center.getY();
		}
		
		public void translate(float _deltaX, float _deltaY)
		{
			leftBound = leftBound + _deltaX;
			upBound = upBound + _deltaY;
			rightBound = rightBound + _deltaX;
			downBound = downBound + _deltaY;
			
			center.setXY(center.getX() + _deltaX, center.getY() + _deltaY);
			
			Transformers.translatePolygon(polygon, polygon, _deltaX, _deltaY);
		}
		
		public Polygon getPolygon()
		{
			if(polygon == null) createPolygon();
			updatePolygon();
			return polygon;
		}
		
		/**
		 * create a Polygon representing this BoundingBox.
		 */
		private void createPolygon()
		{
			polygon = new Polygon(Point.getInstance(leftBound,downBound));
			polygon.addPoint(Point.getInstance(rightBound,downBound));
			polygon.addPoint(Point.getInstance(rightBound,upBound));
			polygon.addPoint(Point.getInstance(leftBound,upBound));
			polygon.close();
		}
		
		/**
		 * 
		 */
		protected void updatePolygon()
		{
			if(polygon == null) createPolygon();
			polygon.getPoint(0).setXY(leftBound,downBound);
			polygon.getPoint(1).setXY(rightBound,downBound);
			polygon.getPoint(2).setXY(rightBound,upBound);
			polygon.getPoint(3).setXY(leftBound,upBound);
			polygon.getPoint(4).setXY(leftBound,downBound);
		}
	}
	
	/**
	 * Used as an alternative to the BoundingBox.
	 * 
	 * @author orelero
	 *
	 */
	public class BoundingCircle implements Cloneable, Serializable
	{
		static final long serialVersionUID = 45L;
		
		public float radius;
		public Point center;

		
		/**
		 * 
		 */
		public BoundingCircle()
		{
			center = Point.getInstance();
		}
		
		/**
		 * copy _center's componants into this center.
		 * 
		 * @param _center
		 */
		public void setCenter(Point _center)
		{
			center.setXY(_center.getX(),_center.getY());
		}
		
		public BoundingCircle clone()
		{
			BoundingCircle boundingCircle = new BoundingCircle();
			boundingCircle.radius = radius;
			boundingCircle.center = center;
			return boundingCircle;
		}
	}
}
