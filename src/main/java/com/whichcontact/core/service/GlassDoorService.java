package com.whichcontact.core.service;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.whichcontact.core.entity.Employers;
import com.whichcontact.core.jpa.EmployersRepository;
@Service
public class GlassDoorService {
 @Inject
 private EmployersRepository employerRepository;
/**
 * @return List
 */
public List < Employers > viewEmployee() {
  return employerRepository.findAll();
 }
}