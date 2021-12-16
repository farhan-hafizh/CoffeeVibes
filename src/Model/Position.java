package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.Connect;

public class Position {
	private int positionId;
	private String name;
	
	private Position(int positionId, String name) {
		this.positionId = positionId;
		this.name = name;
	}
	
	public int getPositionId() {
		return positionId;
	}
	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//set position
	private static Position setPosition(ResultSet rs) {
		String name;
		int posId;
		Position pos=null;
		try {
			name=rs.getString("name");
			posId=rs.getInt("positionID");
			pos= new Position(posId, name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pos;
	}
//	get pos
	public static List<Position> getAllPosition(){
		String query="SELECT * FROM positions";
		ResultSet rs = Connect.getConnection().execute(query);
		Position pos;
		List<Position> list = new ArrayList<Position>();
		try {
			while(rs.next()) {
				pos = setPosition(rs);
				list.add(pos);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static Position getPosition(int positionId) {
		String query = String.format("SELECT * FROM positions pos WHERE pos.positionID= %d", positionId);
		Position pos=null;
		
		ResultSet rs = Connect.getConnection().execute(query);
		try {
			while(rs.next()) {
				pos = setPosition(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pos;
	}
	
}
