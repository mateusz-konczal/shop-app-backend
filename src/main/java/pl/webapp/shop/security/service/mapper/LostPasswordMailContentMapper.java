package pl.webapp.shop.security.service.mapper;

public final class LostPasswordMailContentMapper {

    private LostPasswordMailContentMapper() {
    }

    public static String createPasswordMailContent(String email, String hashLink) {
        return "Witaj " + email + "!"
                + "\n\nKtoś poprosił o wygenerowanie linku do zmiany Twojego hasła."
                + "\nKliknij w poniższy link, aby zresetować hasło: "
                + "\n" + hashLink
                + "\n\nJeśli to pomyłka, zignoruj tę wiadomość e-mail."
                + "\nTwoje hasło nie zmieni się, dopóki nie wejdziesz w link powyżej i nie stworzysz nowego."
                + "\n\nDziękujemy"
                + "\nSklep Shop";
    }
}
