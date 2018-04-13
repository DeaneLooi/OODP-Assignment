package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import item.Item;
import service.Service;
import utils.Constants;
import utils.Serialization;

/**
 * 
 * <h1>Room Service Controller</h1>
 * 
 * This controller class makes use of Room Service Entity class to do simple CRUD operations.
 *
 */
public class ServiceController {

	/**
	 * The list of Room Service Entity objects in the data file
	 */
	private static List<Service> serviceList = retrieveServiceList();

	/**
	 * 
	 * Reads the service data file and returns it in a list format
	 * 
	 * @return Returns the list of Room Service Entity objects in the data file
	 */
	public static List<Service> retrieveServiceList() {

		serviceList = (List<Service>) Serialization.readSerializedObject(Constants.SERVICE_DATA);

		if (serviceList != null)
			return serviceList;
		else {
			System.out.println("No data");
			return null;
		}

	}

	/**
	 * 
	 * Creates or updates the service object into the data file
	 * 
	 * @param service Room Service object to be created or updated
	 * @return Returns true if data file is updated, else returns false
	 */
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

		return Serialization.writeSerializedObject(Constants.SERVICE_DATA, serviceList);

	}

	/**
	 * 
	 * Removes the service object from the data file
	 * 
	 * @param service Room Service object to be removed
	 * @return Returns true if service object is removed from data file, else returns false
	 */
	public static boolean removeService(Service service) {

		if (serviceList != null) {
			if (serviceList.remove(service)) {

				return Serialization.writeSerializedObject(Constants.SERVICE_DATA, serviceList);

			} else
				return false;
		}

		else
			return false;
	}

	/**
	 * 
	 * Returns a list of service objects using reservationCode
	 * 
	 * @param reservationCode Reservation code to search services
	 * @return Returns a list of service objects from reservationCode
	 */
	public static List<Service> getServicesFromReservationCode(String reservationCode) {

		if (serviceList != null) {

			List<Service> reservationServices = new ArrayList();

			for (int i = 0; i < serviceList.size(); i++) {
				if (reservationCode.equals((serviceList.get(i).getReservationCode())))
					reservationServices.add(serviceList.get(i));
			}

			return reservationServices;

		}

		else
			return null;

	}

	/**
	 * 
	 * Returns service object using the primary key of Room Service Entity, serviceID
	 * 
	 * @param serviceID The primary key of Room Service Entity
	 * @return Returns the service object from serviceID
	 */
	public static Service getServiceFromServiceID(int serviceID) {
		Service service = new Service();
		service.setServiceID(serviceID);
		int i;
		if (serviceList != null) {
			if (serviceList.contains(service))
				for (i = 0; i < serviceList.size(); i++) {
					if (serviceList.get(i).equals(service))
						break;
				}

			else
				return null;

			return serviceList.get(i);

		}

		return null;

	}

}
