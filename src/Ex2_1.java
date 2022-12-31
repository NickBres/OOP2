import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;

public class Ex2_1 {

    public static void main(String[] args) {
        long startTime, endTime;
        int numberOfFiles = 30;
        int maxNumberOfLines = 100;
        String fileNames[] = createTextFiles(numberOfFiles, 100, maxNumberOfLines);
        System.out.println("Checking " + numberOfFiles + " files with " + maxNumberOfLines + " maximum lines each:");

        System.out.println("---------------------------------------------------------");
        startTime = System.currentTimeMillis();
        System.out.println("Num of lines in all files: " + getNumOfLines(fileNames));
        endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime) + " ms");
        System.out.println("---------------------------------------------------------");

        startTime = System.currentTimeMillis();
        System.out.println("Num of lines in all files using threads: " + getNumOfLinesThreads(fileNames));
        endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime) + " ms");
        System.out.println("---------------------------------------------------------");

        startTime = System.currentTimeMillis();
        System.out.println("Num of lines in all files using threadPool: " + getNumOfLinesThreadPool(fileNames));
        endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime) + " ms");
        System.out.println("---------------------------------------------------------");

        deleteFiles(fileNames);
    }
    /**
     * Count the number of lines in all files using ThreadPool
     * @param filesNames
     * @return the number of lines in all files
     */
    public static int getNumOfLinesThreadPool(String[] filesNames) {
        int numOfLines = 0;
        int length = filesNames.length;
        ExecutorService threadPool = Executors.newFixedThreadPool(length);
        Future<Integer> futures[] = new CompletableFuture[length];
        for (int i = 0; i < length; i++) {
            futures[i] = threadPool.submit(new CountLinesWithThreadPool(filesNames[i]));
        }
        for (Future<Integer> future : futures) {
            try {
                numOfLines += future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        threadPool.shutdown();
        return numOfLines;
    }

    /**
     * Delete all files in the array
     * @param filesNames
     */
    public static void deleteFiles(String[] filesNames) {
        for (String fileName : filesNames) {
            File file = new File(fileName);
            file.delete();
        }
    }

    /**
     * Count the number of lines in all files using threads
     * @param filesNames
     * @return the number of lines in all files
     */
    public static int getNumOfLinesThreads(String[] filesNames) {
        int length = filesNames.length;
        int numOfLines = 0;
        CountLinesWithThreads countLineWithThreads[] = new CountLinesWithThreads[length];
        for (int i = 0; i < length; i++) {
            countLineWithThreads[i] = new CountLinesWithThreads(filesNames[i]);
            countLineWithThreads[i].start();
        }
        for (CountLinesWithThreads thread : countLineWithThreads) {
            try {
                thread.join();
                numOfLines += thread.getCount();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return numOfLines;
    }

    /**
     * Count the number of lines in all files
     * @param filesNames
     * @return
     */
    public static int getNumOfLines(String[] filesNames) {
        int numOfLines = 0;
        for (String fileName : filesNames) {
            numOfLines += countLines(fileName);
        }
        return numOfLines;
    }

    /**
     * Open a file and count the number of lines in it using Scanner
     * @param fileName
     * @return number of lines in the file
     */
    private static int countLines(String fileName) {
        int count = 0;
        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                count++;
                reader.nextLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return count;
    }

    /**
     * Creates text files with random amount of lines
     * @param n  number of files
     * @param seed  seed for random
     * @param bound  max number of lines in a file
     * @return array of file names
     */
    public static String[] createTextFiles(int n, int seed, int bound) {
        String[] filesNames = new String[n];
        Random rand = new Random(seed);
        for (int i = 0; i < n; i++) {
            int numOfLines = rand.nextInt(bound);
            filesNames[i] = createTextFile(i);
            writeToFile(filesNames[i], numOfLines);
        }
        return filesNames;
    }

    /**
     * Creates a text file with the given name
     * @param numOfFile
     * @return the name of the file
     */
    private static String createTextFile(int numOfFile) {
        String fileName = "file_" + numOfFile + ".txt";
        try {
            File file = new File(fileName);
        } catch (Exception e) {
            System.out.println(e);
        }
        return fileName;
    }

    /**
     * Write some lines to the file
     * @param fileName
     * @param numOfLines
     */
    private static void writeToFile(String fileName, int numOfLines) {
        try {
            FileWriter writer = new FileWriter(fileName);
            for (int i = 0; i < numOfLines; i++) {
                writer.write("This is line " + i + " in file " + fileName + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class CountLinesWithThreads extends Thread {
    private int count = 0;
    String name;

    public CountLinesWithThreads(String name) {
        super(name);
        this.name = name;
    }

    public void run() {
        countLines(name);
    }

    /**
     * Open a file and count the number of lines in it using Scanner
     * @param fileName
     */
    private void countLines(String fileName) {
        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                this.count++;
                reader.nextLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public int getCount() {
        return this.count;
    }
}

class CountLinesWithThreadPool implements Callable<Integer> {
    private int count = 0;
    String name;

    public CountLinesWithThreadPool(String name) {
        this.name = name;
    }
    /**
     * Open a file and count the number of lines in it using Scanner
     * @param fileName
     */
    private void countLines(String fileName) {
        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                this.count++;
                reader.nextLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public int getCount() {
        return this.count;
    }

    @Override
    public Integer call() throws Exception {
        countLines(name);
        return this.count;
    }
}
