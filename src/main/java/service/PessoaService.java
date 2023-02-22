package service;

import entity.Pessoa;
import repository.PessoaRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class PessoaService {

    @Inject
    PessoaRepository pessoaRepository;

    public List<Pessoa> findAllPessoa() {
        return pessoaRepository.findAll().list();
    }

    public void addPessoa(Pessoa pessoa) {
        pessoaRepository.persist(pessoa);
    }

    public Pessoa updatePessoa(Long id, Pessoa pessoa) {
        Pessoa p = Pessoa.findById(id);

        if (p == null) {
            throw new WebApplicationException("Pessoa não encontrada com esse id" + id, Response.Status.NOT_FOUND);
        }

        p.setNome(pessoa.getNome());
        p.setTelefone(pessoa.getTelefone());
        p.setEmail(pessoa.getEmail());
        return p;
    }

}