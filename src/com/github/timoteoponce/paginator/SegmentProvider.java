/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.timoteoponce.paginator;

/**
 * Segment-provider interface used for describing a list segment to be retrieved.
 * @author Timoteo Ponce
 */
public interface SegmentProvider {

    /**
     * Provides a segment to be retrieved.
     * @param totalSize
     * @return current list segment
     */
    Segment getSegment(int totalSize);
}
