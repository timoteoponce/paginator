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
        init();
        paginationData.setCurrentPage(paginationData.getFirstPage());
        markAsDirty();
    }

    public void goPreviousPage() {
        init();
        paginationData.setCurrentPage(paginationData.getPreviousPage());
        markAsDirty();
    }

    public void goNextPage() {
        init();
        paginationData.setCurrentPage(paginationData.getNextPage());
        markAsDirty();

    }

    public void goLastPage() {
        init();
        paginationData.setCurrentPage(paginationData.getLastPage());
        markAsDirty();
    }

    public void refresh() {
        List<T> list = listProvider.provideList(paginationData);
        this.resultList.clear();
        this.resultList.addAll(list);
        setDirty(false);
    }

    private boolean isDirty() {
        return dirty;
    }

    private void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    private void markAsDirty() {
        this.dirty = true;
    }

    public void clear() {
        markAsDirty();
        this.paginationData.clear();
    }

    public void init() {
        if (isDirty()) {
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
}
