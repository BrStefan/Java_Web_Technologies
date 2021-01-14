package com.BrStefan.BrStefan.service;

import com.BrStefan.BrStefan.domain.*;
import com.BrStefan.BrStefan.domain.dto.*;
import com.BrStefan.BrStefan.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class MenuServiceTest{

    @InjectMocks
    private MenuService menuService;

    @Mock
    private MenuRepository menuRepository;


    @Test
    void testBook(){
        MenuDTO menuDTO = new MenuDTO(1,"Test", "Item1 Price1\nItem2 Price2");
        Menu menu = new Menu(1,"Test", "Item1 Price1\nItem2 Price2");

        menuService.add(menuDTO);

        assertThat(menu.getDescription()).isEqualTo(menuDTO.getDescription());

    }

    @Test
    void testList(){
        List<Menu> menuList = new ArrayList<>();
        menuList.add(
                new Menu(1,"Test", "Item1 Price1\nItem2 Price2")
        );
        menuList.add(
                new Menu(1,"Test1", "2Item1 Price1\nItem2 Price2")
        );
        menuList.add(
                new Menu(1,"Test2", "3Item1 Price1\nItem2 Price2")
        );

        List<Menu> res = new ArrayList<>();
        res.add(
                new Menu(1,"Test1", "2Item1 Price1\nItem2 Price2")
        );
        res.add(
                new Menu(1,"Test", "Item1 Price1\nItem2 Price2")
        );

        when(menuService.all()).thenReturn(res);

        assertThat(menuList.get(0).getDescription()).isEqualTo(res.get(1).getDescription());
        assertThat(menuList.get(1).getDescription()).isEqualTo(res.get(0).getDescription());
    }

}