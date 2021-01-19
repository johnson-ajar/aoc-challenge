package com.aoc.days.visitor;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Map;

import com.aoc.data.model.Road;
import com.aoc.data.model.RoadData;
import com.aoc.data.structure.DataLinkedList;

public class Day3Visitor implements IDataVisitor<Road, RoadData> {
	private PropertyChangeSupport support;
	
	private int roadNo = 0;
	private int STEP_RIGHT =3;
	
	private int COUNT_DOWN = 1;
	
	private RoadData currentRoad;
	
	private String activeRoad = "";
	
	private Map<String, Integer> roadPosMap = new HashMap<String,Integer>();
	private Map<String, Integer> treeCount = new HashMap<String, Integer>();
	private Map<String, Integer> count_down = new HashMap<String, Integer>();
	public Day3Visitor() {
		support = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		support.addPropertyChangeListener(pcl);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		support.removePropertyChangeListener(pcl);
	}
	
	private String getKey(int STEP_RIGHT, int STEP_DOWN) {
		return String.format("Right:%d-Down:%d", STEP_RIGHT, STEP_DOWN);
	}
		
	private void step3down1(int STEP_RIGHT, int STEP_DOWN, RoadData inData, DataLinkedList<Road, RoadData> dataList, boolean enablePrint) {
		activeRoad = getKey(STEP_RIGHT, STEP_DOWN);
		if (inData.getIndex()> 0 && this.count_down.get(activeRoad)< STEP_DOWN) {
			this.count_down.put(activeRoad, count_down.get(activeRoad)+1);
			return;
		}
		
		try {
			currentRoad = inData.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		if (inData.getIndex()==0) {
			roadPosMap.put(activeRoad, STEP_RIGHT+1);
			count_down.put(activeRoad, 1);
			treeCount.put(activeRoad, 0);
			if (enablePrint) {
				String report = String.format("[%d, %d %d]", currentRoad.getIndex()+1, roadPosMap.get(activeRoad), this.treeCount.get(activeRoad));
				System.out.println(report+" "+currentRoad.getValue());
			}
		}else if (inData.getIndex()>0) {
			int posCount = 0;
			if (roadPosMap.get(activeRoad) >= currentRoad.getValue().size()) {
				inData.accept(this);
			}
			int roadPos = roadPosMap.get(activeRoad)-1;
			if(posCount == 0 && currentRoad.getValue().get(roadPos).equals("#")) {
				currentRoad.getValue().set(roadPos, "X");
				treeCount.put(activeRoad, treeCount.get(activeRoad)+1);
				support.firePropertyChange(activeRoad, treeCount, treeCount.get(activeRoad));
			} else {
				currentRoad.getValue().set(roadPos, "0");
			}
			
			if(enablePrint) {
				String report = String.format("[%d, %d %d]", currentRoad.getIndex()+1, roadPosMap.get(activeRoad), this.treeCount.get(activeRoad));
				System.out.println(report+" "+currentRoad.getValue());
			}
			while(posCount < STEP_RIGHT) {
				posCount+=1;
				roadPosMap.put(activeRoad, roadPosMap.get(activeRoad)+1);
			}
			this.count_down.put(activeRoad, 1);
		}
	}
	@Override
	public void report(RoadData inData, DataLinkedList<Road, RoadData> dataList) {
		this.step3down1(1, 1, inData, dataList, false);
		
		this.step3down1(3, 1, inData, dataList, false);
		
		this.step3down1(5,1, inData, dataList, false);
		
		this.step3down1(7,1, inData, dataList, false);
	
		this.step3down1(1,2, inData, dataList, false);
	}

	@Override
	public void update(RoadData inData) {
		int size = currentRoad.getValue().size();
		while(size <= this.roadPosMap.get(activeRoad)) {
			//System.out.println("Visitor pattern to update the data " + currentRoad.getValue().size());
			for(int i = 0; i< inData.getValue().size(); i++) {
				currentRoad.getValue().add(inData.getValue().get(i));
			}
			size = currentRoad.getValue().size();
		}
	}

}
