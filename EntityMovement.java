
public class EntityMovement {


	public static void move(Direction direction, Entity entity, double bound){
		
		double speed = entity.getSpeed();
		
		if (isClear(direction, entity, bound, speed)){
			
			switch (direction){
				
			case RIGHT: moveRight(entity, speed); break;
			case LEFT: moveLeft(entity, speed); break;
			case UP: moveUp(entity, speed); break;
			case DOWN: moveDown(entity, speed); break;
			
			}
			
		}
		
	}
	
	public static void move(Direction direction, Entity entity, double bound, double speed){
		
		if (isClear(direction, entity, bound, speed)){
			
			switch (direction){
				
			case RIGHT: moveRight(entity, speed); break;
			case LEFT: moveLeft(entity, speed); break;
			case UP: moveUp(entity, speed); break;
			case DOWN: moveDown(entity, speed); break;
			
			}
			
		}
		
	}
	
	public static boolean isClear(Direction direction, Entity entity, double bound, double speed){
		
		switch (direction){
		
		case RIGHT: return isRightClear(entity, bound, speed);
		case LEFT: return isLeftClear(entity, bound, speed);
		case UP: return isUpClear(entity, bound, speed); 
		case DOWN: return isDownClear(entity, bound, speed);
		default: return false;
		
		}
		
	}
	
	private static boolean isRightClear(Entity entity, double bound, double speed){
		return entity.getXLocation() + entity.getWidth() + speed <= bound;
	}
	
	private static boolean isLeftClear(Entity entity, double bound, double speed){
		return entity.getXLocation() - speed >= bound;
	}

	private static boolean isUpClear(Entity entity, double bound, double speed){
		return entity.getYLocation() - speed >= bound;
	}

	private static boolean isDownClear(Entity entity, double bound, double speed){
		return entity.getYLocation() + speed <= bound;
	}
	
	private static void moveRight(Entity entity, double speed) {

		entity.translateX(speed);
		
	}
	
	private static void moveLeft(Entity entity, double speed) {
		
		entity.translateX(-speed);		
		
	}
	
	private static void moveDown(Entity entity, double speed) {
		
		entity.translateY(speed);
				
	}

	private static void moveUp(Entity entity, double speed) {
		
		entity.translateY(-speed);
		
	}
	
	
}
