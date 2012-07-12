/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.timoteoponce.paginator;

import java.util.List;
import java.util.logging.Logger;

import com.github.timoteoponce.paginator.ListProvider;
import com.github.timoteoponce.paginator.Segment;
import com.github.timoteoponce.paginator.SegmentProvider;

/**
 *
 * @author timoteo
 */
public class TestListProvider<T> implements ListProvider<T> {

    private List<T> sourceList;

    private int requestsCount;

    public TestListProvider(List<T> sourceList) {
        this.sourceList = sourceList;
    }

    public List<T> getSourceList() {
        return sourceList;
    }

    public void setSourceList(List<T> sourceList) {
        this.sourceList = sourceList;
    }

    public List<T> provideList(SegmentProvider rangeProvider) {
        Segment segment = rangeProvider.getSegment(sourceList.size());
        requestsCount++;
        Logger.getLogger(TestListProvider.class.getName()).info("RequestsCount : "+requestsCount);
        return sourceList.subList(segment.getFromIndex(), segment.getToIndex());
    }

}
