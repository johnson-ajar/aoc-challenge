package com.aoc.data.model;

public interface IData<T> extends Comparable<IData<T>> {
	public T getValue();
	
	public void setNext(IData<T> inNext);
	
	public void setPrev(IData<T> inPrev);
	
	public IData<T> addData(IData<T> inData);
	
	public IData<T> replaceData(T inData);
	
	public IData<T> next();
	
	public IData<T> prev();
	
	public void reset();
	
}
