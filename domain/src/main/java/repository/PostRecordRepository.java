package repository;

import entity.Record;
import java.util.Optional;

/**
 * Interface to save record in the repository.
 */
@FunctionalInterface
public interface PostRecordRepository {

  Optional<Long> execute(Record recordToSave);

}
