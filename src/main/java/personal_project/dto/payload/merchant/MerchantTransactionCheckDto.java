package personal_project.dto.payload.merchant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MerchantTransactionCheckDto {
    private String current_time;
    private int transaction;
    private int state;
}
