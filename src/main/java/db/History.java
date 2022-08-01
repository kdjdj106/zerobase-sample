package db;

public class History {
    private int ID;
    private String usedDateTime;
    private double x;
    private double y;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsedDateTime() {
        return usedDateTime;
    }

    public void setUsedDateTime(String usedDateTime) {
        this.usedDateTime = usedDateTime;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
