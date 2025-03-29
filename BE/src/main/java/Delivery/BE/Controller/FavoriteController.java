package Delivery.BE.Controller;

import Delivery.BE.DTO.CreateFavoriteDTO;
import Delivery.BE.DTO.ResponseFavoriteDTO;
import Delivery.BE.Service.FavoriteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/favorite")
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;

    @PostMapping("")
    public ResponseEntity<?> addFavorite(@Valid @RequestBody CreateFavoriteDTO createFavoriteDTO) {
        favoriteService.createFavorite(createFavoriteDTO);
        return ResponseEntity.ok("가게 찜 완료");
    }

    @GetMapping("")
    public ResponseEntity<?> getFavorites() {
        List<ResponseFavoriteDTO> favoriteDTOList = favoriteService.getFavorites();
        return ResponseEntity.ok(favoriteDTOList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFavorite(@PathVariable Long id) {
        favoriteService.deleteFavorite(id);
        return ResponseEntity.ok("찜 삭제 완료");
    }
}
