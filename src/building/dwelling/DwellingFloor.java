package building.dwelling;

import building.interfaces.Floor;
import building.interfaces.Space;

import java.io.Serializable;
import java.util.Iterator;

//Создаем публичный класс DwellingFloor этажа жилого здания
//                              основанного на массиве квартир
public class DwellingFloor implements Floor, Serializable, Cloneable {
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

    /**
     * Добавьте в классы этажей реализации методов boolean equals(Object object). Метод должен возвращать true
     * только в том случае, если объект, на который передана ссылка, является этажом соответствующего типа,
     * количество помещений совпадает и сами помещения эквивалентны помещениям текущего объекта.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DwellingFloor that = (DwellingFloor) o;

        if (this.getSpaceCount() == that.getSpaceCount() && this.getRoomCount() == that.getRoomCount() && this.getSquare() == that.getSquare()) {
            for (int i = 0; i < this.getSpaceCount(); i++) {
                if (!this.getSpace(i).equals(that.getSpace(i))) return false;
            }
        } else return false;
        return true;
    }

    /**
     * Добавьте в классы этажей реализации методов int hashCode(). Значение хеш-функции этажа можно вычислить
     * как значение побитового исключающего ИЛИ количества помещений на этаже и значений хеш-функций помещений этажа.
     */
    @Override
    public int hashCode() {
        int hash = this.getSpaceCount();
        for (int i = 0; i < this.getSpaceCount(); i++) {
            hash ^= this.getSpace(i).hashCode();
        }
        return hash;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Floor clone = new DwellingFloor(this.getSpaces().length);
        for (int i = 0; i < this.getSpaceCount(); i++) {
            clone.setSpace(i, (Space) this.arrayOfFlats[i].clone());
        }
        return clone;
    }

    @Override
    public int compareTo(Floor o) {
        return Integer.compare(getSpaceCount(), o.getSpaceCount());
    }

    @Override
    public Iterator<Space> iterator() {
        return new Iterator<Space>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < DwellingFloor.this.getSpaceCount() - 1;
            }

            @Override
            public Space next() {
                return DwellingFloor.this.getSpace(index);
            }
        };
    }

}
