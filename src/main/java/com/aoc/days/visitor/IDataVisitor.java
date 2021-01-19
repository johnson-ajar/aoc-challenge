package com.aoc.days.visitor;

import com.aoc.data.model.IData;
import com.aoc.data.structure.DataLinkedList;

public interface IDataVisitor<T, D extends IData<T>> {
	public void report(D inData, DataLinkedList<T, D> dataList);
	public void update(D inData);
}
