package Ex2_2;

import java.util.concurrent.*;

public class Task<T> implements Comparable<Task<T>>,Callable<T>  {
    private Future<T> future; // the future object associated with this task
    private final Callable<T> supplier;
    private final TaskType taskType;

    /**
     * Creates a task with the given supplier and task type.
     * @param task
     * @param taskType
     */
    public Task(Callable<T> task, TaskType taskType) {
        this.supplier = task;
        this.taskType = taskType;
    }

    /**
     * Creates a task with the given supplier and default task type.
     * @param task
     */
    public Task(Callable<T> task) {
        this.supplier = task;
        this.taskType = TaskType.OTHER;
    }

    /**
     * Creates a task with the given supplier and task type.
     * @param task
     * @param taskType
     * @return task object
     */
    public static <T> Task<T> createTask(Callable<T> task, TaskType taskType) {
        return new Task<T>(task,taskType);
    }

    /**
     * Compares this task with the specified task for order.
     * @param t1 the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Task t1) { return this.taskType.compareTo(t1.taskType);
    }
    /**
     * Returns the result of the task and time, throws an exception if the task was not completed successfully.
     * @return the result of the task
     */
    public  T get(long num, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return (T) future.get(num,timeUnit);
    }
    /**
     * Returns the result of the task, throws an exception if the task was not completed successfully.
     * @return the result of the task
     */
    public T get() throws InterruptedException, ExecutionException{
        return (T) future.get();
    }
    /**
     * Call method for the task
     * @return result of the task
     */
    public T call() throws Exception {
        return this.supplier.call();
    }
    /**
     * Sets the future object associated with this task.
     * @param future
     */
    public void setFuture(Future<T> future) {
        this.future = future;
    }

    public TaskType getTaskType() {
        return taskType;
    }
}
