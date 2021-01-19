package com.aoc.days.operators;

import com.aoc.data.model.Instruction;
import com.aoc.data.model.InstructionData;
import com.aoc.data.structure.DataLinkedList;
import com.aoc.day1.iterator.IListIterator;
import com.aoc.days.visitor.Day8Visitor;

public class Day8Operator {
	DataLinkedList<Instruction, InstructionData> instruction;
	Day8Visitor visitor = new Day8Visitor();
	public Day8Operator(DataLinkedList<Instruction, InstructionData> instruction) {
		this.instruction = instruction;
	}
	
	public void operate() {
		IListIterator<Instruction, InstructionData> iterator = instruction.iterator();
		//applyOperator1();
		applyOperator2();
		System.out.println(visitor);
	}
	
	private InstructionData applyOperator(IListIterator<Instruction, InstructionData> iterator, InstructionData instructData) {
		if(instructData == null) {
			iterator.reset();
			return (InstructionData)iterator.next();
		}
		Instruction instruction = instructData.getValue();
		// System.out.println(String.format("%d %s", instructData.getIndex(),instructData));
		if(instructData.noCalls(1)) {
			return instructData;
		}
		switch(instruction.getAction()) {
			case "nop":
				instructData.executionCount();
				instructData = (InstructionData) iterator.next();
				
			break;
			case "acc":
				instructData.executionCount();
				instructData.accept(visitor);
				instructData = (InstructionData)iterator.next();
			break;
			case "jmp":
				instructData.executionCount();
				for(int i=0; i<instruction.value(); i++) {
					if(instruction.getOperator().equals("+")) {
						instructData = (InstructionData)iterator.next();
					}else if(instruction.getOperator().equals("-")) {
						instructData = (InstructionData)iterator.previous();
					}
				}
				// System.out.println(String.format("Jumped to : %d %s", instructData.getIndex(),instructData.getValue()));
			break;
		}
		return applyOperator(iterator, instructData);
	}
	
	
	private void applyOperator2() {
		IListIterator<Instruction, InstructionData> iterator1 = instruction.iterator();
		
		while(iterator1.hasNext()) {
			InstructionData instruction1 = (InstructionData)iterator1.next();
			if(instruction1.getValue().getAction().equals("jmp") || instruction1.getValue().getAction().equals("nop")) {
				//Change jmp to nop or nop to jmp
				visitor.reset();
				instruction1.accept(visitor);
				
				//Get a new iterator
				IListIterator<Instruction, InstructionData> iterator2 = instruction.iterator();
				iterator2.resetData();
				System.out.println(instruction1);
				//Apply the instruction and get the final instruction.
				InstructionData finalInstruction = applyOperator(iterator2, (InstructionData)iterator2.next());
				System.out.println(String.format("finalInstruction %s %s ", finalInstruction, visitor) );
				if(finalInstruction.getIndex() == 0) {
					return ;
				}
				iterator2.reset();
				
				//Change back the change to jmp and nop operator.
				instruction1.accept(visitor);
				
			}
			
		}
	}
	
	private void applyOperator1() {
		IListIterator<Instruction, InstructionData> iterator = instruction.iterator();
		applyOperator(iterator, (InstructionData)iterator.next());
	}
}
