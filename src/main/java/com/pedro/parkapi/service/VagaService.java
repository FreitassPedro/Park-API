package com.pedro.parkapi.service;

import com.pedro.parkapi.entity.Vaga;
import com.pedro.parkapi.exception.CodigoUniqueViolantionException;
import com.pedro.parkapi.exception.EntityNotFoundException;
import com.pedro.parkapi.repository.VagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.pedro.parkapi.entity.Vaga.StatusVaga.LIVRE;

@Service
@RequiredArgsConstructor
public class VagaService {
    private final VagaRepository vagaRepository;

    @Transactional
    public Vaga salvar(Vaga vaga) {
        try {
            return vagaRepository.save(vaga);
        } catch (DataIntegrityViolationException exception) {
            throw new CodigoUniqueViolantionException(
                    String.format("Vaga com código %s já cadastrada", vaga.getCodigo()));
        }
    }

    @Transactional(readOnly = true)
    public Vaga buscarPorCodigo(String codigo) {
        return vagaRepository.findByCodigo(codigo).orElseThrow(
                () -> new EntityNotFoundException(String.format("Vaga com código '%s' não encontrada", codigo))
        );
    }

    @Transactional(readOnly = true)
    public Vaga buscarPorVagaLivre() {
        return vagaRepository.findFirstByStatus(LIVRE).orElseThrow(
                () -> new EntityNotFoundException("Nenhuma vaga livre foi encontrada")
        );
    }
}
