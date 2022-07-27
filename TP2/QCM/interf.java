public interface interf{

	private void printPrivate(){
		System.out.println("Methode 'private'");
	}
	
	default void printPrivateDef(){
		printPrivate();
	}
	
	
    default void printDefault(){
		System.out.println("Methode 'default'");
	}
	
}
