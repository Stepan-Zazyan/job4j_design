package ru.job4j.ood.dip.example;

import java.util.HashMap;

public class SomeStore {
    /*У хранилища есть единственное поле - мапа, для хранения данных.
    С точки зрения DIP, это нарушение, потому что мы зависим от реализации, а не абстракции.
    Решение - выделение абстракции Store для хранилища и уже далее от него нужно будет реализовать SomeStore*/
    private HashMap<Integer, String> serviceDB = new HashMap<>();

}
