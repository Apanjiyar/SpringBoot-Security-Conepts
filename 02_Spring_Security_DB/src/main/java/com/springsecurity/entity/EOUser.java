package com.springsecurity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "EOUser_SEQ", allocationSize = 50, sequenceName = "EOUser_SEQ")
public class EOUser {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE) // or GenerationType.AUTO, GenerationType.SEQUENCE, GenerationType.TABLE, GenerationType.IDENTITY
	private Long primaryKey;
	
	@Column(unique = true, nullable = false)
	private String userName;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String role;
}
