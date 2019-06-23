package com.xdx.util;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * java正则表达式匹配测试工具
 * 
 * @author xdx
 *
 */
public class RegUtil {
	public static boolean regMatch(String str, String pattern) {
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(str);
		System.out.println(m.matches());
		return m.matches();
	}

	private static void getString(String str, String regx) {
		// 1.将正在表达式封装成对象Patten 类来实现
		Pattern pattern = Pattern.compile(regx);
		// 2.将字符串和正则表达式相关联
		Matcher matcher = pattern.matcher(str);
		// 3.String 对象中的matches 方法就是通过这个Matcher和pattern来实现的。
		if(matcher.matches()){
			System.out.println(matcher.group());
			while(matcher.find()){
				matcher.group();
			}
			if(matcher.groupCount()>0){
				for (int i = 0; i < matcher.groupCount() + 1; i++) {
					System.out.println("分组" + i + ":" + matcher.group(i));
				}
			}
		}
		
	}

	/**
	 * 字符串的分割
	 */
	private void getDivision(String str, String regx) {
		String[] dataStr = str.split(regx);
		for (String s : dataStr) {
			System.out.println("正则表达式分割++" + s);
		}
	}

	/**
	 * 字符串的替换
	 */
	private void getReplace(String str, String regx, String replaceStr) {
		String stri = str.replaceAll(regx, replaceStr);
		System.out.println("正则表达式替换" + stri);
	}

	/**
	 * 字符串处理之匹配 String类中的match 方法
	 */
	public void getMatch(String str, String regx) {
		System.out.println("正则表达匹配" + str.matches(regx));
	}

	public static void main(String args[]) {
		getString("<H1>Chapter 1 - 介绍正则表达式</H1>", "<.*?>");
	}

}
