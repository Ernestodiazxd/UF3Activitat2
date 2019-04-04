package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Arrays;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Sprite boss;
	TextureAtlas atlas;
	TextureRegion regio,regio2,regio3;
	TextureRegion[] frames,frames2;
	int altura,amplada;
	TextureRegion anim,zona;
	Animation animacio;
	float duracio;


	
	@Override
	public void create () {
		altura = Gdx.graphics.getHeight();
		amplada=Gdx.graphics.getWidth();
		batch = new SpriteBatch();
		atlas=new TextureAtlas("fitxerAtlas.atlas");
		regio=atlas.findRegion("LuffyKun");
		regio2=atlas.findRegion("darksaber");
		regio3=atlas.findRegion("badlogic");
		zona=new TextureRegion(regio3,0,0,25,33);
		boss=new Sprite(regio2,0,0,regio2.getRegionWidth(),regio2.getRegionHeight());
		boss.setPosition(150,50);
		boss.rotate(45);
		boss.flip(true,false);

		TextureRegion[][]temp=regio.split(regio.getRegionWidth()/7,regio.getRegionHeight()/4);
		frames=new TextureRegion[(temp.length*temp[0].length)];

		int index=0;
		for (int i=0; i<temp.length;i++){
			for (int j=0; j< temp[0].length;j++){
				frames[index++]=temp[i][j];
			}
		}

		frames2= Arrays.copyOf(frames,frames.length-4);
		for(int i=0;i<frames2.length;i++){
			frames2[i]=frames[i];
		}

		animacio=new Animation(0.15f,frames2);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		duracio += Gdx.graphics.getDeltaTime();
		anim= (TextureRegion) animacio.getKeyFrame(duracio,true);
		batch.begin();

		boss.draw(batch);
		batch.draw(zona,300,200);
		batch.draw(anim,50,50);


		batch.end();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		atlas.dispose();
	}
}
