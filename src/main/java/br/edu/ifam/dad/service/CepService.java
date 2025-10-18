package br.edu.ifam.dad.service;

import br.edu.ifam.dad.entity.Cep;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface CepService {
    @WebMethod
    Cep buscarCep(String cep);
}
