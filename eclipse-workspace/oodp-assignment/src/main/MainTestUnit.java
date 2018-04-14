package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MainTestUnit {

	@Test
	void testRoom() {
		
		//MainController.createRoom();
		//MainController.printReportByRoomType();
		
		MainController.updateRoom();
		
		MainController.printReportByAvailability();
		
		
	}
	
	

}
