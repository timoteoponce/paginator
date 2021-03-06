/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.timoteoponce.paginator;

import org.junit.Assert;
import org.junit.Test;

import com.github.timoteoponce.paginator.PaginationData;

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
        paginationData.init();
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
        paginationData.init();
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
        paginationData.init();
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
        paginationData.init();
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
        paginationData.init();
        //
        Assert.assertEquals(3, paginationData.getCurrentPage());
        Assert.assertEquals(1, paginationData.getFirstPage());
        Assert.assertEquals(2, paginationData.getPreviousPage());
        Assert.assertEquals(4, paginationData.getNextPage());
        Assert.assertEquals(3, paginationData.getLastPage());
    }

    @Test
    public void testEvenOffsets() {
        PaginationData paginationData = new PaginationData();
        paginationData.setTotalSize(5);
        paginationData.setPageSize(2);
        paginationData.setCurrentPage(4);
        paginationData.init();
        //
        Assert.assertEquals(3, paginationData.getCurrentPage());
        Assert.assertEquals(1, paginationData.getFirstPage());
        Assert.assertEquals(2, paginationData.getPreviousPage());
        Assert.assertEquals(4, paginationData.getNextPage());
        Assert.assertEquals(3, paginationData.getLastPage());
    }

    @Test
    public void testOddChecks() {
        PaginationData paginationData = new PaginationData();
        paginationData.setTotalSize(5);
        paginationData.setPageSize(2);
        paginationData.setCurrentPage(2);
        paginationData.init();
        //
        Assert.assertEquals(true, paginationData.hasNextPage());
        Assert.assertEquals(true, paginationData.hasPreviousPage());
        Assert.assertEquals(false, paginationData.isEmpty());

        paginationData.setCurrentPage(1);
        paginationData.init();
        Assert.assertEquals(true, paginationData.hasNextPage());
        Assert.assertEquals(false, paginationData.hasPreviousPage());
    }

    @Test
    public void testEvenChecks() {
        PaginationData paginationData = new PaginationData();
        paginationData.setTotalSize(6);
        paginationData.setPageSize(1);
        paginationData.setCurrentPage(2);
        paginationData.init();
        //
        Assert.assertEquals(true, paginationData.hasNextPage());
        Assert.assertEquals(true, paginationData.hasPreviousPage());
        Assert.assertEquals(false, paginationData.isEmpty());

        paginationData.setCurrentPage(1);
        paginationData.init();
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
        paginationData.init();
        Assert.assertEquals(0, paginationData.getFromIndex());
        Assert.assertEquals(2, paginationData.getToIndex());

        paginationData.setCurrentPage(2);
        paginationData.init();
        Assert.assertEquals(2, paginationData.getFromIndex());
        Assert.assertEquals(4, paginationData.getToIndex());

        paginationData.setCurrentPage(3);
        paginationData.init();
        Assert.assertEquals(4, paginationData.getFromIndex());
        Assert.assertEquals(6, paginationData.getToIndex());

        paginationData.setCurrentPage(4);
        paginationData.init();
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
        paginationData.init();
        Assert.assertEquals(0, paginationData.getFromIndex());
        Assert.assertEquals(5, paginationData.getToIndex());

        paginationData.setCurrentPage(2);
        paginationData.init();
        Assert.assertEquals(5, paginationData.getFromIndex());
        Assert.assertEquals(10, paginationData.getToIndex());

        paginationData.setCurrentPage(3);
        paginationData.init();
        Assert.assertEquals(10, paginationData.getFromIndex());
        Assert.assertEquals(15, paginationData.getToIndex());

        paginationData.setCurrentPage(4);
        paginationData.init();
        Assert.assertEquals(15, paginationData.getFromIndex());
        Assert.assertEquals(20, paginationData.getToIndex());
    }

     @Test
    public void testDynamicPageSizeChange() {
        PaginationData paginationData = new PaginationData();
        paginationData.setTotalSize(7);
        paginationData.setPageSize(5);
        paginationData.setCurrentPage(2);
        
        paginationData.setPageSize(10);
        paginationData.init();
        Assert.assertEquals(1, paginationData.getCurrentPage());
    }
}
