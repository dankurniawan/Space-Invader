import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;


public class GameFactory {

	private static Timeline timeline;
	
	public static List<AlienEntity> alienEntities = new ArrayList<AlienEntity>();
	public static ShipEntity shipEntity;
	
	private static boolean isPaused;
	
	private static Direction alienCurrentDirection;
	
	public static EventHandler<KeyEvent> keyEvent = new EventHandler<KeyEvent>() {

		public void handle(KeyEvent keyEvent){
		
			ShipEntity ship = shipEntity;		
				
			switch(keyEvent.getCode()){


			case RIGHT: EntityMovement.move(Direction.RIGHT, ship, GameLayout.GAME_WIDTH); break;
			case LEFT: EntityMovement.move(Direction.LEFT, ship, 0); break;

			case SPACE: ProjectileEntity.shoot(ship); break;

			case P: 

				if (isPaused){
					unpause();
				} else {
					pause();
				}

				break;

			default: break;

			}




			ship.render();
							
		}

	};
	
	public static final int FRAME_RATE_PER_SECOND = 100;
	
	private static final int INIT_NUM_ROW_ALIEN = 5;
	private static final int INIT_NUM_COL_ALIEN = 11;
	
	private static final int ALIEN_X_SPACING = 5;
	private static final int ALIEN_Y_SPACING = 5;
	
	private static final int ALIEN_X_INIT_LOCATION = 20;
	private static final int ALIEN_Y_INIT_LOCATION = 20;
	
	private static final int SHIP_X_INIT_LOCATION = 150;
	private static final int SHIP_Y_INIT_LOCATION = 350;
	
	public static void init(){
		
		initAllEntity();
		initTimeline();
		
		
	}

	private static void initAllEntity() {
		
		initAlien();
		initShip();
		
	}

	private static void initAlien() {
		
		for (int i = 0; i < INIT_NUM_COL_ALIEN; i++){
			for (int j = 0; j < INIT_NUM_ROW_ALIEN; j++){
				
				int startingXLocation = ALIEN_X_INIT_LOCATION + (AlienEntity.WIDTH + ALIEN_X_SPACING) * i;
				int startingYLocation = ALIEN_Y_INIT_LOCATION + (AlienEntity.HEIGHT + ALIEN_Y_SPACING) * j;
				
				alienEntities.add(new AlienEntity(startingXLocation, startingYLocation));
				
			}
		}
		
		alienCurrentDirection = Direction.RIGHT;
		
	}

	private static void initShip() {
		
		shipEntity = new ShipEntity(SHIP_X_INIT_LOCATION, SHIP_Y_INIT_LOCATION);
		
	}
	
	private static void initTimeline() {
	
		timeline = new Timeline(new KeyFrame(Duration.millis(1000 / FRAME_RATE_PER_SECOND), e -> renderGame()));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
		
	}


	private static void renderGame() {
		
		moveAllAlien();
		
	}

	private static void moveAllAlien(){
		
		moveAllAlien(alienCurrentDirection);
		
		for (AlienEntity alienEntity: alienEntities){
			alienEntity.render();
		}
		
	}
	
	private static double alienRightBound = GameLayout.GAME_WIDTH;
	private static double alienLeftBound = 0;
	
	private static double getAlienBound(Direction direction){
		
		switch (direction){
		
		case RIGHT: return alienRightBound;
		case LEFT: return alienLeftBound;
		default: return -1;
		
		}
		
	}
	
	private static double alienDownSpeed = AlienEntity.HEIGHT + ALIEN_Y_SPACING;
	
	//private static final double NUM_ALIEN_MOVE_TO_LOSE = 3;
	//private static final double playerLoseBound = ALIEN_Y_INIT_LOCATION + 
	//		(NUM_ALIEN_MOVE_TO_LOSE + INIT_NUM_COL_ALIEN) * alienDownSpeed;
	
	private static double LOSE_SPACING = 20;
	//draw a line in the lose spacing
	
	public static double playerLoseBound = SHIP_Y_INIT_LOCATION - LOSE_SPACING;
	
	private static boolean alienReachPlayerBound(){
		
		double frontAlienY = getFrontAlienY();
		
		if (frontAlienY + AlienEntity.HEIGHT >= playerLoseBound){
			return true;
		} else return false;
		
	}
	
	private static double getFrontAlienY(){
		
		double frontAlienY = alienEntities.get(0).getYLocation();
		
		for (AlienEntity alienEntity: alienEntities){
			double y = alienEntity.getYLocation();
			if (y > frontAlienY){
				frontAlienY = y;
			}
		}
		
		return frontAlienY;
	}
	
	private static void moveAllAlien(Direction alienCurrentDirection){
		
		if (alienIsClearToMove(alienCurrentDirection)){
			
			for (AlienEntity alienEntity: alienEntities){
				EntityMovement.move(alienCurrentDirection, alienEntity, getAlienBound(alienCurrentDirection));
			}
			
		} else {
			
			for (AlienEntity alienEntity: alienEntities){
				EntityMovement.move(Direction.DOWN, alienEntity, 100000, alienDownSpeed);
				
			}
			
			if (alienReachPlayerBound()){
				lose();
			}
			
			GameFactory.alienCurrentDirection = Direction.getOppositeDirection(alienCurrentDirection);			
			
		}
		
	}

	private static boolean alienIsClearToMove(Direction direction) {
		
		double speed = alienEntities.get(0).getSpeed();
		
		for (AlienEntity alienEntity: alienEntities){
			if (!(EntityMovement.isClear(direction, alienEntity, getAlienBound(direction), speed))){
				return false;
			}	
		}
		
		return true;
	}
	
	public static boolean isCollide(ProjectileEntity projectile) {
		
		for (AlienEntity alienEntity: alienEntities){
		
			if (alienEntity.isHit(projectile)){
				
				alienEntity.setYLocation(0);
				alienEntity.setVisible(false);
				alienEntities.remove(alienEntity);
		
				if (isWin()){
					win();
				}
	 			
				return true;
				
			}
			
		}
		
		return (projectile.getYLocation() <= 5);
		
			
	}
	
		
	////////
	

	private static boolean isWin() {
		
		return alienEntities.size() == 0;
	}

	public static List<Entity> getAllEntities(){
	
		List<Entity> entityList = new ArrayList<Entity>();
		
		storeAlienEntities(entityList, alienEntities);
		storeEntity(entityList, shipEntity);
		
		return entityList;
	}
	
	private static void storeAlienEntities(List<Entity> targetList, List<AlienEntity> entityList){
		
		for (Entity entity: entityList){
			storeEntity(targetList, entity);
		}
		
	}
	
	private static void storeEntity(List<Entity> targetList, Entity entity){
		targetList.add(entity);
	}
	
	private static void pause(){
		timeline.pause();
		isPaused = true;
	}
	
	private static void unpause(){
		timeline.play();
		isPaused = false;
	}
	
	private static void lose() {

		System.out.println("lose");
		timeline.stop();
		
	}
	
	private static void win() {
		
		System.out.println("win");
		timeline.stop();
		
	}
	
}
