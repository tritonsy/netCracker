package building.office;

import building.exceptions.FloorIndexOutOfBoundsException;
import building.exceptions.SpaceIndexOutOfBoundsException;
import building.interfaces.Building;
import building.interfaces.Floor;
import building.interfaces.Space;

/**
 * Работа класса основана на двусвязном циклическмом списке этажей
 * с выделенной головой
 * Номер офиса явно не хранится
 */

public class OfficeBuilding implements Building {
    private Node head;

    private class Node {
        private Node next;
        private Node prev;
        private Floor officeFloor;
    }
    //Приватный метод получения узла по его номеру
    private Node getNode(int position) {
        Node tmpNode = head;
        for (int i = 0; i < position; i++) {
            tmpNode = tmpNode.next;
        }
        return tmpNode;
    }

    //Приватный метод добавелиня узла в список по номеру
    private void addNode(int position, Node node) {
        Node tmpNode = head;
        for (int i = 0; i < position; i++) {
            tmpNode = tmpNode.next;
        }
        node.next = tmpNode.next;
        node.prev = tmpNode;
        tmpNode.next.prev = node;
        tmpNode.next = node;
    }
    //Приватный метод удаления узла из списка по его номеру
    private void deleteNode(int position) {
        Node tmpNode = head;
        for (int i = 0; i < position; i++) {
            tmpNode = tmpNode.next;
        }
        tmpNode.next.next.prev = tmpNode;
        tmpNode.next = tmpNode.next.next;
    }

    //Конструктор, который принимает количество этажей и массив
    //количества офисов по этажам
    public OfficeBuilding(int floorAmount, int[] officeAmount) {
        this.head = new Node();
        this.head.officeFloor = new OfficeFloor(officeAmount[0]);
        Node bufNode = this.head;
        for (int i = 1; i < floorAmount; i++) {
            bufNode.next = new Node();
            bufNode.next.prev = bufNode;
            bufNode = bufNode.next;
            bufNode.officeFloor = new OfficeFloor(officeAmount[i]);
        }
        bufNode.next = this.head;
        this.head.prev = bufNode;
    }

    //Конструктор, который принимает массив этажей офисного здания
    public OfficeBuilding(Floor... newOfficeFloor) {
        this.head = new Node();
        this.head.officeFloor = newOfficeFloor[0];
        Node bufNode = this.head;
        for (int i = 1; i < newOfficeFloor.length; i++) {
            bufNode.next = new Node();
            bufNode.next.prev = bufNode;
            bufNode = bufNode.next;
            bufNode.officeFloor = newOfficeFloor[i];
        }
        bufNode.next = this.head;
        this.head.prev = bufNode;
    }

    //Метод получения общего количества этажей здания
    public int getFloorCount() {
        Node bufNode = this.head;
        int sum = 1;
        while (bufNode.next != this.head) {
            bufNode = bufNode.next;
            sum++;
        }
        return sum;
    }

    //Метод получения общего количество офисов здания
    public int getSpaceCount() {
        Node bufNode = this.head;
        int sum = bufNode.officeFloor.getSpaceCount();
        while (bufNode.next != this.head) {
            bufNode = bufNode.next;
            sum += bufNode.officeFloor.getSpaceCount();
        }
        return sum;
    }

    //Метод получения общей площади помещений здания
    public int getSpace() {
        Node bufNode = this.head;
        int sum = bufNode.officeFloor.getSquare();
        while (bufNode.next != this.head) {
            bufNode = bufNode.next;
            sum += bufNode.officeFloor.getSquare();
        }
        return sum;
    }

    //Метод получения общего количества комнат здания
    public int getRoomCount() {
        Node bufNode = this.head;
        int sum = bufNode.officeFloor.getRoomCount();
        while (bufNode.next != this.head) {
            bufNode = bufNode.next;
            sum += bufNode.officeFloor.getRoomCount();
        }
        return sum;
    }

    //Метод получения массива этажей офисного здания
    public Floor[] getFloors() {
        Floor[] gettableFloor = new Floor[getSpaceCount()];
        Node bufNode = this.head;
        gettableFloor[0] = bufNode.officeFloor;
        int i = 1;
        while (bufNode.next != this.head) {
            bufNode = bufNode.next;
            gettableFloor[i] = bufNode.officeFloor;
            i++;
        }
        return gettableFloor;
    }

    //Метод получения объекта этажа по его номеру в здании
    public Floor getFloor(int floorNumber) throws FloorIndexOutOfBoundsException {
        if (floorNumber > getFloorCount() || floorNumber < 0) throw new FloorIndexOutOfBoundsException("invalid floor");
        else {
            Node bufNode = this.head;
            for (int i = 0; i < floorNumber; i++) {
                bufNode = bufNode.next;
            }
            return bufNode.officeFloor;
        }
    }

