package ch.swissbytes.paginator;

import java.util.EventListener;

/**
 * Fork of https://github.com/timoteoponce/paginator.
* @author Timoteo Ponce
*/
public interface PaginatorEventListener extends EventListener{
	void pageChanged(PaginationData paginationData);
}