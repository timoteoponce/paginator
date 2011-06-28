/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.timo.paginator;

/**
 *
 * @author timoteo
 */
class PaginationData implements RangeProvider {

    private int currentPage = 1;
    private int pageSize = Paginator.DEFAULT_PAGE_SIZE;
    private int totalSize;

    public int getCurrentPage() {
        int lastPage = getLastPage();
        if (currentPage > lastPage) {
            this.currentPage = lastPage;
        }
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        if (currentPage > 0 && currentPage <= getLastPage()) {
            this.currentPage = currentPage;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        if (totalSize > -1) {
            this.totalSize = totalSize;
        }
    }

    public Range getRange(int totalSize) {
        setTotalSize(totalSize);
        return new Range(getFromIndex(), getToIndex());
    }

    public int getFromIndex() {
        int firstResult = 0;
        if (!isEmpty() && currentPage > 1) {
            firstResult = (currentPage - 1) * pageSize;

            if ((firstResult + 1) > totalSize) {
                firstResult = totalSize - (pageSize - pageSize % totalSize);
            }
        }
        return firstResult;
    }

    public int getToIndex() {
        int toIndex = getFromIndex() + pageSize;
        if (toIndex > totalSize) {
            toIndex = totalSize;
        }
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
        int lastPage = getFirstPage();
        if (!isEmpty()) {
            lastPage = (totalSize / pageSize);
            if ((totalSize % pageSize) > 0) {
                lastPage++;
            }
        }
        return lastPage;

    }

    public boolean hasNextPage() {
        return !isEmpty() && currentPage < getLastPage();
    }

    public boolean hasPreviousPage() {
        return !isEmpty() && currentPage > 1;
    }

    public boolean isEmpty() {
        return totalSize < 1;
    }

    public void clear() {
        currentPage = 1;
        pageSize = Paginator.DEFAULT_PAGE_SIZE;
        totalSize = 0;
    }
}
