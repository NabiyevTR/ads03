package ru.geekbrains.ntr_ads03.library;

public class QueueImpl<E> implements IQueue<E> {

    static final int DEFAULT_HEAD = 0;
    static final int DEFAULT_TAIL = -1;

    protected final E[] data;
    protected int size;

    int tail;
    int head;

    public QueueImpl(int maxSize) {
        this.data = (E[]) new Object[maxSize];
        this.head = DEFAULT_HEAD;
        this.tail = DEFAULT_TAIL;
    }

    @Override
    public boolean insert(E value) {
        if (isFull()) {
            return false;
        }

        if (tail == data.length - 1) {
            tail = DEFAULT_TAIL;
        }

        data[++tail] = value;
        size++;
        return true;
    }

    @Override
    public E remove() {
        if (isEmpty()) {
            return null;
        }

        if (head == data.length) {
            head = DEFAULT_HEAD;
        }

        E removedValue = data[head];
        data[head++] = null;
        size--;
        return removedValue;
    }

    @Override
    public E peekHead() {
        return data[head];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == data.length;
    }

    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("HEAD -> [");
        int index = head;
        for (int i = 0; i < size; i++) {
            if (index == data.length) {
                index = 0;
            }
            sb.append(data[index++]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("] <-TAIL");
        return sb.toString();
    }
}


