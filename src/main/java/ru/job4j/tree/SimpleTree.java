package ru.job4j.tree;

import java.util.*;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    List<Node<E>> children = new ArrayList<>();

    public SimpleTree(final E root) {
        this.root = new Node<>(root, children);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = true;
        if (Objects.equals(findBy(child), child)) {
            rsl = false;
        }
        if (Objects.equals(findBy(parent), parent)) {
            root.children.add(child);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
