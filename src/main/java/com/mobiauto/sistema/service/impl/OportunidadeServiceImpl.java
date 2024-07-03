package com.mobiauto.sistema.service.impl;

import com.mobiauto.sistema.repository.OportunidadeRepository;
import com.mobiauto.sistema.service.OportunidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OportunidadeServiceImpl implements OportunidadeService {

    private final OportunidadeRepository oportunidadeRepository;


}
