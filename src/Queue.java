import java.util.NoSuchElementException;

public class Queue implements Cloneable {

    private float[] data; // The array that holds the items.
    private int manyItems; // The number of elements in the queue.
    private int front; // The index of the front item.
    private int rear; // The index of the rear item

    public Queue() {
        final int INITIAL_CAPACITY = 10;
        manyItems = 0;
        data = new float[INITIAL_CAPACITY];
    }

    public Queue(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Invalid Capacity. " + "\nYou entered: " + initialCapacity);
        }
        manyItems = 0;
        data = new float[initialCapacity];
    }

    public void add(float item) throws OutOfMemoryError {
        if (manyItems == data.length) {
            ensureCapacity((manyItems * 2) + 1);
        }
        if (manyItems == 0) {
            front = 0;
            rear = 0;
        } else {
            rear = nextIndex(rear);
        }
        data[rear] = item;
        manyItems++; // Indicates that there's now an element in the array.
    }

    private int nextIndex(int i) {
        if (i == data.length - 1) {
            return 0;
        } else {
            return ++i;
        }
    }

    public float remove() throws NoSuchElementException {
        float answer;

        if (manyItems == 0) {
            throw new NoSuchElementException("Queue Underflow.");
        }
        answer = data[front];
        data[front] = 0;
        front = nextIndex(front);
        manyItems--;
        return answer;
    }

    public void ensureCapacity(int minimumCapacity) throws OutOfMemoryError {
        float[] biggerArray;
        int n1, n2;
        if (data.length >= minimumCapacity) {
        } else if (manyItems == 0) {
            data = new float[minimumCapacity];
        } else if (manyItems > 0 && front <= rear) {
            biggerArray = new float[minimumCapacity];
            System.arraycopy(data, front, biggerArray, front, manyItems);
            data = biggerArray;
        } else {
            biggerArray = new float[minimumCapacity];
            n1 = data.length - front;
            n2 = rear + 1; //
            System.arraycopy(data, front, biggerArray, 0, n1);
            System.arraycopy(data, 0, biggerArray, n1, n2);
            front = 0;
            rear = manyItems - 1;
            data = biggerArray;
        }
    }

    public int indexOf(float element) {
        int answer = -1;
        int i = 0;
        while (i < manyItems && answer == -1) {
            if (element == data[i]) {
                answer = i;
            }
            i++;
        }
        return answer;
    }

    @Override
    public Queue clone() {
        Queue clone;
        try {
            clone = (Queue) super.clone();
        } catch (CloneNotSupportedException exception) {
            throw new RuntimeException("No 'Cloneable' interface found.");
        }
        clone.data = data.clone();
        return clone;
    }

    // Write a method that prints the contents of the queue.

    public void print() {
        for (int i = 0; i < data.length; ++i) {
            System.out.println(data[i] + " ");
        }
    }

    public boolean isEmpty() {
        return manyItems == 0;
    }

    public float getRear() {
        return data[rear];
    }

    public float getFront() throws ArrayIndexOutOfBoundsException {
        return data[front];
    }

    public int getCapacity() {
        return data.length;
    }

    public int size() {
        return manyItems;
    }
}
