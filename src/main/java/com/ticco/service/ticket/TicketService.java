package com.ticco.service.ticket;

import com.ticco.common.type.FileType;
import com.ticco.domain.ticket.Ticket;
import com.ticco.domain.ticket.repository.TicketRepository;
import com.ticco.domain.user.User;
import com.ticco.domain.user.repository.OnboardingRepository;
import com.ticco.domain.user.repository.UserRepository;
import com.ticco.service.image.provider.S3Provider;
import com.ticco.service.image.provider.dto.request.ImageUploadFileRequest;
import com.ticco.service.ticket.dto.request.UpdateTicketRequestDto;
import com.ticco.service.user.UserServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class TicketService {

    private final UserRepository userRepository;
    private final OnboardingRepository onboardingRepository;
    private final TicketRepository ticketRepository;
    private final S3Provider s3Provider;

    @Transactional
    public void createTicket(UpdateTicketRequestDto request, MultipartFile image, Long userId) {
        User user = UserServiceUtils.findUserById(userRepository, userId);
        ticketRepository.save(Ticket.newInstance(user.getOnboarding(), request.getDate(), request.getCategory(), request.getTitle(), request.getRating(), request.getContent(), s3Provider.uploadFile(ImageUploadFileRequest.of(FileType.TICKET_IMAGE), image), request.getTheme()));
    }

    @Transactional
    public void updateTicket(Long ticketId, UpdateTicketRequestDto request, MultipartFile image, Long userId) {
        User user = UserServiceUtils.findUserById(userRepository, userId);
        Ticket ticket = TicketServiceUtils.findTicketById(ticketRepository, ticketId);
        TicketServiceUtils.checkForbiddenTicket(user.getOnboarding(), ticket);
        String ticketImageUrl;
        if (image == null) {
            ticketImageUrl = ticket.getTicketImageUrl();
        } else {
            s3Provider.deleteFile(ticket.getTicketImageUrl());
            ticketImageUrl = s3Provider.uploadFile(ImageUploadFileRequest.of(FileType.TICKET_IMAGE), image);
        }
        ticket.updateImage(ticketImageUrl);
        ticket.updateInfo(request);
        ticketRepository.save(ticket);
    }
}
