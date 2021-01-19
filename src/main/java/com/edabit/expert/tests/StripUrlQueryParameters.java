package com.edabit.expert.tests;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StripUrlQueryParameters {
	
	private static String stripUrlParams(String url, String... optional) {
		String[] urlTokens = url.split("[?&]");
		Map<String, String> params  = new LinkedHashMap<String, String>();
		String hostport = urlTokens[0];
		for(int i=1; i< urlTokens.length; i++) {
			String[] paramValue  = urlTokens[i].split("=");
			boolean isOptional = false;
			for(String option: optional) {
				if(option.equals(paramValue[0])) {
					isOptional = true;
				}
			}
			if(!isOptional) {
				params.put(paramValue[0], paramValue[1]);
			}
		}
		String paramStr = params.entrySet().stream().map(e-> e.getKey()+"="+e.getValue()).collect(Collectors.joining("&"));
		return hostport+(paramStr.length()>0?"?":"")+paramStr;
	}
	
	public static void main(String[] args) {
		String value = stripUrlParams("https://edabit.com", "b" );
		System.out.println(value);
	}
}
