package com.pv.followup.repository;

import com.pv.followup.model.FollowUpCase;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowUpCaseRepository extends MongoRepository<FollowUpCase, String> {
}
