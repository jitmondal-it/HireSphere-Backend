package com.hiresphere.service;

import com.hiresphere.dto.LoginDTO;
import com.hiresphere.dto.ResponseDTO;
import com.hiresphere.dto.UserDTO;
import com.hiresphere.exception.JobPortalException;

import jakarta.validation.Valid;

public interface UserService {
	
	public UserDTO registerUser(UserDTO userDTO) throws JobPortalException;
	
	public UserDTO getUserByEmail(String email) throws JobPortalException;

	public UserDTO loginUser(@Valid LoginDTO loginDTO)throws JobPortalException;

	public Boolean sendOtp(String email)throws Exception;

	public Boolean verifyOtp(String email,String otp)throws JobPortalException;

	public ResponseDTO changePassword(LoginDTO loginDTO)throws JobPortalException;

}
