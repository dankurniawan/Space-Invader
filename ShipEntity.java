import javafx.scene.image.Image;


public class ShipEntity extends Entity{

	private static final int WIDTH = 25;
	private static final int HEIGHT = 15;
	
	private static final double SPEED = 3;
	
	public ShipEntity(double x, double y) {
		super(WIDTH, HEIGHT);
		this.setXLocation(x);
		this.setYLocation(y);
	}
	
	@Override
	public Image getSprite() {
		return Sprite.SHIP.getImage();
	}
	
	public ProjectileEntity shoot(){
		
		return new ProjectileEntity(this.getXLocation() + WIDTH / 2, this.getYLocation());
		
	}

	@Override
	public double getSpeed() {
		return SPEED;
	}
	
}

	
