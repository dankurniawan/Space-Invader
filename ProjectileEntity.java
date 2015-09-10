import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;


public class ProjectileEntity extends Entity{

	private static final int WIDTH = 2;
	private static final int HEIGHT = 10;
  	
	private static final double SPEED = 3;
 	
	private static boolean reload;
	private static int reloadTime = 200;
    	
	public ProjectileEntity(double x, double y) {
		super(WIDTH, HEIGHT);
		this.setXLocation(x);
		this.setYLocation(y);
	}

	@Override
	public Image getSprite() {
		return Sprite.PROJECTILE.getImage();
	}
	
	@Override
	public double getSpeed() {
		return SPEED;
	}
	
	public static void shoot(ShipEntity ship) {
		
		if (!(reload)){
			
			ProjectileEntity projectile = ship.shoot();
			Timeline projectileTimeline = initProjectileTimeline(projectile);
			GameLayout.addProjectile(projectile);
			projectileTimeline.play();	
			
			reload();
			
		}
			
	}

	private static void reload() {
		
		reload = true;
		
		Timeline projectileReload = new Timeline(new KeyFrame(Duration.millis(reloadTime), e -> reload = false));
		projectileReload.play();
		
	}

	private static Timeline initProjectileTimeline(ProjectileEntity projectile) {
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000 / GameFactory.FRAME_RATE_PER_SECOND), e -> renderProjectile(projectile)));	
		timeline.setCycleCount(getMaxCycle(projectile));
		
		return timeline;
	}

	private static int getMaxCycle(ProjectileEntity projectile) {
		
		double yPosition = projectile.getYLocation();		
		return (int) (yPosition / projectile.getSpeed());
	}

	private static void renderProjectile(ProjectileEntity projectile) {
		
		if (!(GameFactory.isCollide(projectile))){
			
			projectile.translateY(- projectile.getSpeed());
			projectile.render();
		
		} else {
			
			projectile.setYLocation(0);
			projectile.setVisible(false);
			
		}
			
		
	}
	
}
