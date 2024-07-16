package com.reserveappartment.appartmentreserveapp.controller;

import com.reserveappartment.appartmentreserveapp.domain.Reservation;
import com.reserveappartment.appartmentreserveapp.domain.User;
import com.reserveappartment.appartmentreserveapp.repos.UserRepository;
import com.reserveappartment.appartmentreserveapp.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReservationController {

    private final ReservationService reservationService;
    private final UserRepository userRepository;

    @Autowired
    public ReservationController(ReservationService reservationService, UserRepository userRepository) {
        this.reservationService = reservationService;
        this.userRepository = userRepository;
    }

    @GetMapping("/reservations/new")
    public String showReservationForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "reservation-form";
    }

    @PostMapping("/reservations/save")
    public String saveReservation(Reservation reservation) {
        // Retrieve user from userRepository using reservation.getUserId() and set to reservation
        User user = userRepository.findById(reservation.getUser().getId()).orElseThrow(() -> new IllegalArgumentException("Invalid user Id"));
        reservation.setUser(user);
        reservationService.save(reservation);
        return "redirect:/";
    }
}
