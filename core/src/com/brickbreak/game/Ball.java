package com.brickbreak.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;


public class Ball
{
	private Texture texture_ball;
	private Sprite ball;
	
	//Encapsula um retângulo 2D, TENHO EIXO X E Y ,poderei utilizar o método de intersecção entre objetos(sprites)
	//overlaps = se esse retângulo sobrepõe a outro retângulo.Vamos usar essa ideia para colisão
	private Rectangle bounds;
	
	public Ball()
	{
		texture_ball = new Texture(Gdx.files.internal("ball2p.png"));
		ball = new Sprite(texture_ball);
		bounds = new Rectangle(0,50,20,20);
		
		

	}
	public Texture getBallTexture()
	{
		return texture_ball;
	}
	public Sprite getBallSprite()
	{
		return ball;
	}
	public void setBallBoundsX(int newx)
	{
		bounds.x+=newx;
	}
	public void setBallBoundsY(int newy)
	{
		bounds.y+=newy;
	}
	public float getBallBoundsXY(String valor)
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
	public Rectangle getBallBounds()
	{
		return bounds;
	}
}
