package com.zk.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "teams")
public class Team {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	@Column(name="teamname")
	String teanName;
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.PERSIST)
	Union belong2Union;
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.PERSIST,mappedBy="belong2Team")
	Set<Participator> participators=new HashSet<>();
	public Union getBelong2Union() {
		return belong2Union;
	}
	public int getId() {
		return id;
	}
	public Set<Participator> getParticipators() {
		return participators;
	}
	public String getTeanName() {
		return teanName;
	}
	public void setBelong2Union(Union belong2Union) {
		this.belong2Union = belong2Union;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setParticipators(Set<Participator> participators) {
		this.participators = participators;
	}
	public void setTeanName(String teanName) {
		this.teanName = teanName;
	}
}
