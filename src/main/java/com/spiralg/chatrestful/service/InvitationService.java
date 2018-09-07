package com.spiralg.chatrestful.service;

import com.spiralg.chatrestful.model.Friend;
import com.spiralg.chatrestful.model.api.form.InvitationForm;

import java.util.List;

public interface InvitationService {
    List<Friend> getAllInvitations(InvitationForm invitationForm);
}
