package building.dwelling.hotel;

import building.dwelling.DwellingFloor;
import building.interfaces.Space;

public class HotelFloor extends DwellingFloor {
    public static final int DEFAULT_STAR_K = 1;
    private double koef;
    private int stars;

    public HotelFloor(int flatCount) {
        super(flatCount);
        stars = DEFAULT_STAR_K;
        koef = 0.25;
    }

    public HotelFloor(Space[] spaces) {
        super(spaces);
    }

    public int getStars() {
        return stars;
    }

    public double getKoef() {
        return koef;
    }

    public void setStars(int stars) {
        if (stars > 2) koef = DEFAULT_STAR_K * stars + 0.25;
        else koef = DEFAULT_STAR_K * stars;
        this.stars = stars;
    }

    @Override
    public Space getBestSpace() {
        Space[] spaces = getSpaces();
        Space bestArea = spaces[0];
        for (Space space : spaces) {
            if (space != null && space.getSquare() * koef > bestArea.getSquare() * koef)
                bestArea = space;
        }
        return bestArea;
    }

    @Override
    public String toString() {
        StringBuffer s1 = new StringBuffer();
        s1.append("Hotel floor (")
                .append(this.getStars())
                .append(", ")
                .append(this.getSpaceCount())
                .append(", ");
        for (int i = 0; i < this.getSpaceCount(); i++) {
            if (i < this.getSpaceCount() - 1) s1.append(this.getSpace(i).toString()).append(", ");
            else s1.append(this.getSpace(i).toString());
        }
        return s1.toString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HotelFloor that = (HotelFloor) o;

        if (this.getSpaceCount() == that.getSpaceCount() && this.getRoomCount() == that.getRoomCount() && this.getSquare() == that.getSquare()) {
            for (int i = 0; i < this.getSpaceCount(); i++) {
                if (!this.getSpace(i).equals(that.getSpace(i))) return false;
            }
        } else return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = this.getSpaceCount();
        for (int i = 0; i < this.getSpaceCount(); i++) {
            hash ^= this.getSpace(i).hashCode();
        }
        return hash;
    }

}
