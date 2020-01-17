package ftn.upp.naucnacentrala.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldSubmitRequest {

    String fieldId;

    String fieldValue;
}
