package mensa.api.hibernate.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tags implements Cloneable {
	private int id;
	private boolean bio;
	private boolean fish;
	private boolean pork;
	private boolean cow;
	private boolean cow_aw;
	private boolean vegan;
	private boolean veg;
	private String add;

	@Id @GeneratedValue
	private int getId() {
		return id;
	};	

	public void setId(int id) {
		this.id = id;
	}	
	
	public boolean isBio() {
		return bio;
	}

	public void setBio(boolean bio) {
		this.bio = bio;
	}

	public boolean isFish() {
		return fish;
	}

	public void setFish(boolean fish) {
		this.fish = fish;
	}

	public boolean isPork() {
		return pork;
	}

	public void setPork(boolean pork) {
		this.pork = pork;
	}

	public boolean isCow() {
		return cow;
	}

	public void setCow(boolean cow) {
		this.cow = cow;
	}

	public boolean isCow_aw() {
		return cow_aw;
	}

	public void setCow_aw(boolean cow_aw) {
		this.cow_aw = cow_aw;
	}

	public boolean isVegan() {
		return vegan;
	}

	public void setVegan(boolean vegan) {
		this.vegan = vegan;
	}

	public boolean isVeg() {
		return veg;
	}

	public void setVeg(boolean veg) {
		this.veg = veg;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	/**
	 * Perform shallow copy.
	 */
	public Tags clone() {
		// having a clone method is bad coding style? or not?
		Tags result = null;
		
		try {
			result = (Tags) super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("Internally to Tags, a CloneNotSupportedException "
					+ "was thrown when trying to clone Tags. Did you inherit from Tags?");
			e.printStackTrace();
		}
		
		return result;
	}
}