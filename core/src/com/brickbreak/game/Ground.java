package com.brickbreak.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;


public class Ground
{
	private Texture texture_ground;
	private Sprite ground;
	
	//Encapsula um retângulo 2D, TENHO EIXO X E Y ,poderei utilizar o método de intersecção entre objetos(sprites)
	//overlaps = se esse retângulo sobrepõe a outro retângulo.Vamos usar essa ideia para colisão
	private Rectangle bounds;
	
	public Ground()
	{
		texture_ground = new Texture(Gdx.files.internal("ground.png"));
		ground = new Sprite(texture_ground);
		bounds = new Rectangle(0,0,150,20);
		
		//bounds.setCenter(0, -20);	
	}
	public Texture getGroungTexture()
	{
		return texture_ground;
	}
	public Sprite getGroundSprite()
	{
		return ground;
	}
	public void setGroundBoundsX(int newx)
	{
		bounds.x+=newx;
	}
	public void setGroundBoundsY(int newy)
	{
		bounds.y+=newy;
	}
	public void setGroundBoundsWithTouch(int b)
	{
		bounds.x=b;
	}
	public float getGroundBoundsXY(String valor)
	{
		
			if(valor.equals("x"))
			{
				return bounds.x;
			}
			if(valor.equals("y"))
			{
				return bounds.y;
			}
			System.out.println("por favor use x ou y");
		return 0;	
		
	}
	public Rectangle getGroundBounds()
	{
		return bounds;
	}
}