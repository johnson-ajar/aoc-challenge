package com.aoc.data.model;

import com.aoc.days.visitor.IDataVisitor;

public class IntegerData extends Data<Integer> {
	
	public IntegerData(String inData) {
		super(inData);
	}
	public IntegerData(Integer inData) {
		super(inData);
	}

	@Override
	public Integer getData(String inData) {
		return Integer.valueOf(inData);
	}
	
	@Override
	public int compare(IData<Integer> inData) {
		if (inData.getValue() == null || this.getValue() == null) {
			return 0;
		}
		return  inData.getValue() >= this.getValue() ? 1 : 0;
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.getValue());
	}
	
	@Override
	public <D extends Data<Integer>> void accept(IDataVisitor<Integer, D> visitor) {
		visitor.update((D)this);
	}
}
