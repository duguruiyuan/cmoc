package com.xuequ.cmoc.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestService {

	public static void main(String[] args) {
		String value = "张小王自己打";
		Pattern pattern = Pattern.compile("[\u4E00-\u9FA5]{2,}");
		Matcher m = pattern.matcher(value.trim());
		System.out.println(m.matches());

	}

}
