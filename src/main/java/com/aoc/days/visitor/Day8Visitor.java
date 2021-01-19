package com.aoc.days.visitor;

import com.aoc.data.model.Instruction;
import com.aoc.data.model.InstructionData;
import com.aoc.data.structure.DataLinkedList;

public class Day8Visitor extends BaseVisitor<Instruction, InstructionData> {
	private int accum = 0;
	@Override
	public void report(InstructionData inData, DataLinkedList<Instruction, InstructionData> dataList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(InstructionData inData) {
		if(inData.getValue().getAction().equals("acc")) {
			accumulate(inData);
		}
		this.changeToNop(inData);
	}
	
	public void accumulate(InstructionData inData) {
		if(inData.getValue().getOperator().equals("+")) {
			accum  += inData.getValue().value();
		}else if (inData.getValue().getOperator().equals("-")) {
			accum -= inData.getValue().value();
		}
	}
	
	public void changeToNop(InstructionData inData) {
		if(inData.getValue().getAction().equals("jmp")) {
			inData.getValue().setAction("nop");
		} else if (inData.getValue().getAction().equals("nop")) {
			inData.getValue().setAction("jmp");
		}
	}
	@Override
	public String toString() {
		return String.format("Accumulate : %d ", accum);
	}
	
	public void reset() {
		this.accum = 0;
	}
}
