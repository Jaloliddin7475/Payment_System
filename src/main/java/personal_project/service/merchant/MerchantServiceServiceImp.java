package personal_project.service.merchant;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import personal_project.dto.payload.merchant.MerchantServiceRegisterDto;
import personal_project.entity.merchant.MerchantEntity;
import personal_project.entity.merchant.MerchantServiceEntity;
import personal_project.repository.merchant.MerchantRepository;
import personal_project.repository.merchant.MerchantServiceRepository;

import java.util.Optional;

@Service("merchantServiceServiceImp")
@RequiredArgsConstructor
public class MerchantServiceServiceImp implements MerchantService<MerchantServiceRegisterDto> {

    private final MerchantServiceRepository merchantServiceRepository;
    private final MerchantRepository merchantRepository;

    @Override
    public Boolean add(MerchantServiceRegisterDto merchantServiceRegisterDto){
        Optional<MerchantEntity> optionalMerchant = merchantRepository.findById(merchantServiceRegisterDto.getMerchantId());

        //// TODO: 11.04.2022 add another appropriate exception
        if(optionalMerchant.isEmpty())
            throw new UsernameNotFoundException("merchant not found");

        MerchantServiceEntity merchantServiceEntity = new MerchantServiceEntity();
        merchantServiceEntity.setMerchantEntity(optionalMerchant.get());
        merchantServiceEntity.setMerchantServiceId(merchantServiceRegisterDto.getMerchantServiceId());
        merchantServiceEntity.setName(merchantServiceRegisterDto.getName());
        merchantServiceRepository.save(merchantServiceEntity);

        return true;
    }

}
