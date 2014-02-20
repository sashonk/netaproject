package com.me.neta.util;

public class Position {
	public Position(){
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
		return result;
	}

	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
			return false;
		if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
			return false;
		return true;
	}

	public Position(Position copy){
		this.x = copy.x;
		this.y = copy.y;
	}
	
	public Position(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public float x;
	public float y;
}
