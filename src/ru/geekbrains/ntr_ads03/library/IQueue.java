package ru.geekbrains.ntr_ads03.library;

public interface IQueue<E> {

    boolean insert(E value);

    E remove();

    E peekHead();

    int size();

    void display();

    boolean isEmpty();

    boolean isFull();

}
