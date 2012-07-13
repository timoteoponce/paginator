
package com.github.timoteoponce.paginator;

import java.util.List;
/**
 *
 * @param <T> 
 * @author Timoteo Ponce
 */
public class SimpleListProvider<T> implements ListProvider<T> {
    private final List<T> sourceList;

    /**
     * @param sourceList
     */
    public SimpleListProvider(final List<T> sourceList) {
        if (sourceList == null) {
            throw new IllegalArgumentException("null lists are not allowed");
        }
        this.sourceList = sourceList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.timo.paginator.ListProvider#provideList(org.timo.paginator.SegmentProvider)
     */
    @Override
    public List<T> provideList(final SegmentProvider provider) {
        Segment segment = provider.getSegment(sourceList.size());
        return truncateList(sourceList, segment.getFromIndex(), segment.getSize());
    }

    List<T> truncateList(final List<T> list, final int startingPoint, final int chunkSize) {
        int lastIndex = startingPoint + chunkSize;
        if (lastIndex > list.size() || chunkSize <= 0) {
            lastIndex = list.size();
        }
        return list.subList(startingPoint, lastIndex);
    }
}
