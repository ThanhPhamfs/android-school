package vn.edu.tdc.newsnackview.views;

public class CoorPoint {
	float x, y;
	boolean eated = false;

	public CoorPoint(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public boolean isEated() {
		return eated;
	}

	public void setEated(boolean eated) {
		this.eated = eated;
	}
	
}
