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
public class PaginatorTest {

    @Test
    public void testWithEmptyResults() {
        PaginationData paginationData = new PaginationData();
        Paginator<String> paginator = new Paginator<String>(paginationData, new ListProvider<String>() {

            public PaginationList<String> provideList(RangeProvider rangeProvider) {
                return new PaginationList<String>();
            }
        });
        Assert.assertNotNull(paginator.getList());
        Assert.assertEquals(0, paginator.getList().size());
        paginator.goFirstPage();
        paginator.goPreviousPage();
        paginator.goNextPage();
        paginator.goPreviousPage();
    }

    @Test
    public void testGoNext() {
        final PaginationList list = createNumberList(25);
        PaginationData paginationData = new PaginationData();
        paginationData.setPageSize(5);
        Paginator<String> paginator = new Paginator<String>(paginationData, new SimpleListProvider<String>(list));
        Assert.assertEquals(1, paginator.getPaginationData().getCurrentPage());
        Assert.assertEquals("0", paginator.getList().get(0));
        Assert.assertEquals("4", paginator.getList().get(4));

        paginator.goNextPage();
        Assert.assertEquals(2, paginator.getPaginationData().getCurrentPage());
        Assert.assertEquals("5", paginator.getList().get(0));
        Assert.assertEquals("9", paginator.getList().get(4));

        paginator.goNextPage();
        Assert.assertEquals(3, paginator.getPaginationData().getCurrentPage());
        Assert.assertEquals("10", paginator.getList().get(0));
        Assert.assertEquals("14", paginator.getList().get(4));

        paginator.goNextPage();
        Assert.assertEquals(4, paginator.getPaginationData().getCurrentPage());
        Assert.assertEquals("15", paginator.getList().get(0));
        Assert.assertEquals("19", paginator.getList().get(4));

        paginator.goNextPage();
        Assert.assertEquals(5, paginator.getPaginationData().getCurrentPage());
        Assert.assertEquals("20", paginator.getList().get(0));
        Assert.assertEquals("24", paginator.getList().get(4));

        paginator.goNextPage();
        Assert.assertEquals(5, paginator.getPaginationData().getCurrentPage());
        Assert.assertEquals("24", paginator.getList().get(4));

        paginator.goNextPage();
        Assert.assertEquals(5, paginator.getPaginationData().getCurrentPage());
        Assert.assertEquals("24", paginator.getList().get(4));
    }

    @Test
    public void testGoPrevious() {
        final PaginationList list = createNumberList(15);
        PaginationData paginationData = new PaginationData();
        paginationData.setPageSize(5);
        Paginator<String> paginator = new Paginator<String>(paginationData, new SimpleListProvider<String>(list));
        Assert.assertEquals(1, paginator.getPaginationData().getCurrentPage());
        Assert.assertEquals("0", paginator.getList().get(0));
        Assert.assertEquals("4", paginator.getList().get(4));

        paginator.goPreviousPage();
        Assert.assertEquals(1, paginator.getPaginationData().getCurrentPage());
        Assert.assertEquals("0", paginator.getList().get(0));
        Assert.assertEquals("4", paginator.getList().get(4));

        paginator.goNextPage();
        paginator.goPreviousPage();
        Assert.assertEquals(1, paginator.getPaginationData().getCurrentPage());
        Assert.assertEquals("0", paginator.getList().get(0));
        Assert.assertEquals("4", paginator.getList().get(4));

        paginator.goNextPage();
        paginator.goNextPage();
        paginator.goPreviousPage();
        Assert.assertEquals(2, paginator.getPaginationData().getCurrentPage());
        Assert.assertEquals("5", paginator.getList().get(0));
        Assert.assertEquals("9", paginator.getList().get(4));
    }

    @Test
    public void testGoFirst() {
        final PaginationList list = createNumberList(15);
        PaginationData paginationData = new PaginationData();
        paginationData.setPageSize(5);
        Paginator<String> paginator = new Paginator<String>(paginationData, new SimpleListProvider<String>(list));
        Assert.assertEquals(1, paginator.getPaginationData().getCurrentPage());
        Assert.assertEquals("0", paginator.getList().get(0));
        Assert.assertEquals("4", paginator.getList().get(4));

        paginator.goNextPage();
        paginator.goNextPage();
        Assert.assertEquals(3, paginator.getPaginationData().getCurrentPage());
        Assert.assertEquals("10", paginator.getList().get(0));
        Assert.assertEquals("14", paginator.getList().get(4));

        paginator.goFirstPage();
        Assert.assertEquals(1, paginator.getPaginationData().getCurrentPage());
        Assert.assertEquals("0", paginator.getList().get(0));
        Assert.assertEquals("4", paginator.getList().get(4));
    }

