package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.ArrayUtils;

import com.example.utiks.ConsoleColors;

public class TaskManager {
    public static void main(String[] args) throws IOException {
        Path pathTasks = Paths.get("tasks.csv");
        Scanner scan = new Scanner(System.in);
        start();
        while (scan.hasNextLine()) {
            switch (scan.next()) {
                case "add":
                    addTask();
                    break;

                case "remove":
                    removeTask(pathTasks);
                    break;

                case "list":
                    listTask(pathTasks);
                    break;

                case "exit":
                    System.exit(0);

                default:
                    System.out.println("Please select correct option");
            }
        }
    }

    private static void listTask(Path pathTasks) throws IOException {
        List<String> list2 = Files.readAllLines(pathTasks);
        int counter = 0;
        for (String s : list2) {
            System.out.println(counter + " : " + s);
            counter++;
        }
    }

    private static void removeTask(Path pathTasks) throws IOException {
        Scanner scanRemove = new Scanner(System.in);
        List<String> list = Files.readAllLines(pathTasks);
        System.out.println("Please select number to remove");
        int num = scanRemove.nextInt();
        while (num < 0) {
            System.out.println("Please give number greater or equal 0");
            scanRemove.nextInt();
        }
        list.remove(num);
        Files.write(pathTasks, list);
        System.out.println("Value was sucessfully deleted");
    }

    private static void addTask() throws IOException {
        Scanner scanAdd = new Scanner(System.in);
        System.out.println("Please add task description");
        String description = scanAdd.nextLine();
        System.out.println("Please add task data");
        String taskData = scanAdd.nextLine();
        System.out.println("Is your task is important: true/false");
        boolean importantTask = scanAdd.nextBoolean();
        try (FileWriter fileWriter = new FileWriter("tasks.csv", true)) {
            fileWriter.append(description + ", " + taskData + ", " + importantTask + "\n");
        }
    }

    private static void start() {
        System.out.println(ConsoleColors.BLUE + "Please select an option:" + ConsoleColors.RESET);
        System.out.println("add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");
    }
}
