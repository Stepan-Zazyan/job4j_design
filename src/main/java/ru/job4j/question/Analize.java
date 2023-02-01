package ru.job4j.question;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        ArrayList<String> curName = new ArrayList<>();
        ArrayList<String> prevName = new ArrayList<>();
        ArrayList<Integer> curId = new ArrayList<>();
        ArrayList<Integer> prevId = new ArrayList<>();
        for (User prev : previous) {
            prevName.add(prev.getName());
            prevId.add(prev.getId());
        }
        for (User user : current) {
            curName.add(user.getName());
            curId.add(user.getId());
            if (!prevId.contains(user.getId())) {
                info.setAdded(info.getAdded() + 1);
            }
            if (prevId.contains(user.getId())
                    && !Objects.equals(user.getName(),
                    prevName.get(prevId.indexOf(user.getId())))) {
                info.setChanged(info.getChanged() + 1);
            }
        }
        for (User user : previous) {
            if (!curId.contains(user.getId())) {
                info.setDeleted(info.getDeleted() + 1);
            }
        }
        return info;
    }
}
