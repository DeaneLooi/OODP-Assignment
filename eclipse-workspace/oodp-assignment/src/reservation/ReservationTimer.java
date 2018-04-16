package reservation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import utils.Constants;

/**
 * 
 * <h1>ReservationTimer</h1>
 * 
 * This class is a timer to check whether a Guest's reservation has expired.
 * 
 * @version 1.0
 * @since 2018-04-10
 *
 */
public class ReservationTimer extends TimerTask {

	/**
	 * Date format that is used to compare dates, y.m.d h.m.s
	 */
	SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
	/**
	 * List of reservation objects
	 */
	private List<Reservation> reservations;
	/**
	 * Reservation check-in date
	 */
	private Date reservedDate;
	/**
	 * Date to compare with reservedDate
	 */
	private Date currentDate;

	/**
	 * Default constructor
	 */
	public ReservationTimer() {
		super();
	}

	/**
	 * Retrieves the list of reservation objects and checks each reservation object.
	 * <p>
	 * If the currentDate is 1 hour or more away from the reservedDate, the
	 * reservation will be expired.
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		reservations = ReservationController.retrieveReservationList();
		this.currentDate = new Date();

		for (int i = 0; i < reservations.size(); i++) {
			Reservation reservation = reservations.get(i);
			if (reservation != null) {

				reservedDate = reservation.getCheckInDate();
				reservedDate.setHours(reservedDate.getHours() + 1);

				if (reservedDate.compareTo(currentDate) > 0) {

					// System.out.println("Reservation still valid");

				}

				else if (reservedDate.compareTo(currentDate) < 0) {
					if (reservation.getStatus().equals(Constants.STATUS_CONFIRMED)) {
						reservation.setStatus(Constants.STATUS_EXPIRED);
						boolean result = ReservationController.updateReservationList(reservation);
						if (result)
							System.out.println("\n<< Reservation " + reservation.getReservationCode() + " has expired");

					}

				}
			}

		}

	}

}
