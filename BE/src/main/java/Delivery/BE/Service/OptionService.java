package Delivery.BE.Service;

import Delivery.BE.DTO.CreateOptionDTO;
import Delivery.BE.DTO.UpdateOptionDTO;
import Delivery.BE.Domain.Member;
import Delivery.BE.Domain.Option;
import Delivery.BE.Domain.OptionGroup;
import Delivery.BE.Exception.ForbiddenException;
import Delivery.BE.Exception.NotFoundException;
import Delivery.BE.Repository.OptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OptionService {
    private final OptionRepository optionRepository;
    private final OptionGroupService optionGroupService;
    private final MemberService memberService;

    public void createOption(CreateOptionDTO createOptionDTO) {
        OptionGroup optionGroup = optionGroupService.findOptionGroupById(createOptionDTO.getOptionGroupId());
        optionGroupService.checkOptionGroupOwner(optionGroup);

        Option option = Option.builder()
                .name(createOptionDTO.getName())
                .price(createOptionDTO.getPrice())
                .optionGroup(optionGroupService.findOptionGroupById(createOptionDTO.getOptionGroupId()))
                .build();

        optionRepository.save(option);
    }

    @Transactional
    public void updateOption(Long id, UpdateOptionDTO updateOptionDTO) {
        Option option = findOptionById(id);

        checkOptionOwner(option);

        if (updateOptionDTO.getName() != null) option.setName(updateOptionDTO.getName());
        if (updateOptionDTO.getPrice() != null) option.setPrice(updateOptionDTO.getPrice());

        optionRepository.save(option);
    }

    @Transactional
    public void deleteOption(Long id) {
        Option option = findOptionById(id);
        checkOptionOwner(option);
        optionRepository.delete(option);
    }

    public Option findOptionById(Long id) {
        return optionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("옵션을 찾을 수 없습니다. ID: " + id));
    }

    private void checkOptionOwner(Option option) { // 요청한 옵션에 대한 정보가 소유자가 맞는지 확인
        Member member = memberService.getMemberInfo();
        Member optionOwner = option.getOptionGroup().getMenu().getStore().getMember();

        if (!Objects.equals(optionOwner, member)) {
            throw new ForbiddenException("해당 옵션의 소유자가 아닙니다.");
        }
    }
}
