package Ex2_2;

import java.util.concurrent.*;

public class CustomExecutor {

    private final ExecutorService executorService;

    public CustomExecutor() {
        executorService = new ThreadPoolExecutor(10, 10,
                0L, TimeUnit.MILLISECONDS, new PriorityBlockingQueue<>());
    }

    public <T >Task<T> submit(Task<T> task) {
        submitTask(task);
        return task;
    }

    public <T> Task<T> submit(Callable<T> taskSupplier, TaskType type) {
        final Task<T> task = Task.createTask(taskSupplier, type);
        submitTask(task);
        return task;
    }

    private void submitTask(Task<?> task) {
        final Future future = this.executorService.submit(task);
        task.setFuture(future);
    }

    public String getCurrentMax() {
        return "";
    }

    public void gracefullyTerminate() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }

    }
}
