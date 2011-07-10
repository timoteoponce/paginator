/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.timo.paginator;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author Timoteo Ponce
 * @author Rory Sandoval - original implementation
 */
public class Paginator<T> {

    public static final int DEFAULT_PAGE_SIZE = 5;
    private final PaginationData paginationData;
    private final ListProvider<T> listProvider;
    private List<T> resultList;
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
        paginationData.setCurrentPage(getFirstPage());
        markAsDirty();
    }

    public void goPreviousPage() {
        paginationData.setCurrentPage(getPreviousPage());
        markAsDirty();
    }

    public void goNextPage() {
        paginationData.setCurrentPage(getNextPage());
        markAsDirty();
    }

    public void goLastPage() {
        paginationData.setCurrentPage(getLastPage());
        markAsDirty();
    }

    public void refresh() {
        this.resultList = listProvider.provideList(paginationData);
        if(resultList == null || resultList.isEmpty()){
            resultList = Collections.EMPTY_LIST;
        }
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

     public int getFirstPage() {
        return paginationData.getFirstPage();
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

    public boolean hasFirstPage() {
        return paginationData.hasFirstPage();
    }

    public boolean hasLastPage() {
        return paginationData.hasLastPage();
    }

    public boolean isEmpty() {
        return paginationData.isEmpty();
    }

    private void markAsDirty() {
        this.dirty = true;
    }
}
