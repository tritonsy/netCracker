package building.dwelling.hotel;

import building.dwelling.Dwelling;
import building.interfaces.Floor;
import building.interfaces.Space;

public class Hotel extends Dwelling {

    public Hotel(Floor[] floors) {
        super(floors);
        for (int i = 0; i < floors.length; i++) {
            this.setFloor(i, new HotelFloor(floors[i].getSpaces()));
        }
    }

    public Hotel(int floorCount, int[] flatOnFloor) {
        super(floorCount, flatOnFloor);
        for (int i = 0; i < floorCount; i++) {
            this.setFloor(i, new HotelFloor(flatOnFloor[i]));
        }
    }

    public int getStars() {
        int stars = HotelFloor.DEFAULT_STAR_K;
        for (Floor floor : getFloors()) {
            if (floor instanceof HotelFloor && ((HotelFloor) floor).getStars() > stars) {
                stars = ((HotelFloor) floor).getStars();
            }
        }
        return stars;
    }

    @Override
    public Space getBestSpace() {
        double bestSquare = 0;
        Floor[] floors = getFloors();
        if (floors.length > 0) {
            Space bestSpace = floors[0].getBestSpace();
            for (Floor floor : floors) {
                if (floor != null && floor instanceof HotelFloor &&
                        ((HotelFloor) floor).getBestSpace().getSquare() * ((HotelFloor) floor).getKoef() > bestSquare) {
                    bestSquare = ((HotelFloor) floor).getBestSpace().getSquare() * ((HotelFloor) floor).getKoef();
                    bestSpace = ((HotelFloor) floor).getBestSpace();
                }
            }
            return bestSpace;
        } else return null;
    }

    @Override
    public String toString() {
        StringBuffer s1 = new StringBuffer();
        s1.append("Hotel(")
                .append(this.getStars())
                .append(" ,")
                .append(this.getFloorCount())
                .append(", ");
        for (int i = 0; i < this.getFloorCount(); i++) {
            if (i < this.getFloorCount() - 1)
                s1.append(this.getFloor(i).toString()).append(",    ");
            else s1.append(this.getFloor(i).toString());
        }
        return s1.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel that = (Hotel) o;

        if (this.getFloorCount() == that.getFloorCount() && this.getSpaceCount() == that.getSpaceCount() && this.getRoomCount() == that.getRoomCount() && this.getSpace() == that.getSpace()) {
            for (int i = 0; i < this.getFloorCount(); i++) {
                if (!this.getFloor(i).equals(that.getFloor(i))) return false;
            }
        } else return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = this.getFloorCount();
        for (int i = 0; i < this.getFloorCount(); i++) {
            hash ^= this.getFloor(i).hashCode();
        }
        return hash;
    }


}
