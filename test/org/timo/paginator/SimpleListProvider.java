/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.timo.paginator;

/**
 *
 * @author timoteo
 */
public class SimpleListProvider<T> implements ListProvider<T> {

    private PaginationList<T> sourceList;

    public SimpleListProvider(PaginationList<T> sourceList) {
        this.sourceList = sourceList;
    }

    public PaginationList<T> getSourceList() {
        return sourceList;
    }

    public void setSourceList(PaginationList<T> sourceList) {
        this.sourceList = sourceList;
    }

    public PaginationList<T> provideList(RangeProvider rangeProvider) {
        PaginationList<T> result = new PaginationList<T>();
        result.setTotalSize(sourceList.getTotalSize());
        Range range = rangeProvider.getRange(sourceList.getTotalSize());
        result.addAll(sourceList.subList(range.getFromIndex(), range.getToIndex()));
        return result;
    }

}
