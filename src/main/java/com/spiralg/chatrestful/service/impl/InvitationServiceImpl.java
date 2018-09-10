package com.spiralg.chatrestful.service.impl;

import com.spiralg.chatrestful.model.Friend;
import com.spiralg.chatrestful.model.api.form.AcceptForm;
import com.spiralg.chatrestful.model.api.form.InvitationForm;
import com.spiralg.chatrestful.repository.InvitationRepository;
import com.spiralg.chatrestful.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitationServiceImpl implements InvitationService {

    @Autowired
    InvitationRepository invitationRepository;

    @Override
    public List<Friend> getAllInvitations(InvitationForm invitationForm) {
        return invitationRepository.getAllInvitations( invitationForm );
    }

    @Override
    public int acceptInvitations(AcceptForm acceptForm) {
        return invitationRepository.acceptInvitations( acceptForm );
    }
}
