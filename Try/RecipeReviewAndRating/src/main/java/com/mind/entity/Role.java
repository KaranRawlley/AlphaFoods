package com.mind.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(name = "name")
	private UserRolesTable name;

	public Role() {
	}
	
	public Role(Integer id, UserRolesTable name) {
		this.id = id;
		this.name = name;
	}
	public Role(UserRolesTable name) {
		this.name = name;
	}

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserRolesTable getName() {
		return name;
	}

	public void setName(UserRolesTable name) {
		this.name = name;
	}
}
