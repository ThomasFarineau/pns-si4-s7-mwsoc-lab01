package implem;

import interfaces.IClientBox;
import interfaces.IVODService;
import utils.Bill;
import utils.MovieDesc;
import utils.MovieDescExtended;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class VODService extends UnicastRemoteObject implements IVODService {
    List<MovieDesc> movieDescList;

    public VODService() throws RemoteException {
        super();
        movieDescList = new ArrayList<>();
        instantiateMovies();
    }

    private void instantiateMovies() {
        movieDescList.add(new MovieDesc(
                "La cité de la peur",
                "8975",
                "Odile Deray, attachée de presse, vient au Festival de Cannes pour présenter le film " +
                        "\"Red is Dead\". Malheureusement, celui-ci est d'une telle faiblesse que personne ne souhaite " +
                        "en faire l'écho. Cependant, lorsque les projectionnistes du long-métrage en question " +
                        "meurent chacun leur tour dans d'étranges circonstances, \"Red is dead\" bénéficie d'une " +
                        "incroyable publicité. Serge Karamazov est alors chargé de protéger le nouveau " +
                        "projectionniste du film."));
        movieDescList.add(new MovieDescExtended(
                "La soupe aux choux",
                "5776",
                "Le Claude et Le Bombé vivent dans un petit hameau. Le premier est veuf, le second " +
                        "célibataire. Ensemble, ils passent la plupart de leur temps à trinquer. Une nuit, un " +
                        "extraterrestre atterrit dans le champ de Claude. Il ne semble pas agressif. Le Bombé est " +
                        "réveillé, lui aussi, par la lumière émise par la soucoupe, mais l'extraterrestre, que Le " +
                        "Claude surnommera \"La Denrée,\" le paralyse sur place.",
                new byte[1000])); // placeholder
    }

    public List<MovieDesc> viewCatalog() {
        return movieDescList;
    }

    public Bill playmovie(String isbn, IClientBox box) {
        return null;
    }
}
