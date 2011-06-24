/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.timo.paginator;

/**
 *
 * @author timoteo
 */
public interface ListProvider<T> {
    PaginationList<T> provideList(PaginationData parameters);
}
