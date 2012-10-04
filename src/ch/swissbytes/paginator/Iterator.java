/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.swissbytes.paginator;

/**
 * Fork of https://github.com/timoteoponce/paginator.
 * @param <T>
 * @author timoteo
 */
public class Iterator<T> {

    private final Paginator<T> paginator;
    private T selected;
    private int currentIndex = 0;

    public Iterator(final Paginator<T> paginator) {
        this.paginator = paginator;
    }

    public T goNext() {
        if (currentIndex < paginator.getPageSize() - 1) {
            currentIndex += 1;
        }
        return updateSelected();
    }

    public T goPrevious() {
        if (currentIndex > 0) {
            currentIndex -= 1;
        }
        return updateSelected();
    }

    public T getSelected() {
        return updateSelected();
    }

    private T updateSelected() {
        if (!paginator.getList().isEmpty()) {
            this.selected = paginator.getList().get(currentIndex);
        }
        return selected;

    }
}
