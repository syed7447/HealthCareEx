package com.example.demo.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MyCollectionsUtil {

	public static Map<Long,String> convertToMap(List<Object[]> list) {
		Map<Long,String> map=list.
				stream().
				collect(Collectors.toMap(
						ob-> Long.valueOf(ob[0].toString()),
						ob->ob[1].toString()));
		return map;
		// TODO Auto-generated method stub
		
	}
	
}
