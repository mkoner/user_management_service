package MkonerLivraison.GestionUtilisateurs.constant;

public class SecurityConstante {
	public static final long EXPIRATION_TIME = 432_000_000; // 5 days expressed in milliseconds
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Jetons pas verifiable";
    public static final String TOKEN_PROVIDER = "Mkoner Livraison";
    public static final String TOKEN_USERS_CLIENT = "Client Mkoner Livraison";
    public static final String TOKEN_USERS_LIVREUR = "Livreur  Mkoner Livraison";
    public static final String TOKEN_USERS_OPERATEUR = "Operateur Mkoner Livraison";
    public static final String TOKEN_USERS_ADMIN = "Admin Mkoner Livraison";
    public static final String TOKEN_USERS_MANAGER = "Manager Mkoner Livraison";
    public static final String AUTHORITIES = "authorities";
    public static final String FORBIDDEN_MESSAGE = "Vous devrez vous connectez afin d'acceder à cette page ";
    public static final String ACCESS_DENIED_MESSAGE = "Vous ne disposez pas de permission pour accesseder à cette page";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
    public static final String[] PUBLIC_URLS = { "/user/login", "/user/register"};
    //public static final String[] PUBLIC_URLS = { "**" };
}
