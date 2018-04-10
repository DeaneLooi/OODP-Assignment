package payment;

import java.util.List;

import org.junit.jupiter.api.Test;

public class PaymentTestUnit {
	@Test
	void testPaymentCreation() {
		Payment payment = new Payment("R10001", "0702", "xyz");
		boolean result = PaymentController.updatePaymentList(payment);
		System.out.println("Payment creation result: " + result);
		System.out.println(payment.toString());
	}
	
	@Test
	void testRetrievePaymentList() {
		List<Payment> paymentList = PaymentController.retrievePaymentList();
		int i;
		for(i=0;i< paymentList.size(); i++) {
			System.out.println(paymentList.get(i).toString());
		}
	}
	
	@Test
	void testGetPaymentByPaymentId() {
		Payment payment = PaymentController.getPaymentByPaymentId("P10002");
		System.out.println(payment.toString());
	}
	
	@Test
	void testPaymentUpdate() {
		List<Payment> paymentList = PaymentController.retrievePaymentList();
		Payment payment = null;
		if(paymentList != null) {
			payment = paymentList.get(0);
		}
		payment.setPaymentType("Cash");
		boolean result = PaymentController.updatePaymentList(payment);
		System.out.println("Payment update result: " + result);
		System.out.println(payment.toString());
	}
	
	@Test
	void testRemovePayment() {
		Payment payment = new Payment("e2021201i", "0701", "abc");
		boolean result = PaymentController.removePayment(payment);
		System.out.println("Payment deletion result: " + result);
	}
	
	
	// to be tested once room classes are done
	/*@Test
	void testPrintBillInvoice() {
		
	}*/
	
}
