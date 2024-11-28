/**
 * @author Andoni Villanueva
 * Esta clase es la principal en la gestión de la lógica del juego Pokémon.
 * Se encarga de manejar la interacción con el jugador, incluyendo la creación
 * del Pokémon del jugador, el flujo de combate, y la selección del Pokémon rival.
 * También contiene menús y mensajes para guiar al usuario a lo largo del juego.
 */

import java.util.Scanner;

public class InterfazPokemon {

    // Atributo para leer entradas del usuario desde la consola
    private Scanner teclado;

    // Constructor: Inicializa el scanner para capturar entradas de usuario
    public InterfazPokemon() {
        teclado = new Scanner(System.in);
    }

    /**
     * Método principal del juego.
     * Maneja la lógica completa, incluyendo la creación del Pokémon de jugador,
     * el combate con tres Pokémon rivales, y la gestión de mensajes de victoria o derrota.
     */
    public void Juego() {
        System.out.println("..........................................................");
        System.out.println(" Crea tu Pokémon ......");
        System.out.println(" ..........................................................");

        // Crea el Pokémon del jugador mediante un menú interactivo
        Pokemon pokemonJugador = menuCreacionPokemonJugador();

        // Bucle para realizar combates contra 3 Pokémon rivales
        for (int i = 1; i <= 3; i++) {
            // Selección del Pokémon rival según el número de ronda
            Pokemon pokemonRival = siguientePokemonRival(i);

            System.out.println("Presentación del Pokémon oponente:");
            System.out.println(pokemonRival.toString());
            System.out.println("PULSE ENTER PARA CONTINUAR");
            teclado.nextLine();

            // Realiza el combate entre el Pokémon del jugador y el rival
            Pokemon ganador = Partida(pokemonJugador, pokemonRival);

            if (ganador == pokemonJugador) {
                // Si gana el jugador
                System.out.println("Genial: Has derrotado a " + pokemonRival.getNombre());
                if (i == 3) {
                    // Si es el último rival, muestra el mensaje de victoria
                    mostrarJuegoSuperado();
                    return;
                } else {
                    // Subir nivel del Pokémon del jugador tras ganar una ronda
                    pokemonJugador.subirNivel();
                }
            } else {
                // Si pierde el jugador, muestra el mensaje de derrota y termina
                System.out.println(pokemonRival.getNombre() + " te ha derrotado.");
                mostrarFinPartida();
                return;
            }
        }
    }

    /**
     * Método que maneja el combate entre el Pokémon del jugador y el rival.
     * Se ejecuta en rondas hasta que uno de los Pokémon pierda todo su aguante.
     *
     * @param pokemonJugador El Pokémon del jugador.
     * @param pokemonRival El Pokémon rival.
     * @return El Pokémon ganador del combate.
     */
    private Pokemon Partida(Pokemon pokemonJugador, Pokemon pokemonRival) {
        Combate combate = new Combate(pokemonJugador, pokemonRival);
        System.out.println("El combate comienza!");

        // Bucle de combate por rondas
        while (pokemonJugador.getAguante() > 0 && pokemonRival.getAguante() > 0) {
            String ganadorRonda = combate.ronda(); // Determina el ganador de la ronda

            // Manejo del resultado de la ronda
            if (ganadorRonda == null) {
                System.out.println("Empate en esta ronda");
            } else if (ganadorRonda.equals("Jugador")) {
                pokemonRival.disminuirAguante(); // Reducir aguante del rival
                System.out.println("Gana la ronda: " + pokemonJugador.getNombre());
                pokemonJugador.subirNivel();
                pokemonJugador.actualizarStats(); // Mejora estadísticas tras la ronda
            } else {
                pokemonJugador.disminuirAguante(); // Reducir aguante del jugador
                System.out.println("Gana la ronda: " + pokemonRival.getNombre());
            }

            // Mostrar los valores de aguante actuales de ambos Pokémon
            System.out.println("Aguante de " + pokemonJugador.getNombre() + ": " + pokemonJugador.getAguante());
            System.out.println("Aguante de " + pokemonRival.getNombre() + ": " + pokemonRival.getAguante());
        }

        // Devuelve el Pokémon ganador
        if (pokemonJugador.getAguante() > 0) {
            return pokemonJugador;
        } else {
            return pokemonRival;
        }
    }

    /**
     * Muestra un menú para que el jugador cree su Pokémon seleccionando un nombre y un tipo.
     *
     * @return El Pokémon creado por el jugador.
     */
    private Pokemon menuCreacionPokemonJugador() {
        System.out.println("Introduce un nombre:");
        String nombre = teclado.nextLine();

        System.out.println("Elige su tipo:");
        System.out.println(" 1.- Agua");
        System.out.println(" 2.- Tierra");
        System.out.println(" 3.- Fuego");

        int tipoSeleccionado = teclado.nextInt();
        teclado.nextLine(); // Consumir el salto de línea restante

        String tipo = "";
        if (tipoSeleccionado == 1) {
            tipo = "Agua";
        } else if (tipoSeleccionado == 2) {
            tipo = "Tierra";
        } else if (tipoSeleccionado == 3) {
            tipo = "Fuego";
        } else {
            System.out.println("Opción no válida, seleccionando tipo Agua por defecto.");
            tipo = "Agua";
        }

        return new Pokemon(nombre, tipo);
    }

    /**
     * Genera el Pokémon rival según el número de ronda.
     *
     * @param numero El número de ronda actual.
     * @return El Pokémon rival correspondiente a la ronda.
     */
    public Pokemon siguientePokemonRival(int numero) {
        if (numero == 1) {
            return new Pokemon("Caterpie", "Tierra", 1);
        } else if (numero == 2) {
            return new Pokemon("Bulbasur", "Agua", 2);
        } else if (numero == 3) {
            return new Pokemon("Charmander", "Fuego", 3);
        } else {
            return null; // Caso por defecto si el número no es válido
        }
    }

    /**
     * Muestra un mensaje de victoria cuando el jugador supera el juego.
     */
    private void mostrarJuegoSuperado() {
        System.out.println("++++++++++ ENHORABUENA +++++++++++");
        System.out.println("+++++ HAS SUPERADO EL JUEGO ++++++");
        System.out.println("++++ ERES UN MAESTRO POKÉMON +++++");
    }

    /**
     * Muestra un mensaje de derrota cuando el jugador pierde un combate.
     */
    private void mostrarFinPartida() {
        System.out.println("++++++++++ LO SIENTO +++++++++++");
        System.out.println("+++++ HAS SIDO ELIMINADO ++++++");
        System.out.println("+++++ VUELVE A INTENTARLO +++++");
    }
}
