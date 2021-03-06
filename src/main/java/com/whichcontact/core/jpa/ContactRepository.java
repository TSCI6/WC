package com.whichcontact.core.jpa;

import java.util.HashSet;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whichcontact.core.entity.Contacts;


@Repository
public interface ContactRepository extends JpaRepository<Contacts, Long> {

	HashSet<Contacts> findByEmail(String email);

	List<Contacts> findByUserId(Integer userId);

	List<Contacts> findByCompanyStartingWith(String coName);

	List<Contacts> findByNameStartingWith(String user);

	HashSet<Contacts> findByNameContaining(String string);

	List<Contacts> findByEmailStartingWith(String string);
}
