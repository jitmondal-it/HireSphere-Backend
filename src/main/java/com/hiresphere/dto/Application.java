package com.hiresphere.dto;

import java.time.LocalDateTime;
import java.util.function.LongToDoubleFunction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Application {
	private Long id;
	private Long applicantId;
	private LocalDateTime interviewTime;
	private ApplicationStatus applicationStatus;
}
