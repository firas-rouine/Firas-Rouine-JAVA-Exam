package com.firas.TableMaster.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;





@Entity
@Table(name="tables")
public class TableMaster {
// MEMBER VARIABLES
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 2, message="guest name must be at least 2 letters")
	private String name;
	
	@NotNull(message = "Guest number must be not null")
	@Min(value = 2, message = "Guest number must be at least 1")
	@Max(value = 10, message = "Guest number must be at most 10")
	private Integer numberGuest;
	private String description;
	


	
	
	// M:1
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User poster;

    
	// M:M
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "giveUp_tables", joinColumns = @JoinColumn(name = "table_id"), inverseJoinColumns = @JoinColumn(name = "userGiveUp_id"))
	private List<User> userGiveUp;


    
	


	public List<User> getUserGiveUp() {
		return userGiveUp;
	}


	public void setUserGiveUp(List<User> userGiveUp) {
		this.userGiveUp = userGiveUp;
	}


	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;

	
	
	// EMPTY CONSTRUCTOR
	public TableMaster() {
	}
	

    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getNumberGuest() {
		return numberGuest;
	}


	public void setNumberGuest(Integer numberGuest) {
		this.numberGuest = numberGuest;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public User getPoster() {
		return poster;
	}


	public void setPoster(User poster) {
		this.poster = poster;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	
    
    
	
	
	
	
	
	

}
