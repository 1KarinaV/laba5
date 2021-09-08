package utility;

import data.FormOfEducation;
import data.StudyGroup;
import exceptions.SetIsEmptyException;

import java.time.ZonedDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;

/**
 * @author Karina Vladykina
 * Class for managing set of groups
 */
public class SetManager {
    private final FileManager fileManager;
    LinkedHashSet<StudyGroup> studyGroupSet = new LinkedHashSet<>();
    private final ZonedDateTime lastInitTime;
    private ZonedDateTime lastSaveTime;

    public SetManager(FileManager fileManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.fileManager = fileManager;

        loadSet();
    }

    /**
     * @return Initialization time
     */
    public ZonedDateTime getLastInitTime() {
        return lastInitTime;
    }

    /**
     * Generates next id for group
     * @return New id
     */
    public Long generateNextId() {
        Long maxId = 0L;
        if (studyGroupSet.isEmpty()) return 1L;
        for (StudyGroup studyGroup : studyGroupSet) {
            if (studyGroup.getId() > maxId) {
                maxId = studyGroup.getId();
            }
        }
        return maxId + 1;
    }

    /**
     * Returns max element of set by students number
     * @return Group with max students
     */
    public StudyGroup getMax() {
        Long maxStudentsCount = 0L;
        StudyGroup returnableGroup = null;
        for (StudyGroup studyGroup : studyGroupSet) {
            if (studyGroup.getStudentsCount() > maxStudentsCount) {
                maxStudentsCount = studyGroup.getStudentsCount();
                returnableGroup = studyGroup;
            }
        }
        return returnableGroup;
    }

    /**
     * @return Last time set was saved to file
     */
    public ZonedDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    /**
     * Loads set from file
     */
    private void loadSet() {
        studyGroupSet = fileManager.readSet();
    }

    /**
     * @return Set itself
     */
    public LinkedHashSet<StudyGroup> getSet() {
        return studyGroupSet;
    }

    /**
     * Gets type from group set
     * @return Set Type
     */
    public String setType() {
        return studyGroupSet.getClass().getName();
    }

    /**
     * @return Set size
     */
    public int setSize() {
        return studyGroupSet.size();
    }

    /**
     * @param groupToFind
     * @return Group that is equal to group from parameter
     */
    public StudyGroup getByValue(StudyGroup groupToFind) {
        for (StudyGroup studyGroup : studyGroupSet) {
            if (studyGroup.equals(groupToFind)) return studyGroup;
        }
        return null;
    }

    /**
     * Filters groups that have amount of students to be expelled as user typed
     * @param numberToFilter Number of students to be filtered with
     * @return Filtered information
     */
    public String expelledFilteredInfo(Long numberToFilter) {
        StringBuilder filteredInfo = new StringBuilder();
        for (StudyGroup studyGroup : studyGroupSet) {
            if (Objects.equals(studyGroup.getShouldBeExpelled(), numberToFilter)) {
                filteredInfo.append(studyGroup).append("\n\n");
            }
        }
        return filteredInfo.toString().trim();
    }

    /**
     * Adds another group to set
     * @param studyGroup
     */
    public void addToSet(StudyGroup studyGroup) {
        studyGroupSet.add(studyGroup);
    }

    /**
     * Removes group if such exists in set
     * @param studyGroupToCompare
     */
    public void removeFromSet(StudyGroup studyGroupToCompare) {
        studyGroupSet.removeIf(studyGroup -> studyGroup.compareTo(studyGroupToCompare) == 0);
    }

    /**
     * Clears the set entirely
     */
    public void clearSet() {
        studyGroupSet.clear();
    }

    /**
     * @return Group with maximum value of admin
     * @throws SetIsEmptyException
     */
    public String maxByAdmin() throws SetIsEmptyException {
        if (studyGroupSet.isEmpty()) throw new SetIsEmptyException();

        StudyGroup maxAdminGroup = getMax();
        for (StudyGroup studyGroup : studyGroupSet) {
            if (studyGroup.getGroupAdmin().getWeight() > maxAdminGroup.getGroupAdmin().getWeight()) {
                maxAdminGroup = studyGroup;
            }
        }
        return maxAdminGroup.toString();
    }

    /**
     * Saves set groups to file
     */
    public void saveSet() {
        fileManager.writeSet(studyGroupSet);
        lastSaveTime = ZonedDateTime.now();
    }

    /**
     * @param form FormOfEducation
     * @return Groups with given education form
     */
    public StudyGroup getByEducationForm(FormOfEducation form) {
        for (StudyGroup studyGroup : studyGroupSet) {
            if (studyGroup.getFormOfEducation().equals(form)) return studyGroup;
        }
        return null;
    }

    /**
     * @param id
     * @return Group with given id
     */
    public StudyGroup getById(Long id) {
        for (StudyGroup studyGroup : studyGroupSet) {
            if (Objects.equals(studyGroup.getId(), id)) return studyGroup;
        }
        return null;
    }

    /**
     * Removes all groups which is lower then given
     * @param groupFromSet
     */
    public void removeLower(StudyGroup groupFromSet) {
        studyGroupSet.removeIf(group -> group.compareTo(groupFromSet) < 0);
    }

    public String toString() {
        if (studyGroupSet.isEmpty()) return "Коллекция пуста!";
        StringBuilder info = new StringBuilder();
        for (StudyGroup group : studyGroupSet) {
            info.append(group).append("\n\n");
        }
        return info.toString();
    }
}
