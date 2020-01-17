package ftn.upp.naucnacentrala.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.camunda.bpm.engine.form.FormField;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormFieldsResponse {

    private String taskId;

    private List<FormField> formFields;
}
