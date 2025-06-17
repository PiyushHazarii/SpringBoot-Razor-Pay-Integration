package com.example.SpringBootPayment.controller;

import com.example.SpringBootPayment.Service.PaymentService;
import com.example.SpringBootPayment.model.OrderRequest;
import com.example.SpringBootPayment.model.PaymentResponse;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService razorpayService;

    // agar hum pojo class use karenge to wo json mein data le skta hai matlab lega
    // agar direct hard code kr de means int amount, like this to wo query param lega sirf wo
    // json mein data nhi lega
    @PostMapping("/create-order")
    public String createOrder(@RequestBody OrderRequest request) {
        try {
            return razorpayService.createOrder(
                    request.getAmount(),
                    request.getCurrency(),
                    request.getReceiptId()
            );
        } catch (RazorpayException e) {
            throw new RuntimeException("Error creating Razorpay order", e);
        }
    }

    // 🔥 Save payment response from Razorpay to DB
    @PostMapping("/save-payment")
    public ResponseEntity<String> savePayment(@RequestBody PaymentResponse response) {
        // Save paymentId, orderId, and signature to DB (you can use JPA repository here)
        System.out.println("Saving payment ID: " + response.getPaymentId());

        // For now just return OK
        return ResponseEntity.ok("Payment saved successfully");
    }
}
