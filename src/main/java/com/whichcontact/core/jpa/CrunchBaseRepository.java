package com.whichcontact.core.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whichcontact.core.entity.CrunchBaseFunding;
/**
 * @author AC001133
 *
 */
public interface CrunchBaseRepository extends JpaRepository < CrunchBaseFunding, Long > {
	
	
}