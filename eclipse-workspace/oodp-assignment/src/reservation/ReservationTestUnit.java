package reservation;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import utils.Constants;

class ReservationTestUnit {

	@Test
	void testCreateReservation() {
		Reservation r = new Reservation("e2020202i","2-20",new Date(), new Date(),Constants.STATUS_CONFIRMED,2,1);
		System.out.println(ReservationController.updateReservationList(r));
		
	}
	
	@Test
	void testRetrieveReservations()
	{
		List<Reservation> r = ReservationController.retrieveReservationList();
		
		for(int i=0; i< r.size();i++)
		{
			System.out.println(r.get(i).toString());
		}
	}
	
	@Test
	void testGetReservationFromRoomNo()
	{
		List<Reservation> r = ReservationController.getReservationByRoomNo("2-20");
		
		for(int i=0; i< r.size();i++)
		{
			System.out.println(r.get(i).toString());
		}
		
		System.out.println(ReservationController.getReservationByRoomNo("2-20", Constants.STATUS_CONFIRMED));
	}

}
