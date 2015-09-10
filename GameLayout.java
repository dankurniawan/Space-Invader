import java.util.List;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GameLayout {

public static final int GAME_WIDTH = 500;
public static final int GAME_HEIGHT = 400;

private static List<Entity> entityList = GameFactory.getAllEntities();
private static Pane mainPane = new Pane();

private static Image backgroundImage = new Image("image/background.png");

	public static Scene init(){
		
		
		
		Background background = new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, 
				BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
		
		HorizontalLine loseBound = new HorizontalLine(375, GAME_WIDTH, Color.rgb(0, 255, 0));
		
		addEntity();
		
		mainPane.setPrefHeight(GAME_HEIGHT);
		mainPane.setPrefWidth(GAME_WIDTH);
		mainPane.setBackground(background);
		mainPane.getChildren().add(loseBound);
		
		Scene scene = new Scene(mainPane);
		scene.addEventHandler(KeyEvent.KEY_PRESSED, GameFactory.keyEvent);
		return scene;
		
		
		
	}

	private static void addEntity() {
		
		for (Entity entity: entityList){
			mainPane.getChildren().add(entity.draw());
		}
		
	}
	
	public static void addProjectile(ProjectileEntity projectile){
		mainPane.getChildren().add(projectile.draw());
	}
	
	
	
}
