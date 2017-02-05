/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.jevalab.azure.cbt;

import java.io.Serializable;
import java.util.List;

public class JsonEnglishCategory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -883459092662586609L;
	String instruction;
	List<com.jevalab.azure.cbt.Question> questions;

	public String getInstruction() {
		return this.instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public List<com.jevalab.azure.cbt.Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public JsonEnglishCategory(String instruction, List<Question> questions) {
		this.instruction = instruction;
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "JsonEnglishCategory [instruction=" + instruction
				+ ", questions=" + questions + "]";
	}
	
	
}