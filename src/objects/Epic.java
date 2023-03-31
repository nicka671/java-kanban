package objects;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Subtask> includedSubtasks = new ArrayList<>();

    public ArrayList<Subtask> getIncludedSubtasks() {
        return includedSubtasks;
    }

    public void setIncludedSubtasks(ArrayList<Subtask> includedSubtasks) {
        this.includedSubtasks = includedSubtasks;
    }

   }
