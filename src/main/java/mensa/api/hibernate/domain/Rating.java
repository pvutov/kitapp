package mensa.api.hibernate.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import mensa.api.hibernate.domain.serializers.RatingSerializer;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@Entity
@JsonSerialize(using = RatingSerializer.class)
public class Rating {
	private int id;
	private int value;
	private int userid;

	@Id @GeneratedValue
	private int getId() {
		return id;
	};	
	public void setId(int id) {
		this.id = id;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
}
