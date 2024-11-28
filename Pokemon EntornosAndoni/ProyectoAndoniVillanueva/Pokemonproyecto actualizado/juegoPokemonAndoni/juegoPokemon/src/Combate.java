/**
 * @author Andoni Villanueva
 * Esto es la clase de combate
 * Ronda y Ganador, Para gestionar como va la partida, varias llamadas en la interfaz
 */

public class Combate {

    // Añada los atributos y el constructor *************
private Pokemon pokemonJugador;
private Pokemon pokemonRival;

    public Combate(Pokemon pokemonJugador, Pokemon pokemonRival) {
        this.pokemonJugador = pokemonJugador;
        this.pokemonRival = pokemonRival;
    }


    //***************************************************

// metodo para controlar las rondas de combate
    public String ronda() {

        int poderJugador = pokemonJugador.calcularPoder(pokemonRival);


        int poderRival = pokemonRival.calcularPoder(pokemonJugador);


        System.out.println("Poder de ataque del Pokémon del jugador: " + poderJugador);
        System.out.println("Poder de ataque del Pokémon rival: " + poderRival);


        if (poderJugador > poderRival) {
            System.out.println("El Pokémon del jugador gana el turno!");
            return "Jugador";
        } else if (poderJugador < poderRival) {
            System.out.println("El Pokémon rival gana el turno!");
            return "Rival";
        } else {
            System.out.println("Empate en el turno!");
            return null;
        }
    }

// este metodo lo que hace es Darnos el ganador del combate y juega con el aguante
    public String Ganador() {
        if (pokemonJugador.getAguante() > 0 && pokemonRival.getAguante() <= 0) {
            return "Jugador";
        } else if (pokemonRival.getAguante() > 0 && pokemonJugador.getAguante() <= 0) {
            return "Rival";
        } else {
            return null;
        }
    }


}
