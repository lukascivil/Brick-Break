package com.brickbreak.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public class WorldRenderer implements Disposable
{
	/** the camera **/
	protected OrthographicCamera camera;
	private WorldController worldController;
	private TiledMap map;
	
	private BitmapFont font;
	
	SpriteBatch batch;
	SpriteBatch batch2;
	private Rectangle glViewport;
	
		public WorldRenderer(WorldController worldController)
		{
			
			this.worldController = worldController;
			init();
			
		
		}
		//Apenas é inicializado pelo BrickBreakMain
		private void init()
		{
			//Necessário para carregar o sprite(ela que faz desenhar)
			batch= new SpriteBatch();
			batch2= new SpriteBatch();
			font = new BitmapFont();
			camera = new OrthographicCamera(480, 800); 
			camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			camera.position.set(Gdx.graphics.getWidth() / 2+100000, Gdx.graphics.getHeight() / 2, 0);
			glViewport = new Rectangle(0, 0, 480, 800);
		}
		
		// método que irá conter a lógica de definir qual ordem do jogo
		// objetos são desenhados sobre os outros
		//Está no loop do loop BrickBreakMain
		public void render()
		{
		
			renderTestObjects();	
			
		}
		//Está no loop do loop BrickBreakMain
		private void renderTestObjects()
		{
				// clear the screen and setup the projection matrix
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				//camera.update();
				batch.begin();
					//batch desenha (sprite,x,y), onde x e y sao eixos das coordenadas
					//batch.draw(worldController.ballsprite,worldController.ball.getBallBoundsXY("x"),worldController.ball.getBallBoundsXY("y"));
					batch.draw(worldController.groundsprite,worldController.ground.getGroundBoundsXY("x"),worldController.ground.getGroundBoundsXY("y"));
					
					
					for(Ball ball: worldController.balls)
					{
						if(ball!=null)
						{
							batch.draw(ball.getBallSprite(),worldController.ball.getBallBoundsXY("x"),worldController.ball.getBallBoundsXY("y"));
						}
						
					}
						
				batch.end();	
				
				batch2.begin();
					font.draw(batch2,"Fps:"+ Gdx.graphics.getFramesPerSecond() ,0,800);
					font.draw(batch2,"Cam x:"+ camera.viewportWidth ,50,800);
					font.draw(batch2,"Cam y:"+ camera.viewportHeight ,140,800);
					font.draw(batch2,"Ground x: "+worldController.ground.getGroundBoundsXY("x") ,270, 800);
					font.draw(batch2,"Ground y: "+worldController.ground.getGroundBoundsXY("y") ,380, 800);
					//font.draw(batch2,"Ground x: "+worldController.ground.getGroundBounds().getPosition(new Vector2()) ,150, 80);
					//font.draw(batch2,"Ground y: "+worldController.ground.getGroundBoundsXY("y") ,380, 20);
					font.draw(batch2,"Ball x: "+worldController.ball.getBallBoundsXY("x") ,270, 780);
					font.draw(batch2,"Ball y: "+worldController.ball.getBallBoundsXY("y") ,380, 780);
					font.draw(batch2,"Height "+Gdx.graphics.getHeight(),50, 780);
					font.draw(batch2,"width: "+Gdx.graphics.getWidth(),140, 780);
				batch2.end();
	
		}

		@Override
		public void dispose() 
		{
			//Objects dispose
			 worldController.dispose();
		}
}
