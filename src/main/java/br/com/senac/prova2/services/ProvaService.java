package br.com.senac.prova2.services;

import br.com.senac.prova2.dtos.ProvaFiltroDto;
import br.com.senac.prova2.dtos.ProvaRequestDto;
import br.com.senac.prova2.entidades.Prova;
import br.com.senac.prova2.repositorios.ProvaRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class ProvaService {
    private ProvaRepositorio provaRepositorio;

    public ProvaService(ProvaRepositorio provaRepositorio) {
        this.provaRepositorio = provaRepositorio;
    }

    @GetMapping("/listar")
    public List<Prova> listar(ProvaFiltroDto filtro){
        if(filtro.getTitulo() != null){
            return provaRepositorio.findByTitulo(filtro.getTitulo());
        }
        return provaRepositorio.findAll();
    }

    public Prova atualizar(Long id,
                           ProvaRequestDto prova){
        if (provaRepositorio.existsById(id)){
            Prova provaPersist = this.provaRequestDtoParaProva(prova);
            provaPersist.setId(id);
            return provaRepositorio.save(provaPersist);
        }
        throw new RuntimeException("Prova não encontrada");
    }

    public Prova criar(ProvaRequestDto prova){
        Prova provaPersist = this.provaRequestDtoParaProva(prova);

        return provaRepositorio.save(provaPersist);
    }

    public void deletar(Long id){
        if(provaRepositorio.existsById(id)){
            provaRepositorio.deleteById(id);
            return;
        }
        throw new RuntimeException("Prova não encontrada");
    }

    private Prova provaRequestDtoParaProva(ProvaRequestDto entrada){
        Prova saida = new Prova();
        saida.setTitulo(entrada.getTitulo());
        saida.setMateria(entrada.getMateria());
        saida.setData(entrada.getData());

        return saida;
    }
}
