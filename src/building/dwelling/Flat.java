package building.dwelling;

import building.interfaces.Space;

import java.io.Serializable;

//Создаем публичный класс Flat квартиры жилого дома
//Квартира не хранит свой номер
public class Flat implements Space, Serializable, Cloneable {
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

    /**
     * Добавьте в классы помещений реализации методов boolean equals(Object object). Метод должен возвращать true
     * только в том случае, если объект, на который передана ссылка, является помещением соответствующего типа и
     * все соответствующие параметры помещений равны.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flat flat = (Flat) o;

        if (getRoomAmount() != flat.getRoomAmount()) return false;
        return getSquare() == flat.getSquare();
    }

    /**
     * Добавьте в классы помещений реализации методов int hashCode(). Значение хеш-функции помещения можно вычислить
     * как значение последовательного побитового исключающего ИЛИ битового представления количества комнат
     * помещения, и, например, первых 4 байтов и вторых 4-х байтов (для типа double) битовых представлений
     * площадей помещений этажа (следует воспользоваться вспомогательными методами классов-оберток).
     */
    @Override
    public int hashCode() {
        int result = getRoomAmount();
        result ^= 31 * result + getSquare();
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Space clone = (Space) super.clone();
        clone.setRoomAmount(this.roomAmount);
        clone.setSquare(this.square);
        return clone;
    }

    @Override
    public int compareTo(Space o) {
        return Integer.compare(square, o.getSquare());
    }
}


