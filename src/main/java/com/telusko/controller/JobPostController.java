package com.telusko.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.model.JobPost;
import com.telusko.service.JobPostService;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:3000")
public class JobPostController {

    //constructor injection instead of field injection
    private final JobPostService jobPostService;

    public JobPostController(JobPostService jobPostService) {
        this.jobPostService = jobPostService;
    }


    //controller method for getting all job posts
    @CrossOrigin
    @GetMapping("/allPosts")
    public ResponseEntity<List<JobPost>> getAllJobPosts(){

        return  new ResponseEntity<List<JobPost>>(jobPostService.returnAllJobPosts(),HttpStatus.OK);

    }



    //************************************************************


    //controller method to get a Job Post By Id
    @CrossOrigin
    @GetMapping("/getJobPostById/{id}")
    public ResponseEntity<JobPost> getJobPostById(@PathVariable int id) {
        Optional<JobPost> jobPostOptional = jobPostService.returnJobPostById(id);
        if (jobPostOptional != null) {
            return new ResponseEntity<JobPost>(jobPostOptional.get(), HttpStatus.FOUND);
        }
        return null;
    }



    //************************************************************



    //controller method to add a job post
    @CrossOrigin
    @PostMapping("/addJobPost")
    public ResponseEntity<JobPost> addJobPost(@RequestBody JobPost jobPost) {
        return new ResponseEntity<JobPost>(jobPostService.addJobPost(jobPost),HttpStatus.CREATED);
    }




    //************************************************************





    //controller method to update a job post by id
    @CrossOrigin
    @PutMapping("/updateJobPostById/{id}")
    public ResponseEntity<JobPost> updateJobPost(@PathVariable int id,@RequestBody JobPost jobPost) {
        Optional<JobPost> jobPostOptional=jobPostService.returnJobPostById(id);
        if (jobPostOptional!=null){
        return  new ResponseEntity<JobPost>(jobPostService.updateJobPostById(id, jobPost),HttpStatus.CREATED);
    }
        return null;
    }





    //************************************************************



    //controller method to delete a JobPost by id
   @CrossOrigin
    @DeleteMapping("/deleteJobPostById/{id}")
    public ResponseEntity<String> deleteJobPostById(@PathVariable int id) {
        Optional<JobPost> jobPostOptional=jobPostService.returnJobPostById(id);
        if (jobPostOptional!=null){
        return  new ResponseEntity<String>(jobPostService.deleteJobPostById(id),HttpStatus.ACCEPTED);

    }
        return null;
    }


}
