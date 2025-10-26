import br.edu.ifam.dad.service.CepSOAPServiceImpl;

import javax.xml.ws.Endpoint;

public class PublicaServico {
    public static void main(String[] args) {

        String url = "http://localhost:8088/cep";

        // Publica o serviço
        Endpoint.publish(url, new CepSOAPServiceImpl());

        System.out.println("Serviço SOAP publicado com sucesso!");
        System.out.println("WSDL disponível em: " + url + "?wsdl");
        System.out.println("Pressione Ctrl+C para parar o servidor.");

        // Mantém o servidor rodando
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
