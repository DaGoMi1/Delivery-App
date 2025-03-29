package Delivery.BE.Service;

import Delivery.BE.DTO.CreateOptionGroupDTO;
import Delivery.BE.DTO.ResponseOptionGroupDTO;
import Delivery.BE.DTO.UpdateOptionGroupDTO;
import Delivery.BE.Domain.Member;
import Delivery.BE.Domain.Menu;
import Delivery.BE.Domain.OptionGroup;
import Delivery.BE.Exception.ForbiddenException;
import Delivery.BE.Exception.NotFoundException;
import Delivery.BE.Repository.OptionGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OptionGroupService {
    private final OptionGroupRepository optionGroupRepository;
    private final MenuService menuService;
    private final MemberService memberService;

    @Transactional
    public void createOptionGroup(CreateOptionGroupDTO createOptionGroupDTO) {
        Menu menu = menuService.findMenuById(createOptionGroupDTO.getMenuId());
        menuService.checkMenuOwner(menu);

        OptionGroup optionGroup = OptionGroup.builder()
                .menu(menuService.findMenuById(createOptionGroupDTO.getMenuId()))
                .name(createOptionGroupDTO.getName())
                .build();

        optionGroupRepository.save(optionGroup);
    }

    @Transactional
    public void updateOptionGroup(Long id, UpdateOptionGroupDTO updateOptionGroupDTO) {
        OptionGroup optionGroup = findOptionGroupById(id);

        checkOptionGroupOwner(optionGroup);

        if (updateOptionGroupDTO.getName() != null) optionGroup.setName(updateOptionGroupDTO.getName());

        optionGroupRepository.save(optionGroup);
    }

    @Transactional
    public void deleteOptionGroup(Long id) {
        OptionGroup optionGroup = findOptionGroupById(id);
        checkOptionGroupOwner(optionGroup);
        optionGroupRepository.delete(optionGroup);
    }

    public List<ResponseOptionGroupDTO> getOptionGroups(Long menuId) {
        Menu menu = menuService.findMenuById(menuId);
        List<OptionGroup> optionGroups = menu.getOptionGroups();
        return optionGroups.stream().map(ResponseOptionGroupDTO::new).toList();
    }

    public OptionGroup findOptionGroupById(Long id) {
        return optionGroupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("옵션 그룹을 찾을 수 없습니다. ID: " + id));
    }

    public void checkOptionGroupOwner(OptionGroup optionGroup) { // 요청한 옵션 그룹에 대한 정보가 소유자가 맞는지 확인
        Member member = memberService.getMemberInfo();
        Member optionGroupOwner = optionGroup.getMenu().getStore().getMember();

        if (!Objects.equals(optionGroupOwner, member) && member.getRole() != Member.Role.ADMIN) {
            throw new ForbiddenException("해당 옵션 그룹의 소유자가 아닙니다.");
        }
    }
}
