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

/**
 * Implementing and passing a collisionEventHandler to a TriggerinPolygon gives more
 * flexibility. It enables to "react" during the collision detection process instead of
 * post process CollisionInfos.
 * 
 * @author orelero
 *
 */
public interface CollisionEventHandler
{
	/**
	 * occurs whenever a TriggerinPolygon's edge collided.
	 * Point.dump(...) should be called here if some contact points are no longer used.
	 * 
	 * @param _collisionInfo
	 */
	public void onCollision(CollisionInfo _collisionInfo);
}
