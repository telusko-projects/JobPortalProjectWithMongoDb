package com.telusko.repo;

import com.telusko.model.JobPost;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobPostRepo extends MongoRepository<JobPost,Integer> {

}
