package com.weldoncardoso.PautaAPI.service.impl;

import com.weldoncardoso.PautaAPI.domain.voto.Voto;
import com.weldoncardoso.PautaAPI.domain.voto.VotoPK;
import com.weldoncardoso.PautaAPI.repository.VotoRepository;
import com.weldoncardoso.PautaAPI.service.VotoService;
import com.weldoncardoso.PautaAPI.service.validator.PautaValidador;
import com.weldoncardoso.PautaAPI.service.validator.VotoValidador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static com.weldoncardoso.PautaAPI.builders.voto.VotoBuilder.umVoto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class VotoServiceImplTests {

    private VotoService votoService;

    private VotoRepository votoRepository;

    private VotoValidador votoValidador;

    private PautaValidador pautaValidador;

    private Voto voto;

    @BeforeEach
    public void setUp() {
        voto = umVoto();
        votoValidador = mock(VotoValidador.class);
        pautaValidador = mock(PautaValidador.class);
        votoRepository = mock(VotoRepository.class);
        votoService = new VotoServiceImpl(votoRepository, votoValidador, pautaValidador);
    }

    @Test
    @DisplayName("deve cadastrar voto")
    public void deveCadastrarVotoComSucesso() {
        Mockito.when(votoRepository.save(any(Voto.class))).thenReturn(voto);

        Voto esperado = votoService.cadastrar(umVoto());

        Assertions.assertEquals(esperado.getCpf(), voto.getCpf());
        Assertions.assertEquals(esperado.getVoto(), voto.getVoto());
        Assertions.assertEquals(esperado.getIdPauta(), voto.getIdPauta());
    }

    @Test
    @DisplayName("deve buscar voto por id")
    public void deveBuscarVotoPorId() {
        Mockito.when(votoRepository.findById(any(VotoPK.class))).thenReturn(Optional.of(umVoto()));

        Optional<Voto> esperado = votoService.buscarPorId(umVoto());

        Assertions.assertTrue(esperado.isPresent());
    }
}
