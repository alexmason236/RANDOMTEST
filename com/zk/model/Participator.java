package com.zk.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "participators")
public class Participator {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@Column(name = "participatorname")
	String participatorName;
	@Column(name = "wordnumber")
	String wordNumber;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	Team belong2Team=new Team();

	public Team getBelong2Team() {
		return belong2Team;
	}

	public int getId() {
		return id;
	}

	public String getParticipatorName() {
		return participatorName;
	}

	public String getWordNumber() {
		return wordNumber;
	}

	public void setBelong2Team(Team belong2Team) {
		this.belong2Team = belong2Team;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setParticipatorName(String participatorName) {
		this.participatorName = participatorName;
	}

	public void setWordNumber(String wordNumber) {
		this.wordNumber = wordNumber;
	}

}
