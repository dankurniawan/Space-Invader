import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application {

	Timeline timeline;
	Timeline timeine2;
	
	public static void main(String[] args){
		launch();
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		
		GameFactory.init();
		stage.setScene(GameLayout.init());
		stage.sizeToScene();
		stage.setResizable(false);
		stage.setTitle("SPACE INVADER");
		stage.show(); 
						
		
	}
}