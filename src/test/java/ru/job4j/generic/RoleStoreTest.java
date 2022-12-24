package ru.job4j.generic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRoleIsActor() {
    RoleStore roleStore = new RoleStore();
    Role role = new Role("1", "Actor");
    roleStore.add(role);
    Role result = roleStore.findById("1");
/*    assertThat(result.getRolename()).isEqualTo("Actor");*/
assertEquals(result.getRolename(), "Actor");
    }

    @Test
    void whenReplaceThenRoleIsDriver() {
        RoleStore roleStore = new RoleStore();
        Role role = new Role("1", "Actor");
        roleStore.add(role);
        roleStore.replace("1", new Role("1", "Driver"));
        Role result = roleStore.findById("1");
        assertThat(result.getRolename()).isEqualTo("Driver");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore roleStore = new RoleStore();
        Role role = new Role("1", "Actor");
        roleStore.add(role);
        roleStore.delete("1");
        Role result = roleStore.findById("1");
        assertThat(result).isNull();
    }
}