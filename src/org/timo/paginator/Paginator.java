/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.timo.paginator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Timoteo Ponce
 */
public class Paginator<T> {

    public static final int DEFAULT_PAGE_SIZE = 5;
    private final PaginationData paginationData;
    private final ListProvider<T> listProvider;
    private final List<T> resultList = new ArrayList<T>();
    private boolean dirty = true;

    public Paginator(final ListProvider<T> listProvider) {
        this(listProvider, 1);
    }

    public Paginator(final ListProvider<T> listProvider, final int pageSize) {
        this.paginationData = new PaginationData();
        this.listProvider = listProvider;
        this.paginationData.setPageSize(pageSize);
    }

    public List<T> getList() {
        init();
        return resultList;
    }

    public void goFirstPage() {
        paginationData.setCurrentPage(paginationData.getFirstPage());
        markAsDirty();
        init();
    }

    public void goPreviousPage() {
        paginationData.setCurrentPage(paginationData.getPreviousPage());
        markAsDirty();
        init();
    }

    public void goNextPage() {
        paginationData.setCurrentPage(paginationData.getNextPage());
        markAsDirty();
        init();
    }

    public void goLastPage() {
        paginationData.setCurrentPage(paginationData.getLastPage());
        markAsDirty();
        init();
    }

    public void refresh() {
        this.resultList.clear();
        this.resultList.addAll(listProvider.provideList(paginationData));
        this.dirty = false;
    }

    public void clear() {
        this.resultList.clear();
        this.paginationData.clear();
    }

    public void init() {
        if (dirty) {
            refresh();
        }
    }

    /** masking PaginationData elements **/
    public int getCurrentPage() {
        return paginationData.getCurrentPage();
    }

    public void setCurrentPage(int currentPage) {
        this.paginationData.setCurrentPage(currentPage);
    }

    public int getPageSize() {
        return paginationData.getPageSize();
    }

    public void setPageSize(int pageSize) {
        this.paginationData.setPageSize(pageSize);
    }

    public int getTotalSize() {
        return paginationData.getTotalSize();
    }

    public int getNextPage() {
        return paginationData.getNextPage();
    }

    public int getPreviousPage() {
        return paginationData.getPreviousPage();
    }

    public int getLastPage() {
        return paginationData.getLastPage();
    }

    public boolean hasNextPage() {
        return paginationData.hasNextPage();
    }

    public boolean hasPreviousPage() {
        return paginationData.hasPreviousPage();
    }

    public boolean isEmpty() {
        return paginationData.isEmpty();
    }

    private void markAsDirty() {
        this.dirty = true;
    }
}
