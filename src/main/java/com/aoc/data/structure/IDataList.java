package com.aoc.data.structure;

import com.aoc.data.model.IData;

public interface IDataList<T, D extends IData<T>> {
	public boolean isEmpty();
	
	public D head();
	
	public D last();
}
