package MP2;

public class Assignment {
    private Double minimum;
    private int group;

    public Assignment(double minimum, int group) {
        this.minimum = minimum;
        this.group = group;
    }

    public double getMinimum() {
        return minimum;
    }

    public void setMinimum(double minimum) {
        this.minimum = minimum;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}