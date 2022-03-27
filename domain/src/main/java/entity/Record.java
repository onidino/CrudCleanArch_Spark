package entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Record {

  private Long id;
  private String recordData;

  public Record(Long id, String recordData) {
    this.id = id;
    this.recordData = recordData;
  }

}
