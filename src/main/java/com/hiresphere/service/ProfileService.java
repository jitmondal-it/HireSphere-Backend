package com.hiresphere.service;

import java.util.List;

import com.hiresphere.dto.ProfileDTO;
import com.hiresphere.exception.JobPortalException;

public interface ProfileService {
	public Long createProfile(String name,String email) throws JobPortalException;
	public ProfileDTO getProfile(Long id) throws JobPortalException;
	public ProfileDTO updateProfile(ProfileDTO profileDTO) throws JobPortalException;
	public List<ProfileDTO> getAllProfiles();

}
