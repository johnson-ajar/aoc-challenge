package com.aoc.data.structure;

import com.aoc.data.model.Data;
import com.aoc.data.model.IData;
import com.aoc.data.model.IntegerData;
import com.aoc.day1.iterator.IListIterator;
import com.aoc.days.visitor.IDataVisitor;

public class SortedDataList<T,D extends Data<T>> extends DataLinkedList<T, D> {
	
	public SortedDataList() {
		super();
	}
	
	@Override
	public void addData(D inData, IDataVisitor<T,D> inVisitor) {
		if (size() == 0) {
			super.addData(inData, inVisitor);
			return;
		}
		if(inVisitor != null) {
			inVisitor.report(inData, this);
		}
		IData<T> ldata = this.head.next();
		//Need atleast two elements to insert the new data.
		if (ldata.next() == null) {
			if(ldata.compareTo(inData) == 1) {
				ldata.addData(inData);
			} else {
				this.insertBefore(ldata, inData);
			}
			return;
		} else {
			while (ldata.next() != null) {
				//Check if the incoming data fall with between the prev and next.
				if(ldata.prev().compareTo(inData) == 1 && ldata.compareTo(inData) == 0) {
					//Insert between the prev data and current data.
					this.insertBefore(ldata, inData);
					return;
				} else if(ldata.compareTo(inData) == 1 && ldata.next().compareTo(inData) == 0) {
					//Insert between the current data and next data.
					this.insertAfter(ldata, inData);
					return;
				} else if (ldata.compareTo(inData) == 0) {
					this.insertBefore(ldata, inData);
					return;
				}
				ldata = ldata.next();
			}
		}
		
		if (ldata.next() == null) {
			super.addData(inData, inVisitor);
		}
	}
	
	private void insertBefore(IData<T> ldata, Data<T> inData) {
		inData.setNext(ldata);
		if(ldata.prev() != null) {
			inData.setPrev(ldata.prev());
			ldata.prev().setNext(inData);
		}
		ldata.setPrev(inData);
	}
	
	private void insertAfter(IData<T> ldata, IData<T> inData) {
		inData.setPrev(ldata);
		inData.setNext(ldata.next());
		ldata.next().setPrev(inData);
		ldata.setNext(inData);
	}
	
	public static void main(String[] args) {
		DataLinkedList<Integer, IntegerData> dataList = new SortedDataList<>();
		dataList.addData(new IntegerData("23"), null);
		dataList.addData(new IntegerData("3"), null);
		dataList.addData(new IntegerData("1"), null);
		dataList.addData(new IntegerData("4"), null);
		dataList.addData(new IntegerData("2"), null);
		
		dataList.addData(new IntegerData("10"), null);
		dataList.addData(new IntegerData("8"), null);
		dataList.addData(new IntegerData("15"), null);
		dataList.addData(new IntegerData("0"), null);
		dataList.addData(new IntegerData("100"), null);
		IListIterator<Integer, IntegerData> iterator = dataList.iterator();
		
		System.out.println("-------------------------------");
		while(iterator.hasNext()) {
			IData<Integer> d = iterator.next();
			System.out.println(d.getValue());
		}
	}


}
