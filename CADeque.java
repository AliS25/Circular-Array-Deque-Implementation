//Ali Sbeih 2/22/2021

public class CADeque<E> implements SimpleDeque<E> {

    // The default capacity of the list.
    public static final int DEFAULT_CAPACITY = 16;


    //The current capacity of the list.
    private int capacity;

    //number of elements contained in the list
    private int size = 0;

    //front index
    private int front = 0;

    //back index
    private int back = 0;

    //The contents of the list.
    private Object[] contents;


    //Create a CADeque with the default capacity
    public CADeque() {
        this(DEFAULT_CAPACITY);
    }

    // Create a CADeque with chosen initial capacity
    public CADeque(int initialCapacity) {
        this.capacity = initialCapacity;
        contents = new Object[capacity];
    }

    @Override
    //return the size
    public int size() {
        return size;
    }

    @Override
    //Determine if the queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    //Add a new element to the front of the deque.
    public void addFirst(E x) {

        // check if we need to increase the capacity before inserting
        // the element
        if (size == capacity) {
            increaseCapacity();
        }


        setAddFront();

        ++size;

        // insert x in the front by setting contents[front] to x
        contents[front] = x;
    }


    //     Remove and return the element at the front of the deque, if the
//      deque is not empty. If the deque is empty, an EmptyStackException is thrown.
    @Override
    @SuppressWarnings("unchecked")
    public E removeFirst() {


        // get the item currently at index front
        E item = (E) contents[front];

        setRemoveFront();
        --size;

        return item;
    }


    //      Return the element currently at the front of the deque, or
//      null if the deque is empty. The state of the deque
//      is unchanged after this operation.
    @Override
    @SuppressWarnings("unchecked")
    public E peekFirst() {

        return (E) contents[front];
    }

    // Add a new element x to back of the deque.
    @Override
    public void addLast(E x) {

        // check if we need to increase the capacity before inserting
        // the element
        if (size == capacity) {
            increaseCapacity();
        }


        setAddBack();
        ++size;

        // insert x in the back by setting contents[back] to x
        contents[back] = x;


    }

    //    Remove and return the element at the back of the deque, if the
//    deque is not empty. If the deque is empty, an EmptyStackException
//    is thrown
    @Override
    @SuppressWarnings("unchecked")
    public E removeLast() {



        // get the item currently at index back
        E item = (E) contents[back];

        setRemoveBack();
        --size;

        return item;
    }

    //    Return the element currently at the back of the deque, or
//     null if the deque is empty. The state of the deque
//     is unchanged after this operation
    @Override
    @SuppressWarnings("unchecked")
    public E peekLast() {



        return (E) contents[back];
    }


    //Increase the capacity of the CADeque by copying the elements in contents into a larger
    //array and updating capacity to the size of the new array
    private void increaseCapacity() {

        // create a new array with larger capacity
        Object[] bigContents = new Object[2 * capacity];

        // copy contents to bigContents
        for (int i = 0; i < capacity; ++i) {
            //if BACK is n-1 go back to 0 and add what's inside
            if (back == capacity) {
                back = 0;
                bigContents[i] = contents[back];
                back++;
            }
            //else add what is inside the back
            else {
                bigContents[i] = contents[back];
                back++;
            }
        }


            // set contents to refer to the new array
            contents = bigContents;

            //update front
            front=capacity-1;
            //update back
        back=0;

            // update this.capacity accordingly
            capacity = 2 * capacity;

        }


    // set new front when adding
    private void setAddFront() {

        int sum=back+size;
        if(sum<capacity)front=sum;
        else front=sum-capacity;
    }

    // set new front when removing
    private void setRemoveFront() {
        if (front == 0) front = capacity-1;
        else front--;

    }

    // set new back when adding
    private void setAddBack() {

        int difference=front-size;
        if(difference>=0) back=difference;
        else back=capacity+difference;
    }

    // set new back when removing
    private void setRemoveBack() {
        if (back == capacity-1) back = 0;
        else back++;
    }
}
