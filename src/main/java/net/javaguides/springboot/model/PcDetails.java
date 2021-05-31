package net.javaguides.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pcDetails")
public class PcDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private long pcId;
	
	@Column(name = "Pc_name")
	private String PcName;
	
	@OneToOne
	private Employee PcUser;
	
	
	public PcDetails() {
		super();
	}
	public PcDetails(String pcName, Employee pcUser) {
		super();
		PcName = pcName;
		PcUser = pcUser;
	}
	public long getPcId() {
		return pcId;
	}
	public void setPcId(long pcId) {
		this.pcId = pcId;
	}
	public String getPcName() {
		return PcName;
	}
	public void setPcName(String pcName) {
		PcName = pcName;
	}
	public Employee getPcUser() {
		return PcUser;
	}
	public void setPcUser(Employee pcUser) {
		PcUser = pcUser;
	}
	
	
	
	

}
