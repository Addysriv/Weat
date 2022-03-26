package com.weat.weat.data.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntity implements Comparable<Account> {
	private boolean verified;	
	private AuthProvider authProvider;

	
	@ManyToOne
	@JsonIgnore
	private User user;

	@Override public int compareTo(@NonNull Account other) {

		if(this.isArchived()) {
			return 1;
		}
		if(other.isArchived()) {
			return -1;
		}
		if (this.isVerified()) {
			if (other.isVerified()) {
				// Both are verified
				// Sort in reverse by recent update
				return other.getLastUpdated().compareTo(this.getLastUpdated());
			}
			// only a1 is verified
			return -1;
		} else if (other.isVerified()) {
			// only a2 is verified
			return 1;
		} else {
			// None is verified
			// Sort in reverse by recent update
			return other.getLastUpdated().compareTo(this.getLastUpdated());
		}

	}
}
