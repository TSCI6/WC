package com.whichcontact.core.jpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whichcontact.core.entity.DigitalIndia;

@Repository
public interface DigitalIndiaRepository  extends JpaRepository <DigitalIndia, Long > {
	

}




