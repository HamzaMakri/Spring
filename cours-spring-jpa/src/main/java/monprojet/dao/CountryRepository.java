package monprojet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import monprojet.entity.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.HashMap;

// This will be AUTO IMPLEMENTED by Spring 
//

public interface CountryRepository extends JpaRepository<Country, Integer> {


    //Une méthode qui, pour un pays dont l'ID est passé en paramètre,
    // calcule sa population (somme des populations des villes)
    @Query(value = "SELECT SUM(population) " +
            "FROM CITY " +
            "INNER JOIN COUNTRY ON COUNTRY.id = CITY.COUNTRY_id " +
            "WHERE COUNTRY.id = :id ",
            nativeQuery = true)
    public int populationPays(int id);


    @Query(value = "SELECT name, SUM(population) " +
            "FROM CITY " +
            "INNER JOIN COUNTRY ON COUNTRY.id = CITY.country_id " +
            "GROUP BY name ",
            nativeQuery = true)
    public HashMap<Country,Integer> listePaysEtPopulation();

}
