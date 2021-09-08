package data;

/**
 * @author Karina Vladykina
 * 2-dimensional coordinates
 */
public class Coordinates {
    private final Integer x; //Значение поля должно быть больше -390, Поле не может быть null
    private final Float y; //Поле не может быть null

    public Coordinates(Integer x, Float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return X value
     */
    public Integer getX() {
        return x;
    }

    /**
     * @return Y value
     */
    public Float getY() {
        return y;
    }

    @Override
    public String toString() {
        return "\n X: " + x +
                "\n Y: " + y;
    }
}
