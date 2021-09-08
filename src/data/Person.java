package data;

/**
 * @author Karina Vladykina
 * Person with basic parameters
 */
public class Person {
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final Integer weight; //Поле может быть null, Значение поля должно быть больше 0
    private final String passportID; //Длина строки должна быть не меньше 6, Строка не может быть пустой, Поле не может быть null
    private final Country nationality; //Поле может быть null

    public Person(String name, Integer weight, String passportID, Country nationality) {
        this.name = name;
        this.weight = weight;
        this.passportID = passportID;
        this.nationality = nationality;
    }

    /**
     * @return Person weight
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * @return Person name
     */
    public String getName() {
        return name;
    }

    /**
     * @return Person passport ID
     */
    public String getPassportID() {
        return passportID;
    }

    /**
     * @return Person nationality
     */
    public Country getNationality() {
        return nationality;
    }

    @Override
    public String toString() {
        return "\n Имя: " + name +
                "\n Вес: " + weight +
                "\n ID паспорта: " + passportID +
                "\n Национальность: " + nationality;
    }
}
