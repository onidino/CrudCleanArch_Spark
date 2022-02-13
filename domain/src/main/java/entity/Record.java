package entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Record {

  private Long id;
  private String data;

  public Record(Long id, String data) {
    this.id = id;
    this.data = data;
  }

}
