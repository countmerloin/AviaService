package AviaService.Controllers;

import AviaService.Services.MenuService;

public class MenuController {
    private MenuService menuService = new MenuService();

    public MenuController() {
        this.menuService = new MenuService();
    }

    public String help() {
        return menuService.printMenu();
    }
}
