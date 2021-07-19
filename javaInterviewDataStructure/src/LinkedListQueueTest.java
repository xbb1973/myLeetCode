import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ：xbb
 * @date ：Created in 2021/7/20 12:12 上午
 * @description：
 * @modifiedBy：
 * @version:
 */
class LinkedListQueueTest {
    public static void main(String[] args) {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        System.out.println(queue);
        queue.deQueue();
        System.out.println(queue);
        queue.deQueue();
        System.out.println(queue);

    }

}