package personal_project.service.oson;


import org.springframework.stereotype.Service;
import personal_project.service.BaseService;

@Service
public interface OsonService extends BaseService {
    Boolean add(Long merchantServiceId);
}
