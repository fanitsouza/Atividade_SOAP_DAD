package br.edu.ifam.dad.service;

import br.edu.ifam.dad.dao.CepDAO;
import br.edu.ifam.dad.entity.Cep;
import javax.jws.WebService;

@WebService(endpointInterface = "br.edu.ifam.dad.service.CepService")
public class CepServiceImpl implements CepService {

    private CepDAO dao = new CepDAO();

    @Override
    public Cep buscarCep(String cep) {
        Cep resultado = dao.buscarPorCep(cep);
        if (resultado == null) {
            resultado = new Cep();
            resultado.setCep(cep);
            resultado.setLogradouro("Não encontrado");
            resultado.setCidade("N/A");
            resultado.setEstado("N/A");
        }
        return resultado;
    }
}

