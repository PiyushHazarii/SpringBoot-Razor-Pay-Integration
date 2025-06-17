package com.example.SpringBootPayment.controller;

import com.example.SpringBootPayment.Service.PaymentService;
import com.example.SpringBootPayment.model.OrderRequest;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayEntryController {

    @Autowired
    private PaymentService paymentService;

    // This is the endpoint you call from Postman
    @PostMapping("/pay")
    public String handlePayFromPostman(@RequestBody OrderRequest request) {
        try {
            // Internally call same logic of Razorpay order creation
            return paymentService.createOrder(
                    request.getAmount(),
                    request.getCurrency(),
                    request.getReceiptId()
            );
        } catch (RazorpayException e) {
            throw new RuntimeException("Failed to create order via /pay", e);
        }
    }
}

