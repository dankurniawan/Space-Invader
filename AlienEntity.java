import javafx.scene.image.Image;


public class AlienEntity extends Entity {

	public static final int WIDTH = 20;
	public static final int HEIGHT = 20;
	
	private static final double SPEED = 0.3;
	
	public AlienEntity(double x, double y) {
		super(WIDTH, HEIGHT);
 		this.setXLocation(x);
		this.setYLocation(y);
	}

	@Override
	public Image getSprite() {
		return Sprite.ALIEN.getImage();
	}

	@Override
	public double getSpeed() {
		return SPEED;
	}

	public boolean isHit(ProjectileEntity projectile) {

		double x = projectile.getXLocation();
		double y = projectile.getYLocation();
		
		boolean xIsOnBound = (x >= this.getXLocation() && x <= this.getXLocation() + WIDTH);
		boolean yIsOnBound = (y >= this.getYLocation() && y <= this.getYLocation() + HEIGHT);
		
		return (xIsOnBound && yIsOnBound);
	}
	
}
