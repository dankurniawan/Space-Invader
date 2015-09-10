

public enum Direction {
	
	RIGHT, LEFT, DOWN, UP;
	
	public static Direction getOppositeDirection(Direction direction){
		
		switch (direction){
		
		case RIGHT: return Direction.LEFT;
		case LEFT: return Direction.RIGHT;
		case DOWN: return Direction.UP;
		case UP: return Direction.DOWN;
		default: return null;
		
		}
		
	}
	
}
