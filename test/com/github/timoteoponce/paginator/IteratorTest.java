/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.timoteoponce.paginator;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

import ch.swissbytes.paginator.Iterator;
import ch.swissbytes.paginator.Paginator;

/**
 *
 * @author timoteo
 */
public class IteratorTest {

    @Test
    public void testGoNext() {
        final List<String> list = createNumberList(25);
        Paginator<String> paginator = new Paginator<String>(new TestListProvider<String>(list), 5);
        Iterator<String> iterator = paginator.iterator();

        Assert.assertEquals(iterator.getSelected(), "0");
        Assert.assertEquals(iterator.goNext(), "1");
        Assert.assertEquals(iterator.goNext(), "2");
        Assert.assertEquals(iterator.goNext(), "3");
        Assert.assertEquals(iterator.goNext(), "4");
        Assert.assertEquals(iterator.goNext(), "4");
        Assert.assertEquals(iterator.getSelected(), "4");
    }

    @Test
    public void testGoPrevious() {
        final List<String> list = createNumberList(25);
        Paginator<String> paginator = new Paginator<String>(new TestListProvider<String>(list), 5);
        Iterator<String> iterator = paginator.iterator();

        Assert.assertEquals(iterator.getSelected(), "0");
        Assert.assertEquals(iterator.goPrevious(), "0");
        Assert.assertEquals(iterator.goNext(), "1");
        Assert.assertEquals(iterator.goNext(), "2");
        Assert.assertEquals(iterator.goNext(), "3");
        Assert.assertEquals(iterator.goPrevious(), "2");
        Assert.assertEquals(iterator.getSelected(), "2");
        Assert.assertEquals(iterator.goNext(), "3");
        Assert.assertEquals(iterator.goNext(), "4");
        Assert.assertEquals(iterator.goNext(), "4");
        Assert.assertEquals(iterator.goPrevious(), "3");
        Assert.assertEquals(iterator.getSelected(), "3");
    }

    private List<String> createNumberList(int top) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < top; i++) {
            list.add(Integer.toString(i));
        }
        return list;
    }
}
