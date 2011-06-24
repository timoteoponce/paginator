/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.timo.paginator;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author timoteo
 */
public class PaginationList<T> extends ArrayList<T> {

    private int totalSize;

    public PaginationList() {
    }

    public PaginationList(final Collection<T> collection, final int totalSize) {
        super(collection);
        this.totalSize = totalSize;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }
}
