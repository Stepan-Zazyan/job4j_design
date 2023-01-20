package ru.job4j.tree;

import java.util.*;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
/*      Optional<Node<E>> ch = Optional.of(new Node<>(child));
        Optional<Node<E>> par = Optional.of(new Node<>(parent));
        Optional<Node<E>> parFindBy = findBy(parent);*/
        Node<E> parFind = findBy(parent).get();
/*        Node<E> childFind = null;
        if (findBy(child).isPresent()) {
            childFind = findBy(child).get();
        }*/
       /* проверить, что в дереве существует узел со значением parent
        и при этом отсутствует узел child.
        Если эти условия выполняются,
        то в список потомков к узлу parent добавьте узел child.*/
        if ((Objects.equals(parFind.value, parent)
                && !root.children.contains(new Node<>(child)))) {
            root.children.add(new Node<>(child));
            rsl = true;
        }
        /*Если в root.children в потомках пэрента уже есть вставляемый пэрент,
        тогда этот child становится parent и к нему в его список добавить
        добавляемый child. как к элементы в root.children добавить список, т.е.
        сделать его parent,*/
        if (root.children.contains(new Node<>(parent))) {
            SimpleTree<E> pr = new SimpleTree<E>(parent);
            pr.children.add(new Node<>(child));
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
