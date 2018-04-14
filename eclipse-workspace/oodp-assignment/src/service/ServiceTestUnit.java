package service;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import item.Item;
import item.ItemController;
import utils.Constants;

class ServiceTestUnit {

	@Test
	void testServiceCreation() {

		// create service object
		Service service = new Service("15","10003","Chicken Noodle",new Date(),"NIL",Constants.STATUS_CONFIRMED);
		boolean result = ServiceController.updateServiceList(service);
		System.out.println("Service creation result: " + result);
	}

	@Test
	void testServiceUpdate()
	{
		List<Service> serviceList = ServiceController.retrieveServiceList();
		Service service = null;
		if(serviceList!=null) {
			service = serviceList.get(0);
		}
		service.setStatus(Constants.SERVICE_STATUS_DELIVERED);
		ServiceController.updateServiceList(service);
		
	}

	@Test
	void testGetServiceListFromReservationNo()
	{
		List<Service> serviceList = ServiceController.getServicesFromReservationCode("10001");
		
		for(int i=0; i<serviceList.size();i++)
		{
			System.out.println(serviceList.get(i).toString());
		}
	}
	
	@Test
	void testRetrieval()
	{
		List<Service> serviceList = ServiceController.retrieveServiceList();
		for(int i=0; i<serviceList.size();i++)
		{
			System.out.println(serviceList.get(i).toString());
		}
		
	}
	
	@Test
	void testRemoveItem()
	{
		Service service = new Service();
		service.setServiceID(1);
		boolean result = ServiceController.removeService(service);
		System.out.println("Remove item result: "+result);
	}



}
