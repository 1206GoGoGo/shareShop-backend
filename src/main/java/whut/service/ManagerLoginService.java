package whut.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import whut.utils.ResponseData;

public interface ManagerLoginService {

	ResponseData loginin(String jsonString, HttpServletRequest request, HttpServletResponse response);

	ResponseData loginout(String username, HttpServletRequest request, HttpServletResponse response);

	ResponseData getPhoneCode(String phoneCode);

	ResponseData getMailCode(String mailCode);



}
