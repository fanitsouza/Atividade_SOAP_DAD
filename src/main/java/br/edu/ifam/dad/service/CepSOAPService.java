package br.edu.ifam.dad.service;

import br.edu.ifam.dad.entity.Cep;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface CepSOAPService {
    @WebMethod
    Cep buscarCep(@WebParam(name = "cep") String cep);
}
