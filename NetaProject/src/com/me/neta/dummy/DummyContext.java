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
		public float getZoom() {
			return zoom;
		}
		public void setZoom(float zoom) {
			this.zoom = zoom;
		}
		public Size getOrigin() {
			return origin;
		}
		public void setOrigin(Size origin) {
			this.origin = origin;
		}
		public void setHouse(DummyInfo house) {
			this.house = house;
		}
		public int getOrder() {
			return order;
		}
		public void setOrder(int order) {
			this.order = order;
		}
		private DummyInfo house;
		private float zoom;
		private Size origin;
		private int order;

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
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		private float y;
		private float width;
		private float height;
		private String type;
	}
	
	private List<GroupInfo> dummies;
	
	
	
}
