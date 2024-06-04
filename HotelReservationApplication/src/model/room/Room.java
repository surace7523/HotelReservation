package model.room;

import model.room.enums.RoomType;

public class Room implements IRoom {
    private String roomNumber;
    private Double price;
    private RoomType roomType;
    private boolean isFree;

    public Room(String roomNumber, Double price, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
        this.isFree = price == 0.0;
    }

    public String getRoomNumber() {
        return this.roomNumber;
    }

    public Double getRoomPrice() {
        return this.price;
    }

    public RoomType getRoomType() {
        return this.roomType;
    }

    public boolean isFree() {
        return this.isFree;
    }

    public String toString() {
        String var10000 = this.roomNumber;
        return "Room{roomNumber='" + var10000 + "', price=" + String.valueOf(this.price) + ", roomType=" + String.valueOf(this.roomType) + ", isFree=" + this.isFree + "}";
    }
}
