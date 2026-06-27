package com.hiresphere.api;

import java.util.List;

import org.hibernate.validator.internal.util.actions.NewInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiresphere.dto.ApplicantDTO;
import com.hiresphere.dto.Application;
import com.hiresphere.dto.JobDTO;
import com.hiresphere.dto.ProfileDTO;
import com.hiresphere.dto.ResponseDTO;
import com.hiresphere.dto.UserDTO;
import com.hiresphere.exception.JobPortalException;
import com.hiresphere.service.JobService;

import jakarta.validation.Valid;


@RestController
@CrossOrigin
@Validated
@RequestMapping("/jobs")
public class JobAPI {
	@Autowired
	private JobService jobService;
	
	@PostMapping("/post")
	public ResponseEntity<JobDTO>postJob(@RequestBody @Valid JobDTO jobDTO) throws JobPortalException{
		return new ResponseEntity<>(jobService.postJob(jobDTO),HttpStatus.CREATED);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<JobDTO>>getAllJobs() throws JobPortalException{
		return new ResponseEntity<>(jobService.getAllJobs(),HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<JobDTO>getJob(@PathVariable Long id) throws JobPortalException{
		return new ResponseEntity<>(jobService.getJob(id),HttpStatus.OK);
	}
	
	@PostMapping("/apply/{id}")
	public ResponseEntity<ResponseDTO>applyJob(@PathVariable long id, @RequestBody  ApplicantDTO applicantDTO) throws JobPortalException{
		jobService.applyJob(id,applicantDTO);
		return new ResponseEntity<>(new ResponseDTO("Applied Successfully"),HttpStatus.OK);
	}
	
	@GetMapping("/postedBy/{id}")
	public ResponseEntity<List<JobDTO>>getJobsPostedBy(@PathVariable Long id) throws JobPortalException{
		return new ResponseEntity<>(jobService.getJobsPostedBy(id),HttpStatus.OK);
	}
	
	@PostMapping("/changeAppStatus")
	public ResponseEntity<ResponseDTO>changeAppStatus(@RequestBody  Application application) throws JobPortalException{
		jobService.changeAppStatus(application);
		return new ResponseEntity<>(new ResponseDTO("Status Changed successfully.."),HttpStatus.OK);
	}
	

}
