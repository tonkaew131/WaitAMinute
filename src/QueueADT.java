public interface QueueADT<CustomerData> {
    public void Queue();
    public void enqueue(CustomerData e) throws Exception;
    public Object dequeue() throws Exception;
    public Object front() throws Exception;
    public boolean isEmpty();
    public boolean isFull();
    public void clear();
    public int length();

}
