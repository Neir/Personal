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
import geometry.Vector;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

import transformation.Transformation;


/**
 * A Polygon that triggers events in function of the triggered edge and its id.
 * 
 * 
 * @author orelero
 *
 */
strictfp public class TriggeringPolygon extends Polygon implements Cloneable,Serializable
{
	/**
	 * 
	 */
	static final long serialVersionUID = 44L;
	
	/**
	 * 
	 */
	private MultiPolygon owner;
	
	/**
	 * 
	 */
	private ArrayList<Integer> triggerPerSegmentList;
	
	/**
	 * 
	 */
	private boolean isActive;
	
	/**
	 * 
	 */
	private LinkedList<CollisionInfo> collisionInfoStack;
	
	/**
	 * 
	 */
	private CollisionEventHandler collisionEventHandler;
	
	/**
	 * 
	 */
	public String name;
	
		private Vector motionVector;
	
	/**
	 * @param _startingPoint
	 */
	public TriggeringPolygon(Point _startingPoint)
	{
		super(_startingPoint);
		owner = null;
		triggerPerSegmentList = new ArrayList<Integer>();
		isActive = false;
		collisionInfoStack = new LinkedList<CollisionInfo>();
		collisionEventHandler = null;
		motionVector = new Vector();
	}
	
	/* (non-Javadoc)
	 * @see Geometry.Polygon#clone()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public TriggeringPolygon clone()
	{
		TriggeringPolygon triggeringPolygon = new TriggeringPolygon(null);
		Polygon polygon = super.clone();
		triggeringPolygon.setPointList(polygon.getPoints());
		triggeringPolygon.forceClosure(polygon.isClosed());
		
		ArrayList<Integer> clonedTriggerPerSegmentList = new ArrayList<Integer>();
		for(int integer : triggerPerSegmentList)
		{
			clonedTriggerPerSegmentList.add(integer);
		}
		triggeringPolygon.setTriggerPerSegmentList(clonedTriggerPerSegmentList);
		
		triggeringPolygon.setActivity(isActive);
		triggeringPolygon.setOwner(owner);
		
		LinkedList<CollisionInfo> clonedCollisionInfoStack = new LinkedList<CollisionInfo>();
		for(CollisionInfo ci : collisionInfoStack)
		{
			clonedCollisionInfoStack.add(ci.clone());
		}
		triggeringPolygon.setCollisionInfoStack(clonedCollisionInfoStack);
		
		return triggeringPolygon;
	}
	
	/* (non-Javadoc)
	 * @see Geometry.Polygon#addPoint(Geometry.Point)
	 */
	@Override
	public void addPoint(Point _point)
	{
		super.addPoint(_point);
		triggerPerSegmentList.add(0);
	}
	
	/* (non-Javadoc)
	 * @see Geometry.Polygon#removePoint()
	 */
	@Override
	public void removePoint()
	{
		super.removePoint();
		triggerPerSegmentList.remove(triggerPerSegmentList.size()-1);
	}
	
	/* (non-Javadoc)
	 * @see Geometry.Polygon#close()
	 */
	@Override
	public void close()
	{
		if(!super.isClosed())
		{
			super.forceClosure(true);
			addPoint(super.getPoints().get(0).clone());
		}
	}
	
	/**
	 * @param _segmentIndex
	 * @param _triggerId
	 */
	public void addTrigger(int _segmentIndex, Integer _triggerId)
	{
		triggerPerSegmentList.add(_segmentIndex, _triggerId);
	}
	
	/**
	 * @param _segmentIndex
	 * @return
	 */
	public int getTrigger(int _segmentIndex)
	{
		return triggerPerSegmentList.get(_segmentIndex);
	}
	
	/**
	 * @param _collisionInfo adds _collisionInfo to the the collision stack
	 */
	public void addCollisionInfo(CollisionInfo _collisionInfo)
	{
		collisionInfoStack.add(_collisionInfo);
	}
	
	/**
	 * @param _index
	 */
	public void removeCollisionInfo(int _index)
	{
		collisionInfoStack.remove(_index);
	}
	
	/**
	 * @param _index
	 * @return
	 */
	public CollisionInfo getCollisionInfo(int _index)
	{
			return collisionInfoStack.get(_index);
	}
	
	/**
	 * get _point's estimated motion vector. Divide by a deltaTime to get the linear velocity.
	 * 
	 * @param _point
	 * @return
	 */
	public Vector getMotionVector(Point _point)
	{
		motionVector.setXY(0,0);
		
		Transformation t = owner.getTransformation();
		
		Point anchorPoint = t.getAnchorPoint();
		
		float rotationVectorNorm = t.getLastDeltaAngle() * _point.getDistance(anchorPoint);
		Vector rotationVector =
			(new Vector(_point.getY() - anchorPoint.getY(), anchorPoint.getX() - _point.getX()))
			.normalize().mult(rotationVectorNorm);
		
		Vector translationVector = t.getLastDeltaTranslation();
		
		motionVector.addUp(translationVector).addUp(rotationVector);
		return motionVector;
	}
	
	//GETTERS SETTERS

	/**
	 * @return
	 */
	public ArrayList<Integer> getTriggerPerSegmentList()
	{
		return triggerPerSegmentList;
	}
	
	/**
	 * @param triggerPerSegmentList
	 */
	protected void setTriggerPerSegmentList(ArrayList<Integer> triggerPerSegmentList)
	{
		this.triggerPerSegmentList = triggerPerSegmentList;
	}
	
	/**
	 * @return
	 */
	public boolean isActive()
	{
		return isActive;
	}
	
	/**
	 * Taken in consideration by its kollider if active.
	 * 
	 * @param _isActive
	 */
	public void setActivity(boolean _isActive)
	{
		isActive = _isActive;
	}
	
	/**
	 * @return the MultiPolygon this TriggeringPolygon belongs to.
	 */
	public MultiPolygon getOwner()
	{
		return owner;
	}
	
	/**
	 * not recommended.
	 * 
	 * @param _owner
	 */
	protected void setOwner(MultiPolygon _owner)
	{
		owner = _owner;
	}
	
	/**
	 * @return
	 */
	public LinkedList<CollisionInfo> getCollisionInfoStack()
	{
		return collisionInfoStack;
	}
	
	/**
	 * @param _collisionInfoStack
	 */
	private void setCollisionInfoStack(LinkedList<CollisionInfo> _collisionInfoStack)
	{
		collisionInfoStack = _collisionInfoStack;
	}
	
	public void setCollisionEventHandler(CollisionEventHandler _collisionEventHandler)
	{
		collisionEventHandler = _collisionEventHandler;
	}
	
	public CollisionEventHandler getCollisionEventHandler()
	{
		return collisionEventHandler;
	}
}
