package fritzen;

import java.time.LocalDate;
import java.util.List;

import fritzen.dao.SerieDAO;
import fritzen.database.Database;
import fritzen.model.Season;
import fritzen.model.Serie;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        Database.getConnection();

        SerieDAO serieDAO = new SerieDAO();

        // System.out.println("Listando antes...");
        List<Serie> series = serieDAO.getSeries();
        //System.out.println(series);

        for (Serie serie : series) {
            System.out.println("\n" + serie);
            for (Season temp : serie.getSeasons()) {
                System.out.println("\t" + temp);
            }
        }

        // System.out.println("Adicionando...");
        // Serie s = new Serie("Death Note", LocalDate.parse("2006-10-03"));
        // serieDAO.addSerie(s);

        // System.out.println("Listando depois...");
        // System.out.println(serieDAO.getSeries());

//        while (true) {

            /*
            switch case com um menu com as opções,
                1. add
                2. visualizar
                3. atualizar
                4. remover
                5. sair
            */


//        }
        
    }
}
