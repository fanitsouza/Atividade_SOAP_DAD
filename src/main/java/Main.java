import br.edu.ifam.dad.dao.CepDAO;
import br.edu.ifam.dad.entity.Cep;

public class Main {
    public static void main(String[] args) {
        //Cep cep = new Cep("69000-000", "Av. Eduardo Ribeiro", "Centro", "Manaus", "AM");
        CepDAO cepDAO = new CepDAO();

        System.out.println(cepDAO.buscarPorCep("69000-000"));

    }
}
