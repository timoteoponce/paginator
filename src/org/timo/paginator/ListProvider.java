/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.timo.paginator;

import java.util.List;

/**
 * Client-provider component in charge of providing a segmented
 * list using SegmentProvider's information.
 * @param <T>  working object type
 * @author Timoteo Ponce
 */
public interface ListProvider<T> {

    /**
     * A list provider must provide a list/collection segment within
     * given range.
     * Usage:
     * <pre>
     * List<String> myList = createTestList();
     * Segment segment = segmentProvider.getSegment(myList.size());
     * return myList.subList(segment.getFromIndex(), segment.getToIndex());
     * </pre>
     * @param rangeProvider descriptor component that provides information about the current segment [from-to]
     * @return list segment(can be null)
     */
    List<T> provideList(SegmentProvider rangeProvider);
}
