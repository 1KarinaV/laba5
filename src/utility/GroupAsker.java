package utility;

import data.Coordinates;
import data.Country;
import data.FormOfEducation;
import data.Person;
import exceptions.IncorrectInputInScriptException;
import exceptions.MustBeNotEmptyException;
import exceptions.NotInDefinedLimitsException;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Karina Vladykina
 * Asker class for console
 */
public class GroupAsker {
    private final int MAX_Y = -390;
    //private final int MIN_ID_LENGTH = 6;

    private Scanner userScanner;
    private boolean fileMode;

    public GroupAsker(Scanner userScanner) {
        this.userScanner = userScanner;
        fileMode = false;
    }

    /**
     * @return Scanner for user input
     */
    public Scanner getUserScanner() {
        return userScanner;
    }

    /**
     * Sets a scanner for user input

     */
    public void setUserScanner(Scanner scriptScanner) {
        this.userScanner = scriptScanner;
    }

    /**
     * Script mode switch
     */
    public void setFileMode() {
        fileMode = true;
    }

    /**
     * Interactive mode switch
     */
    public void setUserMode() {
        fileMode = false;
    }

    /**
     * Asks group name
     * @return Group name
     * @throws IncorrectInputInScriptException
     */
    public String askName() throws IncorrectInputInScriptException {
        String name;
        while (true) {
            try {
                Console.println("Введите название группы:");
                Console.print(">");
                name = userScanner.nextLine().trim();
                if (fileMode) Console.println(name);
                if (name.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printError("Название не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                Console.printError("Название не может быть пустым!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return name;
    }

    /**
     * Asks group X coordinate
     * @return X coordinate
     * @throws IncorrectInputInScriptException
     */
    public Integer askX() throws IncorrectInputInScriptException {
        String strX;
        int x;
        while (true) {
            try {
                Console.println("Введите координату X для группы:");
                Console.print(">");
                strX = userScanner.nextLine().trim();
                if (fileMode) Console.println(strX);
                x = Integer.parseInt(strX);
                break;
            } catch (NoSuchElementException exception) {
                Console.printError("Координата X не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printError("Координата X должна быть представлена числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return x;
    }

    /**
     * Asks group Y coordinate
     * @return Y coordinate
     * @throws IncorrectInputInScriptException
     */
    public float askY() throws IncorrectInputInScriptException {
        String strY;
        float y;
        while (true) {
            try {
                Console.println("Введите координату Y для группы (< " + (MAX_Y + 1) + "):");
                Console.print(">");
                strY = userScanner.nextLine().trim();
                if (fileMode) Console.println(strY);
                y = Float.parseFloat(strY);
                if (y > MAX_Y) throw new NotInDefinedLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printError("Координата Y не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDefinedLimitsException exception) {
                Console.printError("Координата Y не может превышать " + MAX_Y + "!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printError("Координата Y должна быть представлена числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return y;
    }
    /**
     * Calls X and Y askers
     * @return Object with X and Y coordinate fields
     * @throws IncorrectInputInScriptException
     */
    public Coordinates askCoordinates() throws IncorrectInputInScriptException {
        int x;
        float y;
        x = askX();
        y = askY();
        return new Coordinates(x, y);
    }

    /**
     * Asks group student count
     * @return Student count
     * @throws IncorrectInputInScriptException
     */
    public long askStudentsCount() throws IncorrectInputInScriptException {
        String strStudentsCount;
        long studentsCount;
        while (true) {
            try {
                Console.println("Введите количество учеников в группе > " + 0 + ":");
                Console.print(">");
                strStudentsCount = userScanner.nextLine().trim();
                if (fileMode) Console.println(strStudentsCount);
                studentsCount = Long.parseLong(strStudentsCount);
                if (studentsCount <= 0) throw new NotInDefinedLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printError("Количество учеников в группе не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDefinedLimitsException exception) {
                Console.printError("Количество учеников в группе должно быть положительным и больше нуля");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printError("Количество учеников в группе должно быть представлено числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return studentsCount;
    }

    /**
     * Asks number of students to be expelled
     * @return Number of students
     * @throws IncorrectInputInScriptException
     */
    public long askShouldBeExpelled() throws IncorrectInputInScriptException {
        String strShouldBeExpelled;
        long shouldBeExpelled;
        while (true) {
            try {
                Console.println("Введите количество людей на отчисление > " + 0 + ":");
                Console.print(">");
                strShouldBeExpelled = userScanner.nextLine().trim();
                if (fileMode) Console.println(strShouldBeExpelled);
                shouldBeExpelled = Long.parseLong(strShouldBeExpelled);
                if (shouldBeExpelled <= 0) throw new NotInDefinedLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printError("Количество людей на отчисление не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDefinedLimitsException exception) {
                Console.printError("Количество людей на отчисление должно быть положительным и больше нуля");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printError("Количество людей на отчисление должно быть представлено числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return shouldBeExpelled;
    }

    /**
     * Asks group average mark
     * @return Average mark
     * @throws IncorrectInputInScriptException
     */
    public int askAverageMark() throws IncorrectInputInScriptException {
        String strAverageMark;
        int averageMark;
        while (true) {
            try {
                Console.println("Введите средний балл группы > " + 0 + ":");
                Console.print(">");
                strAverageMark = userScanner.nextLine().trim();
                if (fileMode) Console.println(strAverageMark);
                averageMark = Integer.parseInt(strAverageMark);
                if (averageMark <= 0) throw new NotInDefinedLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printError("Средний балл группы не распознан!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDefinedLimitsException exception) {
                Console.printError("Средний балл группы должен быть положительным и больше нуля");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printError("Средний балл группы должен быть представлен числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return averageMark;
    }

    /**
     * Asks form of education
     * @return Enumerated form of education value
     * @throws IncorrectInputInScriptException
     */
    public FormOfEducation askFormOfEducation() throws IncorrectInputInScriptException {
        String strFormOfEducation;
        FormOfEducation formOfEducation = null;
        while (true) {
            try {
                Console.println("Список форм обучения – " + FormOfEducation.nameList());
                Console.println("Введите форму обучения:");
                Console.print(">");
                strFormOfEducation = userScanner.nextLine().trim();
                if (fileMode) Console.println(strFormOfEducation);
                for (FormOfEducation each : FormOfEducation.class.getEnumConstants()) {
                    if (each.name().compareToIgnoreCase(strFormOfEducation) == 0) {
                        formOfEducation = each;
                    }
                }
                break;
            } catch (NoSuchElementException exception) {
                Console.printError("Форма обучения не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalArgumentException exception) {
                Console.printError("Формы обучения нет в списке!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return formOfEducation;
    }

    /**
     * Asks nationality
     * @return Enumerated nationality value
     * @throws IncorrectInputInScriptException
     */
    public Country askNationality() throws IncorrectInputInScriptException {
        String strNationality;
        Country nationality = null;
        while (true) {
            try {
                Console.println("Национальности – " + Country.nameList());
                Console.println("Введите национальность:");
                Console.print(">");
                strNationality = userScanner.nextLine().trim();
                if (fileMode) Console.println(strNationality);
                for (Country each : Country.class.getEnumConstants()) {
                    if (each.name().compareToIgnoreCase(strNationality) == 0) {
                        nationality = each;
                    }
                }
                break;
            } catch (NoSuchElementException exception) {
                Console.printError("Форма обучения не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalArgumentException exception) {
                Console.printError("Формы обучения нет в списке!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return nationality;
    }

    /**
     * Asks group admin name
     * @return Admin name
     * @throws IncorrectInputInScriptException
     */
    public String askAdminName() throws IncorrectInputInScriptException {
        String adminName;
        while (true) {
            try {
                Console.println("Введите имя старосты:");
                Console.print(">");
                adminName = userScanner.nextLine().trim();
                if (fileMode) Console.println(adminName);
                if (adminName.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printError("Имя не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                Console.printError("Имя не может быть пустым!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return adminName;
    }

    /**
     * Asks group admin weight
     * @return weight
     * @throws IncorrectInputInScriptException
     */
    public int askWeight() throws IncorrectInputInScriptException {
        String strWeight;
        int weight;
        while (true) {
            try {
                Console.println("Введите вес:");
                Console.print(">");
                strWeight = userScanner.nextLine().trim();
                if (fileMode) Console.println(strWeight);
                weight = Integer.parseInt(strWeight);
                break;
            } catch (NoSuchElementException exception) {
                Console.printError("Вес не распознан!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printError("Вес должен быть представлен числом");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return weight;
    }

    /**
     * Asks group admin passport ID
     * @return Admin passport ID
     * @throws IncorrectInputInScriptException
     */
    public String askPassportID() throws IncorrectInputInScriptException {
        String passportID;
        while (true) {
            try {
                Console.println("Введите номер паспорта:");
                Console.print(">");
                passportID = userScanner.nextLine().trim();
                if (fileMode) Console.println(passportID);
                if (passportID.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printError("Номер не распознан!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                Console.printError("Номер не может быть пустым!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return passportID;
    }
    /**
     * Calls admin parameters asker
     * @return Person object with fields
     * @throws IncorrectInputInScriptException
     */
    public Person askGroupAdmin() throws IncorrectInputInScriptException {
        String adminName;
        int weight;
        String passportID;
        Country nationality;
        adminName = askAdminName();
        weight = askWeight();
        passportID = askPassportID();
        nationality = askNationality();
        return new Person(adminName, weight, passportID, nationality);
    }
    /**
     * Asks questions for update command
     * @return Boolean value of user answer
     * @param question
     * @throws IncorrectInputInScriptException
     */
    public boolean askQuestion(String question) throws IncorrectInputInScriptException {
        String finalQuestion = question + " (+/-):";
        String answer;
        while (true) {
            try {
                Console.println(finalQuestion);
                Console.print(">");
                answer = userScanner.nextLine().trim();
                if (fileMode) Console.println(answer);
                if (!answer.equals("+") && !answer.equals("-")) throw new NotInDefinedLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printError("Ответ не распознан!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDefinedLimitsException exception) {
                Console.printError("Ответ должен быть представлен знаками '+' или '-'!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return answer.equals("+");
    }
}
