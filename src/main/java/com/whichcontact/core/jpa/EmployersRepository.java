package com.whichcontact.core.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whichcontact.core.entity.Employers;
/**
 * @author AC001133
 *
 */
public interface EmployersRepository extends JpaRepository < Employers, Long > {}