    //Метод изменения этажа по его номеру в здании в ссылке
    //на объект офиса
    public void setFloor(int floorNumber, Floor officeFloor) throws FloorIndexOutOfBoundsException {
        if (floorNumber > getFloorCount() || floorNumber < 1) throw new FloorIndexOutOfBoundsException("invalid floor");
        else getNode(floorNumber).officeFloor = officeFloor;
    }

    //Метод получения объекта офиса по его номеру в офисном здании
    public Space getSpace(int officeNumber) throws SpaceIndexOutOfBoundsException {
        if (officeNumber < 0 || officeNumber > getSpaceCount())
            throw new SpaceIndexOutOfBoundsException("Invalid office number");
        else {
            Node bufNode = this.head;
            while (officeNumber >= bufNode.officeFloor.getSpaceCount()) {
                officeNumber -= bufNode.officeFloor.getSpaceCount();
                bufNode = bufNode.next;
            }
            return bufNode.officeFloor.getSpace(officeNumber);
        }
    }

    //Метод изменения объекта офиса в здании по номеру офиса в здании
    //и ссылке на обект офиса
    public void setSpace(int officeNumber, Space newOffice) throws SpaceIndexOutOfBoundsException {
        if (officeNumber < 0 || officeNumber > getSpaceCount())
            throw new SpaceIndexOutOfBoundsException("Invalid office number");
        else {
            Node bufNode = this.head;
            while (officeNumber >= bufNode.officeFloor.getSpaceCount()) {
                officeNumber -= bufNode.officeFloor.getSpaceCount();
                bufNode = bufNode.next;
            }
            bufNode.officeFloor.setSpace(officeNumber, newOffice);
        }
    }

    //Метод добавления офиса в здание по номеру офиса в здании
    //и ссылке на объект офиса
    public void addSpace(int officeNumber, Space newOffice) throws SpaceIndexOutOfBoundsException {
        if (officeNumber < 0 || officeNumber > getSpaceCount())
            throw new SpaceIndexOutOfBoundsException("Invalid office number");
        {
            Node bufNode = this.head;
            while (officeNumber >= bufNode.officeFloor.getSpaceCount()) {
                officeNumber -= bufNode.officeFloor.getSpaceCount();
                bufNode = bufNode.next;
            }
            bufNode.officeFloor.addSpace(officeNumber, newOffice);
        }
    }

    //Метод удаления офиса по его номеру в здании
    public void delSpace(int officeNumber) throws SpaceIndexOutOfBoundsException {
        if (officeNumber < 0 || officeNumber > getSpaceCount())
            throw new SpaceIndexOutOfBoundsException("Invalid office number");
        {
            Node bufNode = this.head;
            while (officeNumber >= bufNode.officeFloor.getSpaceCount()) {
                officeNumber -= bufNode.officeFloor.getSpaceCount();
                bufNode = bufNode.next;
            }
            bufNode.officeFloor.delSpace(officeNumber);
        }
    }

    //Метод получения самого большого по площади офиса здания
    public Space getBestSpace() {
        Node bufNode = this.head;
        Space bestSpaceOffice = this.head.officeFloor.getBestSpace();
        while (bufNode.next != this.head) {
            bufNode = bufNode.next;
            if (bestSpaceOffice.getSquare() < bufNode.officeFloor.getBestSpace().getSquare()) {
                bestSpaceOffice = bufNode.officeFloor.getBestSpace();
            }
        }
        return bestSpaceOffice;
    }

    //Метод получения отсортированного по убыванию площадей массива офисов
    public Space[] getSortedSpaces() {
        Space[] sortedArray = new Space[getSpaceCount()];
        int k = 0;
        Node bufNode = this.head;
        for (int i = 0; i < getFloorCount(); i++) {
            for (int j = 0; j < bufNode.officeFloor.getSpaceCount(); j++) {
                sortedArray[k] = bufNode.officeFloor.getSpace(j);
                k++;
            }
            bufNode = bufNode.next;
        }
        quickSort(sortedArray);
        return sortedArray;
    }

    //Реализация сортировки
    private static void quickSort(Space[] array) {
        int startIndex = 0;
        int endIndex = array.length - 1;
        doSort(startIndex, endIndex, array);
    }

    private static void doSort(int start, int end, Space[] array) {
        if (start >= end)
            return;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (array[i].getSquare() <= array[cur].getSquare())) {
                i++;
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
                else if (j == cur)
                    cur = i;
            }
        }
        doSort(start, cur, array);
        doSort(cur + 1, end, array);
    }

    public String toString() {
        StringBuilder s1 = new StringBuilder();
        s1.append("Office building(").append(this.getFloorCount()).append(", ");
        for (int i = 0; i < this.getFloorCount(); i++) {
            if (i < this.getFloorCount() - 1)
                s1.append(this.getFloor(i).toString()).append(", ");
            else s1.append(this.getFloor(i).toString());
        }
        return s1.toString();
    }
}