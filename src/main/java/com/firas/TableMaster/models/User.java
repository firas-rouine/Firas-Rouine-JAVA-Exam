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
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	// MEMBER VARIABLES
		 	@Id
		    @GeneratedValue(strategy = GenerationType.IDENTITY)
		    private Long id;
		 	@Pattern(regexp = "^[a-zA-Z]*$", message = "Username must contain only letters")
		    @NotBlank(message="Username is required!")
		    @Size(min=3, message="Username must be at least 3 characters")
		    private String userName;
		    
		 	@NotBlank(message="Email is required!")
		    @Email(message="Please enter a valid email!")
		    private String email;
		    
		    @NotBlank(message="Password is required!")
		    @Size(min=8, message="Password must be  at least 8 characters")
		    private String password;
		    
		    @Transient
		    @NotBlank(message="Confirm Password is required!")
		    @Size(min=8, message="Confirm Password must be at least 8 characters")
		    private String confirm;
		    
		    
		    
		    @Column(updatable=false)
		    @DateTimeFormat(pattern="yyyy-MM-dd")
			private Date createdAt;
			
		    @DateTimeFormat(pattern="yyyy-MM-dd")
			private Date updatedAt;
		    
		    
		    @OneToMany(mappedBy="poster", fetch=FetchType.LAZY)
		    List<TableMaster> TableMastersCreated;
		    
			// M:M
			@ManyToMany(fetch = FetchType.LAZY)
			@JoinTable(name = "giveUp_tables", joinColumns = @JoinColumn(name = "userGiveUp_id"), inverseJoinColumns = @JoinColumn(name = "table_id"))
			private List<TableMaster> tableGiveUp;
		    
		    
		    
//			----- methods ---
		    // other getters and setters removed for brevity
		    @PrePersist
		    protected void onCreate(){
		        this.createdAt = new Date();
		    }
		    @PreUpdate
		    protected void onUpdate(){
		        this.updatedAt = new Date();
		    }
		    
		    
		    // EMPTY CONSTRUCTOR
		    public User () {}
			public Long getId() {
				return id;
			}
			public void setId(Long id) {
				this.id = id;
			}
			public String getUserName() {
				return userName;
			}
			public void setUserName(String userName) {
				this.userName = userName;
			}
			public String getEmail() {
				return email;
			}
			public void setEmail(String email) {
				this.email = email;
			}
			public String getPassword() {
				return password;
			}
			public void setPassword(String password) {
				this.password = password;
			}
			public String getConfirm() {
				return confirm;
			}
			public void setConfirm(String confirm) {
				this.confirm = confirm;
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
			public List<TableMaster> getTableMastersCreated() {
				return TableMastersCreated;
			}
			public void setTableMastersCreated(List<TableMaster> tableMastersCreated) {
				TableMastersCreated = tableMastersCreated;
			}
			public List<TableMaster> getTableGiveUp() {
				return tableGiveUp;
			}
			public void setTableGiveUp(List<TableMaster> tableGiveUp) {
				this.tableGiveUp = tableGiveUp;
			}


		    
		    
		    
		    

}
