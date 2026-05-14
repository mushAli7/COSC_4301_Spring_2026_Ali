import api.ApiClient;
import api.CreatureApi;
import menu.MainMenu;

/*
 * Entry point for the Neon Ark Creature Management CLI.
 */
public class Main {

    public static void main(String[] args) {

        ApiClient apiClient = new ApiClient();
        CreatureApi creatureApi = new CreatureApi(apiClient);

        MainMenu mainMenu = new MainMenu(creatureApi);
        mainMenu.run();
    }
}