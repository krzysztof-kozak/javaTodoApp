package todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {
        Scanner system = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Walk the doggo", true));
        tasks.add(new Task("Do the homework", false));
        tasks.add(new Task("Practice some Java", true));

        showTasks(tasks);

        while (system.hasNext()) {
            String userInput = system.nextLine();

            if (isAdd(userInput)) {
                String sanitizedInput = getCleanString(userInput, 3);
                tasks.add(new Task(sanitizedInput, false));

                System.out.println("Here is an updated list for you:");
                showTasks(tasks);
            }

            if (isRemove(userInput)) {
                String sanitizedInput = getCleanString(userInput, 6);
                int index = Integer.parseInt(sanitizedInput);

                if (isOutOfBounds(tasks, index)) {
                    System.out.println("Index out of bounds.");

                } else {

                    tasks.remove(index - 1);

                    System.out.println("Task number #" + index + " removed!");
                    System.out.println("Here is an updated list for you:");
                    showTasks(tasks);
                }
            }

            if (isToggle(userInput)) {
                String sanitizedInput = getCleanString(userInput, 6);
                int index = Integer.parseInt(sanitizedInput);

                if (isOutOfBounds(tasks, index)) {
                    System.out.println("Index out of bounds.");

                } else {
                    boolean initialStatus = tasks.get(index - 1).isCompleted();
                    tasks.get(index - 1).toggleCompleted();

                    String info;
                    if (initialStatus) {
                        info = " un-completed";
                    } else {
                        info = " completed";

                    }

                    System.out.println("Task number #" + index + info);
                    System.out.println("Here is an updated list for you:");
                    showTasks(tasks);
                }


            }

            if (isEdit(userInput)) {
                String sanitizedInput = getCleanString(userInput, 4);
                int index = Integer.parseInt(sanitizedInput);

                if (isOutOfBounds(tasks, index)) {
                    System.out.println("Index out of bounds.");

                } else {
                    System.out.println("Enter new task content to replace the old one: ");

                    final String newContent = system.nextLine();
                    tasks.get(index - 1).editContent(newContent);

                    System.out.println("Task number #" + index + " edited!");
                    System.out.println("Here is an updated list for you:");
                    showTasks(tasks);
                }


            }

        }

    }

    private static void showTasks(List<Task> tasks) {
        AtomicInteger index = new AtomicInteger();
        tasks.forEach(task -> System.out.println(
                (index.getAndIncrement() + 1) + ". "
                        + task.getContent()
                        + " ("
                        + task.isCompleted() + ")"));

        System.out.println("Available commands: add <task name>, remove <task number>, " +
                "toggle <task number>, edit <task index>");
    }

    private static boolean isAdd(String userInput) {
        return userInput.toLowerCase().startsWith("add");

    }

    private static boolean isRemove(String userInput) {
        return userInput.toLowerCase().startsWith("remove");
    }

    private static boolean isToggle(String userInput) {
        return userInput.toLowerCase().startsWith("toggle");
    }

    private static boolean isEdit(String userInput) {
        return userInput.toLowerCase().startsWith("edit");
    }

    private static String getCleanString(String userInput, int range) {
        return userInput.replace(userInput.substring(0, range), "").trim();
    }

    public static boolean isOutOfBounds(List<Task> list, int index) {
        int listSize = list.size();
        return index >= listSize + 1 || index < 1;

    }

}
