package com.crazyBird.controller.curriculum;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.crazyBird.controller.curriculum.model.Course;
import com.crazyBird.controller.curriculum.model.StuSimpleInfo;

/**
 * HTML工具类，主要用于提取HTML文档中的信息
 * @author EsauLu
 *
 */
public class HtmlTools {

	private static final int FIND_XND=1;//学年度
	private static final int FIND_XQD=2;//学期
	
	/**
	 * 查找__VIEWSTATE参数的值
	 * @param html HTML文档
	 * @return 返回
	 */
	public static String findViewState(String html) {
		String res="";
		String pattern="<input type=\"hidden\" name=\"__VIEWSTATE\" value=\"(.*?)\" />";
		
		Pattern p=Pattern.compile(pattern);
		Matcher m=p.matcher(html);
		
		if(m.find()){
			res=m.group();
			res=res.substring(res.indexOf("value=\"")+7,res.lastIndexOf("\""));
		}
		return res;
	}
	
	/**
	 * 查找课表部分的HTML字符串
	 * @param html HTML文档
	 * @return 课表的HTML串
	 */
	private static String findCourseTableHtml(String html){
		String res="";
		String tar="<table id=\"Table1\" class=\"blacktab\" bordercolor=\"Black\" border=\"0\" width=\"100%\">";
		String pattern="<table id=\"Table1\" class=\"blacktab\" bordercolor=\"Black\" border=\"0\" width=\"100%\">([\\S\\s]+?)</table>";		
		Pattern p=Pattern.compile(pattern);
		Matcher m=p.matcher(html);		
		if(m.find()){
			res=m.group(0);
			res=res.substring(res.indexOf(tar)+tar.length(),res.lastIndexOf("</table>")).trim();
		}else{
			System.out.println(html);
			System.out.println("什么都没有");
		}
		return res;
	}
	
	/**
	 * 在HTML中提取学年度或者学期的选项的HTML记录串
	 * @param html HTML文档
	 * @param x 代表学期或者学年度的参数
	 * @return  返回HTML表示的学年度或者学期选项
	 */
	private static String findXndOrXqdHtml(String html,int x){
		String res="";
		String pattern=null;
		switch(x){
			case FIND_XND:
				pattern="<select name=\"xnd\" onchange=\"__doPostBack\\('xnd',''\\)\" language=\"javascript\" id=\"xnd\">([\\s\\S]+?)</select>";
				break;
			case FIND_XQD:
				pattern="<select name=\"xqd\" onchange=\"__doPostBack\\('xqd',''\\)\" language=\"javascript\" id=\"xqd\">([\\s\\S]+?)</select>";
				break;
		}
		Pattern p=Pattern.compile(pattern);
		Matcher m=p.matcher(html);		
		if(m.find()){
			res=m.group(1);
		}
		return res.trim();
	}
	
	/**
	 * 在HTML中提取学年度或者学期的选项列表
	 * @param html HTML文档
	 * @param x 代表学年度或者学期
	 * @return 返回学年度或者学期的选项数组
	 */
	private static String[] getOptions(String html,int x){
	
		String[] ops=null;
		String res="";
		String tar=findXndOrXqdHtml(html, x);
		ArrayList<String> arr=new ArrayList<String>();
		String pattern="<option([\\s\\S]*?)>(.*?)</option>";		
		Pattern p=Pattern.compile(pattern);
		Matcher m=p.matcher(tar);		
		while(m.find()){
			res=m.group(2);
			arr.add(res);
		}
		
		ops=new String[arr.size()];
		for(int i=0;i<ops.length;i++){
			ops[i]=arr.get(i);
		}
		
		return ops;
	}
	
	/**
	 * 获取学年度选项
	 * @param html HTML文档
	 * @return 返回学年度选项数组
	 */
	public static String[] getXnd(String html){			
		return getOptions(html, FIND_XND);
	}	
	
	/**
	 * 获取学期选项数组
	 * @param html HTML文档
	 * @return 学期选项数组
	 */
	public static String[] getXqd(String html){			
		return getOptions(html, FIND_XQD);
	}
	
	/**
	 * 获取学生简要信息
	 * @param html HTML文档
	 * @return 返回学生简要信息数组
	 */
	public static StuSimpleInfo getStuInfo(String html){
		String[] info=null;
		//(<span id="([\\s\\S]+?)">([\\s\\S]+?)</span>\\|)?<span id="(.+)">(.+?)</span>
		String res="";
		String pattern="<TR class=\"trbg1\">"
				+ "([\\s\\S]*?)<TD>([\\s\\S]*?)"
				+ "<span id=\"Labe(.+?)\">([\\s\\S]+?)</span>(\\|([\\s\\S]*?)<span id=\"Labe(.+?)\">([\\s\\S]+?)</span>)*";		
		Pattern p=Pattern.compile(pattern);
		Matcher m=p.matcher(html);		
		if(m.find()){
			res=m.group(0);
			info=res.split("\\|");
			pattern="<span id=\"Labe(.+?)\">([\\s\\S]+?)：([\\s\\S]+?)</span>";
			p=Pattern.compile(pattern);
			for(int i=0;i<info.length;i++){
				m=p.matcher(info[i]);
				if(m.find()){
					info[i]=m.group(3);
				}else{
					info[i]="";
				}
			}
		}
		
		return BeanFactor.createStuSimpleInfo(info);
	}
	
	/**
	 * 在HTML文档中提取课表
	 * @param html HTML文档
	 * @return 返回课表
	 */
	public static ArrayList<Course> getCourseList(String html){
		
		String courseTableHtml=findCourseTableHtml(html);//找到课表部分的HTML
		ArrayList<Course> courses=new ArrayList<Course>();

		String[] rows=courseTableHtml.split("</tr><tr>");//按上课时间分隔HTML
		for(int i=2;i<rows.length;i+=2){
			
			String r=rows[i];
			String[] cols=r.split("</td><td([\\S\\s]*?)>");//按星期几分隔HTML
			
			int j=1;
			if(i==2||i==6||i==10){
				j=2;
			}
			
			int x=1;
			for(;j<cols.length;x++,j++){
				String c=cols[j];
				String[] info=c.split("<br>");
				if(info[0].contains("&nbsp;")) continue;				
				String[] tem=new String[4];
				int t=0;
				
				for(int k=0;k<info.length;k++){
					String item=info[k].trim();
					if(item.equals("")){
						//处理同一时间不同周数的课程
						t=0;
						tem=new String[4];
						continue;
					}
					tem[t++]=item;
					if(t==4){
						Course course=BeanFactor.createCourse(tem, i-1, x);
						courses.add(course);
					}
				}
				
			}
			
		}
				
		return courses;
	}	
	
}









































