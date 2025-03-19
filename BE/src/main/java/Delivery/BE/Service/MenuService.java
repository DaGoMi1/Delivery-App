package Delivery.BE.Service;

import Delivery.BE.DTO.CreateMenuDTO;
import Delivery.BE.Domain.*;
import Delivery.BE.Exception.ForbiddenException;
import Delivery.BE.Exception.NotFoundException;
import Delivery.BE.Repository.CategoryRepository;
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
