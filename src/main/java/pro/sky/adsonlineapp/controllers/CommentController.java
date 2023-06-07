package pro.sky.adsonlineapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.adsonlineapp.dto.ResponseWrapperComment;
import pro.sky.adsonlineapp.model.Comment;

/**
 * контроллер для работы с комментариями
 */
@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/comment")
public class CommentController {

    @PostMapping("/{id}")
    @Operation(
            summary = "Добавить комментарий к объявлению",
            description = "Нужно написать комментарий "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Комментарий добавлен"
    )
    @ApiResponse(
            responseCode = "401",
            description = "для того чтобы оставить комментарий необходимо авторизоваться"
    )
    public ResponseEntity<ResponseWrapperComment> saveComment(@PathVariable("id") int id,
                                                         @RequestBody ResponseWrapperComment comments) {

        return ResponseEntity.ok(comments);
    }



    @GetMapping("/{id}")
    @Operation(
            summary = "Получить комментарии объявления",
            description = "Нужно написать id автора "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Комментарий найден"
    )
    @ApiResponse(
            responseCode = "401",
            description = "для того чтобы найти комментарий необходимо авторизоваться"
    )
    public ResponseEntity<ResponseWrapperComment> getComments(@PathVariable("id") int id) {
        //Создание нового объекта ResponseWrapperComment,
        //который будет заполнен данными о комментариях и
        //возвращен в качестве ответа на запрос.
        ResponseWrapperComment response = new ResponseWrapperComment();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление комментарий",
            description = "нужно написать комментарий id и рекламу id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Удалось удалить комментарий"
    )
    @ApiResponse(
            responseCode = "401",
            description = "чтобы удалить комментарий необходимо авторизоваться"
    )
    @ApiResponse(
            responseCode = "403",
            description = "отстутсвуют права доступа"
    )
    public ResponseEntity<Object> deleteComments(@PathVariable Integer pk,
                                                 @PathVariable Integer commentId) {

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    @Operation(
            summary = "обновление комментария",
            description = "нужно написать комментарий id и рекламу id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Удалось обновить комментарий"
    )
    @ApiResponse(
            responseCode = "401",
            description = "чтобы обновить комментарий необходимо авторизоваться"
    )
    @ApiResponse(
            responseCode = "403",
            description = "отстутсвуют права доступа"
    )
    public ResponseEntity<Comment> updateComments(@PathVariable Integer id,
                                                  @PathVariable Integer commentId) {

        return ResponseEntity.ok().build();
    }
    }