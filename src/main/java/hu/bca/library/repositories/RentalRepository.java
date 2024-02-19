package hu.bca.library.repositories;

import hu.bca.library.models.Rental;
import org.springframework.data.repository.CrudRepository;

public interface RentalRepository extends CrudRepository<Rental, Long> {

}
