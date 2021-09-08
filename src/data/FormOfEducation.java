package data;

/**
 * @author Karina Vladykina
 * Form of education constants
 */
public enum FormOfEducation {
    DISTANCE_EDUCATION,
    FULL_TIME_EDUCATION,
    EVENING_CLASSES;

    /**
     * Puts all enum values into list
     *
     * @return comma separated enum values
     */
    public static String nameList() {
        StringBuilder nameList = new StringBuilder();
        for (FormOfEducation form : values()) {
            nameList.append(form.name()).append(", ");
        }
        return nameList.substring(0, nameList.length() - 2);
    }
}
