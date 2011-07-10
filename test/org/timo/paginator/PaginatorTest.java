/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.timo.paginator;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author timoteo
 */
public class PaginatorTest {

    @Test
    public void testWithEmptyResults() {
        Paginator<String> paginator = new Paginator<String>(new ListProvider<String>() {

            public List<String> provideList(RangeProvider rangeProvider) {
                return new ArrayList<String>();
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
        final List<String> list = createNumberList(25);
        Paginator<String> paginator = new Paginator<String>(new SimpleListProvider<String>(list), 5);
        Assert.assertEquals(1, paginator.getCurrentPage());
        Assert.assertEquals("0", paginator.getList().get(0));
        Assert.assertEquals("4", paginator.getList().get(4));

        paginator.goNextPage();
        paginator.getList();
        Assert.assertEquals(2, paginator.getCurrentPage());
        Assert.assertEquals("5", paginator.getList().get(0));
        Assert.assertEquals("9", paginator.getList().get(4));

        paginator.goNextPage();
        paginator.getList();
        Assert.assertEquals(3, paginator.getCurrentPage());
        Assert.assertEquals("10", paginator.getList().get(0));
        Assert.assertEquals("14", paginator.getList().get(4));

        paginator.goNextPage();
        paginator.getList();
        Assert.assertEquals(4, paginator.getCurrentPage());
        Assert.assertEquals("15", paginator.getList().get(0));
        Assert.assertEquals("19", paginator.getList().get(4));

        paginator.goNextPage();
        paginator.getList();
        Assert.assertEquals(5, paginator.getCurrentPage());
        Assert.assertEquals("20", paginator.getList().get(0));
        Assert.assertEquals("24", paginator.getList().get(4));

        paginator.goNextPage();
        paginator.getList();
        Assert.assertEquals(5, paginator.getCurrentPage());
        Assert.assertEquals("24", paginator.getList().get(4));

        paginator.goNextPage();
        paginator.getList();
        Assert.assertEquals(5, paginator.getCurrentPage());
        Assert.assertEquals("24", paginator.getList().get(4));
    }

    @Test
    public void testGoPrevious() {
        final List<String> list = createNumberList(15);
        Paginator<String> paginator = new Paginator<String>(new SimpleListProvider<String>(list), 5);
        Assert.assertEquals(1, paginator.getCurrentPage());
        Assert.assertEquals("0", paginator.getList().get(0));
        Assert.assertEquals("4", paginator.getList().get(4));

        paginator.goPreviousPage();
        paginator.getList();
        Assert.assertEquals(1, paginator.getCurrentPage());
        Assert.assertEquals("0", paginator.getList().get(0));
        Assert.assertEquals("4", paginator.getList().get(4));

        paginator.goNextPage();
        paginator.goPreviousPage();
        paginator.getList();
        Assert.assertEquals(1, paginator.getCurrentPage());
        Assert.assertEquals("0", paginator.getList().get(0));
        Assert.assertEquals("4", paginator.getList().get(4));

        paginator.goNextPage();
        paginator.goNextPage();
        paginator.goPreviousPage();
        paginator.getList();
        Assert.assertEquals(2, paginator.getCurrentPage());
        Assert.assertEquals("5", paginator.getList().get(0));
        Assert.assertEquals("9", paginator.getList().get(4));
    }

    @Test
    public void testGoFirst() {
        final List<String> list = createNumberList(15);
        Paginator<String> paginator = new Paginator<String>(new SimpleListProvider<String>(list), 5);
        Assert.assertEquals(1, paginator.getCurrentPage());
        Assert.assertEquals("0", paginator.getList().get(0));
        Assert.assertEquals("4", paginator.getList().get(4));

        paginator.goNextPage();
        paginator.goNextPage();
        paginator.getList();
        Assert.assertEquals(3, paginator.getCurrentPage());
        Assert.assertEquals("10", paginator.getList().get(0));
        Assert.assertEquals("14", paginator.getList().get(4));

        paginator.goFirstPage();
        paginator.getList();
        Assert.assertEquals(1, paginator.getCurrentPage());
        Assert.assertEquals("0", paginator.getList().get(0));
        Assert.assertEquals("4", paginator.getList().get(4));
    }

    @Test
    public void testGoLast() {
        final List<String> list = createNumberList(15);
        Paginator<String> paginator = new Paginator<String>(new SimpleListProvider<String>(list), 5);
        paginator.refresh();

        paginator.goLastPage();
        paginator.getList();
        Assert.assertEquals(3, paginator.getCurrentPage());
        Assert.assertEquals("10", paginator.getList().get(0));
        Assert.assertEquals("14", paginator.getList().get(4));
    }

    @Test
    public void testRefresh() {
        final List<String> list = createNumberList(15);
        SimpleListProvider<String> listProvider = new SimpleListProvider<String>(list);
        Paginator<String> paginator = new Paginator<String>(listProvider, 5);
        paginator.refresh();
        
        paginator.goLastPage();
        paginator.getList();
        Assert.assertEquals(3, paginator.getCurrentPage());

        listProvider.setSourceList(createNumberList(5));
        paginator.refresh();
        Assert.assertEquals(1, paginator.getCurrentPage());

        listProvider.setSourceList(createNumberList(15));
        paginator.refresh();
        Assert.assertEquals(1, paginator.getCurrentPage());

        paginator.goNextPage();
        paginator.goNextPage();
        Assert.assertEquals(3, paginator.getCurrentPage());

        listProvider.setSourceList(createNumberList(10));
        paginator.refresh();
        Assert.assertEquals(2, paginator.getCurrentPage());
    }

    @Test
    public void testDynamicGoNext() {
        final List<String> list = createNumberList(15);
        SimpleListProvider<String> listProvider = new SimpleListProvider<String>(list);
        Paginator<String> paginator = new Paginator<String>(listProvider, 5);

        paginator.goNextPage();
        paginator.getList();
        Assert.assertEquals(2, paginator.getCurrentPage());

        listProvider.setSourceList(createNumberList(5));
        paginator.goNextPage();
        paginator.getList();
        Assert.assertEquals(1, paginator.getCurrentPage());

        listProvider.setSourceList(createNumberList(15));
        paginator.goNextPage();
        paginator.getList();
        Assert.assertEquals(2, paginator.getCurrentPage());

        listProvider.setSourceList(createNumberList(15));
        paginator.goNextPage();
        paginator.getList();
        Assert.assertEquals(3, paginator.getCurrentPage());
    }

    @Test
    public void testDynamicGoPrevious() {
        final List<String> list = createNumberList(15);
        SimpleListProvider<String> listProvider = new SimpleListProvider<String>(list);
        Paginator<String> paginator = new Paginator<String>(listProvider, 5);

        paginator.goNextPage();
        paginator.goPreviousPage();
        paginator.getList();
        Assert.assertEquals(1, paginator.getCurrentPage());

        listProvider.setSourceList(createNumberList(5));
        paginator.goPreviousPage();
        paginator.getList();
        Assert.assertEquals(1, paginator.getCurrentPage());

        listProvider.setSourceList(createNumberList(15));
        paginator.goNextPage();
        paginator.goNextPage();
        paginator.getList();

        listProvider.setSourceList(createNumberList(5));
        paginator.goPreviousPage();
        paginator.getList();
        Assert.assertEquals(1, paginator.getCurrentPage());
    }

    @Test
    public void testDynamicGoLast() {
        final List<String> list = createNumberList(15);
        SimpleListProvider<String> listProvider = new SimpleListProvider<String>(list);
        Paginator<String> paginator = new Paginator<String>(listProvider, 5);
        paginator.refresh();

        paginator.goLastPage();
        Assert.assertEquals(3, paginator.getCurrentPage());

        listProvider.setSourceList(createNumberList(5));
        paginator.refresh();
        paginator.goLastPage();        
        Assert.assertEquals(1, paginator.getCurrentPage());

        listProvider.setSourceList(createNumberList(25));
        paginator.refresh();
        paginator.goLastPage();        
        Assert.assertEquals(5, paginator.getCurrentPage());
    }

    private List<String> createNumberList(int top) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < top; i++) {
            list.add(Integer.toString(i));
        }
        return list;
    }
}
