package com.aoc.days.datareader;

import com.aoc.data.model.Instruction;
import com.aoc.data.model.InstructionData;
import com.aoc.data.structure.DataLinkedList;
import com.aoc.days.visitor.IDataVisitor;

public class Day8DataReader extends AbstractReader<Instruction, InstructionData> {

	public Day8DataReader(DataLinkedList<Instruction, InstructionData> inData) {
		super(inData);
	}

	@Override
	public String getType() {
		return "Instruction";
	}

	@Override
	protected IDataVisitor<Instruction, InstructionData> getVisitor() {
		// TODO Auto-generated method stub
		return null;
	}

}
