package com.me.neta;

public class Size {
	public Size(float w, float h){
		width = w;
		height = h;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(height);
		result = prime * result + Float.floatToIntBits(width);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Size other = (Size) obj;
		if (Float.floatToIntBits(height) != Float.floatToIntBits(other.height))
			return false;
		if (Float.floatToIntBits(width) != Float.floatToIntBits(other.width))
			return false;
		return true;
	}

	public float width;
	public float height;
}
