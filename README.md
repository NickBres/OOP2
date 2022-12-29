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