    @Test
    public void testGoLast() {
        final PaginationList list = createNumberList(15);
        PaginationData paginationData = new PaginationData();
        paginationData.setPageSize(5);
        Paginator<String> paginator = new Paginator<String>(paginationData, new SimpleListProvider<String>(list));

        paginator.goLastPage();
        Assert.assertEquals(3, paginator.getPaginationData().getCurrentPage());
        Assert.assertEquals("10", paginator.getList().get(0));
        Assert.assertEquals("14", paginator.getList().get(4));
    }

    @Test
    public void testRefresh() {
        final PaginationList list = createNumberList(15);
        PaginationData paginationData = new PaginationData();
        paginationData.setPageSize(5);
        SimpleListProvider<String> listProvider = new SimpleListProvider<String>(list);
        Paginator<String> paginator = new Paginator<String>(paginationData, listProvider);

        paginator.goLastPage();
        Assert.assertEquals(3, paginator.getPaginationData().getCurrentPage());
        
        listProvider.setSourceList(createNumberList(5));
        paginator.refresh();
        Assert.assertEquals(1, paginator.getPaginationData().getCurrentPage());
        
        listProvider.setSourceList(createNumberList(15));
        paginator.refresh();
        Assert.assertEquals(1, paginator.getPaginationData().getCurrentPage());
        
        paginator.goNextPage();
        paginator.goNextPage();
        Assert.assertEquals(3, paginator.getPaginationData().getCurrentPage());

        listProvider.setSourceList(createNumberList(10));
        paginator.refresh();
        Assert.assertEquals(2, paginator.getPaginationData().getCurrentPage());
    }

    @Test
    public void testDynamicGoNext() {
        final PaginationList list = createNumberList(15);
        PaginationData paginationData = new PaginationData();
        paginationData.setPageSize(5);
        SimpleListProvider<String> listProvider = new SimpleListProvider<String>(list);
        Paginator<String> paginator = new Paginator<String>(paginationData, listProvider);

        paginator.goNextPage();
        Assert.assertEquals(2, paginator.getPaginationData().getCurrentPage());

        listProvider.setSourceList(createNumberList(5));
        paginator.goNextPage();
        Assert.assertEquals(1, paginator.getPaginationData().getCurrentPage());

        listProvider.setSourceList(createNumberList(15));
        paginator.goNextPage();
        Assert.assertEquals(2, paginator.getPaginationData().getCurrentPage());

        listProvider.setSourceList(createNumberList(15));
        paginator.goNextPage();
        Assert.assertEquals(3, paginator.getPaginationData().getCurrentPage());
    }

    @Test
    public void testDynamicGoPrevious() {
        final PaginationList list = createNumberList(15);
        PaginationData paginationData = new PaginationData();
        paginationData.setPageSize(5);
        SimpleListProvider<String> listProvider = new SimpleListProvider<String>(list);
        Paginator<String> paginator = new Paginator<String>(paginationData, listProvider);

        paginator.goNextPage();
        paginator.goPreviousPage();
        Assert.assertEquals(1, paginator.getPaginationData().getCurrentPage());

        listProvider.setSourceList(createNumberList(5));
        paginator.goPreviousPage();
        Assert.assertEquals(1, paginator.getPaginationData().getCurrentPage());

        listProvider.setSourceList(createNumberList(15));
        paginator.goNextPage();
        paginator.goNextPage();

        listProvider.setSourceList(createNumberList(5));
        paginator.goPreviousPage();
        Assert.assertEquals(1, paginator.getPaginationData().getCurrentPage());
    }

    @Test
    public void testDynamicGoLast() {
        final PaginationList list = createNumberList(15);
        PaginationData paginationData = new PaginationData();
        paginationData.setPageSize(5);
        SimpleListProvider<String> listProvider = new SimpleListProvider<String>(list);
        Paginator<String> paginator = new Paginator<String>(paginationData, listProvider);

        paginator.goLastPage();
        Assert.assertEquals(3, paginator.getPaginationData().getCurrentPage());

        listProvider.setSourceList(createNumberList(5));
        paginator.goLastPage();
        Assert.assertEquals(1, paginator.getPaginationData().getCurrentPage());

        listProvider.setSourceList(createNumberList(25));
        paginator.goLastPage();
        Assert.assertEquals(5, paginator.getPaginationData().getCurrentPage());
    }

    private PaginationList createNumberList(int top) {
        PaginationList<String> list = new PaginationList<String>();
        for (int i = 0; i < top; i++) {
            list.add(Integer.toString(i));
        }
        list.setTotalSize(list.size());
        return list;
    }
}
