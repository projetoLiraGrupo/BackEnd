package com.lira.grupo.api.lira_api.service;

import com.lira.grupo.api.lira_api.repository.InstrumentoRepository;
import org.springframework.stereotype.Service;

@Service
public class InstrumentoService {

    private final InstrumentoRepository instrumentoRepository;


    public InstrumentoService(InstrumentoRepository instrumentoRepository) {
        this.instrumentoRepository = instrumentoRepository;
    }
}
