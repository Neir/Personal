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

import java.util.ArrayList;
import java.util.LinkedList;


/**
 * handles the necessary information to compute whatever needed.
 * A TriggeringPolygon can handle many CollisionInfos at a time.
 * 
 * *An Obstructer is the other TriggeringPolygon that caused the collision.
 * 
 * @author orelero
 *
 */
strictfp public class CollisionInfo implements Cloneable
{
	/**
	 * 
	 */
	private static LinkedList<CollisionInfo> collisionInfoRecycler = new LinkedList<CollisionInfo>();
	
	/**
	 * 
	 */
	private TriggeringPolygon obstructer;
	
	
	private Integer vertexContactIndex;
	
	private Integer edgeContactIndex;
	
	private Point contactPoint;
	
	private Integer vertexContactIndexObstructer;
	
	private Integer edgeContactIndexObstructer;
	
	private Point contactPointObstructer;
	
	/**
	 * @param _obstructer
	 * @param _vertexContactIndex
	 * @param _edgeContactIndex
	 * @param _contactPoint
	 * @param _vertexContactIndexObstructer
	 * @param _edgeContactIndexObstructer
	 * @param _contactPointObstructer
	 * @return
	 */
	public static CollisionInfo
			getInstance(TriggeringPolygon _obstructer, Integer _vertexContactIndex,
			Integer _edgeContactIndex, Point _contactPoint, Integer _vertexContactIndexObstructer,
			Integer _edgeContactIndexObstructer, Point _contactPointObstructer)
	{
		if(collisionInfoRecycler.size() == 0) return new CollisionInfo(_obstructer, _vertexContactIndex,
				_edgeContactIndex, _contactPoint, _vertexContactIndexObstructer,
				_edgeContactIndexObstructer, _contactPointObstructer);
		else
		{
			CollisionInfo collisionInfo = collisionInfoRecycler.get(0);
			collisionInfo.setObstructer(_obstructer);
			collisionInfo.setVertexContactIndex(_vertexContactIndex);
			collisionInfo.setEdgeContactIndex(_edgeContactIndex);
			collisionInfo.setContactPoint(_contactPoint);
			collisionInfo.setVertexContactIndexObstructer(_vertexContactIndexObstructer);
			collisionInfo.setEdgeContactIndexObstructer(_edgeContactIndexObstructer);
			collisionInfo.setContactPointObstructer(_contactPointObstructer);
			collisionInfoRecycler.remove(0);
			return collisionInfo;
		}
	}
	
	/**
	 * @param _collisionInfo called as much as possible to recycle CollisionInfo objects.
	 */
	public static void dump(CollisionInfo _collisionInfo)
	{
		if(_collisionInfo != null) collisionInfoRecycler.add(_collisionInfo);
	}
	
	/**
	 * @param _segmentIndex
	 * @param _contactPoint
	 * @param _obstructer
	 * @param _segmentIndexObstructer
	 */
	private CollisionInfo(TriggeringPolygon _obstructer, Integer _vertexContactIndex,
			Integer _edgeContactIndex, Point _contactPoint, Integer _vertexContactIndexObstructer,
			Integer _edgeContactIndexObstructer, Point _contactPointObstructer)
	{
		obstructer = _obstructer;
		vertexContactIndex = _vertexContactIndex;
		edgeContactIndex = _edgeContactIndex;
		contactPoint = _contactPoint;
		vertexContactIndexObstructer = _vertexContactIndexObstructer;
		edgeContactIndexObstructer = _edgeContactIndexObstructer;
		contactPointObstructer = _contactPointObstructer;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public CollisionInfo clone()
	{
		CollisionInfo collisionInfo = getInstance(obstructer, (int)(vertexContactIndex),
				(int)(edgeContactIndex), contactPoint.clone(), (int)(vertexContactIndexObstructer),
				(int)(edgeContactIndexObstructer), contactPointObstructer.clone());
		return collisionInfo;
	}

	//GETTERS SETTERS
	
	/**
	 * @return the TriggeringPolygon that obstructed the targeted TriggeringPolygon.
	 */
	public TriggeringPolygon getObstructer() {
		return obstructer;
	}

	public void setObstructer(TriggeringPolygon obstructer) {
		this.obstructer = obstructer;
	}

	/**
	 * @return the vertex index of the targeted TriggeringPolygon implicated in this Collision.
	 */
	public Integer getVertexContactIndex() {
		return vertexContactIndex;
	}

	public void setVertexContactIndex(Integer vertexContactIndex) {
		this.vertexContactIndex = vertexContactIndex;
	}

	/**
	 * @return the edge index of the targeted TriggeringPolygon implicated in this Collision.
	 */
	public Integer getEdgeContactIndex() {
		return edgeContactIndex;
	}

	public void setEdgeContactIndex(Integer edgeContactIndex) {
		this.edgeContactIndex = edgeContactIndex;
	}

	/**
	 * @return the contact Point of the targeted TriggeringPolygon implicated in this Collision.
	 */
	public Point getContactPoint() {
		return contactPoint;
	}

	public void setContactPoint(Point contactPoint) {
		this.contactPoint = contactPoint;
	}

	/**
	 * @return
	 */
	public Integer getVertexContactIndexObstructer() {
		return vertexContactIndexObstructer;
	}

	public void setVertexContactIndexObstructer(Integer vertexContactIndexObstructer) {
		this.vertexContactIndexObstructer = vertexContactIndexObstructer;
	}

	/**
	 * @return
	 */
	public Integer getEdgeContactIndexObstructer() {
		return edgeContactIndexObstructer;
	}

	public void setEdgeContactIndexObstructer(Integer edgeContactIndexObstructer) {
		this.edgeContactIndexObstructer = edgeContactIndexObstructer;
	}

	/**
	 * @return
	 */
	public Point getContactPointObstructer() {
		return contactPointObstructer;
	}

	public void setContactPointObstructer(Point contactPointObstructer) {
		this.contactPointObstructer = contactPointObstructer;
	}
}
