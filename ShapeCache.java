package vector;

import java.util.Hashtable;

public class ShapeCache {
	public static Hashtable<String, Shape> shapeMap  = new Hashtable<String, Shape>();

	   public static Shape getShape(String shapeId) {
	      Shape cachedShape = shapeMap.get(shapeId);
	      return (Shape) cachedShape.clone();
	   }

}
