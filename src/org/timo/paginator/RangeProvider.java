/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.timo.paginator;

/**
 *
 * @author timoteo
 */
public interface RangeProvider {

    Range getRange(int totalSize);
}
