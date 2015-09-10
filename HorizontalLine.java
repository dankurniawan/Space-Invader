import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;


public class HorizontalLine extends Rectangle{

	public HorizontalLine(double yLocation, double length, Color color) {
		super(0, yLocation, length, 2);
		this.setFill(color);
		
	}

}
