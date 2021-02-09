package ru.geekbrains.ntr_ads03.library;

public interface IStack<E> {
    void push(E value);

    E pop();

    E peek();

    int size();

    boolean isEmpty();

    boolean isFull();

    void display();

}
