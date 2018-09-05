package com.spiralg.chatrestful.service.impl;

import com.spiralg.chatrestful.model.Token;
import com.spiralg.chatrestful.repository.TokenRepository;
import com.spiralg.chatrestful.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public Token getByUserId(int userId) {
        return tokenRepository.getByUserId( userId );
    }
}
