package building.dwelling;

import building.interfaces.Building;
import building.interfaces.Floor;
import building.interfaces.Space;

public class Dwelling implements Building {
    private Floor floorArray[];

    //Конструктор, который принимает количество этажей и массив количества квартир по этажам
    public Dwelling(int floorAmount, int... arrayFlatAmount) {
        this.floorArray = new Floor[floorAmount];
        for (int i = 0; i < floorAmount; i++) {
            floorArray[i] = new DwellingFloor(arrayFlatAmount[i]);
        }
    }

    //Конструктор, принимающий массив этажей дома
    public Dwelling(Floor... arrayOfFloors) {
        this.floorArray = new Floor[arrayOfFloors.length];
        for (int i = 0; i < arrayOfFloors.length; i++) {
            this.floorArray[i] = new DwellingFloor(arrayOfFloors[i].getSpaces());
        }
    }

    //Метод получения общего количества этажей
    public int getFloorCount() {
        return this.floorArray.length;
    }

    //Метод получения общего количества квартир в доме
    public int getSpaceCount() {
        int summ = 0;
        for (int i = 0; i < this.floorArray.length; i++) {
            summ += this.floorArray[i].getSpaceCount();
        }
        return summ;
    }

    //Метод получения общей площади квартир дома
    public int getSpace() {
        int summ = 0;
        for (int i = 0; i < this.floorArray.length; i++) {
            summ += this.floorArray[i].getSquare();
        }
        return summ;
    }

    //Метод получения общего количества комнат в доме
    public int getRoomCount() {
        int summ = 0;
        for (int i = 0; i < this.floorArray.length; i++) {
            summ += this.floorArray[i].getRoomCount();
        }
        return summ;
    }

    //Метод получения массива этажа дома
    public Floor[] getFloors() {
        return this.floorArray;
    }

    //Метод получения объекта этажа по его номеру в доме
    public Floor getFloor(int floorNumber) {
        return this.floorArray[floorNumber];
    }

    //Метод изменения этажа по его номеру в доме и ссылке на обновленный этаж
    public void setFloor(int floorNumber, Floor theFloor) {
        this.floorArray[floorNumber] = theFloor;
    }

    //Метод получения объекта квартиры по ее номеру в доме
    public Space getSpace(int flatNumber) {
        int i = 0;
        while ((i > this.floorArray.length) || (flatNumber - this.floorArray[i].getSpaceCount() > 0)) {
            flatNumber -= this.floorArray[i].getSpaceCount();
            i++;
        }
        return this.floorArray[i].getSpaces()[flatNumber];
    }

    //Создайте метод изменения объекта квартиры по ее номеру в доме и ссылке на объект квартиры
    public void setSpace(int flatNumber, Space newFlat) {
        int i = 0;
        while ((i < this.floorArray.length) && (flatNumber - this.floorArray[i].getSpaceCount() > 0)) {
            flatNumber -= this.floorArray[i].getSpaceCount();
            i++;
        }
        this.floorArray[i].getSpaces()[flatNumber] = newFlat;
    }

    //Метод добавления квартиры в дом по будущему номеру квартиры в доме и ссылке на объект квартиры
    public void addSpace(int flatNumber, Space newFlat) {
        int i = 0;
        while ((i > this.floorArray.length) || (flatNumber - this.floorArray[i].getSpaceCount() > 0)) {
            flatNumber -= this.floorArray[i].getSpaceCount();
            i++;
        }
        this.floorArray[i].addSpace(flatNumber, newFlat);
    }

    //Метод удаления квартиры по ее номеру в доме
    public void delSpace(int flatNumber) {
        int i = 0;
        while ((i > this.floorArray.length) || (flatNumber - this.floorArray[i].getSpaceCount() > 0)) {
            flatNumber -= this.floorArray[i].getSpaceCount();
            i++;
        }
        this.floorArray[i].delSpace(flatNumber);
    }

    //Метод получения самой большой по площади квартиры в доме
    public Space getBestSpace() {
        Space bestFlat = this.floorArray[0].getBestSpace();
        for (int i = 1; i < this.floorArray.length; i++) {
            if (bestFlat.getSquare() < this.floorArray[i].getBestSpace().getSquare()) {
                bestFlat = floorArray[i].getBestSpace();
            }
        }
        return bestFlat;
    }

    //Метод получения отсортированного по убыванию площадей массива квартир
    public Space[] getSortedSpaces() {
        Space[] sortedSquareArray = new Space[getSpaceCount()];
        int k = 0;
        for (int i = 0; i < this.floorArray.length; i++) {
            for (int j = 0; j < this.floorArray[i].getSpaceCount(); j++) {
                sortedSquareArray[k] = this.floorArray[i].getSpaces()[j];
                k++;
            }
        }
        quickSort(sortedSquareArray);
        return sortedSquareArray;
    }

    //Метод отображения дома
    public String toString() {
        StringBuilder s1 = new StringBuilder();
        s1.append("Dwelling(").append(this.getFloorCount()).append(", " + '\n');
        for (int i = 0; i < this.getFloorCount(); i++) {
            if (i < this.getFloorCount() - 1)
                s1.append(this.getFloor(i).toString()).append("\n");
            else s1.append(this.getFloor(i).toString());
        }
        return s1.toString();
    }

    private static void quickSort(Space[] array) {
        int startIndex = 0;
        int endIndex = array.length - 1;
        doSort(startIndex, endIndex, array);
    }

    //Реадизация быстрой сортировки
    private static void doSort(int start, int end, Space[] array) {
        if (start >= end)
            return;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            if (i < cur && (array[i].getSquare() <= array[cur].getSquare())) {
                do {
                    i++;
                } while (i < cur && (array[i].getSquare() <= array[cur].getSquare()));
            }
            while (j > cur && (array[cur].getSquare() <= array[j].getSquare())) {
                j--;
            }
            if (i < j) {
                Space temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                if (i == cur)
                    cur = j;
                else if (j == cur) {
                    cur = i;
                }
            }
        }
        doSort(start, cur, array);
        doSort(cur + 1, end, array);
    }
}