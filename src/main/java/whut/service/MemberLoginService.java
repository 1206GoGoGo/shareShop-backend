package whut.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import whut.utils.ResponseData;

public interface MemberLoginService {

	ResponseData loginin(String jsonString, HttpServletRequest request, HttpServletResponse response);

	ResponseData loginout(HttpServletRequest request, HttpServletResponse response);

	ResponseData getPhoneCode(String phoneCode);

	ResponseData getMailCode(String mailCode);



}
