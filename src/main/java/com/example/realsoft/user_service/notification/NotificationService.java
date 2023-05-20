package com.example.realsoft.user_service.notification;

import com.example.realsoft.user_service.entity.User;
import com.example.realsoft.user_service.exception.ResourceNotFound;
import com.example.realsoft.user_service.model.CardAssignDto;
import com.example.realsoft.user_service.model.UserResponse;
import com.example.realsoft.user_service.repository.UserRepository;
import com.example.realsoft.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final EmailService emailService;
    private final UserService userService;
    public final static String QUEUE = "card.assigned.queue";

    @RabbitListener(queues = QUEUE)
    public void handleEmail(CardAssignDto cardAssignDto) throws ResourceNotFound {
        System.out.println(cardAssignDto);
        log.info("User {} ", cardAssignDto.getUserId());
        UserResponse user = userService.getUserById(cardAssignDto.getUserId());
        String userEmail = user.getEmail();
        String message = "Card " + cardAssignDto.getCardId() + " has been assigned to you.";
        System.out.println(userEmail);
        emailService.sendEmail(userEmail, cardAssignDto.getTitle(), message);
    }
}
