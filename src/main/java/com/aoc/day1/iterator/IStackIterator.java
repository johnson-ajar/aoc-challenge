package com.aoc.day1.iterator;

import com.aoc.data.model.IData;

public interface IStackIterator<T, D extends IData<T>> {
	public D pop();
	public D push(D inData);
	public D peek();
}
