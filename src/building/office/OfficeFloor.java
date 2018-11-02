package building.office;

import building.interfaces.Floor;
import building.interfaces.Space;
import building.exceptions.SpaceIndexOutOfBoundsException;

/**
 * Работа класса основана на односвязном циклическом списке офиса
 * с выделенной головой (head)
 * Номер офиса явно не хранится
 */
public class OfficeFloor implements Floor {

    private Node head;

    private class Node {
        private Node next;
        private Space office;
    }

    //Приватный метод получения узла по его номеру
    private Node getNode(int position) {
        Node tmpNode = head;
        for (int i = 0; i < position; i++) {
            tmpNode = tmpNode.next;
        }
        return tmpNode;
    }

    //Приватный метод добавления узла в список по номеру
    private void addNode(int position, Node node) {
        Node tmpNode = head;
        for (int i = 0; i < position; i++) {
            tmpNode = tmpNode.next;
        }
        node.next = tmpNode.next;
        tmpNode.next = node;
    }

    //Приватный метод удаления узла из списка по его номеру
    private void deleteNode(int position) {
        Node tmpNode = head;
        for (int i = 0; i < position; i++) {
            tmpNode = tmpNode.next;
        }
        tmpNode.next = tmpNode.next.next;
    }

    //Конструктор, который принмает количество офисов на этаже
    public OfficeFloor(int officeCount) {
        head = new Node();
        head.office = new Office();
        Node tmpNode = head;
        for (int i = 1; i < officeCount; i++) {
            tmpNode.next = new Node();
            tmpNode.next.office = new Office();
            tmpNode = tmpNode.next;
        }
        tmpNode.next = this.head;
    }

    //Конструктор, который принимает массив офисов этажа
    public OfficeFloor(Space... offices) {
        head = new Node();
        head.office = new Office();
        Node tmpNode = head;
        for (Space office : offices) {
            tmpNode.next = new Node();
            tmpNode.next.office = office;
            tmpNode = tmpNode.next;
        }
        tmpNode.next = this.head;
    }

    //Метод поулчения количество офисов на этаже
    public int getSpaceCount() {
        int officeCount = 1;
        Node tmpNode = head;
        while (tmpNode.next != head) {
            officeCount++;
            tmpNode = tmpNode.next;
        }
        return officeCount;
    }

    //Метод получения общей площади помещений этажа
    public int getSquare() {
        Node tmpNode = head;
        int totalSquare = tmpNode.office.getSquare();
        while (tmpNode.next != head) {
            tmpNode = tmpNode.next;
            totalSquare += tmpNode.office.getSquare();
        }
        return totalSquare;
    }

    //Метод получения общего количества комнат этажа
    public int getRoomCount() {
        Node tmpNode = head;
        int roomCount = tmpNode.office.getRoomAmount();
        while (tmpNode.next != head) {
            roomCount += tmpNode.office.getRoomAmount();
            tmpNode = tmpNode.next;
        }
        return roomCount;
    }

    //Метод получения массива офисов этажа
    public Space[] getSpaces() {
        Space[] offices = new Space[getSpaceCount()];
        offices[0] = head.office;
        Node tmpNode = head;
        int i = 1;
        while (tmpNode.next != head) {
            tmpNode = tmpNode.next;
            offices[i] = tmpNode.office;
            i++;
        }
        return offices;
    }

    //Метод получения офиса по его номеру на этаже
    public Space getSpace(int position) throws SpaceIndexOutOfBoundsException {
        if (position < 0 || position > getSpaceCount())
            throw new SpaceIndexOutOfBoundsException("Invalid office number");
        else
            return getNode(position).office;
    }

    //Метод изменения офиса по его номеру на этаже и ссылке на обновленный офис
    public void setSpace(int position, Space office) throws SpaceIndexOutOfBoundsException {
        if (position < 0 || position > getSpaceCount())
            throw new SpaceIndexOutOfBoundsException("Invalid office number");
        else
            getNode(position).office = office;
    }

    //Метод добавления новго офиса на этаже по будущему номеру офиса
    public void addSpace(int position, Space newOffice) throws SpaceIndexOutOfBoundsException {
        if (position < 0 || position > getSpaceCount())
            throw new SpaceIndexOutOfBoundsException("Invalid office number");
        else {
            Node node = new Node();
            node.office = newOffice;
            addNode(position, node);
        }
    }

    //Метод удаления офиса по его номеру на этаже
    public void delSpace(int position) throws SpaceIndexOutOfBoundsException {
        if (position < 0 || position > getSpaceCount())
            throw new SpaceIndexOutOfBoundsException("Invalid office number");
        else
            deleteNode(position);
    }

    //Метод получения самого большого по площади офиса этажа
    public Space getBestSpace() {
        Node tmpNode = this.head;
        Space bestOffice = this.head.office;
        while (tmpNode.next != this.head) {
            tmpNode = tmpNode.next;
            if (bestOffice.getSquare() < tmpNode.office.getSquare())
                bestOffice = tmpNode.office;
        }
        return bestOffice;
    }

    //Метод отображения
    public String toString() {
        StringBuilder s1 = new StringBuilder();
        s1.append("Office floor (").append(this.getSpaceCount()).append(", ");
        for (int i = 0; i < this.getSpaceCount(); i++) {
            if (i < this.getSpaceCount() - 1) s1.append(this.getSpace(i).toString()).append(", ");
            else s1.append(this.getSpace(i).toString());
        }
        return s1.toString() + ")";
    }

}