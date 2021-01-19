package com.aoc.day1.iterator;

import com.aoc.data.model.IData;
import com.aoc.data.structure.DataLinkedList;

public class DataIterator<T, D extends IData<T>> implements IListIterator<T,D> {
	
	private final DataLinkedList<T,D> dataList;
	
	int currPos = 0;
	private IData<T> currentData;
	public DataIterator(DataLinkedList<T, D> inList) {
		this.dataList = inList;
		this.currentData = inList.head();
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		if(currentData == null || currentData.next() == null) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean hasPrevious() {
		if(currentData == null || currentData.prev() == this.dataList.head()) {
			return false;
		}
		return true;
	}

	//If there is no next data return the null.
	@Override
	public D next() {
		if(hasNext()) {
			IData<T> nextData = currentData.next();
			currentData = nextData;
			currPos+=1;
			return (D)currentData;
		}
		return null;
	}
	
	@Override
	public D previous() {
		if(hasPrevious()) {
			IData<T> prevData = currentData.prev();
			currentData = prevData;
			currPos-=1;
			return (D)currentData;
		}
		return null;
	}

	//reset the position to the start of the link.
	@Override
	public void reset() {
		this.currentData = dataList.head();
		this.currPos = 0;
	}
		
	@Override
	public void resetData() {
		while(this.hasNext()) {
			IData<T> d = this.next();
			d.reset();
		}
		reset();
	}

	
}
