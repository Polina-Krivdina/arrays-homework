package com.edu;

import java.util.Arrays;
import java.util.Iterator;

public class DefaultCustomArrayList<E> implements CustomArrayList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public DefaultCustomArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public boolean add(E element) {
        ensureCapacity();
        elements[size++] = element;
        return true;
    }

    @Override
    public boolean remove(E element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    private void removeAt(int index) {
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (E) elements[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0;
    }

    @Override
    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @SuppressWarnings("unchecked")
            @Override
            public E next() {
                return (E) elements[cursor++];
            }
        };
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
    }
}
