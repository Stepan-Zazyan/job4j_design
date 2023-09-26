package ru.job4j.ood.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        if (Objects.equals(childName, "")) {
            rootElements.add(new SimpleMenuItem(parentName, actionDelegate));
        } else {
            Optional<ItemInfo> parent = findItem(parentName);
            if (parentName.isEmpty()) {
                return false;
            }
            MenuItem men = parent.get().getMenuItem();
            String menuItem = parent.get().getMenuItem().getName();
            List<MenuItem> listChildren = parent.get().getMenuItem().getChildren();
            parent.get().getMenuItem().getChildren().add(new SimpleMenuItem(childName, actionDelegate));
        }
        return true;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        ItemInfo itemInfo = findItem(itemName).get();
        MenuItemInfo menuItemInfo = new MenuItemInfo(itemInfo.getMenuItem(), itemInfo.getNumber());
        return Optional.of(menuItemInfo);
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        DFSIterator dfsIterator = new DFSIterator();
        return new Iterator<MenuItemInfo>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                if (!dfsIterator.hasNext()) {
                    throw new ConcurrentModificationException();
                }
                return index < rootElements.size();
            }

            @Override
            public MenuItemInfo next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                ItemInfo itemInfo = dfsIterator.next();
                index++;
                return new MenuItemInfo(itemInfo.getMenuItem(), itemInfo.getNumber());
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
        ItemInfo itemInfo = new ItemInfo();
        for (MenuItem x : rootElements) {
            if (x.getName().equals(name)) {
                itemInfo.setNumber(x.getName());
                itemInfo.setMenuItem(x);
            }
            if (!x.getChildren().isEmpty()) {
                for (MenuItem i : x.getChildren()) {
                    if (i.getName().equals(name)) {
                        itemInfo.setNumber(i.getName());
                        itemInfo.setMenuItem(i);
                    }
                }
            }
        }
        return Optional.of(itemInfo);
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious(); ) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }

    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }

        public ItemInfo() {
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public MenuItem getMenuItem() {
            return menuItem;
        }

        public void setMenuItem(MenuItem menuItem) {
            this.menuItem = menuItem;
        }
    }

}
