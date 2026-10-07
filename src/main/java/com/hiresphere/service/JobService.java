package com.hiresphere.service;

import java.util.List;

import com.hiresphere.dto.ApplicantDTO;
import com.hiresphere.dto.Application;
import com.hiresphere.dto.JobDTO;
import com.hiresphere.exception.JobPortalException;



public interface JobService {

	public JobDTO postJob(JobDTO jobDTO) throws JobPortalException;

	public List<JobDTO> getAllJobs();

	public JobDTO getJob(Long id)throws JobPortalException;

	public void applyJob(long id, ApplicantDTO applicantDTO) throws JobPortalException;

	public List<JobDTO> getJobsPostedBy(Long id);

	public void changeAppStatus(Application application) throws JobPortalException;
	
}
