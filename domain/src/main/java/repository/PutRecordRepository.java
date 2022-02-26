package repository;

import entity.Record;
import java.util.Optional;

/**
 * Interface to modify record in the repository.
 */
@FunctionalInterface
public interface PutRecordRepository {

  Optional<Record> execute(Record recordModified);

}
