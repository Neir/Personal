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

import java.io.Serializable;

import geometry.Point;
import geometry.Vector;

/**
 * keeps track of translation and rotation relative to a centerPosition and a null angle.
 * Used internally to make backtracking and geometry updates easier.
 * 
 * @author orelero
 *
 */
strictfp public class Transformation implements Cloneable, Serializable
{
	static final long serialVersionUID = 48L;
	

	/**
	 * can be checked but not update. Indicates this Transformation has been taken in consideration,
	 * but doesn't mean the transformation has been applied to the targeted geometry.
	 */
	private boolean checked;
	
	/**
	 * 
	 */
	private boolean updated;
	
	/**
	 * 
	 */
	private float angle;
	/**
	 * 
	 */
	private Point anchorPoint;
	
	/**
	 * 
	 */
	private Point centerPosition; //NEVER CHANGES
	
	/**
	 * 
	 */
	private Point translatedCenterPosition;
	
	/**
	 * 
	 */
	private Vector translation;
	
			/**
			 * 
			 */
			private Point workPoint;
			
			private float lastDeltaAngle;
			
			private Vector lastDeltaTranslation;
	
	/**
	 * @param _angle
	 * @param _centerPosition
	 * @param _translation
	 */
	public Transformation(float _angle, Point _centerPosition, Vector _translation)
	{
		angle = _angle;
		centerPosition = _centerPosition;
		translation = _translation;
		updated = true;
		checked = false;
		workPoint = Point.getInstance();
		anchorPoint = Point.getInstance();
		translatedCenterPosition = null;
		
		lastDeltaAngle = 0;
		lastDeltaTranslation = new Vector();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Transformation clone()
	{
		Transformation transformation = new Transformation(angle, centerPosition.clone(), translation.clone());
		transformation.setUpdated(updated);
		transformation.setAnchorPoint(anchorPoint.clone());
		transformation.setTranslatedCenterPosition(translatedCenterPosition.clone());
		transformation.setLastDeltaAngle(lastDeltaAngle);
		transformation.setLastDeltaTranslation(lastDeltaTranslation.clone());
		return transformation;
	}
	
	/**
	 * deep-copy all _transformation's field to this Transformation excepting lastDelta variables
	 * 
	 * @param _transformation
	 */
	public void copyTransferFrom(Transformation _transformation)
	{
		angle = _transformation.getAngle();
		anchorPoint.copyTransferFrom(_transformation.getAnchorPoint());
		centerPosition.setXY(_transformation.getCenterPosition().getX(), _transformation.getCenterPosition().getY());
		translation.setXY(_transformation.getTranslation().getX(), _transformation.getTranslation().getY());
		updated = _transformation.isUpdated();
		//lastDeltaAngle = _transformation.getLastDeltaAngle();
		//lastDeltaTranslation.copyTransferFrom(_transformation.getLastDeltaTranslation());
	}
	
	/**
	 * should be called only once per process update.
	 * 
	 * @param _deltaX
	 * @param _deltaY
	 */
	public void translate(float _deltaX, float _deltaY)
	{
		if(checked)
		{
			setLastDeltaAngle(0);getLastDeltaTranslation().setXY(0,0);
			checked = false;
		}
		getLastDeltaTranslation().addUp(_deltaX, _deltaY);
		translation.setXY(translation.getX() + _deltaX, translation.getY() + _deltaY);
		translatedCenterPosition.setXY(centerPosition.getX()+translation.getX(), centerPosition.getY()+translation.getY());
		setUpdated(true);
	}
	
	/**
	 * should be called only once per process update.
	 * 
	 * @param _angle delta angle in radians.
	 * @param _anchorPoint
	 */
	public void rotate(double _angle, Point _anchorPoint)
	{
		if(checked)
		{
			setLastDeltaAngle(0);getLastDeltaTranslation().setXY(0,0);
			checked = false;
		}
		
		anchorPoint.copyTransferFrom(_anchorPoint);
		workPoint.copyTransferFrom(translatedCenterPosition);
		Transformers.rotatePoint(workPoint, workPoint, _anchorPoint, _angle);
		float translationX = workPoint.getX() - translatedCenterPosition.getX();
		float translationY = workPoint.getY() - translatedCenterPosition.getY();
		if(translationX != 0 | translationY != 0)
		{
			translate(translationX, translationY);
		}
		angle = (float)Transformers.correctDirtyAngle(angle + _angle);
		lastDeltaAngle = (float)_angle;
		setUpdated(true);
	}
	
	//GETTERS SETTERS
	
	/**
	 * @return
	 */
	public float getAngle()
	{
		return angle;
	}

	/**
	 * @param angle
	 */
	public void setAngle(float angle)
	{
		this.angle = angle;
	}

	/**
	 * @return
	 */
	protected Point getCenterPosition()
	{
		return centerPosition;
	}

	/**
	 * @param centerPosition
	 */
	public void setCenterPosition(Point _centerPosition)
	{
		translatedCenterPosition = _centerPosition.clone();
		centerPosition = _centerPosition;
	}


	/**
	 * @return
	 */
	public Vector getTranslation()
	{
		return translation;
	}


	/**
	 * @param translation
	 */
	public void setTranslation(Vector translation)
	{
		this.translation = translation;
	}


	/**
	 * @return
	 */
	public boolean isUpdated()
	{
		return updated;
	}


	/**
	 * force the geometry to be update in function of this Transformation
	 * 
	 * @param updated
	 */
	public void setUpdated(boolean updated)
	{
		this.updated = updated;
	}

	/**
	 * @return
	 */
	public Point getAnchorPoint() {
		return anchorPoint;
	}

	/**
	 * @return
	 */
	public Point getTranslatedCenterPosition()
	{
		return translatedCenterPosition;
	}


	/**
	 * @param translatedCenterPosition
	 */
	public void setTranslatedCenterPosition(Point translatedCenterPosition)
	{
		this.translatedCenterPosition = translatedCenterPosition;
	}

	/**
	 * @param anchorPoint center of rotation.
	 */
	public void setAnchorPoint(Point anchorPoint)
	{
		this.anchorPoint = anchorPoint;
	}

	/**
	 * @return the delta angle between the last rotation and the current one.
	 */
	public float getLastDeltaAngle()
	{
		return lastDeltaAngle;
	}

	/**
	 * @return the delta translation between the last position and the current one.
	 */
	public Vector getLastDeltaTranslation()
	{
		return lastDeltaTranslation;
	}

	protected void setLastDeltaAngle(float lastDeltaAngle)
	{
		this.lastDeltaAngle = lastDeltaAngle;
	}

	protected void setLastDeltaTranslation(Vector lastDeltaTranslation)
	{
		this.lastDeltaTranslation = lastDeltaTranslation;
	}
	
	/**
	 * used internally only.
	 * 
	 * @param _checked
	 */
	public void setChecked(boolean _checked)
	{
		checked = _checked;
	}
}
