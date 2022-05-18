package personal_project.service.gateway;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import personal_project.dto.request.gateway.payme.PaymeCheckTransactionRequestDto;
import personal_project.entity.merchant.MerchantServiceEntity;
import personal_project.entity.transaction.TransactionEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymeTransactionService {
    private final RestTemplate restTemplate;
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitMq.topic.exchange.name}")
    private String exchangeName;

    @Value("${rabbitMq.topic.route.key.name}")
    private String routingKey;
    public TransactionEntity createTransaction(
            TransactionEntity transactionEntity,
            MerchantServiceEntity merchantServiceEntity
    ) {
        PaymeCheckTransactionRequestDto paymeCheckTransactionRequestDto =
                new PaymeCheckTransactionRequestDto(
                        merchantServiceEntity.getId(),
                        Timestamp.valueOf(LocalDateTime.now()),
                        transactionEntity.getTransactionAccount(),
                        transactionEntity.getTransactionAmountToMerchant()
                );

        return null;
    }
}
