package com.telusko.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/JobPost")
    public ResponseEntity<List<JobPost>> getAllJobPosts(){

        return  new ResponseEntity<List<JobPost>>(jobPostService.returnAllJobPosts(),HttpStatus.OK);

    }



    //************************************************************


    //controller method to get a Job Post By Id
    @CrossOrigin
    @GetMapping("/JobPost/{id}")
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
    @PostMapping("/JobPost")
    public ResponseEntity<JobPost> addJobPost(@RequestBody JobPost jobPost) {
        return new ResponseEntity<JobPost>(jobPostService.addJobPost(jobPost),HttpStatus.CREATED);
    }




    //************************************************************





    //controller method to update a job post by id
    @CrossOrigin
    @PutMapping("/JobPost/{id}")
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
    @DeleteMapping("/JobPost/{id}")
    public ResponseEntity<String> deleteJobPostById(@PathVariable int id) {
        Optional<JobPost> jobPostOptional=jobPostService.returnJobPostById(id);
        if (jobPostOptional!=null){
        return  new ResponseEntity<String>(jobPostService.deleteJobPostById(id),HttpStatus.ACCEPTED);

    }
        return null;
    }

    //****************************************************************

    @GetMapping("/JobPost/search/{text}")
    public ResponseEntity<List<JobPost>> searchJobPosts(@PathVariable String text) {
        List<JobPost> jobPosts = jobPostService.wildcardSearch(text);
        if (!jobPosts.isEmpty()) {
            return new ResponseEntity<>(jobPosts, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/JobPost/searchById")
    public ResponseEntity<List<JobPost>> searchByPostId(@RequestParam int postId) {
        List<JobPost> jobPosts = jobPostService.searchByPostId(postId);
        if (!jobPosts.isEmpty()) {
            return new ResponseEntity<>(jobPosts, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/JobPost/searchByExp")
    public ResponseEntity<List<JobPost>> searchByReqExperience(@RequestParam int reqExperience) {
        List<JobPost> jobPosts = jobPostService.searchByReqExperience(reqExperience);
        if (!jobPosts.isEmpty()) {
            return new ResponseEntity<>(jobPosts, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
