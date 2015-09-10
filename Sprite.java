import javafx.scene.image.Image;


public enum Sprite {
	
	ALIEN("image/alien.png"), SHIP("image/ship.png"), PROJECTILE("image/projectile.png");

	private String location;
	
	private Sprite(String location){
		
		this.location = location;
		
	}
	
	public Image getImage(){
		return new Image(location);
	}
	
	
}
