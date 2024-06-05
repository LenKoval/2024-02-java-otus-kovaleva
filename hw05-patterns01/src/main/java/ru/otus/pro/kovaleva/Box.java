package ru.otus.pro.kovaleva;

import lombok.Getter;

import java.util.*;

@Getter
public final class Box implements Iterable<String> {
    private final List<String> one;
    private final List<String> two;
    private final List<String> three;
    private final List<String> four;

    public Box() {
        this.one = new ArrayList<>();
        this.two = new ArrayList<>();
        this.three = new ArrayList<>();
        this.four = new ArrayList<>();
    }

    @Override
    public Iterator<String> iterator() {
        return new BoxIterator();
    }

    public class BoxIterator implements Iterator<String> {
        private int index = 0;
        private int current = 1;
        private Queue<List<String>> queue = new LinkedList<>();

        public BoxIterator() {
            queue.add(one);
            queue.add(two);
            queue.add(three);
            queue.add(four);
        }

        private String getElements() {
            if (current == 1) {
                return one.get(index++);
            } else if (current == 2) {
                return two.get(index++);
            } else if (current == 3) {
                return three.get(index++);
            } else if (current == 4) {
                return four.get(index++);
            } else {
                return null;
            }
        }

        @Override
        public boolean hasNext() {
            while (current <= 4) {
                if (index < queue.peek().size()) {
                    return true;
                } else {
                    current++;
                    index = 0;
                }
            }
            return false;
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return getElements();
        }
    }
}
