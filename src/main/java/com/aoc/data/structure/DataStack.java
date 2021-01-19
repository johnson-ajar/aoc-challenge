package com.aoc.data.structure;

import com.aoc.data.model.IData;
import com.aoc.data.model.IntegerData;
import com.aoc.day1.iterator.DataIterator;
import com.aoc.day1.iterator.IListIterator;
import com.aoc.day1.iterator.IStackIterator;


public class DataStack <T, D extends IData<T>> extends DataLinkedList<T,D> implements IStackIterator<T,D>{
	
	public DataStack() {
		
	}
	
	@Override
	public IListIterator<T,D> iterator() {
		return new DataIterator<T,D>(this);
	}
	
	@Override
	public D pop() {
		D topData = this.last;
		this.last = (D)this.last.prev();
		this.size-=1;
		return topData;
	}

	@Override
	public D push(D inData) {
		if(this.head.next() == null) {
			inData.setPrev(this.head);
			this.size+=1;
			this.head.setNext(inData);
			this.last = inData;
			return (D)this.last;
		}
		inData.setPrev(this.last);
		this.last.setNext(inData);
		this.last = inData;
		this.size+=1;
		return (D)this.last;
	}
	
	
	public static void main(String[] args) {
		DataStack<Integer, IntegerData> dataStack = new DataStack<>();
		dataStack.push(new IntegerData(10));
		dataStack.push(new IntegerData(20));
		dataStack.push(new IntegerData(30));
		dataStack.push(new IntegerData(40));
		dataStack.push(new IntegerData(50));
		
		System.out.println(dataStack.size());
		IListIterator<Integer, IntegerData> iterator = dataStack.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
		iterator.reset();
		
		while(iterator.hasNext() && !dataStack.isEmpty()) {
			System.out.println(String.format("%d %s", dataStack.size(), dataStack.pop()));
		}
		
	}

	@Override
	public D peek() {
		return this.last;
	}
	
	
}
