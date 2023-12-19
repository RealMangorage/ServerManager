package org.mangorage.servermanager.utils;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

// Backed by an ArrayList
public class DirtyArrayList<E> implements DirtyList<E> {
    public static <E> DirtyArrayList<E> create(List<E> list) {
        return new DirtyArrayList<>(list);
    }

    private final List<E> BACKED_LIST;
    private final List<BiConsumer<List<E>, PropertyUpdate>> LISTENERS = new ArrayList<>();

    private DirtyArrayList(List<E> list) {
        this.BACKED_LIST = list;
    }

    private void markDirty() {
        markDirty(PropertyUpdate.NORMAL);
    }
    private void markDirty(PropertyUpdate propertyUpdate) {
        LISTENERS.forEach(a -> a.accept(BACKED_LIST, propertyUpdate));
    }

    private <R> R doAndThen(Function<List<E>, R> A,  Runnable runnable) {
        var result = A.apply(getOriginal());
        runnable.run();
        return result;
    }

    private <R> void doAndThenVoid(Consumer<List<E>> A,  Runnable runnable) {
        A.accept(getOriginal());
        runnable.run();
    }

    @Override
    public int size() {
        return BACKED_LIST.size();
    }

    @Override
    public boolean isEmpty() {
        return BACKED_LIST.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return BACKED_LIST.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return BACKED_LIST.iterator();
    }

    @Override
    public Object[] toArray() {
        return BACKED_LIST.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return BACKED_LIST.toArray(a);
    }

    @Override
    public boolean add(E e) {
        return doAndThen(a -> a.add(e), this::markDirty);
    }

    @Override
    public boolean remove(Object o) {
        return doAndThen(BACKED_LIST::remove, this::markDirty);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return c.containsAll(BACKED_LIST);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return doAndThen(BACKED_LIST::addAll, this::markDirty);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return doAndThen(BACKED_LIST::addAll, this::markDirty);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return doAndThen(BACKED_LIST::removeAll, this::markDirty);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return BACKED_LIST.retainAll(c);
    }

    @Override
    public void clear() {
        doAndThenVoid(List::clear, this::markDirty);
    }

    @Override
    public E get(int index) {
        return BACKED_LIST.get(index);
    }

    @Override
    public E set(int index, E element) {
        return doAndThen(a -> a.set(index, element), this::markDirty);
    }

    @Override
    public void add(int index, E element) {
        doAndThenVoid(a -> a.add(index, element), this::markDirty);
    }

    @Override
    public E remove(int index) {
        return doAndThen(a -> a.remove(index), this::markDirty);
    }

    @Override
    public int indexOf(Object o) {
        return BACKED_LIST.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return BACKED_LIST.lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        return BACKED_LIST.listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return BACKED_LIST.listIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return BACKED_LIST.subList(fromIndex, toIndex);
    }

    @Override
    public void listen(BiConsumer<List<E>, PropertyUpdate> listener) {
        LISTENERS.add(listener);
    }

    @Override
    public List<E> getOriginal() {
        return BACKED_LIST;
    }
}
