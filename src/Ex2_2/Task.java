package Ex2_2;

import java.util.concurrent.*;

public class Task<T> implements Comparable<Task<T>>,Callable<T>  {
    Future<T> future;
    final Callable<T> supplier;

    final TaskType taskType;


    public Task(Callable<T> task, TaskType taskType) {
        this.supplier = task;
        this.taskType = taskType;
    }

    public static <T> Task<T> createTask(Callable<T> task, TaskType taskType) {
        return new Task<T>(task,taskType);
    }

    @Override
    public int compareTo(Task t1) {
        return t1.taskType.compareTo(this.taskType);
    }
    public  T get(long num, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return (T) future.get(num,timeUnit);
    }
    public T get() throws InterruptedException, ExecutionException{
        return (T) future.get();
    }
    public T call() throws Exception {
        return this.supplier.call();
    }
    public void setFuture(Future<T> future) {
        this.future = future;
    }
}
