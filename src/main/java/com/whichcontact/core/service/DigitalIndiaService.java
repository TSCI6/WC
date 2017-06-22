
package com.whichcontact.core.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.whichcontact.core.entity.DigitalIndia;

import com.whichcontact.core.jpa.DigitalIndiaRepository;


/**
 * 

 * @author TS001127
 *
 */
@Service
public class DigitalIndiaService {
	
	@Inject
	private DigitalIndiaRepository digitalIndiaRepository;



	/**
	 * @return data from table digitalIndia
	 */
	/**
	 * @return
	 */
	public List<DigitalIndia> indianCompanies() {
		return digitalIndiaRepository.findAll();
	}
}