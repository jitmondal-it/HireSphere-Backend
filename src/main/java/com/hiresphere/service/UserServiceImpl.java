package com.hiresphere.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hiresphere.dto.LoginDTO;
import com.hiresphere.dto.NotificationDTO;
import com.hiresphere.dto.ResponseDTO;
import com.hiresphere.dto.UserDTO;
import com.hiresphere.entity.OTP;
import com.hiresphere.entity.User;
import com.hiresphere.exception.JobPortalException;
import com.hiresphere.repository.OTPRepository;
import com.hiresphere.repository.UserRepository;
import com.hiresphere.utility.Data;
import com.hiresphere.utility.Utilities;
import com.resend.Resend;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;

import org.springframework.beans.factory.annotation.Value;

import jakarta.validation.Valid;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

	@Value("${resend.api.key}")
	private String resendApiKey;

	@Value("${resend.from.email}")
	private String resendFromEmail;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OTPRepository otpRepository;
	
	@Autowired
	private ProfileService profileService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private NotificationService notificationService;

	@Override
	public UserDTO registerUser(UserDTO userDTO) throws JobPortalException {
		Optional<User> optional = userRepository.findByEmail(userDTO.getEmail());
		if (optional.isPresent())
			throw new JobPortalException("USER_FOUND");
		userDTO.setId(profileService.createProfile(userDTO.getName(),userDTO.getEmail()));
		userDTO.setId(Utilities.getNextSequence("users"));
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		User user = userDTO.toEntity();
		user = userRepository.save(user);
		return user.toDTO();
	}

	@Override
	public UserDTO loginUser(@Valid LoginDTO loginDTO) throws JobPortalException {
		User user = userRepository.findByEmail(loginDTO.getEmail())
				.orElseThrow(() -> new JobPortalException("USER_NOT_FOUND"));
		if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword()))
			throw new JobPortalException("INVALID_CREDENTIALS");
		return user.toDTO();
	}

//	@Override
//	public Boolean sendOtp(String email) throws Exception {
//
//		//System.out.println("1. Finding user...");
//		User user = userRepository.findByEmail(email)
//				.orElseThrow(() -> new JobPortalException("USER_NOT_FOUND"));
//
//		//System.out.println("2. Creating email...");
//		MimeMessage mm = mailSender.createMimeMessage();
//		MimeMessageHelper message = new MimeMessageHelper(mm, true);
//
//		message.setTo(email);
//		message.setSubject("Your OTP Code");
//
//		String genOtp = Utilities.generateOtp();
//
//		OTP otp = new OTP(email, genOtp, LocalDateTime.now());
//
//		//System.out.println("3. Saving OTP...");
//		otpRepository.save(otp);
//
//		message.setText(Data.getMessageBody(genOtp, user.getName()), true);
//
//		//System.out.println("4. Sending email...");
//		mailSender.send(mm);
//
//	   //	System.out.println("5. Email sent successfully!");
//
//		return true;
//	}

	@Override
	public Boolean sendOtp(String email) throws Exception {

		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new JobPortalException("USER_NOT_FOUND"));

		String genOtp = Utilities.generateOtp();

		OTP otp = new OTP(email, genOtp, LocalDateTime.now());

		Resend resend = new Resend(resendApiKey);

		CreateEmailOptions params = CreateEmailOptions.builder()
				.from(resendFromEmail)
				.to(email)
				.subject("Your OTP Code")
				.html(Data.getMessageBody(genOtp, user.getName()))
				.build();

		CreateEmailResponse response = resend.emails().send(params);

		otpRepository.save(otp);

		//System.out.println("OTP email sent successfully. Email ID: " + response.getId());

		return true;
	}

	@Override
	public Boolean verifyOtp(String email, String otp) throws JobPortalException {
		OTP otpEntity = otpRepository.findById(email).orElseThrow(() -> new JobPortalException("OTP_NOT_FOUND"));
		if (!otpEntity.getOtpCode().equals(otp))
			throw new JobPortalException("OTP_INCORRECT");
		return true;
	}

	@Override
	public ResponseDTO changePassword(LoginDTO loginDTO) throws JobPortalException {
		User user = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(() -> new JobPortalException("USER_NOT_FOUND"));
		user.setPassword(passwordEncoder.encode(loginDTO.getPassword()));
		userRepository.save(user);
		NotificationDTO noti = new NotificationDTO();
		noti.setUserId(user.getId());
		noti.setMessage("Password Reset Seccessful");
		noti.setAction("Password Reset");
		notificationService.sendNotification(noti);
		return new ResponseDTO("Password changed Successfully...");
	}
	
	@Scheduled(fixedRate = 60000)
	public void removeExpiredOTPs() {
		LocalDateTime expiry = LocalDateTime.now().minusMinutes(5);
		List<OTP> expiredOTPs = otpRepository.findByCreationTimeBefore(expiry);
		if(!expiredOTPs.isEmpty()) {
			otpRepository.deleteAll(expiredOTPs);
			System.out.print("Removed " + expiredOTPs.size() + " expired OTPs.");
		}
	}

	@Override
	public UserDTO getUserByEmail(String email) throws JobPortalException {
		return userRepository.findByEmail(email).orElseThrow(() -> new JobPortalException("USER_NOT_FOUND")).toDTO();
	}

}
