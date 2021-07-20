package ru.geekbrains.ntr_ads03;

import ru.geekbrains.ntr_ads03.library.*;

public class Task3 {
    public static void main(String[] args) {
        IDeque<Integer> deque = new DequeImpl(5);

        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addFirst(5);
        deque.addFirst(6);

        deque.display();  //HEAD -> [5, 4, 3, 2, 1] <-TAIL
        System.out.println("Peek first:" + deque.peekFirst());
        System.out.println("Peek last:" + deque.peekLast());

        deque.removeLast();
        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();

        deque.display();  //HEAD -> [2] <-TAIL

        deque.addLast(3);
        deque.addLast(4);
        deque.addLast(5);

        deque.display();  //HEAD -> [2, 3, 4, 5] <-TAIL

        deque.removeLast();
        deque.removeFirst();

        deque.display();  //HEAD -> [3, 4] <-TAIL

        deque.push(3);
        deque.display();  //HEAD -> [3, 3, 4] <-TAIL

    }


}
