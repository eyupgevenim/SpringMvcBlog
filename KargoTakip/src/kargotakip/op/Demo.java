package kargotakip.op;

import java.util.ArrayList;

public class Demo {

	public Demo() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		ArrayList<Room> r=getRooms(4, "Erkek", true);
		
		if(!r.equals(null))
		for (Room room:r) {
			System.out.println(room.roomType+" "+room.roomGender+" "+room.roomStage);
		}
		
	}
	
	public static ArrayList<Room> getRooms(int roomType, String roomGender, boolean roomStage) {
	    ArrayList<Room> rooms = new ArrayList<Room>();
	    Room r=new Room();
	    r.roomType=4;
	    r.roomGender="Erkek";
	    r.roomStage=true;
	    rooms.add(r);
	    
	    if(!rooms.equals(null))
	    for (Room room:rooms) {
	        if (roomType == room.getRoomType() && roomGender.equals(room.getRoomGender()) && roomStage) {

	            rooms.add(room);
	        }
	    }
	    
	    return  rooms;
	}
}

class Room{
	
	public int roomType;
	public String roomGender;
	public boolean roomStage;
	
	public int getRoomType() {
		return roomType;
	}
	public void setRoomType(int roomType) {
		this.roomType = roomType;
	}
	public String getRoomGender() {
		return roomGender;
	}
	public void setRoomGender(String roomGender) {
		this.roomGender = roomGender;
	}
	public boolean isRoomStage() {
		return roomStage;
	}
	public void setRoomStage(boolean roomStage) {
		this.roomStage = roomStage;
	}
	
}
