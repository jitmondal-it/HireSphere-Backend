package com.hiresphere.utility;

public class Data {
	public static String getMessageBody(String otp,String name) {
		        return
		        		"<!DOCTYPE html>\n" +
		        		"<html lang=\"en\">\n" +
		        		"<head>\n" +
		        		"  <meta charset=\"UTF-8\">\n" +
		        		"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
		        		"  <title>Your OTP Code</title>\n" +
		        		"  <link href=\"https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;600&family=DM+Mono:wght@500&display=swap\" rel=\"stylesheet\">\n" +
		        		"  <style>\n" +
		        		"    * { margin:0; padding:0; box-sizing:border-box; }\n" +
		        		"    body { background-color:#f0f2f5; font-family:'DM Sans', Arial, sans-serif; padding:40px 16px; }\n" +
		        		"    .wrapper { max-width:560px; margin:auto; }\n" +
		        		"    .card { background:#ffffff; border-radius:16px; overflow:hidden; box-shadow:0 4px 24px rgba(0,0,0,0.08); }\n" +
		        		"    .banner { background:linear-gradient(135deg, #0f172a 0%, #1e3a5f 100%); padding:36px 40px; text-align:center; }\n" +
		        		"    .banner-logo { font-size:22px; font-weight:600; color:#ffffff; letter-spacing:0.5px; }\n" +
		        		"    .banner-logo span { color:#60a5fa; }\n" +
		        		"    .banner-tagline { font-size:12px; color:#94a3b8; margin-top:4px; letter-spacing:1.5px; text-transform:uppercase; }\n" +
		        		"    .body { padding:40px; }\n" +
		        		"    .greeting { font-size:18px; font-weight:600; color:#0f172a; margin-bottom:8px; }\n" +
		        		"    .message { font-size:14px; color:#64748b; line-height:1.7; margin-bottom:28px; }\n" +
		        		"    .otp-label { font-size:11px; font-weight:600; color:#94a3b8; letter-spacing:2px; text-transform:uppercase; margin-bottom:10px; }\n" +
		        		"    .otp-container { background:#f8fafc; border:1.5px dashed #cbd5e1; border-radius:12px; padding:24px; text-align:center; margin-bottom:24px; }\n" +
		        		"    .otp-code { font-family:'DM Mono', monospace; font-size:36px; font-weight:500; letter-spacing:6px; color:#0f172a; display:inline-block; white-space:nowrap; word-break:keep-all; overflow-wrap:normal; line-height:1; text-align:center; }\n" +
		        		"    .otp-validity { display:inline-flex; align-items:center; gap:6px; background:#eff6ff; color:#2563eb; font-size:12px; font-weight:500; padding:6px 14px; border-radius:20px; margin-bottom:24px; }\n" +
		        		"    .otp-validity::before { content:'⏱'; font-size:13px; }\n" +
		        		"    .divider { border:none; border-top:1px solid #f1f5f9; margin:24px 0; }\n" +
		        		"    .warning { display:flex; gap:10px; background:#fff7ed; border-left:3px solid #f97316; border-radius:0 8px 8px 0; padding:12px 16px; font-size:13px; color:#9a3412; line-height:1.5; }\n" +
		        		"    .warning-icon { font-size:16px; flex-shrink:0; }\n" +
		        		"    .footer { padding:20px 40px; background:#f8fafc; border-top:1px solid #f1f5f9; text-align:center; }\n" +
		        		"    .footer p { font-size:12px; color:#94a3b8; line-height:1.8; }\n" +
		        		"    .footer a { color:#60a5fa; text-decoration:none; }\n" +
		        		"  </style>\n" +
		        		"</head>\n" +
		        		"<body>\n" +
		        		"  <div class=\"wrapper\">\n" +
		        		"    <div class=\"card\">\n" +
		        		"      <div class=\"banner\">\n" +
		        		"        <div class=\"banner-logo\">Hire<span>Sphere</span></div>\n" +
		        		"        <div class=\"banner-tagline\">Talent Meets Opportunity</div>\n" +
		        		"      </div>\n" +
		        		"      <div class=\"body\">\n" +
		        		"        <p class=\"greeting\">Hello, " + name + " 👋</p>\n" +
		        		"        <p class=\"message\">We received a request to verify your email address. Use the OTP below to complete your verification. It&apos;s only valid for a short window, so act quickly.</p>\n" +
		        		"        <p class=\"otp-label\">Your One-Time Password</p>\n" +
		        		"        <div class=\"otp-container\">\n" +
		        		"          <div class=\"otp-code\">" + otp + "</div>\n" +
		        		"        </div>\n" +
		        		"        <div style=\"text-align:center\">\n" +
		        		"          <span class=\"otp-validity\">Valid for 5 minutes only</span>\n" +
		        		"        </div>\n" +
		        		"        <hr class=\"divider\">\n" +
		        		"        <div class=\"warning\">\n" +
		        		"          <span class=\"warning-icon\">🔒</span>\n" +
		        		"          <span>For your security, <strong>never share this OTP</strong> with anyone. HireSphere will never ask for your OTP via call, chat, or email.</span>\n" +
		        		"        </div>\n" +
		        		"      </div>\n" +
		        		"      <div class=\"footer\">\n" +
		        		"        <p>If you didn&apos;t request this, you can safely ignore this email.<br>\n" +
		        		"        &copy; 2026 HireSphere &bull; All rights reserved</p>\n" +
		        		"      </div>\n" +
		        		"    </div>\n" +
		        		"  </div>\n" +
		        		"</body>\n" +
		        		"</html>";
	}
}
