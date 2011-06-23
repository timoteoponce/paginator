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
public class List<T> extends ArrayList<T> {

    private Long totalSize;

    public List() {
    }

    public List(final Collection<T> collection, final Long totalSize) {
        super(collection);
        this.totalSize = totalSize;
    }

    public Long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }
}
