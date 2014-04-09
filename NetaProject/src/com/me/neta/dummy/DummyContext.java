package com.me.neta.dummy;

import java.io.Serializable;
import java.util.List;

import com.me.neta.NetaGame;
import com.me.neta.Size;

public class DummyContext implements Serializable{
	



	/**
	 * 
	 */
	private static final long serialVersionUID = 6198089952455262502L;

	public Size getOrigin() {
		return origin;
	}

	public void setOrigin(Size origin) {
		this.origin = origin;
	}

	public float getZoom() {
		return zoom;
	}

	public void setZoom(float zoom) {
		this.zoom = zoom;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public List<GroupInfo> getDummies() {
		return dummies;
	}

	public List<GroupInfo> getGroups() {
		return dummies;
	}

	public void setDummies(List<GroupInfo> dummies) {
		this.dummies = dummies;
	}

	public static class GroupInfo implements Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -7978173217545219296L;
		private DummyInfo barrier;
		private List<DummyInfo> flowers ;
		public DummyInfo getBarrier() {
			return barrier;
		}
		public void setBarrier(DummyInfo barrier) {
			this.barrier = barrier;
		}
		public List<DummyInfo> getFlowers() {
			return flowers;
		}
		public void setFlowers(List<DummyInfo> flowers) {
			this.flowers = flowers;
		}
		public DummyInfo getHouse() {
			return house;
		}
		public void setHouse(DummyInfo house) {
			this.house = house;
		}
		private DummyInfo house;
	}
	
	public static class DummyInfo implements Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 5345382922976258748L;
		private String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public float getWidth() {
			return width;
		}
		public void setWidth(float width) {
			this.width = width;
		}
		public float getHeight() {
			return height;
		}
		public void setHeight(float height) {
			this.height = height;
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
		private float x ;
		private float y;
		private float width;
		private float height;
	}
	
	private List<GroupInfo> dummies;
	
	private Size origin;
	
	private float zoom;
	
	private int order;
}
