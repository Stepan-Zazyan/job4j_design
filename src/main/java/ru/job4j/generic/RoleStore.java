package ru.job4j.generic;

public class RoleStore implements Store<Role> {

private final Store<Role> roleStore = new MemStore<>();
    @Override
    public void add(Role model) {
        roleStore.add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
        if (findById(id) == null) {
            return false;
        }
        roleStore.replace(id, model);
        return true;
    }

    @Override
    public boolean delete(String id) {
        if (findById(id) == null) {
            return false;
        }
        roleStore.delete(id);
        return true;
    }

    @Override
    public Role findById(String id) {
        return roleStore.findById(id);
    }
}
