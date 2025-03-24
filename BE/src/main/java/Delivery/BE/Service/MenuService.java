package Delivery.BE.Service;

import Delivery.BE.DTO.CreateMenuDTO;
import Delivery.BE.DTO.UpdateMenuDTO;
import Delivery.BE.Domain.*;
import Delivery.BE.Exception.ForbiddenException;
import Delivery.BE.Exception.NotFoundException;
import Delivery.BE.Repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final StoreService storeService;
    private final MemberService memberService;

    @Transactional
    public void createMenu(CreateMenuDTO createMenuDTO) {
        Store store = storeService.findStoreById(createMenuDTO.getStoreId());
        storeService.checkStoreOwner(store);

        Menu menu = Menu.builder()
                .name(createMenuDTO.getName())
                .description(createMenuDTO.getDescription())
                .price(createMenuDTO.getPrice())
                .imageUrl(createMenuDTO.getImageUrl())
                .store(storeService.findStoreById(createMenuDTO.getStoreId()))
                .isAvailable(false)
                .build();

        menuRepository.save(menu);
    }

    @Transactional
    public void updateMenu(Long id, UpdateMenuDTO updateMenuDTO) {
        Menu menu = findMenuById(id);

        checkMenuOwner(menu);

        if (updateMenuDTO.getName() != null) menu.setName(updateMenuDTO.getName());
        if (updateMenuDTO.getDescription() != null) menu.setDescription(updateMenuDTO.getDescription());
        if(updateMenuDTO.getPrice() != null) menu.setPrice(updateMenuDTO.getPrice());
        if(updateMenuDTO.getImageUrl() != null) menu.setImageUrl(updateMenuDTO.getImageUrl());
        if (updateMenuDTO.getIsAvailable() != null) menu.setAvailable(updateMenuDTO.getIsAvailable());

        menuRepository.save(menu);
    }

    @Transactional
    public void deleteMenu(Long id) {
        Menu menu = findMenuById(id);
        checkMenuOwner(menu);
        menuRepository.delete(menu);
    }

    public Menu findMenuById(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("메뉴를 찾을 수 없습니다. ID: " + id));
    }

    public void checkMenuOwner(Menu menu) { // 요청한 메뉴에 대한 정보가 소유자가 맞는지 확인
        Member member = memberService.getMemberInfo();

        if (!Objects.equals(menu.getStore().getMember(), member)) {
            throw new ForbiddenException("해당 메뉴의 소유자가 아닙니다.");
        }
    }
}
