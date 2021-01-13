package com.BrStefan.BrStefan.service;

import com.BrStefan.BrStefan.domain.Menu;
import com.BrStefan.BrStefan.domain.dto.MenuDTO;
import com.BrStefan.BrStefan.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public void add(MenuDTO menuDTO){
        menuRepository.add(menuDTO);
    }

    public List<Menu> all(){
        return menuRepository.all();
    }
}
