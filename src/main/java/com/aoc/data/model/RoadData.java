package com.aoc.data.model;

import com.aoc.days.visitor.IDataVisitor;

public class RoadData extends Data<Road> implements Cloneable {
	private int index;
	public RoadData(int index, String inData) {
		super(inData);
		this.index = index;
	}
	
	public RoadData(int index, Road road) {
		super(road);
		this.index = index;
	}

	@Override
	protected Road getData(String inData) {
		Road road = new Road();
		for(char c : inData.toCharArray()) {
			road.add(String.valueOf(c));
		}
		return road;
	}

	@Override
	public int compare(IData<Road> inValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <D extends Data<Road>> void accept(IDataVisitor<Road, D> iDataVisitor) {
		iDataVisitor.update((D)this);
	}
	
	public int getIndex() {
		return this.index;
	}
	
	@Override
	public RoadData clone() throws CloneNotSupportedException {
		return new RoadData(this.index, (Road)this.getValue().clone());
	}
}
