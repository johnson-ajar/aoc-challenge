package com.aoc.data.structure;

import com.aoc.data.model.Data;
import com.aoc.data.model.IData;
import com.aoc.data.model.IntegerData;
import com.aoc.day1.iterator.DataIterator;
import com.aoc.day1.iterator.IListIterator;
import com.aoc.days.visitor.IDataVisitor;

public class DataLinkedList<T, D extends IData<T>> implements IDataList<T,D>{
	protected D head;
	protected D last;
	protected int size = 0;
	protected final IListIterator<T,D> iterator;
	public DataLinkedList() {
	
		this.head = (D)new Data<T>("") {
			@Override
			public T getData(String inData) {
				return null;
			}

			@Override
			public int compare(IData<T> inValue) {
				return 0;
			}

			@Override
			public <D extends Data<T>> void accept(IDataVisitor<T, D> iDataVisitor) {
				
			}

			@Override
			public IData<T> replaceData(T inData) {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
		this.last = head;
		
		this.iterator = new DataIterator<T,D>(this);
	}
	
	public void addData(D inData, IDataVisitor<T,D> inVisitor) {
		last = (D)last.addData(inData);
		size = size+1;
		if (inVisitor != null) {
			inVisitor.report(inData, this);
		}
	}
	
	public int size() {
		return this.size;
	}
	
	@Override
	public D head() {
		return this.head;
	}
	
	@Override
	public D last() {
		return this.last;
	}
	
	public IListIterator<T, D> iterator() {
		return new DataIterator<T,D>(this);
	}
	
	public void reset() {
		iterator.reset();
	}
	
	public IData<T> get(int index) {
		this.checkIndexOutOfBound(index);
		IData<T> data = null;
		for(int i=0; i< index; i++) {
			if(iterator.hasNext()) {
				data = iterator.next();
			}
		}
		return data;
	}
	
	public IData<T> set(int index, IData<T> inData) {
		IData<T> data = this.get(index);
		if(data != null) {
			data.replaceData(inData.getValue());
		}
		return data;
	}
	
	private void checkIndexOutOfBound(int index) {
		if(index > this.size()) {
			throw new IndexOutOfBoundsException(String.format("The size of list is %d and provided index is ", this.size(), index));
		}
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		while(this.iterator.hasNext()) {
			buffer.append(iterator.next()+"\n");
		}
		return buffer.toString();
	}
	
	public static void main(String[] args) {
		DataLinkedList<Integer, IntegerData> dataList = new DataLinkedList<>();
		dataList.addData(new IntegerData("2"), null);
		dataList.addData(new IntegerData("1"), null);
		dataList.addData(new IntegerData("4"), null);
		dataList.addData(new IntegerData("5"), null);
		
		IListIterator<Integer, IntegerData> iterator = dataList.iterator();
		while(iterator.hasNext()) {
			IData<Integer> d = iterator.next();
			d = iterator.next();
			System.out.println(d.getValue());
			if(iterator.hasPrevious()) {
				IData<Integer> dp = iterator.previous();
				System.out.println(dp);
			}
		}
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}
	
}
