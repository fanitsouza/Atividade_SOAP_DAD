package br.edu.ifam.dad.service;

import br.edu.ifam.dad.entity.Cep;
import javax.jws.WebService;

@WebService(endpointInterface = "br.edu.ifam.dad.service.CepSOAPService")
public class CepSOAPServiceImpl implements CepSOAPService {

    private CepService cepService = new CepService();

    @Override
    public Cep buscarCep(String cep) {

        try{
            return cepService.buscarEnderecoPorCep(cep);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

