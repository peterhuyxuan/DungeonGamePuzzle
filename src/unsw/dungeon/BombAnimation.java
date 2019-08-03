package unsw.dungeon;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class BombAnimation {

	
	
	private Image lit1;
	private Image lit2;
	private Image lit3;
	private Image lit4;
	private ArrayList<Image> images;
		
	public BombAnimation () {
		lit1 = new Image("bomb_lit_1.png");
		lit2 = new Image("bomb_lit_2.png");
		lit3 = new Image("bomb_lit_3.png");
		lit4 = new Image("bomb_lit_4.png");	
	}
	
	public Timeline makeTimeLine(){
		/*
		KeyValue kvL1True = new KeyValue (this.getLit1(), true);
		KeyValue kvL1False = new KeyValue (this.getLit1(), false);
		KeyValue kvL2True = new KeyValue (this.getLit2(), true);
		KeyValue kvL2False = new KeyValue (this.getLit2(), false);
		KeyValue kvL3True = new KeyValue (this.getLit3(), true);
		KeyValue kvL3False = new KeyValue (this.getLit3(), false);
		KeyValue kvL4True = new KeyValue (this.getLit4(), true);
		KeyValue kvL4False = new KeyValue (this.getLit4(), false);
		KeyValue kvL4False = new KeyValue (this.getLit4(), false);
		
		final Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), kvL1True, kvL2False, kvL3False, kvL4False));
	 	timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), kvL1False, kvL2True, kvL3False, kvL4False));
	 	timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2000), kvL1False, kvL2False, kvL3True, kvL4False));
	 	timeline.getKeyFrames().add(new KeyFrame(Duration.millis(3000), kvL1False, kvL2False, kvL3False, kvL4True));
	 	timeline.getKeyFrames().add(new KeyFrame(Duration.millis(4000), kvL1False, kvL2False, kvL3False, kvL4False));*/
		
		TranslateTransition tt = new TranslateTransition(Duration.millis(2000), new ImageView(lit1));
	    tt.getNode().setVisible(true);
	 
	     tt.play();
	 
		
	}

}
