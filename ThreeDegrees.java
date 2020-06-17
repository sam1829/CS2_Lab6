/**
 * @author Susan Margevich sam1829
 * filename: ThreeDegrees.java
 * Main function: Locates and prints out connections between actors based on the movies they were in. Multiple checks
 * are performed to ensure the functionality is proper.
 */

import java.util.Scanner;
import java.util.List;
import java.io.FileNotFoundException;

public class ThreeDegrees
{
    /**
     * Constructor: sets up scanner to iterate through lines, prints out the movie graphic
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException
    {
        System.out.println("Enter movie data filename: ");
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        Graph graph = new Graph(str);
        System.out.println(graph.toString());
        /**
         * ALL CHECKS AND FUNCTION ARE WITHIN THE WHILE: TRUE LOOP.
         */
        while (true)
        // start with handling first input actor*
        // then handle cases with second input actor*
        // if that's all good, check if they're both in the graph*
        // if they're both in the graph, check if the number of graphs is 3 or less*
        // if hops are 3 or less, make sure they aren't the same person*
        // if there is no connection, tell them*
        // if they are connected, print out big fancy string
        {
            System.out.println("Enter first actor name (two case-sensitive words): ");
            String name1 = scan.nextLine();
            if (name1.isEmpty()) // if there is no input for the first name, break the program
            {
                break;
            }
            if (!graph.isInGraph(name1)) // if the first inputted name is not found in the graph, print error to user and restart while loop
            {
                System.out.println(name1 + " is not known.");
                continue;
            }
            System.out.println("Enter second actor name (two case-sensitive words): ");
            String name2 = scan.nextLine();
            if (name2.isEmpty()) // if there is no input for the second name, break the program
            {
                break;
            }
            if (!graph.isInGraph(name2)) // if the second inputted name is not found in the graph, print error to user and restart while loop
            {
                System.out.println(name2 + " is not known.");
                continue;
            }
            if (graph.isInGraph(name1) && graph.isInGraph(name2))
            {
                List<Node> hops = graph.searchBFS(name1, name2);
                if (((hops.size() - 1 )/ 2 ) > 3) // math is performed to make hop size true to movies and not create a hop within actors
                {
                    System.out.println("No path less than or equal to a 3-hop distance exists between " + name1 + " and " + name2 + ".");
                    continue;
                }
                else
                {
                    if (name1.equals(name2)) // if the two actors are the same, error is printed, and the while loop restarts
                    {
                        System.out.println("Those are the same actors. Try again.");
                        continue;
                    }
                    if (hops.isEmpty()) // if there are no hops, then no connection exists, and the while loop restarts
                    {
                        System.out.println("There is no connection between these actors. Sorry. Try again.");
                        continue;
                    }
                    else
                    {
                        if (((hops.size() - 1 )/ 2 ) == 3) // if the amount of hops is 3, the print statement is as follows:
                        {
                            System.out.println(hops.get(0).getName() + " was in " + hops.get(1).getName());
                            System.out.println("with " + hops.get(2).getName() + " who was in " + hops.get(3).getName());
                            System.out.println("with " + hops.get(4).getName() + " who was in " + hops.get(5).getName());
                            System.out.println("with " + hops.get(6).getName());
                        }
                        if (((hops.size() - 1 )/ 2 ) == 2) // if the amount of hops is 2, the print statement is as follows:
                        {
                            System.out.println(hops.get(0).getName() + " was in " + hops.get(1).getName());
                            System.out.println("with " + hops.get(2).getName() + " who was in " + hops.get(3).getName());
                            System.out.println("with " + hops.get(4).getName());
                        }
                        if (((hops.size() - 1 )/ 2 ) == 1) // if the amount of hops is 1, the print statement is as follows:
                        {
                            System.out.println(hops.get(0).getName() + " was in " + hops.get(1).getName());
                            System.out.println("with " + hops.get(2).getName());
                        }
                    }
                }
            }
        }
    }
}
