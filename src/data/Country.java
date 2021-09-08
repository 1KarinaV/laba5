package data;

/**
 * @author Karina Vladykina
 * Country constants
 */
public enum Country {
    USA,
    VATICAN,
    ITALY;

    /**
     * Puts all enum values into list
     *
     * @return comma separated enum values
     */
    public static String nameList() {
        StringBuilder nameList = new StringBuilder();
        for (Country country : values()) {
            nameList.append(country.name()).append(", ");
        }
        return nameList.substring(0, nameList.length() - 2);
    }
}
