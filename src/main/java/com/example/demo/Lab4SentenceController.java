package com.example.demo;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController
public class Lab4SentenceController {
	
	//1. NOTE: This version of the method was used when we AREN'T using Ribbon  
	/*@Autowired
	 DiscoveryClient client;*/
	 
	 @Autowired 
	 RestTemplate template;
	
	@GetMapping("/sentence")
	  public @ResponseBody String getSentence() {
	    return 
	      getWord("LAB-4-SUBJECT") + " "
	      + getWord("LAB-4-VERB") + " "
	      + getWord("LAB-4-ARTICLE") + " "
	      + getWord("LAB-4-ADJECTIVE") + " "
	      + getWord("LAB-4-NOUN") + "."
	      ;
	  }
	  
	//2. NOTE: This version of the method was used when we AREN'T using Ribbon  
	/*public String getWord(String service) {
		List<ServiceInstance> list = client.getInstances(service);
		if (list != null && list.size() > 0) {
			URI uri = list.get(0).getUri();
			if (uri != null) {
				return (new RestTemplate()).getForObject(uri, String.class);
			}
		}
		return null;
	}*/
	
	//3. NOTE: This version of the method was used when we ARE using Ribbon  
	public String getWord(String service) {
		String strResponse = null;
		try {
		 strResponse = template.getForObject("http://" + service, String.class);
		}catch(Exception e) {
			System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>> Exception in sentence service: " + e);
		}
		return strResponse;
		
	}
}
