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

//    private RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
//	//static RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//    private HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
//    private HttpServletResponse response = ((ServletRequestAttributes)requestAttributes).getResponse();
//    private int userId = Integer.parseInt( ((HttpSession) request.getSession()).getAttribute("userId").toString() );
    
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
 
    public static HttpServletResponse getResponse() {
    	return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    public static HttpSession getSession() {
        return (((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()).getSession();
    }
    
    public static int getUserId() {
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    	int userId = Integer.parseInt( ((HttpSession) request.getSession()).getAttribute("userId").toString() );
        return userId;
    }
}
