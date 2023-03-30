package manager;

import objects.Epic;
import objects.Subtask;
import objects.Task;

import java.util.HashMap;

import static enums.Status.*;

public class TaskManager {

    protected int idGenerator = 0;
    Task task;
    Subtask subtask;
    Epic epic;

    // Возможность хранить задачи всех типов.

    HashMap<Integer, Task> allTasks = new HashMap<>();
    HashMap<Integer, Subtask> allSubtasks = new HashMap<>();
    HashMap<Integer, Epic> allEpics = new HashMap<>();


    //  Получение списка всех задач.

    public HashMap<Integer, Task> getAllTasks() {
        return allTasks;
    }

    public HashMap<Integer, Subtask> getAllSubtasks() {
        return allSubtasks;
    }

    public HashMap<Integer, Epic> getAllEpics() {
        return allEpics;
    }


    //  Удаление всех задач.

    public HashMap<Integer, Task> deleteAllTasks() {
        allTasks.clear();
        return allTasks;
    }

    public HashMap<Integer, Subtask> deleteAllSubtasks() {
        allSubtasks.clear();
        return allSubtasks;
    }

    public HashMap<Integer, Epic> deleteAllEpics() {
        System.out.println("Подзадачи тоже будут удалены");
        allEpics.clear();
        allSubtasks.clear();

        return allEpics;
    }

    //  Получение по идентификатору.

    public Task getTask(int i) {
        return allTasks.get(i);
    }

    public Subtask getSubtask(int i) {
        return allSubtasks.get(i);
    }

    public Epic getEpic(int i) {
        return allEpics.get(i);
    }

    // Создание. Сам объект должен передаваться в качестве параметра.

    public Task createTask(Task task) {
        task = new Task();
        idGenerator = allTasks.size() + 1;
        task.setId(idGenerator);
        task.setCurrent_status(NEW);
        allTasks.put(idGenerator, task);
        return task;
    }

    public Subtask createSubtask(Subtask subtask, int epicId) {

        if (allEpics.containsKey(epicId)) {
            subtask = new Subtask();
            idGenerator = allSubtasks.size() + 1;
            subtask.setId(idGenerator);
            subtask.setCurrent_status(NEW);
            subtask.setEpicsId(epicId);
            allSubtasks.put(idGenerator, subtask);
            allEpics.get(epicId).includedSubtasks.add(subtask);

            if (allEpics.get(epicId).getCurrent_status() == DONE) {
                allEpics.get(epicId).setCurrent_status(IN_PROGRESS);
            }
        } else {
            System.out.println("Для того, чтобы создать подзадачу, сначала создайте эпик, к которому привяжете ее");
        }
        return subtask;
    }

    public Epic createEpic(Epic Epic) {
        epic = new Epic();
        idGenerator = allEpics.size() + 1;
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


            int saveId = getAllSubtasks().get(id).getEpicsId();
            int a = 0;
            allSubtasks.put(id, subtask);
            allEpics.get(saveId).includedSubtasks.set(id - 1, subtask);


            getAllSubtasks().get(id).setEpicsId(saveId);


            int i = subtask.getEpicsId();
            for (Subtask includedSubtask : getAllEpics().get(i).includedSubtasks) {
                if (includedSubtask.getCurrent_status() != DONE) {
                    a += 1;

                }

                if (includedSubtask.getCurrent_status() == IN_PROGRESS) {
                    getAllEpics().get(i).setCurrent_status(IN_PROGRESS);
                }
            }

            if (a == 0) {
                getAllEpics().get(1).setCurrent_status(DONE);
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

    public void deleteTaskById(Task task, int id) {
        if (allTasks.containsKey(id)) {
            allTasks.remove(id);
        } else {
            System.out.println("Введите конкретный идентификатор");

        }
    }

    public void deleteSubtaskById(Subtask subtask, int id) {
        if (allSubtasks.containsKey(id)) {
            allSubtasks.remove(id);
        } else {
            System.out.println("Введите конкретный идентификатор");

        }
    }

    public void deleteEpicsById(Epic epic, int id) {
        if (allEpics.containsKey(id)) {
            allEpics.remove(id);
        } else {
            System.out.println("Введите конкретный идентификатор");

        }
    }
}