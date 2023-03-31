package manager;

import objects.Epic;
import objects.Subtask;
import objects.Task;

import java.util.ArrayList;
import java.util.HashMap;

import static enums.Status.*;

public class TaskManager {

    private int idGenerator = 0;



    // Возможность хранить задачи всех типов.

    private HashMap<Integer, Task> allTasks = new HashMap<>();
    private HashMap<Integer, Subtask> allSubtasks = new HashMap<>();
    private HashMap<Integer, Epic> allEpics = new HashMap<>();


    //  Получение списка всех задач.

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> taskList = new ArrayList<>();
        for (Task task : allTasks.values())
        {
            taskList.add(task);
        }
        return taskList;
    }

    public ArrayList<Subtask> getAllSubtasks() {
        ArrayList<Subtask> subtaskList = new ArrayList<>();
        for (Subtask subtask : allSubtasks.values()) {
            subtaskList.add(subtask);
        }
        return subtaskList;
    }

        public ArrayList<Epic> getAllEpics() {
            ArrayList<Epic> epicsList = new ArrayList<>();
            for (Epic epic : allEpics.values()) {
                epicsList.add(epic);
            }
            return epicsList;
        }



    //  Удаление всех задач.

    public void deleteAllTasks() {
        allTasks.clear();
    }

    public void deleteAllSubtasks() {
        allSubtasks.clear();
    }

    public void deleteAllEpics() {
        System.out.println("Подзадачи тоже будут удалены");
        allEpics.clear();
        allSubtasks.clear();

    }

    //  Получение по идентификатору.

    public Task getTask(int i) {
        return allTasks.get(i);
    }

    public Subtask getSubtask(int i) {
        return allSubtasks.get(i);
    }


    public ArrayList<Subtask> getAllSubtaskByEpicId(int i) {
        return allEpics.get(i).getIncludedSubtasks();
    }


    public Epic getEpic(int i) {
        return allEpics.get(i);
    }

    // Создание. Сам объект должен передаваться в качестве параметра.

    public Task createTask(Task task) {
        idGenerator += 1;
        task.setId(idGenerator);
        task.setCurrent_status(NEW);
        allTasks.put(idGenerator, task);
        return task;
    }

    public Subtask createSubtask(Subtask subtask, int epicId) {

        if (allEpics.containsKey(epicId)) {
            idGenerator += 1;
            subtask.setId(idGenerator);
            subtask.setCurrent_status(NEW);
            subtask.setEpicsId(epicId);
            allSubtasks.put(idGenerator, subtask);
            allEpics.get(epicId).getIncludedSubtasks().add(subtask);

            if (allEpics.get(epicId).getCurrent_status() == DONE) {
                allEpics.get(epicId).setCurrent_status(IN_PROGRESS);
            }
        } else {
            System.out.println("Для того, чтобы создать подзадачу, сначала создайте эпик, к которому привяжете ее");
        }
        return subtask;
    }

    public Epic createEpic(Epic epic) {
        idGenerator += 1;
        epic.setId(idGenerator);
        epic.setCurrent_status(NEW);
        allEpics.put(idGenerator, epic);
        return epic;
    }

    //Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра.

    public Task updateTask(Task task, int id) {
        if (allTasks.containsKey(id)) {

            if (allTasks.get(id).getCurrent_status() == NEW) {
                task.setCurrent_status(IN_PROGRESS);
            } else if (allTasks.get(id).getCurrent_status() == IN_PROGRESS) {
                task.setCurrent_status(DONE);
            }

            allTasks.put(id, task);
            return task;
        } else {
            System.out.println("Введите конкретный идентификатор");
            return task;
        }
    }

    public Subtask updateSubtask(Subtask subtask, int id) {


        if (allSubtasks.containsKey(id)) {
            if (allSubtasks.get(id).getCurrent_status() == NEW) {
                subtask.setCurrent_status(IN_PROGRESS);

            } else if (allSubtasks.get(id).getCurrent_status() == IN_PROGRESS) {
                subtask.setCurrent_status(DONE);
            }


            int saveId = allSubtasks.get(id).getEpicsId();
            int a = 0;
            allSubtasks.put(id, subtask);
            int pomogite = allEpics.get(saveId).getIncludedSubtasks().indexOf(subtask);
            allEpics.get(saveId).getIncludedSubtasks().set(pomogite, subtask);


            allSubtasks.get(id).setEpicsId(saveId);


            int i = subtask.getEpicsId();
            for (Subtask includedSubtask : allEpics.get(i).getIncludedSubtasks()) {
                if (includedSubtask.getCurrent_status() != DONE) {
                    a += 1;

                }

                if (includedSubtask.getCurrent_status() == IN_PROGRESS) {
                    allEpics.get(i).setCurrent_status(IN_PROGRESS);
                }
            }

            if (a == 0) {
                allEpics.get(i).setCurrent_status(DONE);
            }


            return subtask;
        } else {
            System.out.println("Введите конкретный идентификатор");
            return subtask;
        }
    }

    public Epic updateEpic(Epic epic, int id) {
        if (allEpics.containsKey(id)) {
            allEpics.put(id, epic);
            return epic;
        } else {
            System.out.println("Введите конкретный идентификатор");
            return epic;
        }
    }


    // Удаление по идентификатору.

    public void deleteTaskById(int id) {
        if (allTasks.containsKey(id)) {
            allTasks.remove(id);
        } else {
            System.out.println("Введите конкретный идентификатор");

        }
    }

    public void deleteSubtaskById(int id) {
        if (allSubtasks.containsKey(id)) {
            allSubtasks.remove(id);
        } else {
            System.out.println("Введите конкретный идентификатор");

        }
    }

    public void deleteEpicsById(int id) {
        if (allEpics.containsKey(id)) {
            allEpics.remove(id);
        } else {
            System.out.println("Введите конкретный идентификатор");

        }
    }
}