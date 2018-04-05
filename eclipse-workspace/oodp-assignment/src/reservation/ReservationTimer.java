package reservation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class ReservationTimer extends TimerTask{

	
	SimpleDateFormat ft = 
  	      new SimpleDateFormat ("yyyy.MM.dd hh:mm:ss");

  private Date reservedDate;
	private Date currentDate;
	public final static long MILLIS_PER_DAY = 1 * 60 * 60 * 1000L;

	
	public ReservationTimer() {
			Date reservedDate = new Date();
			reservedDate.setHours(16);
			reservedDate.setMinutes(17);
			reservedDate.setSeconds(0);
			this.reservedDate = reservedDate;

		
		System.out.println(this.reservedDate);
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		this.currentDate = new Date();
		System.out.println(this.currentDate);
		
		if(reservedDate.compareTo(currentDate)>0) {
			
			System.out.println("Reservation still valid");

		}
		
		else if(reservedDate.compareTo(currentDate)<0) {
			System.out.println("Reservation Expired");

		}
		
		
	}

}
