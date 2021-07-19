import java.util.*;

/**
 * @author ：xbb
 * @date ：Created in 2021/7/20 12:00 上午
 * @description：Lyko-49-QueueBasedOnLinkedList
 * @modifiedBy：
 * @version:
 */
public class LinkedListQueue<E> implements Queue<E> {

    private LinkedList<E> list;

    public LinkedListQueue() {
        list = new LinkedList<>();
    }

    public void enQueue(E e) {
        list.addFirst(e);
    }

    public E deQueue() {
        return list.removeLast();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E poll() {
        return deQueue();
    }

    @Override
    public E element() {
        return null;
    }

    @Override
    public E peek() {
        // 查看队首元素
        return list.getLast();
    }

    @Override
    public String toString() {
        return "LinkedListQueue{" +
                "list=" + list +
                '}';
    }
}
