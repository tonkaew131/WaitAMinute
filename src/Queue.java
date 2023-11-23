public class Queue implements QueueADT {
    private int total;
    private Object s[];
    int front;//,front1,front2;
    int rear;//,rear1,rear2;
    int count;

    public Queue() {
        Queue();
    }

    public void Queue() {
        front = -1;
        rear = -1;
        total = 100;
        s = new Object[total];
    }

    public void enqueue(Object e) throws Exception {
        if (isFull()) {
            throw new Exception("Queue is full");
        } else {
            rear++;
            s[rear] = e;
            count++;
        }
    }

    public Object dequeue() throws Exception {
        if (isEmpty()) {
            throw new Exception("Queue is empty");
        } else {
            front++;
            count--;
            return s[front];
        }
    }

    public Object front() throws Exception {
        if (isEmpty()) {
            throw new Exception("Queue is empty ");
        }

        return s[front + 1];
    }

    //ช่วงล่างนี้คือ เอาไว้เช็ค
    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == total;
    }

    public void clear() {
        front = rear = -1;
    }

    public int length() {
        return count;
    }
}

