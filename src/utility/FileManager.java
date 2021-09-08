package utility;

import data.StudyGroup;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Karina Vladykina
 * Doing everything that applies to files
 */
public class FileManager {
    private final String fileName;

    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Reads set records from CSV file end creates set
     */
    public LinkedHashSet<StudyGroup> readSet() {
        LinkedHashSet<StudyGroup> linkedStudyGroups = new LinkedHashSet<>();
        if (!fileName.equals("")) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
                List<List<String>> dataList = br.lines()
                        .map(k -> Arrays.asList(k.split(",")))
                        .collect(Collectors.toCollection(LinkedList::new));
                for (List<String> line : dataList) {
                    linkedStudyGroups.add(new StudyGroup(line));
                }
            } catch (IOException e) {
                Console.printError("Загрузочный файл не найден!");
            }
        } else Console.printError("Аргумент командной строки не найден!");
        return linkedStudyGroups;
    }

    /**
     * Writes all set groups with it's values to CSV file
     * @param set to write
     */
    public void writeSet(Set<StudyGroup> set) {
        if (System.getenv().get(fileName) != null) {
            try (CSVPrinter csvPrinter = new CSVPrinter(new BufferedWriter(new FileWriter(System.getenv().get(fileName))), CSVFormat.DEFAULT)) {
                for (StudyGroup group : set) {
                    csvPrinter.printRecord(group.getId(), group.getName(), group.getCoordinates().getX(), group.getCoordinates().getY(), group.getCreationDate(), group.getStudentsCount(), group.getShouldBeExpelled(), group.getAverageMark(), group.getFormOfEducation(), group.getGroupAdmin().getName(), group.getGroupAdmin().getWeight(), group.getGroupAdmin().getPassportID(), group.getGroupAdmin().getNationality());
                }
                csvPrinter.flush();
            } catch (IOException e) {
                Console.printError("Загрузочный файл не найден!");
            }
        } else Console.printError("Системная переменная с загрузочным файлом не найдена!");
    }
}