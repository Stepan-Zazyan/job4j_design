package ru.job4j.collection;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] a) {
         /*SimpleArrayList<Integer> list = new SimpleArrayList(10);
        list.add(0);
        list.add(1);
        list.add(2);
        boolean rez = list.get(0).equals(2);
        System.out.println(list.remove(1));
        System.out.println(list.size());

        for (Integer i : list) {
            System.out.println(i);
        }
        main("m");


        Stream.of(1, 1, 2, 2).collect(
                Collectors.toMap(
                        e -> e,
                        e -> e * e,
                        (existing, replacement) -> existing
                )
        );

        SimpleLinkedList<Integer> link = new SimpleLinkedList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        System.out.println(list.remove(1));
        System.out.println(list.size());
       Collection<String> col = new ArrayList<>();
        col.add("a");
        col.add("b");
        col.add("c");
        Iterator it = col.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }*/

        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.pop();

        System.out.println(stack.pop());

    }

    public static void main(String args) {
        System.out.println("Method2");
    }

}
