package Ex2_2;

import java.util.concurrent.*;

public class CustomExecutor {

    private final ExecutorService executorService;
    private final PriorityBlockingQueue<Runnable> priorityBlockingQueue; // the priority queue of tasks
    private int processors;// number of processors available for the JVM

    /**
     * Creates a new custom executor with number of threads depends on available processors.
     */
    public CustomExecutor() {
        this.processors = Runtime.getRuntime().availableProcessors();
        this.priorityBlockingQueue = new PriorityBlockingQueue<>();
        this.executorService = new ThreadPoolExecutor(processors / 2, processors - 1,
                300, TimeUnit.MILLISECONDS, priorityBlockingQueue);
    }

    /**
     * Gives task to the executor service.
     *
     * @param task
     */
    public <T> Task<T> submit(Task<T> task) {
        submitTask(task);
        return task;
    }

    /**
     * Creates a task with the given supplier and task type and gives it to the executor service.
     *
     * @param taskSupplier
     * @param type
     * @return task object
     */
    public <T> Task<T> submit(Callable<T> taskSupplier, TaskType type) {
        final Task<T> task = Task.createTask(taskSupplier, type);
        submitTask(task);
        return task;
    }

    /**
     * Submits a task to the executor service.And sets the future object associated with this task.
     *
     * @param task
     */
    private void submitTask(Task<?> task) {
        final Future future = this.executorService.submit(task);
        task.setFuture(future);
    }

    /**
     * Check if there are tasks in the queue.And returns first tasks priority type in the queue.
     *
     * @return task type string
     */
    public String getCurrentMax() {
        Task task = (Task) priorityBlockingQueue.peek();
        if (task != null) {
            String max = task.getTaskType().toString();
            return max;
        }
        return "No tasks";
    }

    /**
     * Shuts down the executor service.
     */
    public void gracefullyTerminate() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }

    }
}
