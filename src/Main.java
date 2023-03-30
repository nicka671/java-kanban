import manager.TaskManager;
import objects.Epic;
import objects.Subtask;
import objects.Task;

import java.util.*;

import static enums.Status.DONE;

public class Main {

    static Scanner scanner = new Scanner((System.in));
    private static TaskManager taskManager;
    private static Subtask subtask;
    private static Task task;
    private static Epic epic;

    public static void printMenu() {
        System.out.println("МЕНЮ:");
        System.out.println("1 - Получение списка всех задач.");
        System.out.println("2 - Получение списка всех подзадач.");
        System.out.println("3 - Получение списка всех эпиков.");
        System.out.println("4 - Удаление всех задач.");
        System.out.println("5 - Удаление всех подзадач.");
        System.out.println("6 - Удаление всех эпиков.");
        System.out.println("7 - Получение задачи по идентификатору.");
        System.out.println("8 - Получение подзадачи по идентификатору.");
        System.out.println("9 - Получение эпика по идентификатору.");
        System.out.println("10 - Создание задачи. Сам объект должен передаваться в качестве параметра.");
        System.out.println("11 - Создание подзадачи в эпике с epicId. Сам объект должен передаваться в качестве параметра.");
        System.out.println("12 - Создание эпика. Сам объект должен передаваться в качестве параметра.");
        System.out.println("13 - Обновление задачи с taskId. Новая версия объекта с верным идентификатором передаётся в виде параметра.");
        System.out.println("14 - Обновление подзадачи с subtaskId. Новая версия объекта с верным идентификатором передаётся в виде параметра.");
        System.out.println("15 - Обновление эпика с epicId. Новая версия объекта с верным идентификатором передаётся в виде параметра.");
    }

    public static void main(String[] args) {

        taskManager = new TaskManager();
        task = new Task();
        subtask = new Subtask();
        epic = new Epic();

        printMenu();

        while (true) {
            int command = scanner.nextInt();

            if (command == 1) {
                System.out.println(taskManager.getAllTasks());
                printMenu();
            } else if (command == 2) {
                System.out.println(taskManager.getAllSubtasks());
                printMenu();
            } else if (command == 3) {
                System.out.println(taskManager.getAllEpics());
                printMenu();
            } else if (command == 4) {
                System.out.println(taskManager.deleteAllTasks());
                printMenu();
            } else if (command == 5) {
                System.out.println(taskManager.deleteAllSubtasks());
                printMenu();
            } else if (command == 6) {
                System.out.println(taskManager.deleteAllEpics());
                printMenu();
            } else if (command == 7) {
                int i = scanner.nextInt();
                System.out.println(taskManager.getTask(i));
                printMenu();
            } else if (command == 8) {
                int i = scanner.nextInt();
                System.out.println(taskManager.getSubtask(i));
                printMenu();
            } else if (command == 9) {
                int i = scanner.nextInt();
                System.out.println(taskManager.getEpic(i));
                printMenu();
            } else if (command == 10) {
                taskManager.createTask(task);
                printMenu();
            } else if (command == 11) {
                int i = scanner.nextInt();
                taskManager.createSubtask(subtask, i);
                printMenu();
            } else if (command == 12) {
                taskManager.createEpic(epic);
                printMenu();
            } else if (command == 13) {
                int i = scanner.nextInt();
                taskManager.updateTask(task, i);
                printMenu();
            } else if (command == 14) {
                System.out.println("Введите id подзадачи");
                int i = scanner.nextInt();
                taskManager.updateSubtask(subtask, i);
                printMenu();
            } else if (command == 15) {
                int i = scanner.nextInt();
                taskManager.updateEpic(epic, i);
                printMenu();
            } else {
                return;
            }
        }
    }
}