/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.timo.paginator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author timoteo
 */
public class SimpleListProvider<T> implements ListProvider<T> {

    private List<T> sourceList;

    public SimpleListProvider(List<T> sourceList) {
        this.sourceList = sourceList;
    }

    public List<T> getSourceList() {
        return sourceList;
    }

    public void setSourceList(List<T> sourceList) {
        this.sourceList = sourceList;
    }

    public List<T> provideList(RangeProvider rangeProvider) {
        List<T> result = new ArrayList<T>();
        Range range = rangeProvider.getRange(sourceList.size());
        result.addAll(sourceList.subList(range.getFromIndex(), range.getToIndex()));
        return result;
    }

}
