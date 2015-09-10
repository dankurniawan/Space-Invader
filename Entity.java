import javafx.scene.image.Image;


public abstract class Entity {

	private double xLocation;
	private double yLocation;
	private int width;
	private int height;
	private SpriteView sprite;
	private boolean isVisible;
	
	public Entity(int width, int height){

		this.width = width;
		this.height = height;
	}
	
	//location set up
	
	public void relocate(double x, double y){
		setXLocation(x);
		setYLocation(y);
	}
	
	public void setXLocation(double x){
		xLocation = x;
	}
	
	public void setYLocation(double y){
		yLocation = y;
	}
	
	public double getXLocation(){
		return xLocation;
	}
	
	public double getYLocation(){
		return yLocation;
	}
	
	public void translateX(double x){
		setXLocation(getXLocation() + x);
	}
	
	public void translateY(double y){
		setYLocation(getYLocation() + y);
	}
	
	//
	
	//dimension
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	//
	
	public abstract Image getSprite();
	
	//
	
	public abstract double getSpeed();
	
	//
	
	public void setVisible(boolean isVisible){
		this.isVisible = isVisible;
		sprite.setVisible(isVisible);
	}
	
	public SpriteView draw(){
		sprite = new SpriteView(this);
		isVisible = true;
		return sprite;
		
	}
	
	public void render(){
		
		if (isVisible){
			sprite.render();
		}
		
	}
	
}
