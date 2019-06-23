package com.xdx.util;

import java.util.Date;
import java.util.Random;


public class RandomUtil {
	/**
	 * 获取时间戳加四位随机整数
	 * @return
	 */
	public static long getTimeStampPlusRand(){
		return new Date().getTime()+getRandNum(1, 1000);
	}
	/**
	 * 获取一个随机整数
	 * @param min
	 * @param max
	 * @return
	 */
	public static Integer getRandNum(int min,int max){
		Random random = new Random();
		int s = random.nextInt(max)%(max-min+1) + min;
		return s;
	}

}
