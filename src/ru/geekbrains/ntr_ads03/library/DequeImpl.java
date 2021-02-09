package ru.geekbrains.ntr_ads03.library;

import java.util.NoSuchElementException;

public class DequeImpl<E> implements IDeque<E> {

    private static final int DEFAULT_HEAD = 0;
    private static final int DEFAULT_TAIL = -1;

    protected final E[] data;
    protected int size;

    private int tail;
    private int head;

    @SuppressWarnings("unchecked")
    public DequeImpl(int maxSize) {
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

    @Override
    public void addFirst(E obj) {
        offerFirst(obj);
    }

    @Override
    public void addLast(E obj) {
        offerLast(obj);
    }

    @Override
    public E getFirst() {
        E obj = peekFirst();
        if (obj == null) throw new NoSuchElementException();
        return obj;
    }

    @Override
    public E getLast() {
        E obj = peekLast();
        if (obj == null) throw new NoSuchElementException();
        return obj;
    }

    @Override
    public boolean offerFirst(E obj) {
        if (isFull()) {
            return false;
        }

        if (head == DEFAULT_HEAD) {
            head = data.length;
        }

        data[--head] = obj;
        size++;
        return true;
    }

    @Override
    public boolean offerLast(E obj) {
        return insert(obj);
    }

    @Override
    public E peekFirst() {
        if (isEmpty()) return null;
        return data[head];
    }

    @Override
    public E peekLast() {
        if (isEmpty()) return null;
        return data[tail == DEFAULT_TAIL ? data.length - 1 : tail];
    }

    @Override
    public E pollFirst() {
        return remove();
    }

    @Override
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }

        if (tail == DEFAULT_TAIL) {
            tail = data.length-1;
        }

        E removedValue = data[tail];
        data[tail--] = null;
        size--;
        return removedValue;
    }

    @Override
    public E pop() {
        if (isEmpty()) throw new NoSuchElementException();
        return removeFirst();
    }

    @Override
    public void push(E element) {
        addFirst(element);
    }

    @Override
    public E removeFirst() {
        E obj = pollFirst();
        if (obj == null) throw new NoSuchElementException();
        return obj;
    }

    @Override
    public E removeLast() {
        E obj = pollLast();
        if (obj == null) throw new NoSuchElementException();
        return obj;
    }

    @Override
    public boolean removeFirstOccurrence(Object obj) {
        throw new UnsupportedOperationException("Unsupported operation.");
    }

    @Override
    public boolean removeLastOccurrence(Object obj) {
        throw new UnsupportedOperationException("Unsupported operation.");
    }


    public void display() {
        System.out.println(this);
    }

    @SuppressWarnings("DuplicatedCode")
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

