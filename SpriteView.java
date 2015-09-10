import javafx.scene.image.ImageView;


public class SpriteView extends ImageView {

	private Entity entity;
	
	public SpriteView(Entity entity){
		super(entity.getSprite());
		this.setFitWidth(entity.getWidth());
		this.setFitHeight(entity.getHeight());	
		this.entity = entity;
		render();		
		
	}
	
	public void render(){
		this.setLayoutX(entity.getXLocation());
		this.setLayoutY(entity.getYLocation());
	}
	
}
