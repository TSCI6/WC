package com.whichcontact.core.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whichcontact.core.entity.Invitation;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {

	List<Invitation> findByUserId(Integer user_id);

}
