# OOP2

## Ex2_1
### Description
Ex2_1 is a Java class that demonstrates three different ways of counting the number of lines in multiple text files. The first method, getNumOfLines, simply reads each file sequentially and counts the number of lines in each file. The second method, getNumOfLinesThreads, uses separate threads to count the number of lines in each file concurrently. The third method, getNumOfLinesThreadPool, uses a thread pool to count the number of lines in each file concurrently.

The CountLinesWithThreads class is a helper class that extends the Thread class and implements the run method. Its run method reads a given text file and counts the number of lines in it. The CountLinesWithThreadPool class is a helper class that implements the Callable interface and overrides the call method. Its call method reads a given text file and counts the number of lines in it. Both of these classes are used to count the number of lines in a text file concurrently.

![image](https://user-images.githubusercontent.com/70432147/209985784-f0a5c3a8-0363-406a-8653-3e2803af3aac.png)


### Conclusion
It is difficult to say which method will be faster without knowing the specific details of the system it is running on. In general, using multiple threads or a thread pool to perform tasks concurrently can be faster than doing them sequentially, especially if the tasks can be done in parallel and the system has multiple processors or cores. However, creating and managing threads or a thread pool has overhead, so it may not always be faster to use them.

To determine which method is faster, it is best to measure the actual execution time of each method on the specific system it is running on. In the provided code, the execution time of each method is measured using the System.currentTimeMillis method, which returns the current time in milliseconds. The difference between the start time and end time gives the total execution time of each method.

It is also worth noting that the performance of each method may vary depending on the size and number of the text files being processed. If the text files are very large or there are a very large number of them, using multiple threads or a thread pool may be more beneficial. On the other hand, if the text files are small or there are only a few of them, using multiple threads or a thread pool may not provide much of a performance improvement.
#### Examples
![image](https://user-images.githubusercontent.com/70432147/209985244-2379cbad-476f-41f9-8020-b8076bbecd5f.png)
![image](https://user-images.githubusercontent.com/70432147/209985469-ee284bde-57ca-415d-bd28-9c6363d66c85.png)
![image](https://user-images.githubusercontent.com/70432147/209985588-03408d43-bdd0-44b3-bc90-4d788eca0b23.png)
![image](https://user-images.githubusercontent.com/70432147/209985602-db355137-9cc4-44bc-88ea-a632ff7e493e.png)


## Ex2_2
### Description
CustomExecutor is a custom implementation of an ExecutorService, a class that manages a pool of threads and executes submitted tasks. It has a priority queue of tasks and uses a ThreadPoolExecutor to execute these tasks. The CustomExecutor class has methods to submit tasks, get the current maximum priority task in the queue, and shut down the ExecutorService gracefully.

The Task class is a Callable that can be submitted to an ExecutorService for execution. It has a TaskType that defines the priority of the task and a Future object that is associated with the task. The Task class has a compareTo method that compares the priority of this task to another task, based on the TaskType. It also has a method to set the Future object associated with the task. The Task class has a static factory method to create a new Task object.
![image](https://user-images.githubusercontent.com/70432147/210372723-1366698f-e44e-4935-9dda-a30a7f46edb3.png)

### Additional instructions answers:

#### Consider how developers might extend your classes (either by inheritance or by composition) :

Inheritance:

A developer might create a subclass of CustomExecutor to add additional functionality or behavior. For example, they might create a CustomExecutorWithMetrics class that tracks metrics like the number of tasks executed, the average execution time of tasks, etc.
A developer might create a subclass of Task to add additional functionality or behavior. For example, they might create a TaskWithRetry class that retries the task a certain number of times if it fails, or a TaskWithTimeout class that cancels the task if it takes too long to execute.

Composition:

A developer might use the CustomExecutor class as a field in a new class to provide additional functionality or behavior. For example, they might create a ScheduledTaskExecutor class that schedules tasks to be executed at a certain time or frequency, using the CustomExecutor to execute the tasks.
A developer might use the Task class as a field in a new class to provide additional functionality or behavior. For example, they might create a TaskGroup class that manages a group of tasks and provides methods to add, remove, and wait for the completion of the tasks.

#### Consider how developers might use your classes, for example sorting Task instances or maintaining instances in hash-based data-structures :

Developers might use the CustomExecutor class to execute a list of tasks concurrently. They could use the submit method to submit the tasks to the CustomExecutor and use the Future object associated with each task to track the progress or results of the task.
Developers might use the getCurrentMax method of the CustomExecutor class to get the priority of the highest priority task in the queue. This could be useful, for example, to display the current status of the executor to the user or to make decisions about which tasks to submit next.

#### Describe the design and development considerations and provide techniques/patterns you employed :

* Concurrency: The CustomExecutor class uses a ThreadPoolExecutor to manage a pool of threads and execute tasks concurrently. This can help improve the performance of the application by allowing multiple tasks to be executed in parallel.
* Task priority: The Task class has a TaskType field that defines the priority of the task. The Task class also has a compareTo method that compares the priority of this task to another task, based on the TaskType. This allows the CustomExecutor to prioritize tasks based on their priority and execute the most important tasks first.
* Task execution tracking: The Task class has a Future field that is associated with the task. This allows developers to track the progress or results of the task using the Future object.
* Graceful shutdown: The CustomExecutor class has a gracefullyTerminate method that shuts down the executor service cleanly. This can be important to ensure that all tasks have completed and resources have been released properly.

Some design patterns that are used in these classes include:
* Factory pattern: The Task class has a static factory method (createTask) that creates a new Task object. This can make it easier to create new Task objects and helps to enforce the invariants of the Task class.
* Comparable interface: The Task class implements the Comparable interface, which allows tasks to be compared to each other based on their priority. This can be useful, for example, when sorting a list of tasks or inserting tasks into a sorted data structure.
* Executor pattern: The CustomExecutor class uses the Executor pattern to manage a pool of threads and execute tasks concurrently. This can help to improve the performance of the application by allowing tasks to be executed in parallel and abstracting the details of thread management away from the developer.



