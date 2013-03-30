/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.timoteoponce.paginator;

/**
 *
 * @param <T>
 * @author timoteo
 */
public class Iterator<T> {

    private final Paginator<T> paginator;
    private final PaginationData paginationData;
    private T selected;
    private int currentIndex = 0;

    public Iterator(final Paginator<T> paginator, PaginationData paginationData) {
        this.paginator = paginator;
        this.paginationData = paginationData;
    }

    public T goNext() {        
        if (paginationData.isInPagesRange(currentIndex)) {
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
        if (!paginator.isEmpty()) {            
            this.selected = paginator.getItem(currentIndex);
        }
        return selected;
    }
}
