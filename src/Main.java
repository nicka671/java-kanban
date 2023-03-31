import manager.TaskManager;
import objects.Epic;
import objects.Subtask;
import objects.Task;

import java.util.*;

import static enums.Status.DONE;

public class Main {

    private static TaskManager taskManager = new TaskManager();
    private static Subtask subtask;
    private static Subtask subtask1;
    private static Subtask subtask2;
    private static Task task;
    private static Task task1;
    private static Epic epic;
    private static Epic epic1;


    public static void main(String[] args)
    {
        epic = new Epic();
        epic1 = new Epic();
        task = new Task();
        task1 = new Task();
        subtask = new Subtask();
        subtask1 = new Subtask();
        subtask2 = new Subtask();

        taskManager.createTask(task);
        taskManager.createTask(task1);
        taskManager.createEpic(epic);
        taskManager.createSubtask(subtask, 3);
        taskManager.createSubtask(subtask1, 3);
        taskManager.createEpic(epic1);
        taskManager.createSubtask(subtask2, 6);
        System.out.println(taskManager.getAllEpics());
        System.out.println(taskManager.getAllTasks());
        System.out.println(taskManager.getAllSubtasks());

        // Измените статусы созданных объектов, распечатайте. Проверьте, что статус задачи и подзадачи сохранился, а статус эпика рассчитался по статусам подзадач.
        taskManager.updateTask(task, 1);
        taskManager.updateTask(task, 1);
        System.out.println("Статус задачи с id 1 " +  task.getCurrent_status());
        taskManager.updateTask(task, 2);
        System.out.println("Статус задачи с id 2 " +  task.getCurrent_status());

        taskManager.updateSubtask(subtask, 4);
        taskManager.updateSubtask(subtask, 4);
        taskManager.updateSubtask(subtask1, 5);
        taskManager.updateSubtask(subtask1, 5);
        System.out.println("Статус эпика с id 3 " +  epic.getCurrent_status());

        taskManager.updateSubtask(subtask2, 7);
        System.out.println("Статус эпика с id 6 " +  epic1.getCurrent_status());

        taskManager.deleteTaskById(1);
        taskManager.deleteSubtaskById(4);

    }
}