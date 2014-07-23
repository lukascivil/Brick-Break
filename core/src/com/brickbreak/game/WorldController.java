package com.brickbreak.game;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;

public class WorldController 
{
	//ball
	public Ball ball;
	public Sprite ballsprite;
	public Texture balltexture;
	public Array<Sprite> balls = new Array<Sprite>();
	//ground
	public Ground ground;
	public Sprite groundsprite;
	public Texture groundtexture;
	
	
	
		//constructor
		public WorldController()
		{
			
			init();	
			
		}
		//start the WorldController, good practice to not initialize the class with the Constructor(libgdx order)
		//iff we need to reset an object in the game. With this method we save performance
		private void init()
		{
			initTestObjects();
		}
		//game logic, it will be called secveral ties per second.it can aply updates 
		//to the game world according to the fraction of time that has passed since the last rendered time.
		private void initTestObjects() //1vez
		{
		    ball = new Ball();
		    	//pode utilizar as duas maneiras
			 	ballsprite= ball.getBallSprite();	
			 	balltexture= ball.getBallTexture();
			 	balls.add(ballsprite);
			ground = new Ground();
				groundsprite= ground.getGroundSprite();
				
				//---------------------------------------------
				
				
		}
		public void update() //loop
		{
			if(Constants.playball==true)
			{
				ball.setBallBoundsX(5);
				ball.setBallBoundsY(5);
				Constants.velx=5;
				Constants.vely=5;
			}
					if(ball.getBallBoundsXY("y")>700)
					{
						//bateu em cima
						Constants.playball=false;
						Constants.touch=1;	
						Constants.velx=Constants.velx;
						Constants.vely=-Constants.vely;
				
					}
					if(ball.getBallBoundsXY("y")<-150)
					{
						//bate no chão
						Constants.playball=false;
						Constants.touch=0;
						Constants.velx=Constants.velx;
						Constants.vely=-Constants.vely;
						balls.removeIndex(0);
						
						
					}
					if(ball.getBallBoundsXY("x")<0)
					{
						//bateu no lado esquerdo
						Constants.playball=false;
						Constants.touch=2;
						Constants.velx=-Constants.velx;
						Constants.vely=Constants.vely;
						
						
						
					}
					if(ball.getBallBoundsXY("x")>380)
					{
						//bateu no lado direito
						Constants.playball=false;
						Constants.touch=3;
						Constants.velx=-Constants.velx;
						Constants.vely=Constants.vely;
						
					}
					
				
					
					
				//------------------------------------------------------
				if(Constants.touch==0)
				{
					ball.setBallBoundsX(Constants.velx);
					ball.setBallBoundsY(Constants.vely);		
				}
				if(Constants.touch==1)
				{			
					ball.setBallBoundsX(Constants.velx);
					ball.setBallBoundsY(Constants.vely);	
				}
				if(Constants.touch==2)
				{	
					ball.setBallBoundsX(Constants.velx);
					ball.setBallBoundsY(Constants.vely);	
				}
				if(Constants.touch==3)
				{
					ball.setBallBoundsX(Constants.velx);
					ball.setBallBoundsY(Constants.vely);	
				}
				if(Constants.touch==4)
				{
					ball.setBallBoundsX(Constants.velx);
					ball.setBallBoundsY(Constants.vely);	
				}
				
				//System.out.println(" width "+ground.getGroundBounds().width);
				//System.out.println(" x "+ground.getGroundBounds().x);
				
				/*if(ball.getBallBounds().overlaps(ground.getGroundBounds()))
				{
				
					Constants.touch=4;
					Constants.velx=Constants.velx;
					Constants.vely=-Constants.vely;
					System.out.println("ai meu rim");
				}*/
				
				 if(Intersector.overlaps(ball.getBallBounds(),ground.getGroundBounds()))
				 {
					    
					    Constants.touch=4;
						Constants.velx=Constants.velx;
						Constants.vely=-Constants.vely;
						System.out.println("ai meu rim");
						
						//TENHO DE PARAR DE ANDAR COM O GROUND pular -6 em y , para nao bugar
						//ground.setGroundBoundsY(-6);
						
				 }
				 		 		 
				 //LADO ESQUERDO DO GROUND
				 //ground(x1,y1)---------(x2,y2)    //  ball(x3,y3)---------(x4,y4)
				 //NÃO PODE SER RETAS PARALELAS (ERRO), POIS HÁ VARIOS PONTOS DE INTERSECÇÃO
				 if(Intersector.intersectSegments(ground.getGroundBoundsXY("x")-20,ground.getGroundBoundsXY("y")+20,
						 ground.getGroundBoundsXY("x")-10,ground.getGroundBoundsXY("y"),
						 ball.getBallBoundsXY("x")+100, ball.getBallBoundsXY("y")+100,
						 ball.getBallBoundsXY("x")+100, ball.getBallBoundsXY("y"), new Vector2()))
				 {
					 System.out.println("BATEU NA ESQUERDA");
					 Constants.touch=2;
					 Constants.velx=-Constants.velx;
					 Constants.vely=Constants.vely;
					//TENHO DE PARAR DE ANDAR COM O GROUND pular 6 em X , para nao bugar
					 ground.setGroundBoundsX(6);
				 }
				 
				 //LADO DIREITO DO GROUND
				 //ground(x1,y1)---------(x2,y2)    //  ball(x3,y3)---------(x4,y4)
				 //NÃO PODE SER RETAS PARALELAS (ERRO), POIS HÁ VARIOS PONTOS DE INTERSECÇÃO
				 if(Intersector.intersectSegments(ground.getGroundBoundsXY("x")+170,ground.getGroundBoundsXY("y")+20,
						 ground.getGroundBoundsXY("x")+160,ground.getGroundBoundsXY("y"),
						 ball.getBallBoundsXY("x"), ball.getBallBoundsXY("y")+100,
						 ball.getBallBoundsXY("x"), ball.getBallBoundsXY("y"), new Vector2()))
				 {
					 System.out.println("BATEU NA DIREITA");
					 Constants.touch=2;
					 Constants.velx=-Constants.velx;
					 Constants.vely=Constants.vely;
					//TENHO DE PARAR DE ANDAR COM O GROUND pular 6 em X , para nao bugar
					 ground.setGroundBoundsX(-6);
				 }
				
			
			
			//Interação com o usuário
			handleDebugInput();
		}
		private void handleDebugInput()
		{
			//processor = new MyInputProcessor();	         //I DONT KNOW HOW TO INSERT MORE THAN 2 SETINPUTPROCESSOR
			//Gdx.input.setInputProcessor(processor);      //I DONT KNOW HOW TO INSERT MORE THAN 2 SETINPUTPROCESSOR
			//--------------------------------------------------------------------
			//CameraInputController processortest = new CameraInputController();
			//Gdx.input.setInputProcessor(processortest);
			//--------------------------------------------------------------------
			
			
			//float deltaTime=0.5f;
			if(Gdx.app.getType()==ApplicationType.Desktop)
			{
				 		
				if(Gdx.input.isKeyPressed(Keys.A))
				{
					//Faz o acesso a minha variável ground e mexe no seu eixo x (seto minha velocidade a esquerda)
					ground.setGroundBoundsX(-5);					
					
				}
				if(Gdx.input.isKeyPressed(Keys.D))
				{
					//Faz o acesso a minha variável ground e mexe no seu eixo x (seto minha velocidade a direita)
					ground.setGroundBoundsX(5);				
					
				}
				if(Gdx.input.isKeyPressed(Keys.ENTER))
				{
					//Começa o Jogo
					Constants.playball=true;
				}
				if(Gdx.input.isKeyPressed(Keys.S))
				{
					//ground.setGroundBoundsY(-5);
				}
				if(Gdx.input.isKeyPressed(Keys.W))
				{
					//ground.setGroundBoundsY(5);
				}
				
			}
			if(Gdx.app.getType()==ApplicationType.Android)
			{
				if(Gdx.input.isTouched())
				{
					//pego a coordenada x da minha sprite
					ground.setGroundBoundsWithTouch(Gdx.input.getX()-100);
			
					//quando a tela for tocada posso ativar o update da bola
					if(Constants.contplayandroid==0)
					{
						Constants.contplayandroid++;
						Constants.playball=true;
					}
					
				}
									
				
			}
		}
		public void dispose()
		{
			
		}

}
