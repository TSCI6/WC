package com.whichcontact.core.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.whichcontact.core.entity.DigitalIndia;
import com.whichcontact.core.entity.Employers;
import com.whichcontact.core.jpa.DigitalIndiaRepository;
import com.whichcontact.core.jpa.EmployersRepository;

/**
 * 
 * @author AC001127
 * @author TS001127
 *
 */
@Service
public class GlassDoorService {
	@Inject
	private EmployersRepository employerRepository;
	@Inject
	private DigitalIndiaRepository digitalIndiaRepository;

	/**
	 * @return List
	 */
	public List<Employers> viewEmployee() {
		return employerRepository.findAll();
	}

	/**
	 * @return data from table digitalIndia
	 */
	public List<DigitalIndia> indianCompanies() {
		return digitalIndiaRepository.findAll();
	}
}