package Ex2_2;

import java.util.concurrent.*;

public class Task  {
    private Executor executor;
    private Future future;

    public Callable getCallable() {
        return callable;
    }

    private final Callable callable;

    public Task(Callable task) {
        this.callable = task;
    }

    public static Task createTask(Callable task, TaskType taskType) {
        return switch (taskType){
            case COMPUTATIONAL -> new Task(task);
            case IO, OTHER -> new Task(task);
            default -> throw new RuntimeException("WRONG TYPE");
        };
    }

    public Object get(long num, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException{
        return future.get(num,timeUnit);
    }
    public Object get() throws InterruptedException, ExecutionException{
        return future.get();
    }


    public void setFuture(Future future) {
        this.future = future;
    }
}
