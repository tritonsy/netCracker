package building.dwelling;

import building.interfaces.Floor;
import building.interfaces.Space;

import java.io.Serializable;

//Создаем публичный класс DwellingFloor этажа жилого здания
//                              основанного на массиве квартир
public class DwellingFloor implements Floor, Serializable {
    private Space arrayOfFlats[];

    //Конструктор, который принимает количество квартир
    //Нумерация начинается с нуля
    public DwellingFloor(int amountOfFlats) {
        this.arrayOfFlats = new Space[amountOfFlats];
        for (int i = 0; i < amountOfFlats; i++) {
            this.arrayOfFlats[i] = new Flat();
        }
    }

    //Конструктор, который принимает массив квартир этажа
    public DwellingFloor(Space... flatArray) {
        this.arrayOfFlats = new Flat[flatArray.length];
        for (int i = 0; i < flatArray.length; i++) {
            this.arrayOfFlats[i] = new Flat(flatArray[i].getRoomAmount(), flatArray[i].getSquare());
        }
    }

    //Метод получения количества квартир на этаже
    public int getSpaceCount() {
        return this.arrayOfFlats.length;
    }

    //Метод получения общей площади квартир этажа
    public int getSquare() {
        int summ = 0;
        for (int i = 0; i < this.arrayOfFlats.length; i++) {
            summ += this.arrayOfFlats[i].getSquare();
        }
        return summ;
    }

    //Метод получения общего количества комнат этажа
    public int getRoomCount() {
        int summ = 0;
        for (int i = 0; i < this.arrayOfFlats.length; i++) {
            summ += this.arrayOfFlats[i].getRoomAmount();
        }
        return summ;
    }

    //Метод получения массива квартир этажа
    public Space[] getSpaces() {
        return this.arrayOfFlats;
    }

    //Метод получения объекта квартиры по ее номеру на этаже
    public Space getSpace(int flatNumber) {
        return this.arrayOfFlats[flatNumber];
    }

    //Метод изменения квартиры по ее номеру на этаже
    //                         и ссылке на новую квартиру
    public void setSpace(int flatNumber, Space newFlat) {
        this.arrayOfFlats[flatNumber] = newFlat;
    }

    //Метод добавления новой квартиры на этаже по будущему номеру квартиры
    public void addSpace(int prefFlatNumber, Space newFlat) {
        Space[] tmp = new Space[this.arrayOfFlats.length + 1];
        for (int i = 0; i < prefFlatNumber; i++) {
            tmp[i] = this.arrayOfFlats[i];
        }
        tmp[prefFlatNumber] = newFlat;
        for (int i = prefFlatNumber + 1; i < tmp.length; i++) {
            tmp[i] = this.arrayOfFlats[i - 1];
        }
        this.arrayOfFlats = tmp;
    }

    //Метод удаления квартиры по ее номеру на этаже
    public void delSpace(int flatNumber) {
        for (int i = flatNumber; i < this.arrayOfFlats.length - 1; i++) {
            this.arrayOfFlats[i] = this.arrayOfFlats[i + 1];
        }
        Space[] tmp = new Space[this.arrayOfFlats.length - 1];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = this.arrayOfFlats[i];
        }
        this.arrayOfFlats = tmp;
    }

    //Метод получения самой большой по площади квартиры
    public Space getBestSpace() {
        Space bestSpace = new Flat(0, 0);
        for (int i = 0; i < this.arrayOfFlats.length; i++) {
            if (this.arrayOfFlats[i].getSquare() > bestSpace.getSquare())
                bestSpace = this.arrayOfFlats[i];
        }
        return bestSpace;
    }

    //Метод для отображения Dwelling Floor
    public String toString() {
        StringBuilder s1 = new StringBuilder();
        s1.append("Dwelling floor (").append(this.getSpaceCount()).append(", ");
        for (int i = 0; i < this.getSpaceCount(); i++) {
            if (i < this.getSpaceCount() - 1) s1.append(this.getSpace(i).toString()).append(", ");
            else s1.append(this.getSpace(i).toString());
        }
        return s1.toString() + ")";
    }

}
