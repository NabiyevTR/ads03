package ru.geekbrains.ntr_ads03.library;

public class PriorityQueueImpl<E extends Comparable<? super E>> implements IQueue<E> {

    private final E[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public PriorityQueueImpl(int maxSize) {
        this.data = (E[]) new Comparable[maxSize];
    }

    @Override //O(n)
    public boolean insert(E value) {
        if (isFull()) {
            return false;
        }

        int index;
        for (index = size() - 1; index >= 0; index--) {
            if (value.compareTo(data[index]) > 0) {
                data[index + 1] = data[index];
            } else {
                break;
            }
        }

        data[index + 1] = value;
        size++;
        return true;
    }

    @Override
    public E remove() {
        return isEmpty() ? null : data[--size];
    }

    @Override
    public E peekHead() {
        return isEmpty() ? null : data[size - 1];
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

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("HEAD -> [");

        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("] <-TAIL");
        return sb.toString();
    }

}
