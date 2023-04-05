package com.pictu.blog.payloads;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.security.core.context.SecurityContextHolder;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class AuditableEntity {

	 @Column(name = "created_at")
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date createdAt;

	    @Column(name = "modified_at")
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date modifiedAt;

	    @Column(name = "deleted_at")
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date deletedAt;

	    @Column(name = "last_accessed_by")
	    private String lastAccessedBy;
	    
	    @Column(name = "created_by")
	    private String createdBy;

	    // Define a @PrePersist method to set the creation time before saving the entity to the database
	    @PrePersist
	    public void setCreationTimeAndCreator() {
	        createdAt = new Date();
	        createdBy = SecurityContextHolder.getContext().getAuthentication().getName();
	    }

	    // Define a @PreUpdate method to set the modification time before updating the entity in the database
	    @PreUpdate
	    public void setModificationTime() {
	        modifiedAt = new Date();
	    }

	    // Define a @PreRemove method to set the deletion time before removing the entity from the database
	    @PreRemove
	    public void setDeletionTime() {
	        deletedAt = new Date();
	    }

	    // Define a @PostLoad method to set the last accessed user after loading the entity from the database
	    @PostLoad
	    public void setLastAccessedBy() {
	        lastAccessedBy = SecurityContextHolder.getContext().getAuthentication().getName();
	    }
	    
	   
}
