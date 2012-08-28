package com.github.timoteoponce.paginator;

import java.util.EventListener;

/**
* @author Timoteo Ponce
*/
public interface PaginatorEventListener extends EventListener{
	void pageChanged();
}