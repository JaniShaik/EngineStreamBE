package com.ste.inventorymanagement.model;
import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Size;  

/**
 * BaseEntity class.
 * @author STE Aerospace
 *
 */
@MappedSuperclass  
public abstract class BaseEntity implements Serializable {  

	private static final long serialVersionUID = 1L;
	@Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    @Basic(optional = false)  
    @Column(name = "Id", nullable = false)  
    protected Long id;  
	
	@Column(name = "Status")
	private Boolean status;

	@Column(name = "CreatedAt", nullable = false)
//	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Size(max = 20)
	@Column(name = "CreatedBy", length = 20)
	private String createdBy;

	@Column(name = "UpdatedAt", nullable = false)
//	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@Size(max = 20)
	@Column(name = "UpdatedBy", length = 20)
	private String updatedBy;
	
	
    public BaseEntity() {
		super();
		// TODO Auto-generated constructor stub
		id = 0L;
	}

	public Long getId() {  
        return id;  
    }  
  
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

    @Override  
    public int hashCode() {  
        int hash = 0;  
        hash += (this.getId() != null ? this.getId().hashCode() : 0);  
  
        return hash;  
    }  
  
    @Override  
    public boolean equals(Object object) {  
    if (this == object)  
            return true;  
        if (object == null)  
            return false;  
        if (getClass() != object.getClass())  
            return false;  
  
        BaseEntity other = (BaseEntity) object;  
        if (this.getId() != other.getId() && (this.getId() == null || !this.id.equals(other.id))) {  
            return false;  
        }  
        return true;  
    }  
  
    @PrePersist
	protected void onCreate() {
		// FIXME: Set the created by and updated by to the currently logged in user
		updatedAt = createdAt = new java.sql.Date(System.currentTimeMillis());
		updatedBy = createdBy = "1";
		status = true;
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = new java.sql.Date(System.currentTimeMillis());
		updatedBy = "1";
	}
    @Override  
    public String toString() {  
        return this.getClass().getName() + " [ID=" + id + "]";  
    }

	public void setId(Long id) {
		this.id = id;
	}  
    
}  