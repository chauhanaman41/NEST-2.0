package com.pv.adverse.repository;

import com.pv.adverse.model.AdverseEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdverseEventRepository extends MongoRepository<AdverseEvent, String> {
}
