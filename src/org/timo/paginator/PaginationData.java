/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.timo.paginator;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Timoteo Ponce
 * @author Rory Sandoval - original implementation
 */
class PaginationData implements RangeProvider {

    private static final Logger log = Logger.getLogger(PaginationData.class.getName());
    private int currentPage = 1;
    private int pageSize = Paginator.DEFAULT_PAGE_SIZE;
    private int totalSize;
    private int fromIndex;
    private int toIndex;
    private int lastPage;

    protected void init() {
        if (totalSize < 0) {
            totalSize = 0;
        }
        //
        if (pageSize < 1) {
            pageSize = Paginator.DEFAULT_PAGE_SIZE;
        }
        //
        lastPage = getFirstPage();
        if (!isEmpty()) {
            lastPage = (totalSize / pageSize);
            if ((totalSize % pageSize) > 0) {
                lastPage++;
            }
        }
        //
        if (currentPage > lastPage) {
            currentPage = lastPage;
        }
        if (!(currentPage > 0 && currentPage <= lastPage)) {
            currentPage = 1;
        }
        //
        if (!isEmpty() && currentPage > 1) {
            fromIndex = (currentPage - 1) * pageSize;

            if ((fromIndex + 1) > totalSize) {
                fromIndex = totalSize - (pageSize - pageSize % totalSize);
            }
        } else {
            fromIndex = 0;
        }
        //
        toIndex = getFromIndex() + pageSize;
        if (toIndex > totalSize) {
            toIndex = totalSize;
        }
        log.log(Level.FINE, "Calculated : " + toString());
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public Range getRange(int totalSize) {
        setTotalSize(totalSize);
        init();
        return new Range(getFromIndex(), getToIndex());
    }

    public int getFromIndex() {
        return fromIndex;
    }

    public int getToIndex() {
        return toIndex;
    }

    public int getFirstPage() {
        return 1;
    }

    public int getNextPage() {
        return currentPage + 1;
    }

    public int getPreviousPage() {
        return currentPage - 1;
    }

    public int getLastPage() {
        return lastPage;
    }

    public boolean hasNextPage() {
        return !isEmpty() && currentPage < getLastPage();
    }

    public boolean hasPreviousPage() {
        return currentPage > 1;
    }

    public boolean hasFirstPage() {
        return currentPage == 1;
    }

    public boolean hasLastPage() {
        return currentPage == getLastPage();
    }

    public boolean isEmpty() {
        return totalSize < 1;
    }

    public void clear() {
        currentPage = 1;
        pageSize = Paginator.DEFAULT_PAGE_SIZE;
        totalSize = 0;
    }

    @Override
    public String toString() {
        return "PaginationData{" + "currentPage=" + currentPage + ",pageSize=" + pageSize + ",totalSize=" + totalSize + ",fromIndex=" + fromIndex + ",toIndex=" + toIndex + ",lastPage=" + lastPage + '}';
    }
}
