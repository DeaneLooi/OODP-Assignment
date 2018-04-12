package reservation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import utils.Constants;

public class ReservationTimer extends TimerTask{

	
	SimpleDateFormat ft = 
  	      new SimpleDateFormat ("yyyy.MM.dd hh:mm:ss");
	private List<Reservation> reservations;
	private Date reservedDate;
	private Date currentDate;

	
	public ReservationTimer() {
		super();
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		reservations = ReservationController.retrieveReservationList();
		this.currentDate = new Date();

		for(int i=0; i< reservations.size();i++)
		{
			Reservation reservation = reservations.get(i);
			if(reservation!=null)
			{
				
				reservedDate = reservation.getCheckInDate();
				reservedDate.setHours(reservedDate.getHours()+1);
				
				if(reservedDate.compareTo(currentDate)<0) {
					
					System.out.println("Reservation still valid");

				}
				
				else if(reservedDate.compareTo(currentDate)>0) {
					if(reservation.getStatus().equals(Constants.STATUS_CONFIRMED))
						reservation.setStatus(Constants.STATUS_EXPIRED);
					boolean result = ReservationController.updateReservationList(reservation);
					if(result)
						System.out.println("Reservation expired");
					else
						System.out.println("Reservation update failed");
				}
			}
			
		}

		
		
	}

}
