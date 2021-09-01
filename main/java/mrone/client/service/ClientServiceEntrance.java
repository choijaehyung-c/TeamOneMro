package mrone.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mrone.teamone.beans.ClientOrderBean;

@Service
public class ClientServiceEntrance {
	@Autowired
	ClientServiceCtl csc;
	
	public String clientRequest(ClientOrderBean co) {
		return csc.clientRequestCtl(co);
	}

}
