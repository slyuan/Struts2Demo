package org.crazyit.app.action;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class LoginAction extends ActionSupport //implements ServletResponseAware
{
	// �����װ���������username��password��Ա����
	private String username;
	private String password;
	private String tip;
	
	private HttpServletResponse response;
	
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}	
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	// ���崦���û������execute����
	public String login() throws Exception
	{
		
		ActionContext ac = ActionContext.getContext();
		
		Integer counter = (Integer)ac.getApplication().get("counter");
		
		if(counter == null){
			counter = 1;
		}else{
			counter = counter+1;
		}
		
		ac.getApplication().put("counter", counter);
		
		// ��usernameΪcrazyit.org��passwordΪleegangʱ����¼�ɹ�
		if (getUsername().equals("123")
			&& getPassword().equals("123"))
		{
			Cookie ck = new Cookie("user", getUsername());
			ck.setMaxAge(60*60);
			ServletActionContext.getResponse().addCookie(ck);
			ac.getSession().put("user" , getUsername());
			ac.put("tip", "��������ʾ�����ѳɹ���¼��");
			return SUCCESS;
		}
		return ERROR;
	}
	
	// ���崦���û������ע�᷽��
	public String register() throws Exception
	{
		
		ActionContext ac = ActionContext.getContext();
		
		if (!(getUsername().equals("")
			&& getPassword().equals(""))&&(getUsername()!=null&&getPassword()!=null))
		{

			ac.getSession().put("user" , getUsername());
			ac.put("tip", "��������ʾ�����ѳɹ�ע�ᣡ");
			return SUCCESS;
		}else{
			ac.put("tip", "��������ʾ���û��������벻��Ϊ�գ�");
		}
		
		return ERROR;
	}
	
	
	
/*	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}*/
}