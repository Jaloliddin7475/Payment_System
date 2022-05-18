package personal_project.service.oson;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import personal_project.entity.merchant.MerchantServiceEntity;
import personal_project.entity.oson.OsonServiceEntity;
import personal_project.repository.OsonRepository;
import personal_project.repository.merchant.MerchantServiceRepository;

@Service
@RequiredArgsConstructor
public class OsonServiceImp implements OsonService{

    private final OsonRepository osonRepository;
    private final MerchantServiceRepository merchantServiceRepository;
    private final ModelMapper modelMapper;

    @Override
    public Boolean add(Long merchantServiceId) {

        MerchantServiceEntity merchantServiceEntity
                = merchantServiceRepository.findByMerchantServiceId(merchantServiceId);

        //TODO: 11.04.2022 use appropriate exception
        if(merchantServiceEntity == null)
            throw new UsernameNotFoundException("merchant service not found");

        OsonServiceEntity osonServiceEntity = new OsonServiceEntity();
        osonServiceEntity.setMerchantServiceEntity(merchantServiceEntity);
        osonRepository.save(osonServiceEntity);

        return true;
    }
}
