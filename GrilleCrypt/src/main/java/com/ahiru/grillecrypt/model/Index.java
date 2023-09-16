package com.ahiru.grillecrypt.model;

public class Index {
	private int row;
	private int column;
	
	public Index() {
		super();
	}

	public Index(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
}
