package com.aoc.data.model;

import com.aoc.days.visitor.IDataVisitor;

public abstract class Data<T> implements IData<T> {
	private T data;
	private IData<T> next = null;
	private IData<T> prev = null;
	
	public Data(String inData) {
		data = getData(inData);
	}
	
	public Data(T inData) {
		data = inData;
	}
	
	//Add the new data to the another data in the list.
	public IData<T> addData(IData<T> inData) {
		this.setNext(inData);
		inData.setPrev(this);
		return inData;
	}
	
	protected abstract T getData(String inData);
	
	public abstract int compare(IData<T> inValue);
	
	@Override
	public int compareTo(IData<T> inValue) {
		return compare(inValue);
	}
	
	
	public abstract <D extends Data<T>> void accept(IDataVisitor<T, D> iDataVisitor);
	
	public void setNext(IData<T> inNext) {
		this.next = inNext;
	}
	
	public void setPrev(IData<T> inPrev) {
		this.prev = inPrev;
	}
	
	public IData<T> next() {
		return this.next;
	}
	
	public IData<T> prev() {
		return this.prev;
	}
	
	public T getValue() {
		return this.data;
	}
	
	public String toString( ) {
		return data.toString();
	}
	
	public IData<T> replaceData(T inData) {
		this.data = inData;
		return this;
	}
	
	public void reset() {
		
	}
}
