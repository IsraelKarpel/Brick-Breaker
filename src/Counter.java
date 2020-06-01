/**
 * counter that count certain things.
 */
public class Counter {
    private int num;

    /**
    * constructor.
    * @param number the number we want the count to have.
    */
    public Counter(int number) {
        this.num = number;
    }
        /**
        * add number to current count.
        * @param number the number we want to add.
        */
        void increase(int number) {
        this.num += number;
        }
        /**
        *  subtract number from current count.
        * @param number the number we want to sub.
        */
        void decrease(int number) {
            this.num -= number;
        }
        /**
        *  get current count.
        * @return the number we count.
        */
        int getValue() {
            return this.num;
        }

        /**
        * Change the counter into other number.
        * @param number the number we want to change the counter to.
        */
        void setCounter(int number) {
            this.num = number;
        }
    }