import java.util.Random;

//Ali Sbeih 2/22/2022
//A program to measure the running time of AddFirst and AddLast operations
public class Timing {
    /** step size between tested sizes of arrays */
    public static final int STEP = 100;

    /** maximum array size to consider */
    public static final int MAX = 8_000;

    public static void main(String[] args){
        testAddFirst();
        testAddLast();
    }

    /**
     * Measure the (average) running times of addFirst for a range of instance
     * sizes of deques. Record the running times in a CSV file called
     * "add-first-times.csv"
     */
    static void testAddFirst(){
        RunTimer rt = new RunTimer();
        CSVWriter csv = new CSVWriter("add-first-times.csv");
        Random r = new Random();

        // make the first row of the csv file with appropriate
        // headings
        csv.addEntry("deque size");
        csv.addEntry("time per addFirst operation");
        csv.endLine();

        for (int size = STEP; size <= MAX; size += STEP) {
            // create a new array of correct size
            SimpleDeque<Integer> deque = new CADeque<Integer>();
//provide random values to use in operation
            for(int i=0; i<size;i++){
                // measure the elapsed time for addFirst
                rt.start();
                deque.addFirst(r.nextInt());
                rt.stop();
            }

            // add a new row with the size and the *average* time per
            // operation
            csv.addEntry(size);
            csv.addEntry(rt.getElapsedNanos() / size);
            csv.endLine();

            rt.reset();
        }
        csv.close();
    }
    /** Measure the (average) running times of addLast for a range of instance
     * sizes of deques. Record the running times in a CSV file called
     * "add-last-times.csv"
     */
    static void testAddLast(){
        RunTimer rt = new RunTimer();
        CSVWriter csv = new CSVWriter("add-last-times.csv");
        Random r = new Random();

        // make the first row of the csv file with appropriate
        // headings
        csv.addEntry("deque size");
        csv.addEntry("time per addLast operation");
        csv.endLine();

        for (int size = STEP; size <= MAX; size += STEP) {
            // create a new array of correct size
            SimpleDeque<Integer> deque = new CADeque<>();
//provide random values to use in operation
            for(int i=0; i<size;i++){
                // measure the elapsed time for addLast
                rt.start();
                deque.addLast(r.nextInt());
                rt.stop();
            }

            // add a new row with the size and the *average* time per
            // operation
            csv.addEntry(size);
            csv.addEntry(rt.getElapsedNanos() / size);
            csv.endLine();

            rt.reset();
        }
        csv.close();
    }


}
