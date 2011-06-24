/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.timo.paginator;

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
        PaginationData paginationData = new PaginationData();
        Paginator<String> paginator = new Paginator<String>(paginationData, new ListProvider<String>() {

            public PaginationList<String> provideList(PaginationData parameters) {
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

    private PaginationList createNumberList(int top) {
        PaginationList<String> list = new PaginationList<String>();
        for (int i = 0; i < top; i++) {
            list.add(Integer.toString(i));
        }
        list.setTotalSize(list.size());
        return list;
    }
}
