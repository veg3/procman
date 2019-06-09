package edu.mmc.factory;
import edu.mmc.entity.Authority;
import edu.mmc.service.IAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;

public class FilterChainDefinitionMapBuilder {
	@Autowired
	private IAuthorityService aus;
	public LinkedHashMap<String, String> buildFilterChainDefinitionMap(){
		System.out.println("--eee--");
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		List<Authority> list = aus.list();
		for(Authority au : list){
			String str = null;
			if(au.getRole() != null){
				str = au.getInc()+",roles[\""+au.getRole()+"\"]";
			}
			else {
				str = au.getInc();
			}
			map.put(au.getUrl(),str);
		}
		return map;
	}


}
