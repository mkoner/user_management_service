package MkonerLivraison.GestionUtilisateurs.exception.domain;

public class UsernameNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UsernameNotFoundException (String message) {
        super(message);
    }

}
