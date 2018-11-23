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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "unions")
public class Union {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	@Column(name="unionname")
	String unionName;
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.PERSIST,mappedBy="belong2Union")
	Set<Team> teams=new HashSet<>();
	public int getId() {
		return id;
	}
	public Set<Team> getTeams() {
		return teams;
	}
	public String getUnionName() {
		return unionName;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}
	public void setUnionName(String unionName) {
		this.unionName = unionName;
	}
}
