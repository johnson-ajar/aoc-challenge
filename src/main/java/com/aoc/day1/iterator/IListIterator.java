package com.aoc.day1.iterator;

import com.aoc.data.model.IData;

public interface IListIterator<T, D extends IData<T>> {
	public void reset();
	public D next();
	public D previous();
	public boolean hasNext();
	public boolean hasPrevious();
	public void resetData();
}
