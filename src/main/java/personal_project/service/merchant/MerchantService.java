package personal_project.service.merchant;


import org.springframework.stereotype.Repository;
import personal_project.service.BaseService;

@Repository
public interface MerchantService<T> extends BaseService {
    Boolean add(T t);
}
