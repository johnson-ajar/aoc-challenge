package com.aoc.days.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.aoc.data.model.Navigation;
import com.aoc.data.model.NavigationData;
import com.aoc.data.structure.DataLinkedList;

public class Day12Visitor extends BaseVisitor<Navigation, NavigationData>{
	private String orientation;
	private double rotate=90.0;
	private double X=0.0;
	private double Y = 0.0;
	private Map<Integer, String> orientationMap = new HashMap<Integer, String>();
	private WayPoint wayPoint = new WayPoint();
	private Map<String, Double> position = new HashMap<String, Double>();
	private boolean use_waypoint = true;
	public Day12Visitor() {
		orientation = "E";
		position.put("N", 0.0);
		position.put("S", 0.0);
		position.put("E", 0.0);
		position.put("W", 0.0);
		
		orientationMap.put(0, "N");
		orientationMap.put(1, "E");
		orientationMap.put(2, "S");
		orientationMap.put(3, "W");
	}
	
	@Override
	public void report(NavigationData inData, DataLinkedList<Navigation, NavigationData> dataList) {
		inData.accept(this);
	}

	@Override
	public void update(NavigationData inData) {
		Navigation nav = inData.getValue();
		switch(nav.getAction()) {
			case "N": case "S": case "E": case "W":
				if(!this.use_waypoint) { 
					this.move(nav.getAction(), nav); 
				} else {
					this.wayPoint.setUnits(nav.getAction(), (int)nav.getStep());
				}
			break;
			case "R": case "L":
				System.out.println(String.format("Current orientation to %3.2f %s", this.rotate, this.orientation));
				if(!this.use_waypoint) {
					this.checkOrientation(nav);
				}else {
					this.wayPoint.rotate((int)(nav.getStep()/90.0), nav.getAction());
				}
				System.out.println(String.format("Changed orientation to %3.2f %s", this.rotate, this.orientation));
			break;
			case "F":
				this.move(nav);
			break;
		}
		printNavigation();
	}
	
	private void printNavigation() {
		for(String pos : position.keySet()) {
			System.out.print(String.format("%s %3.2f ", pos, position.get(pos)));
		}
		System.out.println(this.wayPoint);
		System.out.println(String.format("Manhattan distance %3.2f + %3.2f = %3.2f", X
				,Y, Math.abs(X)+ Math.abs(Y)));
	}
	
	
	private void checkOrientation(Navigation nav) {
		int currLoc = orientationMap.entrySet().stream().filter(o -> o.getValue() == this.orientation).iterator().next().getKey();
		int num_rot = (int)(nav.getStep()/90.0);
		int count = 0;
		Set<Integer> indices = orientationMap.keySet();
		
		while(count < num_rot) {
			currLoc = nav.getAction().equals("R")?currLoc + 1:currLoc-1;
			
			if(currLoc >= orientationMap.size()) {
				currLoc = 0;
			}
			if(currLoc < 0) {
				currLoc = orientationMap.size()-1;
			}
			this.orientation = orientationMap.get(currLoc);
			count+=1;
		}
	}
	
	private void move(String orientation, Navigation nav) {
		int units = this.use_waypoint ? this.wayPoint.getUnits(orientation):1;
		
			if( units > 0 && orientation.equals("S")) {
				this.moveTo("N", "S", nav);
				this.Y = this.Y - units*nav.getStep();
			}
			
			if(units > 0 && orientation.equals("N")) {
				this.moveTo("S", "N", nav);
				this.Y = this.Y + units*nav.getStep();
			} 
		
		
		
			if(units > 0 && orientation.equals("E")) {
				this.moveTo("W", "E", nav);
				this.X = this.X + units*nav.getStep();
			} 
			if(units > 0 && orientation.equals("W")) {
				this.moveTo("E", "W", nav);
				this.X = this.X - units*nav.getStep();
			}
		
	}
	
	private void moveWaypoint(Navigation nav) {
		for(String direction : this.wayPoint.getDirections()) {
			move(direction, nav);
		}
	}
	
	private void moveTo(String from, String to, Navigation nav) {
		System.out.println(String.format("%s %d", to, this.wayPoint.getUnits(to)));
		double movStep = this.position.get(from) - (this.use_waypoint ? this.wayPoint.getUnits(to):1)* nav.getStep();
		if(movStep < 0.0) {
			this.position.put(from, 0.0);
			this.position.put(to, this.position.get(to)+Math.abs(movStep));
		} else {
			this.position.put(from, movStep);
			this.position.put(to, this.position.get(to)+nav.getStep());
		} 
	}
	
	private void move(Navigation nav) {
		if(this.use_waypoint) {
			this.moveWaypoint(nav);
			return;
		}
		this.move(this.orientation, nav);
	}
	
	
	public static void main(String[] args) {
		WayPoint wp = new WayPoint();
		wp.rotate(3, "L");
	}
	
	
	static class WayPoint {
		private String [] directions = {"N","E","S","W"};
		private Integer[] units = {1,10, 0,0};
		
		public WayPoint() {}
		public void rotate(int rotcount, String dir) {
			int count = 0;
			List<String> rPosition = new ArrayList<String>();
			for(String pos : this.directions) {
				rPosition.add(pos);
			}
			while(count < rotcount) {
				
				if(dir.equals("R")) {
					String sPos = this.directions[0];
					for(int i=0; i < this.directions.length-1; i++) {
						rPosition.set(i, this.directions[i+1]);
					}
					rPosition.set(directions.length-1, sPos);
				} else {
					String sPos = directions[directions.length-1];
					for(int i=directions.length-1; i >= 1; i--) {
						rPosition.set(i, directions[i-1]);
					}
					rPosition.set(0, sPos);
				}
				count+=1;
				directions = rPosition.toArray(new String[0]);
				printPosition();
			}
		}
		
		private String printPosition() {
			StringBuilder builder = new StringBuilder();
			builder.append("[");
			for(String pos : this.directions) {
				builder.append(String.format("%s,", pos));
			}
			builder.append("]");
			return builder.toString();
		}
		
		private String printUnits() {
			StringBuilder builder = new StringBuilder();
			builder.append("[");
			for(Integer unit : this.units) {
				builder.append(String.format("%d,", unit));
			}
			builder.append("]");
			return builder.toString();
		}
		
		private int directionIndex(String direction) {
			for(int i=0; i< this.directions.length; i++) {
				if(this.directions[i].equals(direction)) {
					return i;
				}
			}
			return 0;
		}
		public int getUnits(String direction) {
			return units[directionIndex(direction)];
		}
		
		public void setUnits(String direction, Integer unit) {
			units[directionIndex(direction)] += unit;
		}
		
		public String[] getDirections() {
			return this.directions;
		}
		
		@Override
		public String toString() {
			return String.format("%s  %s", printPosition(), printUnits());
		}
	}
	
	
}
