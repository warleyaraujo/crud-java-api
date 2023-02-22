package controller;

import entity.Pessoa;
import service.PessoaService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/api/pessoa")
public class PessoaController {

    @Inject
    PessoaService pessoaService;

    @GET
    public List<Pessoa> retrievePessoa() {
        List<Pessoa> pessoa = new ArrayList<>();
        try {
            pessoa = pessoaService.findAllPessoa();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pessoa;
    }

    @POST
    @Transactional
    public Response addPessoa(Pessoa pessoa) {
        pessoaService.addPessoa(pessoa);
        return Response.ok(pessoa).status(201).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updatePessoa(@PathParam("id")Long id, Pessoa pessoa) {
        Pessoa p = pessoaService.updatePessoa(id, pessoa);
        return Response.ok(p).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deletePessoa(@PathParam("id")Long id, Pessoa pessoa) {
        Pessoa p = Pessoa.findById(id);

        p.delete();
        return Response.status(204).build();
    }

}
