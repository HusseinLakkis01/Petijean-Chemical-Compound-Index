
import java.io.File;

import java.util.Scanner;

/* if you want to test your own set of files, please make sure they
 * are from chEMBL because the parsing of files is done accoriding to
 * their format
 */
public class CHEMBL {

	public static void main(String[] args) throws Exception

	{
		// open the directory containing SDF files
		File folder = new File("/Users/husseinalilakkis/Desktop/CHEM/");

		// list all files in the directory into an array
		File[] listOfFiles = folder.listFiles();

		// for each file in the list
		for (File file : listOfFiles) {
			// open the file with scanner
			Scanner sc = new Scanner(file);

			// print the file name and path

			System.out.println(file.getName());

			// skip header of file
			sc.nextLine();
			sc.nextLine();

			// get the first 2 numbers in the 3rd line
			// which are atom numbers and bond numbers

			int atom = Integer.parseInt(sc.next());
			int bond = Integer.parseInt(sc.next());
			Graph g = new Graph(atom);
			System.out.println(" we have " + atom + " atoms and " + bond + " bonds");
			// skip the coordinate block, after knowing number of atoms
			for (int i = 0; i <= atom; i++)
				sc.nextLine();
			// iterate over the bonds and add them to graph
			for (int i = 0; i < bond; i++) {

				// separator between numbers is either one or 2 spaces
				// split accordingly into an array, but these need parsing
				String[] S = sc.nextLine().split(" {1,2}");
				// parse char to int
				int v = Integer.parseInt(S[1]);
				int w = Integer.parseInt(S[2]);
				System.out.println(v + " " + w);
				// add edge to graph
				g.addEdge(v - 1, w - 1);
			}
			// print the index
			System.out.println("The petijean index for this molecule is: " + g.petijean());
			System.out.println();
			System.out.println("      "
					+ "       ***************************************");

			// close scanner
			sc.close();

		}

	}

}
