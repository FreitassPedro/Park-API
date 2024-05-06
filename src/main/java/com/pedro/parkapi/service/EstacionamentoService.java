package com.pedro.parkapi.service;

import com.pedro.parkapi.entity.Cliente;
import com.pedro.parkapi.entity.ClienteVaga;
import com.pedro.parkapi.entity.Vaga;
import com.pedro.parkapi.util.EstacionamentoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.pedro.parkapi.entity.Vaga.StatusVaga.LIVRE;
import static com.pedro.parkapi.entity.Vaga.StatusVaga.OCUPADA;

@Service
@RequiredArgsConstructor
public class EstacionamentoService {

    private final ClienteVagaService clienteVagaService;
    private final ClienteService clienteService;
    private final VagaService vagaService;

    @Transactional
    public ClienteVaga checkIn(ClienteVaga clienteVaga) {
        Cliente cliente = clienteService.buscarPorCpf(clienteVaga.getCliente().getCpf());
        clienteVaga.setCliente(cliente);

        Vaga vaga  = vagaService.buscarPorVagaLivre();
        vaga.setStatus(OCUPADA);
        clienteVaga.setVaga(vaga);

        clienteVaga.setDataEntrada(LocalDateTime.now());
        clienteVaga.setRecibo(EstacionamentoUtils.gerarRecibo());
        return clienteVagaService.salvar(clienteVaga);
    }

    @Transactional
    public ClienteVaga checkOut(String recibo) {
        ClienteVaga clienteVaga = clienteVagaService.buscarPorRecibo(recibo);

        LocalDateTime dataSaida = LocalDateTime.now();
        BigDecimal valor = EstacionamentoUtils.calcularCusto(clienteVaga.getDataEntrada(), dataSaida);
        clienteVaga.setValor(valor);

        long totalDeVezesEstacionado = clienteVagaService.getTotalVezesEstacionamentos(clienteVaga.getCliente().getCpf());
        BigDecimal desconto = EstacionamentoUtils.calcularDesconto(valor, totalDeVezesEstacionado);
        clienteVaga.setDesconto(desconto);

        clienteVaga.setDataSaida(dataSaida);
        clienteVaga.getVaga().setStatus(LIVRE);

        return clienteVagaService.salvar(clienteVaga);
    }
}
