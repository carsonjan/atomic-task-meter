package model;

// Represents a subject (or a project) with it's name, estimated time, and tasks
public class Subject extends NodeLike {
    private String name;            // unique: name of the subject

    /*
     * REQUIRES: name is correctly formatted and not empty
     * EFFECTS: initializes the name. Set estimated and actual time to zero, tasks to empty collection
     */
    public Subject(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
