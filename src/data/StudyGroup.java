package data;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author Karina Vladykina
 * Study group which is stored in set
 */
public class StudyGroup implements Comparable<StudyGroup> {
    private final Long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final Coordinates coordinates; //Поле не может быть null
    private final java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private final Long studentsCount; //Значение поля должно быть больше 0, Поле не может быть null
    private final Long shouldBeExpelled; //Значение поля должно быть больше 0, Поле не может быть null
    private final Integer averageMark; //Значение поля должно быть больше 0, Поле не может быть null
    private final FormOfEducation formOfEducation; //Поле не может быть null
    private final Person groupAdmin; //Поле может быть null

    /**
     * Another constructor for reading from file
     *
     * @param list from parsed CSV file
     */
    public StudyGroup(List<String> list) {
        this.id = Long.parseLong(list.get(0));
        this.name = list.get(1);
        this.coordinates = new Coordinates(Integer.parseInt(list.get(2)), Float.parseFloat(list.get(3)));
        this.creationDate = ZonedDateTime.parse(
                list.get(4)
        );
        this.studentsCount = Long.parseLong(list.get(5));
        this.shouldBeExpelled = Long.parseLong(list.get(6));
        this.averageMark = Integer.parseInt(list.get(7));
        this.formOfEducation = FormOfEducation.valueOf(list.get(8));
        this.groupAdmin = new Person(list.get(9), Integer.parseInt(list.get(10)), list.get(11), Country.valueOf(list.get(12)));
    }

    /**
     * Default constructor
     */
    public StudyGroup(Long id, String name, Coordinates coordinates, ZonedDateTime creationDate, Long studentsCount, Long shouldBeExpelled, Integer averageMark, FormOfEducation formOfEducation, Person groupAdmin) {
        this.id = id;
        this.name = name;
        this.coordinates = new Coordinates(coordinates.getX(), coordinates.getY());
        this.creationDate = creationDate;
        this.studentsCount = studentsCount;
        this.shouldBeExpelled = shouldBeExpelled;
        this.averageMark = averageMark;
        this.formOfEducation = formOfEducation;
        this.groupAdmin = new Person(groupAdmin.getName(), groupAdmin.getWeight(), groupAdmin.getPassportID(), groupAdmin.getNationality());
    }

    /**
     * @return Group ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @return Amount of students in group
     */
    public Long getStudentsCount() {
        return studentsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyGroup that = (StudyGroup) o;
        return id.equals(that.id) && name.equals(that.name) && coordinates.equals(that.coordinates) && formOfEducation == that.formOfEducation;
    }

    /**
     * @return Number of students that should be expelled
     */
    public Long getShouldBeExpelled() {
        return shouldBeExpelled;
    }

    /**
     * @return Group admin
     */
    public Person getGroupAdmin() {
        return groupAdmin;
    }

    /**
     * @return Group form of education
     */
    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    @Override
    public int compareTo(StudyGroup studyGroup) {
        return id.compareTo(studyGroup.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, formOfEducation);
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public Integer getAverageMark() {
        return averageMark;
    }

    @Override
    public String toString() {
        return
                "Группа №" + id +
                        " (добавлена " + creationDate.toLocalDate() + " " + creationDate.toLocalTime() + ")" +
                        "\n Имя: " + name +
                        "\n Местоположение: " + coordinates +
                        "\n Количество учеников: " + studentsCount +
                        "\n Количество людей к отчислению: " + shouldBeExpelled +
                        "\n Форма обучения: " + formOfEducation +
                        "\n Староста группы: " + groupAdmin;
    }
}
