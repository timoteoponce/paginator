/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.timo.paginator;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author timoteo
 */
public class PaginationDataTest {

    @Test
    public void testWithEmptyResults() {
        PaginationData paginationData = new PaginationData();
        paginationData.setTotalSize(0);
        paginationData.setCurrentPage(5);
        //
        Assert.assertEquals(1, paginationData.getCurrentPage());
        Assert.assertEquals(1, paginationData.getFirstPage());
        Assert.assertEquals(0, paginationData.getPreviousPage());
        Assert.assertEquals(2, paginationData.getNextPage());
        Assert.assertEquals(1, paginationData.getLastPage());
    }

    @Test
    public void testEmptyChecks() {
        PaginationData paginationData = new PaginationData();
        paginationData.setTotalSize(0);
        //
        Assert.assertEquals(false, paginationData.hasNextPage());
        Assert.assertEquals(false, paginationData.hasPreviousPage());
        Assert.assertEquals(true, paginationData.isEmpty());
    }

    @Test
    public void testWithOddResults() {
        PaginationData paginationData = new PaginationData();
        paginationData.setTotalSize(5);
        paginationData.setPageSize(2);
        paginationData.setCurrentPage(2);
        //
        Assert.assertEquals(2, paginationData.getCurrentPage());
        Assert.assertEquals(1, paginationData.getFirstPage());
        Assert.assertEquals(1, paginationData.getPreviousPage());
        Assert.assertEquals(3, paginationData.getNextPage());
        Assert.assertEquals(3, paginationData.getLastPage());
    }

    @Test
    public void testWithEvenResults() {
        PaginationData paginationData = new PaginationData();
        paginationData.setTotalSize(6);
        paginationData.setPageSize(2);
        paginationData.setCurrentPage(2);
        //
        Assert.assertEquals(2, paginationData.getCurrentPage());
        Assert.assertEquals(1, paginationData.getFirstPage());
        Assert.assertEquals(1, paginationData.getPreviousPage());
        Assert.assertEquals(3, paginationData.getNextPage());
        Assert.assertEquals(3, paginationData.getLastPage());
    }

    @Test
    public void testOddOffsets() {
        PaginationData paginationData = new PaginationData();
        paginationData.setTotalSize(5);
        paginationData.setPageSize(2);
        paginationData.setCurrentPage(4);
        //
        Assert.assertEquals(1, paginationData.getCurrentPage());
        Assert.assertEquals(1, paginationData.getFirstPage());
        Assert.assertEquals(0, paginationData.getPreviousPage());
        Assert.assertEquals(2, paginationData.getNextPage());
        Assert.assertEquals(3, paginationData.getLastPage());
    }

    @Test
    public void testEvenOffsets() {
        PaginationData paginationData = new PaginationData();
        paginationData.setTotalSize(5);
        paginationData.setPageSize(2);
        paginationData.setCurrentPage(4);
        //
        Assert.assertEquals(1, paginationData.getCurrentPage());
        Assert.assertEquals(1, paginationData.getFirstPage());
        Assert.assertEquals(0, paginationData.getPreviousPage());
        Assert.assertEquals(2, paginationData.getNextPage());
        Assert.assertEquals(3, paginationData.getLastPage());
    }

    @Test
    public void testOddChecks() {
        PaginationData paginationData = new PaginationData();
        paginationData.setTotalSize(5);
        paginationData.setPageSize(2);
        paginationData.setCurrentPage(2);
        //
        Assert.assertEquals(true, paginationData.hasNextPage());
        Assert.assertEquals(true, paginationData.hasPreviousPage());
        Assert.assertEquals(false, paginationData.isEmpty());

        paginationData.setCurrentPage(1);
        Assert.assertEquals(true, paginationData.hasNextPage());
        Assert.assertEquals(false, paginationData.hasPreviousPage());
    }

    @Test
    public void testEvenChecks() {
        PaginationData paginationData = new PaginationData();
        paginationData.setTotalSize(6);
        paginationData.setCurrentPage(2);
        //
        Assert.assertEquals(true, paginationData.hasNextPage());
        Assert.assertEquals(true, paginationData.hasPreviousPage());
        Assert.assertEquals(false, paginationData.isEmpty());

        paginationData.setCurrentPage(1);
        Assert.assertEquals(true, paginationData.hasNextPage());
        Assert.assertEquals(false, paginationData.hasPreviousPage());
    }

    @Test
    public void testOddLowerIndex() {
        PaginationData paginationData = new PaginationData();
        paginationData.setTotalSize(7);
        paginationData.setPageSize(2);
        /*
        0 1
        1 2 
        2 3 - page 2
        3 4 
        4 5 - page 3
        5 6 
        6 7 - page 4
         */
        paginationData.setCurrentPage(1);
        Assert.assertEquals(0, paginationData.getFromIndex());
        Assert.assertEquals(2, paginationData.getToIndex());

        paginationData.setCurrentPage(2);
        Assert.assertEquals(2, paginationData.getFromIndex());
        Assert.assertEquals(4, paginationData.getToIndex());

        paginationData.setCurrentPage(3);
        Assert.assertEquals(4, paginationData.getFromIndex());
        Assert.assertEquals(6, paginationData.getToIndex());

        paginationData.setCurrentPage(4);
        Assert.assertEquals(6, paginationData.getFromIndex());
        Assert.assertEquals(7, paginationData.getToIndex());
    }

    @Test
    public void testEvenLowerIndex() {
        PaginationData paginationData = new PaginationData();
        paginationData.setTotalSize(28);
        paginationData.setPageSize(5);
        /*
        0 1
        1 2
        2 3
        3 4
        4 5 - page 2
        5 6
        6 7*/
        paginationData.setCurrentPage(1);
        Assert.assertEquals(0, paginationData.getFromIndex());
        Assert.assertEquals(5, paginationData.getToIndex());

        paginationData.setCurrentPage(2);
        Assert.assertEquals(5, paginationData.getFromIndex());
        Assert.assertEquals(10, paginationData.getToIndex());

        paginationData.setCurrentPage(3);
        Assert.assertEquals(10, paginationData.getFromIndex());
        Assert.assertEquals(15, paginationData.getToIndex());

        paginationData.setCurrentPage(4);
        Assert.assertEquals(15, paginationData.getFromIndex());
        Assert.assertEquals(20, paginationData.getToIndex());
    }
}
