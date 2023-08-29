package ru.job4j.ood.dip.example;

public class SomeClass {
    /*
        Подобная запись также является нарушением DIP,
        потому что есть прямая зависимость самого логгирования от реализации,
         в данном случае оно напрямую зависит от консольного вывода
    */
    public String simpleMethod(int a) {
        if (a > 100) {
            return "Error message: number " + a + " is more than 100";
        }
        return "The number is fine";
    }
}
