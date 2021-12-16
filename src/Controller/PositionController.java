package Controller;

import java.util.List;

import Model.Position;

public class PositionController {
	public PositionController() {
		
	}
	public static List<Position> getAllPosition(){
		List<Position> list = Position.getAllPosition();
		return list;
	} 
}
