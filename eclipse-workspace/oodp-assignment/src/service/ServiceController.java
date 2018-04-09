package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import item.Item;
import service.Service;
import utils.Constants;
import utils.Serialization;

public class ServiceController {

	private static List<Service> serviceList = retrieveServiceList();
	
	
	public static List<Service> retrieveServiceList() {

		serviceList = (List<Service>) Serialization.readSerializedObject(Constants.SERVICE_DATA);

		if (serviceList != null)
			return serviceList;
		else {
			System.out.println("No data");
			return null;
		}

	}

	public static boolean updateServiceList(Service service) {


		if (serviceList != null) {
			

			if (service.getServiceID() == 0)
				service.setServiceID(serviceList.size() + 1);

			if (serviceList.contains(service)) {
				int i = 0;

				for (i = 0; i < serviceList.size(); i++) {
					if (service.equals(serviceList.get(i)))
						break;
				}

				serviceList.set(i, service);
			}

			else
				serviceList.add(service);
		}

		else {
			service.setServiceID(1);
			serviceList = new ArrayList();
			serviceList.add(service);
		}

		try {
			Serialization.writeSerializedObject(Constants.SERVICE_DATA, serviceList);

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static boolean removeService(Service service) {


		if (serviceList != null) {
			if (serviceList.remove(service)) {
				try {
					Serialization.writeSerializedObject(Constants.SERVICE_DATA, serviceList);

				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}

				return true;
			} else
				return false;
		}

		else
			return false;
	}

	public static List<Service> getServicesFromReservationNo(String reservationNo) {

		if (serviceList != null) {
			
				List<Service> reservationServices = new ArrayList();

				for (int i = 0; i < serviceList.size(); i++) {
					if (reservationNo.equals((serviceList.get(i).getReservationCode())))
						reservationServices.add(serviceList.get(i));
				}
				
				return reservationServices;
			
			

		}

		else
			return null;

	}

}