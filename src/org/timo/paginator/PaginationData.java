/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.timo.paginator;

/**
 *
 * @author timoteo
 */
public class PaginationData {

    private int currentPage = 1;
    private int pageSize = 1;
    private int totalSize;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        if (currentPage > 0 && currentPage <= getLastPage()) {
            this.currentPage = currentPage;
        } else {
            this.currentPage = getFirstPage();
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

    public int getLowerIndex() {
        int firstResult = 0;
        if (!isEmpty() && getCurrentPage() > 1) {
            firstResult = (currentPage - 1) * pageSize;
        }
        return firstResult;
    }

    public int getFirstPage() {
        return 1;
    }

    public int getNextPage() {
        int nextPage = currentPage + 1;
        if (!hasNextPage()) {
            nextPage = -1;
        }
        return nextPage;
    }

    public int getPreviousPage() {
        int previousPage = currentPage - 1;
        if (!hasPreviousPage()) {
            previousPage = -1;
        }
        return previousPage;
    }

    public int getLastPage() {
        int lastPage = getFirstPage();
        if (!isEmpty()) {
            lastPage = (totalSize / pageSize) + (totalSize % pageSize);
        }
        return lastPage;

    }

    public boolean hasNextPage() {
        int lastPage = getLastPage();
        return !isEmpty() && currentPage < lastPage;
    }

    public boolean hasPreviousPage() {
        return !isEmpty() && currentPage > 1;
    }

    public boolean isEmpty() {
        return totalSize < 1;
    }

    public static void main(String[] args) {
        int pageSize = 1;
        int totalSize = 0;
        System.out.println("" + (((totalSize / pageSize) + (totalSize % pageSize))));
        System.out.println("" + ((int) Math.pow(4, 0)));

    }
}
