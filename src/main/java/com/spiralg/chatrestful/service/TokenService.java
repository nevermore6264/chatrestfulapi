package com.spiralg.chatrestful.service;

import com.spiralg.chatrestful.model.Token;

public interface TokenService {

    Token getByUserId(int userId);

}
