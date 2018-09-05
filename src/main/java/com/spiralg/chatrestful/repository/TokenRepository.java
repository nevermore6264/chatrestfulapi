package com.spiralg.chatrestful.repository;

import com.spiralg.chatrestful.model.Token;

public interface TokenRepository {

    Token getByUserId(int userId);

}
