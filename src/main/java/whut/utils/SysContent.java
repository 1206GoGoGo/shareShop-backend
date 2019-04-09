package whut.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 获取上下文及当前session
 * @author chen cheng
 *
 */
public class SysContent {

    private static RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
	//static RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
    private static HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
    private static HttpServletResponse response = ((ServletRequestAttributes)requestAttributes).getResponse();
    private static int userId = Integer.parseInt( ((HttpSession) request.getSession()).getAttribute("userId").toString() );
    
    public static HttpServletRequest getRequest() {
        return request;
    }
 
    public static HttpServletResponse getResponse() {
        return response;
    }

    public static HttpSession getSession() {
        return (HttpSession) request.getSession();
    }
    
    public static int getUserId() {
        return userId;
    }
}
