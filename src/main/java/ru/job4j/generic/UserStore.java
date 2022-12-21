package ru.job4j.generic;

public class UserStore<T extends Base> implements Store<User> {

    private final Store<User> store = new MemStore<>();

    @Override
    public void add(User model) {
        store.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        if (findById(id) == null) {
            return false;
        }
        store.replace(id, model);
        return true;
    }

    @Override
    public boolean delete(String id) {
        if (findById(id) == null) {
            return false;
        }
        store.delete(id);
        return true;
    }

    @Override
    public User findById(String id) {
        return store.findById(id);
    }
}