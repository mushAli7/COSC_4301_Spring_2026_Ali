/*
 * Neon Ark Admin Warden Onboarding Console
 * Course: COSC-4301 Modern Programming
 *
 * Purpose:
 * Handles console menu navigation and user interaction
 * for the Neon Ark onboarding system.
 */

import menu.WardenMenu;

public class Main {

    public static void main(String[] args) {

        // Starts the Neon Ark command-line console.
        WardenMenu menu = new WardenMenu();
        menu.start();
    }
}