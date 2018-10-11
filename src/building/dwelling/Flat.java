package building.dwelling;

//Создаем публичный класс Flat квартиры жилого дома
//Квартира не хранит свой номер
public class Flat {
    public static final int DEF_ROOM_AMOUNT = 2;//Константа комнат
    public static final int DEF_SQUARE = 50;    //Константа площади комнат
    private int roomAmount, square;

    //Конструктор по умолчанию
    public Flat() {
        this.roomAmount = DEF_ROOM_AMOUNT;
        this.square = DEF_SQUARE;
    }

    //Конструктор, который принимает площадь квартиры
    //              и создает квартиру с двумя комнатами
    public Flat(int square) {
        this.roomAmount = DEF_ROOM_AMOUNT;
        this.square = square;
    }

    //Конструктор, который принимает площадь квартиры
    //                                  и количество комнат
    public Flat(int roomAmount, int square) {
        this.roomAmount = roomAmount;
        this.square = square;
    }

    //Метод для получения количества комнат в квартире
    public int getRoomAmount() {
        return this.roomAmount;
    }

    //Метод изменения количества комнат в квартире
    public void setRoomAmount(int roomAmount) {
        this.roomAmount = roomAmount;
    }

    //Метод для получения площади квартиры
    public int getSquare() {
        return this.square;
    }

    //Метод для изменения площади квартиры
    public void setSquare(int square) {
        this.square = square;
    }

    //Метод отображения Flat
    public String toString() {
        return ("Flat (" + this.getRoomAmount() + ", " + this.getSquare()) + ")";
    }

}


