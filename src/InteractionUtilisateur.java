import java.util.Scanner;

public class InteractionUtilisateur
{
    Scanner scanner = null;
    public InteractionUtilisateur()
    {
        scanner = new Scanner(System.in);
    }
    public int askForInt( String message)
    {
        View vue = new View();
        int reponse = -1;
        vue.printMessage(message);
       // System.out.println(message);
        while ( reponse < 0)
        {
            try {
                reponse = scanner.nextInt();

            } catch (Exception e) {
                vue.printError("You didn't type an integer");
                scanner.nextLine();
            }

        }
        return reponse;
    }
}
