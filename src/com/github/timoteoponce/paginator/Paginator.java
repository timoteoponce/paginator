/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.timoteoponce.paginator;

import javax.swing.event.EventListenerList;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Core component handling pagination operations. It provides pagination
 * operations and pages information., aditionally provides an event-listener 
 * model that is triggered every time a pagination action modifies the current page (all 'go' operations).
 * 
 *
 * 
 * @param <T> working object type
 * @author Timoteo Ponce
 * @author Rory Sandoval - original implementation
 */
public class Paginator<T> implements Serializable, Iterable<Collection<T>> {

    public static final int DEFAULT_PAGE_SIZE = 5;
    private final PaginationData paginationData;
    private ListProvider<T> listProvider;
    private List<T> resultList;
    private boolean dirty = true;
    private final EventListenerList listenerList = new EventListenerList();

    public Paginator() {     
        this(DEFAULT_PAGE_SIZE);
    }

    public Paginator(final int pageSize) {     
        this.paginationData = new PaginationData();
        this.paginationData.setPageSize(pageSize);
    }

    public Paginator(final ListProvider<T> listProvider) {
        this(listProvider, 1);
    }

    public Paginator(final ListProvider<T> listProvider, final int pageSize) {
        this.paginationData = new PaginationData();
        this.listProvider = listProvider;
        this.paginationData.setPageSize(pageSize);
    }

    public Paginator(final ListProvider<T> listProvider, final PaginationData paginationData) {
        this.paginationData = paginationData;
        this.listProvider = listProvider;
    }    

    public void setListProvider(final ListProvider<T> listProvider) {
        this.listProvider = listProvider;
        this.refresh();
    }

    /**
     * Returns current page's list. If there's not result at all, it returns
     * an empty list.
     * @return current page's list, or an empty list when there's not data
     */
    public List<T> getList() {
        init();
        return resultList;
    }

    /**
     * Redirects to the first page.
     */
    public void goFirstPage() {
        init();
        paginationData.setCurrentPage(getFirstPage());
        markAsDirty();
        fireChangedEvent();
    }

    /**
     * Redirects to the previous page if available, if not
     * current page's value remains.
     */
    public void goPreviousPage() {
        init();
        paginationData.setCurrentPage(getPreviousPage());
        markAsDirty();
        fireChangedEvent();
    }

    /**
     * Redirects to the next page if available, if not
     * current page's value remains.
     */
    public void goNextPage() {
        init();
        paginationData.setCurrentPage(getNextPage());
        markAsDirty();
        fireChangedEvent();
    }

    /**
     * Redirects to the last page if available, if not
     * current page's value remains.
     */
    public void goLastPage() {
        init();
        paginationData.setCurrentPage(getLastPage());
        markAsDirty();		
        fireChangedEvent();
    }

    /**
     * Refreshes current result list.
     */
    public void refresh() {
        if(listProvider == null){
            throw new IllegalArgumentException("ListProvider must be set");
        }
        this.resultList = listProvider.provideList(paginationData);
        if (resultList == null || resultList.isEmpty()) {
            resultList = Collections.EMPTY_LIST;
        }
        this.dirty = false;
    }

    /**
     * Clears current result list. It
     * basically resets the component.
     */
    public void clear() {
        if (resultList != null) {
            this.resultList.clear();
        }
        markAsDirty();
    }

    public void clearPagination() {
        this.paginationData.clear();
        markAsDirty();
    }

    void init() {
        if (dirty) {
            refresh();
        }
    }

    /** masking PaginationData elements *
     * 
     * @return 
     */
    public int getCurrentPage() {
        init();
        return paginationData.getCurrentPage();
    }

    public void setCurrentPage(final int currentPage) {
        this.paginationData.setCurrentPage(currentPage);
    }

    public int getPageSize() {
        return paginationData.getPageSize();
    }

    public void setPageSize(final int pageSize) {
        this.paginationData.setPageSize(pageSize);
    }

    public int getTotalSize() {
        init();
        return paginationData.getTotalSize();
    }

    public int getFirstPage() {
        init();
        return paginationData.getFirstPage();
    }

    public int getNextPage() {
        init();
        return paginationData.getNextPage();
    }

    public int getPreviousPage() {
        init();
        return paginationData.getPreviousPage();
    }

    public int getLastPage() {
        init();
        return paginationData.getLastPage();
    }

    public boolean hasNextPage() {
        init();
        return paginationData.hasNextPage();
    }

    public boolean hasPreviousPage() {
        init();
        return paginationData.hasPreviousPage();
    }

    public boolean hasFirstPage() {
        init();
        return paginationData.hasFirstPage();
    }

    public boolean hasLastPage() {
        init();
        return paginationData.hasLastPage();
    }

    public boolean isEmpty() {
        init();
        return paginationData.isEmpty();
    }

    private void markAsDirty() {
        this.dirty = true;
    }

    public java.util.Iterator<Collection<T>> iterator() {
        return new Iterator(this);
    }

    // events
    public void addListener(final PaginatorEventListener listener){
        listenerList.add(PaginatorEventListener.class, listener);
    }

    public void removeListener(final PaginatorEventListener listener){
        listenerList.remove(PaginatorEventListener.class, listener);
    }

    private void fireChangedEvent(){
        PaginatorEventListener[] listeners = listenerList.getListeners(PaginatorEventListener.class);
        for (PaginatorEventListener listener : listeners) {
            listener.pageChanged();
        }
    }

    public T getItem(final int currentIndex) {        
        return getList().get(currentIndex);
    }

    public static<T> ListProvider<T> listProviderOf(final List<T> sourceList){
        return new SimpleListProvider<T>(sourceList);
    }

    public boolean isLastPage() {
        return getLastPage() == getCurrentPage();
    }


}
