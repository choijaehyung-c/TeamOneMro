package mrone.teamone.utill;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;



@Component
public class ProjectUtils {
	@Autowired
	DataSourceTransactionManager tx;
	private DefaultTransactionDefinition def;
	private TransactionStatus status;
	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectUtils.class);
	/* RequestContetHolder Class --> Spring 3.0 이상
	 *  : ThreadLocal를 사용해서 현재 쓰레드에 RequestAttributes 인스턴스를 바인딩 해두었다가 요청을 하면 이 인스턴스를 돌려주는 역할을 합니다.
	 *  : ThreadLocal을 사용하는 경우 ThreadLocal 변수에 보관된 데이터의 사용이 끝나면 반드시 삭제 해야 재사용되는 쓰레드의 잘못된 참조를 방지
	 *    그렇지 않으면, 재사용되는 쓰레드가 올바르지 않은 데이터를 참조할 수 있다.
	 *    
	 * org.springframework.mobile   spring-mobile-device
	 */
	
	public int screenType(int serviceCode){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return serviceCode * (isMobile(request)? -1 : 1);
	}
	
	private boolean isMobile(HttpServletRequest request){
		return DeviceUtils.getCurrentDevice(request).isMobile();
	}
	
	/* Session영역으로부터 attribute 값을 가져 오기 위한 method  */
    public Object getAttribute(String name) throws Exception {
        return (Object) RequestContextHolder.getRequestAttributes().getAttribute(name, RequestAttributes.SCOPE_SESSION);
    }
 
    /* Session영역에 attribute 설정 method */
    public void setAttribute(String name, Object object) throws Exception {
        RequestContextHolder.getRequestAttributes().setAttribute(name, object, RequestAttributes.SCOPE_SESSION);
    }
 
    /* Session영역에 설정된 attribute 삭제 */
    public void removeAttribute(String name) throws Exception {
        RequestContextHolder.getRequestAttributes().removeAttribute(name, RequestAttributes.SCOPE_SESSION);
    }
 
    /* Session영역의 SessionId값을 가져오기 위한 Method */
    public String getSessionId() throws Exception  {
        return RequestContextHolder.getRequestAttributes().getSessionId();
    }
    
    public String setFile(MultipartFile file,HttpServletRequest req){
    	String saveFileName =null;
    	 
		String uploadLocation =req.getSession().getServletContext().getRealPath("/")+".."+File.separator+".."+File.separator+".."+File.separator+"img"+File.separator;
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
				Calendar cal = Calendar.getInstance();
				UUID uuid = UUID.randomUUID();
				String fileName = file.getOriginalFilename();
				int dot = fileName.lastIndexOf(".");
				String ext = fileName.substring(dot+1); 
				saveFileName = uuid+"_"+sdf.format(cal.getTime())+"."+ext;
				
				/*
				 * File sfile = new File(uploadLocation,saveFileName); file.transferTo(sfile);
				 * fileInfo = uploadLocation+saveFileName;
				 */
				
				
				 byte[] data = file.getBytes();
				 FileOutputStream fos = new FileOutputStream(uploadLocation+saveFileName);
				 fos.write(data);
				 fos.close();
				 
				System.out.println("업로드성공");
				LOGGER.debug("upup 업로드 성공");
			}
			
			catch (Exception e) {
				LOGGER.debug("upup xx 업로드 실패");
				System.out.println("errrrror");
			}
			
			return saveFileName;
    }
    
    public String[] setFile(MultipartFile[] file) {
    	String[] fileInfo = new String[file.length];
    	String uploadLocation = "."+File.separator+"src"+File.separator+"main"+File.separator+
    			"webapp"+File.separator+ "resources" +File.separator+"file"+File.separator;
    	
    	for(int i=0; i<file.length;i++) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
				Calendar cal = Calendar.getInstance();
				UUID uuid = UUID.randomUUID();
				String saveFileName = uuid+"_"+sdf.format(cal.getTime())+"_"+file[i].getOriginalFilename();
				File sfile = new File(uploadLocation,saveFileName);
				file[i].transferTo(sfile);
				fileInfo[i] = saveFileName;
				System.out.println("업로드성공"+i);
			}
			
			catch (Exception e) {
				System.out.println("errrrror"+i);
			}
		}
    	return fileInfo;
    }
    
    
	public void setTransactionConf(int propagation,int isolationLevel, boolean isRead) {
		def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(propagation);
		def.setIsolationLevel(isolationLevel);
		def.setReadOnly(isRead);
		status = tx.getTransaction(def);
	}
	
	public void setTransactionResult(boolean isCheck) {
		if(isCheck) {
			tx.commit(status);
		}else {
			tx.rollback(status);
		}

	}
	
	private boolean convertToBoolean(int data) {
		return data>0? true : false;
	}
	
}
