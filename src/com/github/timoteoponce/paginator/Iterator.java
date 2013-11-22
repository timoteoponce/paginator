/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.timoteoponce.paginator;

import java.util.Collection;

/**
 *
 * @param <T>
 * @author timoteo
 */
public class Iterator<T> implements java.util.Iterator<Collection<T>> {

    private final Paginator<T> paginator;

    private int nextPage = 0;

    public Iterator(final Paginator<T> paginator) {
        this.paginator = paginator;
    }

    @Override
    public boolean hasNext() {
        return paginator.hasNextPage() || nextPage == 0;
    }

    @Override
    public Collection<T> next() {
        if (nextPage == 0 || paginator.isLastPage()) {
            paginator.goFirstPage();
        }else{
            paginator.goNextPage();
        }
        nextPage++;
        return paginator.getList();
    }

    @Override
    public void remove() {
        // do nothing
    }
}
