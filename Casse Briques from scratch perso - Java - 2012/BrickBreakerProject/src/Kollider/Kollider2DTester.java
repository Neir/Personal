package Kollider;
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

import geometry.*;

import java.util.ArrayList;
import java.util.LinkedList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


import collision.*;
import collision.MultiPolygon.BoundingCircle;



public class Kollider2DTester extends BasicGame
{
	private Image libPic;
	private Image contactPointPic;
	
	private final float e1Speed = 0.1F;
	private final double e1RotateSpeed = Math.PI/1700;
	
	private Kollider kollider;
	
	private MultiPolygon entity1;
	private MultiPolygon entity2;
	
	
	public Kollider2DTester(String _title)
	{
		super(_title);
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer container = new AppGameContainer(new Kollider2DTester("kollider2D demo"), 800, 600, false); 
			container.start();
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		} 
	}

	@SuppressWarnings("serial")
	@Override
	public void init(GameContainer container) throws SlickException
	{
		libPic = new Image("resources/kollider2Dsmall.png");
		contactPointPic = new Image("resources/contactPoint.png");
		
		container.setShowFPS(true); 
		container.setTargetFrameRate(60);
        container.getGraphics().setAntiAlias(true);
        
        kollider = new Kollider(true);
        
        entity1 = new MultiPolygon(kollider, true, false);
        
        TriggeringPolygon tp = new TriggeringPolygon(Point.getInstance(60,50));
        	
        tp.addPoint(Point.getInstance(70,90));
        tp.addPoint(Point.getInstance(80,70));
        tp.close();
        tp.setActivity(true);
        tp.name = "e1 - 3points";
        
        TriggeringPolygon tp3 = new TriggeringPolygon(Point.getInstance(90,90));
        	
        CollisionEventHandler ceh = new CollisionEventHandler()
        {
        	public void onCollision(CollisionInfo _collisionInfo)
        	{
        		System.out.println("tp3 collided !");
        	}
        };
        
        tp3.setCollisionEventHandler(ceh);
        
        tp3.addPoint(Point.getInstance(130,90));
        tp3.addPoint(Point.getInstance(130,130));
        tp3.addPoint(Point.getInstance(90,130));
        tp3.close();
        tp3.setActivity(true);
        tp3.name = "e1 - 4points";
        
        
        entity1.addTriggeringPolygon(tp,true);
        entity1.addTriggeringPolygon(tp3,true);
        entity1.updateBoundingBox();
        entity1.setActivity(true);
        entity1.getTransformation().translate(0, -30);
        
        entity2 = new MultiPolygon(kollider, true, true);
        
        TriggeringPolygon tp2 = new TriggeringPolygon(Point.getInstance(450,40));
        	
        tp2.addPoint(Point.getInstance(500,40));
        tp2.addPoint(Point.getInstance(470,70));
        tp2.close();
        tp2.setActivity(true);
        tp2.name = "e2 - 3points";
        
        entity2.addTriggeringPolygon(tp2,true);
        entity2.updateBoundingBox();
        entity2.setActivity(true);
	}

	@Override
	public void update(GameContainer container, int tpf) throws SlickException
	{
		//TODO: use a system timer to get rid of the "window-move-with-no-update" bug ! ie: don't use tpf

		updateInputs(container, tpf);
		
		//entity2.getTransformation().rotate(e1RotateSpeed*tpf, entity2.getTransformation().getTranslatedCenterPosition());
		
		kollider.process();
	}

	
	public void render(GameContainer container, Graphics g) throws SlickException
	{
		g.setBackground(Color.gray);
		
		drawMultiPolygons(kollider.getDynamicMultiPolygons(), g);
		
		drawMultiPolygons(kollider.getStaticMultiPolygons(), g);
		
		g.drawImage(libPic, 300, 300);
		
		g.setColor(Color.white);
		
		g.drawString("Control the MultiPolygon with arrow keys and hit space to rotate it", 275, 2);
	}

	public void updateInputs(GameContainer _container, int _tpf)
	{
		if (_container.getInput().isKeyDown(Input.KEY_LEFT))
		{ 
			entity1.getTransformation().translate(-_tpf*e1Speed, 0);
        } 
        if (_container.getInput().isKeyDown(Input.KEY_RIGHT))
        { 
        	entity1.getTransformation().translate(_tpf*e1Speed, 0);
        } 
        if (_container.getInput().isKeyDown(Input.KEY_UP))
        { 
        	entity1.getTransformation().translate(0, -_tpf*e1Speed);
        } 
        if (_container.getInput().isKeyDown(Input.KEY_DOWN))
        { 
        	entity1.getTransformation().translate(0, _tpf*e1Speed);
        } 
        if (_container.getInput().isKeyDown(Input.KEY_SPACE))
        { 
        	entity1.getTransformation().rotate(e1RotateSpeed*_tpf, entity1.getTransformation().getTranslatedCenterPosition());
        } 
	}
	
	public void drawMultiPolygons(ArrayList<MultiPolygon> _mps, Graphics _g)
	{
		for(MultiPolygon mp : _mps)
		{
			drawMultiPolygon(mp, _g);
		}
	}
	
	public void drawMultiPolygon(MultiPolygon _mp, Graphics _g)
	{
		boolean hasCollided = _mp.hasCollided();
		
		if(hasCollided)
		{
			_mp.backTrack();
		}
		else _mp.saveTransformation();
		
		for(TriggeringPolygon tp : _mp.getTriggeringPolygons())
		{
			for(int i = 0 ; i < tp.getPoints().size()-1 ; i++)
			{
				_g.setColor(Color.red);
				Segment s = tp.getSegment(i);
				_g.drawLine(s.getP1().getX(), s.getP1().getY(), s.getP2().getX(), s.getP2().getY());
				_g.setColor(Color.white);
				Segment normal = s.updateNormal();
				_g.drawLine(normal.getP1().getX(), normal.getP1().getY(), normal.getP2().getX(), normal.getP2().getY());
			}
			
			_g.setColor(Color.lightGray);
			if(_mp.isBoxBounded())
			_g.drawRect(_mp.getBoundingBox().leftBound, _mp.getBoundingBox().downBound, _mp.getBoundingBox().width, _mp.getBoundingBox().height);
			else
			{
				BoundingCircle bc = _mp.getBoundingCircle();
				_g.drawOval(bc.center.getX() - bc.radius, bc.center.getY() - bc.radius, 2*bc.radius, 2*bc.radius);
			}
		}
		
		if(hasCollided)
		{
			for(TriggeringPolygon tp : _mp.getTriggeringPolygons())
			{
				LinkedList<CollisionInfo> collisionInfoStack = tp.getCollisionInfoStack();
				
				for(CollisionInfo ci : collisionInfoStack)
				{
					if(ci.getContactPoint() != null)
					{
						_g.drawImage(contactPointPic, ci.getContactPoint().getX()-2, ci.getContactPoint().getY()-2);
						
						Vector motionVector = tp.getMotionVector(ci.getContactPoint());
						
						_g.setColor(Color.blue);
						_g.drawLine(ci.getContactPoint().getX(), ci.getContactPoint().getY()
								, ci.getContactPoint().getX()+motionVector.getX(), ci.getContactPoint().getY()+motionVector.getY());
					}
					
					if(ci.getEdgeContactIndex() != null)
					{
						Segment s = tp.getSegment(ci.getEdgeContactIndex());
						_g.setColor(Color.orange);
						_g.drawLine(s.getP1().getX(), s.getP1().getY(), s.getP2().getX(), s.getP2().getY());
					}
				}
			}
		}
	}
	
	public static void drawPolygon(Polygon _p, Graphics _g)
	{
		for(int i = 0 ; i < _p.getPoints().size()-1 ; i++)
		{
			_g.setColor(Color.blue);
			Segment s = _p.getSegment(i);
			_g.drawLine(s.getP1().getX(), s.getP1().getY(), s.getP2().getX(), s.getP2().getY());
		}
	}
}
