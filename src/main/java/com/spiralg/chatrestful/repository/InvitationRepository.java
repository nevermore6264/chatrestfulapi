package com.spiralg.chatrestful.repository;

import com.spiralg.chatrestful.model.Friend;
import com.spiralg.chatrestful.model.api.form.InvitationForm;

import java.util.List;

public interface InvitationRepository {

    List<Friend> getAllInvitations(InvitationForm invitationForm);
}
