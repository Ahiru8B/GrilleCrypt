package com.ahiru.grillecrypt.model;

import java.util.Objects;

import jakarta.validation.constraints.Min;

public class Index implements Comparable<Index>{
	@Min(value = 0, message = "Номер строки должен быть положительным")
	private int row;
	@Min(value = 0, message = "Номер стобца должен быть положительным")
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

	@Override
	public int hashCode() {
		return Objects.hash(column, row);
	}

	@Override
	public boolean equals(Object otherObject) {
		if (this == otherObject)
			return true;
		if (otherObject == null)
			return false;
		if (getClass() != otherObject.getClass())
			return false;
		Index otherIndex = (Index) otherObject;
		return column == otherIndex.column && row == otherIndex.row;
	}

	@Override
	public String toString() {
		return "Index [row=" + row + ", column=" + column + "]";
	}

	@Override
	public int compareTo(Index otherIndex) {
		if(this.row > otherIndex.row) {
			return 1;
		} else if(this.row < otherIndex.row) {
			return -1;
		} else {
			if(this.column > otherIndex.column) {
				return 1;
			} else if(this.column < otherIndex.column) {
				return -1;
			} else {
				return 0;
			}
		}
	}
	
}
