package AviaService.Services;

import AviaService.Menu;

public class MenuService {
    public String printMenu() {
        return new Menu().show();
    }
}

