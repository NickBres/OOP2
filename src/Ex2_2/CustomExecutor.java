package Ex2_2;

import java.util.PriorityQueue;
import java.util.concurrent.*;

public class CustomExecutor {

    private final ExecutorService executorService;

    public CustomExecutor() {
        executorService = new ThreadPoolExecutor(10, 10,
                0L, TimeUnit.MILLISECONDS,new PriorityBlockingQueue<>());
    }

    public Task submit(Task task) {
        submitTask(task);
        return task;
    }

    private void submitTask(Task task) {
        final Future future = this.executorService.submit(task.getCallable());
        task.setFuture(future);
    }

    public Task submit(Callable taskSupplier, TaskType type) {
        final Task task = Task.createTask(taskSupplier, type);
        submitTask(task);
        return task;
    }
    public String getCurrentMax() {
        return "";
    }

    public void gracefullyTerminate() {
    }
}
