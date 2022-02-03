package monprojet.dao;

import monprojet.dto.PopulationParPays;
import org.springframework.data.jpa.repository.JpaRepository;
import monprojet.entity.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.HashMap;
import java.util.List;

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


    @Query(value = "SELECT COUNTRY.name countryName , SUM(CITY.population) as countryPop " +
            "FROM COUNTRY " +
            "INNER JOIN CITY ON COUNTRY.id = CITY.country_id " +
            "GROUP BY COUNTRY.name",
            nativeQuery = true)
    public List<PopulationParPays> populationParPays();

}
