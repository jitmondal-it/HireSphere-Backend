package com.hiresphere.service;

import java.security.cert.X509Certificate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiresphere.dto.ApplicantDTO;
import com.hiresphere.dto.Application;
import com.hiresphere.dto.ApplicationStatus;
import com.hiresphere.dto.JobDTO;
import com.hiresphere.dto.JobStatus;
import com.hiresphere.dto.NotificationDTO;
import com.hiresphere.entity.Applicant;
import com.hiresphere.entity.Job;
import com.hiresphere.exception.JobPortalException;
import com.hiresphere.repository.JobRepository;
import com.hiresphere.utility.Utilities;

@Service("jobService")
public class JobServiceImpl  implements JobService{
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private NotificationService notificationService;

	@Override
	public JobDTO postJob(JobDTO jobDTO) throws JobPortalException {
		if(jobDTO.getId() == 0) {
			jobDTO.setId(Utilities.getNextSequence("jobs"));
			jobDTO.setPostTime(LocalDateTime.now());
			NotificationDTO notiDto = new NotificationDTO();
			notiDto.setAction("Job Posted");
			notiDto.setMessage("Job posted Successfully for "+jobDTO.getJobTitle() + " at " + jobDTO.getCompany());
			notiDto.setUserId(jobDTO.getPostedBy());
			notiDto.setRoute("/posted-job/" + jobDTO.getId());
				notificationService.sendNotification(notiDto);
			
		}
		else {
			Job job  = jobRepository.findById(jobDTO.getId()).orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND"));
			if(job.getJobStatus().equals(JobStatus.DRAFT) ||jobDTO.getJobStatus().equals(JobStatus.CLOSED) )jobDTO.setPostTime(LocalDateTime.now());
		}
		return jobRepository.save(jobDTO.toEntity()).toDTO()
;	}

	@Override
	public List<JobDTO> getAllJobs() {
		return jobRepository.findAll().stream().map((x)-> x.toDTO()).toList();
	}

	@Override
	public JobDTO getJob(Long id) throws JobPortalException {
		return jobRepository.findById(id).orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND")).toDTO();
	}

	@Override
	public void applyJob(long id, ApplicantDTO applicantDTO) throws JobPortalException {
		Job job = jobRepository.findById(id).orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND"));
		List<Applicant> applicants = job.getApplicants();
		if(applicants == null) applicants = new ArrayList<>();
		if(applicants.stream().filter((x)->x.getApplicantId()==applicantDTO.getApplicantId()).toList().size()>0)throw new JobPortalException("JOB_APPLIED_ALREADY");
		applicantDTO.setApplicationStatus(ApplicationStatus.APPLIED);
		applicants.add(applicantDTO.toEntity());
		job.setApplicants(applicants);
		jobRepository.save(job);
	}

	@Override
	public List<JobDTO> getJobsPostedBy(Long id) {
		return jobRepository.findByPostedBy(id).stream().map((x)-> x.toDTO()).toList();
	}

	@Override
	public void changeAppStatus(Application application) throws JobPortalException {
		Job job = jobRepository.findById(application.getId()).orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND"));
		List<Applicant> applicants = job.getApplicants().stream().map((x)->{
			if(application.getApplicantId() == x.getApplicantId()) {
				x.setApplicationStatus(application.getApplicationStatus());
				if(application.getApplicationStatus().equals(ApplicationStatus.INTERVIEWING)) {
					x.setInterviewTime(application.getInterviewTime()); 
					NotificationDTO notiDto = new NotificationDTO();
					notiDto.setAction("Interview Schedule");
					notiDto.setMessage("Interview Schedule for Job id: " + application.getId());
					notiDto.setUserId(application.getApplicantId());
					notiDto.setRoute("/job-history");
					try {
						notificationService.sendNotification(notiDto);
					} catch (JobPortalException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					}
			}
			return x;
		}).toList();
		job.setApplicants(applicants);
		jobRepository.save(job);
	}
	
}
