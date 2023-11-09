# Prod-Cons monitor
 Simple program to understand how the problem of the proucers and consumers works with monitors in java.

### How does it work? 

The basic structure is a classic: there is a shared data structure (a buffer in this case) where a producers write and consumers read.
Since we are working in a multi-thread environment and we have a share data structure we have to be delicate and guarantee that we don't go in deadlocks or something bad.
Java gives us few possibilities, we can handle this with explicit locks or we can use monitors.

#### But what is a monitor?

With _monitor_ we refer to a high-level paradigm for handling concurrent operations in a shared data structure.
We can use the keyword _synchronized_ for entire methods or for small portions of code when we want to access the shared structure.
The JVM guarantess that with this keyword all the operations will be synchronized and therefore we will have integrity of shared data.
Also we can use the three condition variables: _wait()_, _notify()_ and _notifyall()_.

*Wait*
- by the consumer: puts himself in the _Wait Set_ if the buffer is empty, so it waits that some producer produces something.
- by the producer: puts himself in the _Wait Set_ if the buffer is full, so it waits that some consumer consumes data from the buffer.

*Notify*
- the thread callig this wakes up one thread in the _Wait Set_ and puts him in the _Ready Set_ then it will be ready to get the implicit lock for the buffer with the other ready threads.

*NotifyAll* 
- the thread callig this wakes up all the thread in the _Wait Set_ and puts them in the _Ready Set_, so all of them can run for the lock and try to take it.

---

#### Buffer
The buffer is the shared data structure between the producers and the consumers, it has a maximum _capacity_ and keeps track of the items in it with the _count_ variable.

It has the following 2 methods:
- _produce_: in a _while_ checks if the buffer is full, if it si then waits. If not, puts an item in the buffer at _count_ position and wakes up a thread in the _Wait Set_ as he calls the _notify_ condition variable.
- _consume_: in a _while_ checks if the buffer is empty, if it is then waits. If not, consumes an item and calls the _notify_ for waking up a thread in the _Wait Set_.

*Note*: this methods are _synchronized_ because, as I said before, producers and consumer are operating with a shared data structure, therefore we have to handle multi-threading.

#### Producer
The producer is a thread that simply takes the shared _buffer_ and tries to write something on it, I added some sleep between the threads for simulatig a real execution.

#### Consumer
The consumer is a thread that takes the shared _buffer_ and tries to consume (read) an item put by a producer in the _buffer_.

#### Main
The _main_ class simply initializes the buffer with the initial capacity and the consumer and producer threads, passing to them the shared buffer as a parameter. Then starts the threads.

---

We have seen how monitors work in a multi-thread environment, Java gives us the possibility to handle these situations in a very simple and intuitive way, but it also has some downsides.
