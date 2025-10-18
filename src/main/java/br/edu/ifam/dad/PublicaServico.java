package br.edu.ifam.dad;

import br.edu.ifam.dad.service.CepServiceImpl;

import javax.xml.ws.Endpoint;

public class PublicaServico {
    public static void main(String[] args) {
        String url = "http://localhost:8088/cepservice";
        System.out.println("Publicando serviço SOAP em: " + url);
        Endpoint.publish(url, new CepServiceImpl());
    }
}
