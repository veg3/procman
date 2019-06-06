package edu.mmc.factory;
import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {

	public LinkedHashMap<String, String> buildFilterChainDefinitionMap(){
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		map.put("/pages/user/fotPwd.html", "anon");
		map.put("/pages/login.html", "anon");
		map.put("/user/login", "anon");
		map.put("/pages/statistic/statistic.html", "authc,roles[3]");
		map.put("/pages/statistic/statistic.html", "authc,roles[4]");
		map.put("/pages/index.html", "authc");
		map.put("/pages/welcome.html", "authc");
		map.put("/static/**", "anon");
		map.put("/**", "anon");
		return map;
	}
	
}
