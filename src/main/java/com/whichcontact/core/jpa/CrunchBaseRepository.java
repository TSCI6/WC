package com.whichcontact.core.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whichcontact.core.entity.CrunchBaseFunding;

public interface CrunchBaseRepository extends JpaRepository < CrunchBaseFunding, Long > {
	
	
}