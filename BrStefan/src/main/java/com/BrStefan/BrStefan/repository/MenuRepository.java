package com.BrStefan.BrStefan.repository;

import com.BrStefan.BrStefan.domain.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuRepository {

    private List<Menu> menuList;

    private Menu add(Menu menu){
        menuList.add(menu);
        return menu;
    }

    private List<Menu> getAll(){
        return menuList;
    }

    private Menu delete(Menu menu){
        menuList.remove(menu);
        return menu;
    }
}
