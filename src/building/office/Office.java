package building.office;

import building.exceptions.InvalidRoomsCountException;
import building.exceptions.InvalidSpaceAreaException;
import building.interfaces.Space;

public class Office implements Space {
    private static final int DEFAULT_ROOM_AMOUNT = 1;
    private static final int DEFAULT_SQUARE = 250;
    private int square;
    private int roomCount;

    //Конструктор по умолчанию: создает офис из 1 комнаты площадью 250 кв.м.
    public Office() {
        this.roomCount = DEFAULT_ROOM_AMOUNT;
        this.square = DEFAULT_SQUARE;
    }

    //Конструктор, который приниммает площадь офиса
    public Office(int square) {
        this.roomCount = DEFAULT_ROOM_AMOUNT;
        this.square = square;
    }

    //Конструктор, который принимает площадь и количество комнат
    public Office(int square, int roomCount) {
        this.roomCount = square;
        this.square = roomCount;
    }

    //Метод получения количества комнат в офисе
    public int getRoomAmount() throws InvalidRoomsCountException {
        return this.roomCount;
    }

    //Метод изменения количества комнат в офисе
    public void setRoomAmount(int roomCount) {
        if (roomCount > 0) this.roomCount = roomCount;
        else throw new InvalidRoomsCountException("roomCount < 1");
    }

    //Метод получения площади офиса
    public int getSquare() {
        return this.square;
    }

    //Метод изменения площади офиса
    public void setSquare(int square) throws InvalidSpaceAreaException {
        if (square > 0) this.square = square;
        else throw new InvalidSpaceAreaException("square is <1");
    }

    //Метод отображения
    public String toString() {
        return ("Office (" + this.getRoomAmount() + ", " + this.getSquare()) + ")";
    }

}