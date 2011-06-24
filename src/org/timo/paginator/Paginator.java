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

    private final PaginationData paginationData;
    private final ListProvider<T> listProvider;
    private final List<T> resultList = new ArrayList<T>();
    private boolean dirty = true;

    public Paginator(PaginationData paginationData, ListProvider<T> listProvider) {
        this.paginationData = paginationData;
        this.listProvider = listProvider;
    }

    public List<T> getList() {
        if (isDirty()) {
            refresh();
        }
        return resultList;
    }

    public PaginationData getPaginationData() {
        return paginationData;
    }

    public void goFirstPage() {
        paginationData.setCurrentPage(paginationData.getFirstPage());
        markAsDirty();
    }

    public void goPreviousPage() {
        if (paginationData.hasPreviousPage()) {
            paginationData.setCurrentPage(paginationData.getPreviousPage());
            markAsDirty();
        }
    }

    public void goNextPage() {
        if (paginationData.hasNextPage()) {
            paginationData.setCurrentPage(paginationData.getNextPage());
            markAsDirty();
        }
    }

    public void goLastPage() {
        paginationData.setCurrentPage(paginationData.getLastPage());
        markAsDirty();
    }

    public void refresh() {
        PaginationList<T> list = listProvider.provideList(paginationData);
        this.resultList.clear();
        this.resultList.addAll(list);
        this.paginationData.setTotalSize(list.getTotalSize());
        setDirty(false);
    }

    private boolean isDirty() {
        return dirty;
    }

    private void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    private void markAsDirty(){
        this.dirty = true;
    }

    public void clear(){
        markAsDirty();
        this.paginationData.clear();
    }
}
