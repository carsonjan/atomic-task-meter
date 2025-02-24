package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads ATM from JSON data stored in file
// source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads ATM from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ATM read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAtm(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses atm from JSON object and returns it
    private ATM parseAtm(JSONObject jsonObject) {
        ATM atm = new ATM();
        addProjects(atm, jsonObject);
        return atm;
    }

    // MODIFIES: atm
    // EFFECTS: parses projects from JSON object and adds them to atm
    private void addProjects(ATM atm, JSONObject jsonObject) {
        JSONObject object = jsonObject.getJSONObject("projects");
        JSONArray names = object.names();
        // Collection<Object> jsonArray = jsonObject.getJSONObject("projects").toMap().values();
        if (names != null) {
            for (Object name : names) {
                JSONObject nextProject = object.getJSONObject((String) name);
                addProject(atm, nextProject);
            }
        }
        
    }

    // MODIFIES: atm
    // EFFECTS: parses project from JSON object and adds it to atm
    private void addProject(ATM atm, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Duration totalEstTime = Duration.parse(jsonObject.getString("totalEstTime"));
        Duration totalActTime = Duration.parse(jsonObject.getString("totalActTime"));
        Project project = new Project(name);
        project.setTotalActTime(totalActTime);
        project.setTotalEstTime(totalEstTime);
        addTasks(project, jsonObject);
        atm.addProject(project);
    }

    // MODIFIES: project
    // EFFECTS: parses tasks from JSON object and adds them to project
    private void addTasks(Project project, JSONObject jsonObject) {
        JSONObject object = jsonObject.getJSONObject("tasks");
        JSONArray names = object.names();
        if (names != null) {
            for (Object name : names) {
                JSONObject nextTask = object.getJSONObject((String) name);
                addTask(project, nextTask);
            }
        }
    }

    // MODIFIES: project
    // EFFECTS: parses task from JSON object and adds it to project
    private void addTask(Project project, JSONObject jsonObject) {
        String path = jsonObject.getString("path");
        Duration estTime = Duration.parse(jsonObject.getString("estTime"));
        Duration actTime = Duration.parse(jsonObject.getString("actTime"));
        boolean isDone = jsonObject.getBoolean("isDone");
        String projectName = jsonObject.getString("projectName");
        String name = jsonObject.getString("name");
        Task task = new Task(path, estTime.toHours());
        task.setDone(isDone);
        task.setActTime(actTime);
        task.setProjectName(projectName);
        task.setName(name);
        project.addTask(task);
    }

}
