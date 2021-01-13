package com.BrStefan.BrStefan.controller;

import com.BrStefan.BrStefan.domain.Menu;
import com.BrStefan.BrStefan.domain.dto.MenuDTO;
import com.BrStefan.BrStefan.exceptions.MenuException;
import com.BrStefan.BrStefan.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/menu")
@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody @Valid MenuDTO menuDTO){
        try{
            menuService.add(menuDTO);
            return ResponseEntity.ok().body("Menu added");
        }catch (MenuException e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Object> all(){
        try{
            List<Menu> menus = menuService.all();
            return ResponseEntity.ok().body(menus);
        }catch (MenuException e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
