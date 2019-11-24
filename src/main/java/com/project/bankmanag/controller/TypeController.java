package com.project.bankmanag.controller;

import com.project.bankmanag.dto.TypeDTO;
import com.project.bankmanag.mapper.TypeMapper;
import com.project.bankmanag.model.Type;
import com.project.bankmanag.service.type.TypeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/types")
public class TypeController {

    private TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping
    @ApiOperation("Find all Types")
    @ApiResponses(value = @ApiResponse(code = 200, message = "List of types received successfully"))
    public ResponseEntity<Page<TypeDTO>> findAll(Pageable pageable) {
        Page<TypeDTO> typeDTOPage = typeService.getAll(pageable).map(TypeMapper::toTypeDTO);
        return ResponseEntity.status(HttpStatus.OK).body(typeDTOPage);
    }

    @PostMapping
    @ApiOperation("Add new Type")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Type created successfully"),
            @ApiResponse(code = 409, message = "A type already exists with same name"),
            @ApiResponse(code = 400, message = "Field validation failed")})
    public ResponseEntity<TypeDTO> addNewType(@RequestBody @Valid Type newType) {
        TypeDTO expectedTypeDTO = TypeMapper.toTypeDTO(typeService.addType(newType));
        return ResponseEntity.status(HttpStatus.OK).body(expectedTypeDTO);
    }

    @GetMapping("{typeId}")
    @ApiOperation("Find Type by Id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Type found"),
            @ApiResponse(code = 404, message = "Could not find type")})
    public ResponseEntity<TypeDTO> getTypeById(@PathVariable Long typeId) {
        Type expectedType = typeService.getTypeById(typeId);
        return expectedType != null ?
                ResponseEntity.status(HttpStatus.OK).body(TypeMapper.toTypeDTO(expectedType)) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @PutMapping("{typeId}")
    @ApiOperation("Update Type")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Type updated successfully"),
            @ApiResponse(code = 404, message = "Could not find type to update"),
            @ApiResponse(code = 400, message = "Field validation failed")})
    public ResponseEntity<TypeDTO> updateType(@RequestBody @Valid Type newType, @PathVariable Long typeId) {
        Type updateType = typeService.updateType(newType, typeId);
        return updateType != null ?
                ResponseEntity.status(HttpStatus.OK).body(TypeMapper.toTypeDTO(updateType)) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("{typeId}")
    @ApiOperation("Delete Type by Id")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "Type deleted successfully"),
            @ApiResponse(code = 404, message = "Could not find type to delete")})
    public ResponseEntity<Void> removeType(@PathVariable Long typeId) {
        typeService.deleteType(typeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
