/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.timo.paginator;

import java.util.List;

/**
 *
 * @author timoteo
 */
public class SimpleListProvider<T> implements ListProvider<T> {

    private final PaginationList<T> sourceList;

    public SimpleListProvider(PaginationList<T> sourceList) {
        this.sourceList = sourceList;
    }

    public PaginationList<T> provideList(PaginationData parameters) {
        PaginationList<T> result = new PaginationList<T>();
        result.setTotalSize(sourceList.getTotalSize());
        result.addAll(truncateList(sourceList, parameters.getLowerIndex(), parameters.getPageSize()));
        return result;
    }

    <T> List<T> truncateList(List<T> sourceList, final int from, final int chunkSize) {
        int fromIndex = from;
        if (fromIndex > sourceList.size()) {
            fromIndex = 0;
        }
        int toIndex = fromIndex + chunkSize;
        if (toIndex > sourceList.size()) {
            toIndex = sourceList.size();
        }
        return sourceList.subList(fromIndex, toIndex);
    }
}
