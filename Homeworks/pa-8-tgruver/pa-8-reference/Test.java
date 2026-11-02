import java.util.Comparator;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Collection;

public class Test{
    public static void main(String[] args){
        Collection<MapEntry<String,ArrayList<String>>> c = new ArrayList<>();
        ArrayList<String> colors = new ArrayList<>();
        colors.add("purple");
        c.add(new MapEntry<>("Orchid", colors));
        colors = new ArrayList<>(); colors.add("white");
        c.add(new MapEntry<>("Lily", colors));
        colors = new ArrayList<>(); colors.add("white"); colors.add("pink");
        c.add(new MapEntry<>("Lotus", colors));
        colors = new ArrayList<>(); colors.add("white"); colors.add("pink"); colors.add("red");
        c.add(new MapEntry<>("Poeny", new ArrayList<String>()));

        // Testing the PriorityQueue
        System.out.println("Priority Queue with flowers ordered by name");
        class ComparatorByName implements Comparator<MapEntry<String, ArrayList<String>>>{
            public int compare(MapEntry<String, ArrayList<String>> e1,
                               MapEntry<String, ArrayList<String>> e2){
                return e1.getKey().compareTo(e2.getKey());
            }
        };
        PriorityQueue<MapEntry<String, ArrayList<String>>> pq = new PriorityQueue<>(new ComparatorByName());
        
        
        System.out.print("\nTest case 1: PriorityQueue constructued correctly (method offer)\n");
        readFile(pq, "flowers.txt");
        System.out.println(pq.size() + " flowers read from the file and added to the priority queue");
        print(pq.iterator());

        System.out.print("\nTest case 2: Method contains (successful)\n");
        System.out.println("Heap contains \"Rose\"? " + pq.contains(new MapEntry<String, ArrayList<String>>("Rose", null)));

        System.out.print("\nTest case 3: Method contains (fail)\n");
        System.out.println("Heap contains \"Coquelicot\"? " + pq.contains(new MapEntry<String, ArrayList<String>>("Coquelicot", null)));

        System.out.print("\nTest case 4: Method remove (successful)\n");
        System.out.println("\"Hyacinth\" removed? " + pq.remove(new MapEntry<String, ArrayList<String>>("Hyacinth", null)));

        System.out.print("\nTest case 5: Method remove (fail)\n");
        System.out.println("\"Musk\" removed? " + pq.remove(new MapEntry<String, ArrayList<String>>("Musk", null)));

        System.out.print("\nTest case 6: Method addAll\n");
        PriorityQueue<MapEntry<String,ArrayList<String>>> pqCopy = (PriorityQueue<MapEntry<String,ArrayList<String>>>) (pq.clone());
        pqCopy.addAll(c);
        print(pqCopy.iterator());

        System.out.print("\nTest case 7: Method retainAll\n");
        pqCopy = (PriorityQueue<MapEntry<String,ArrayList<String>>>) (pq.clone());
        pqCopy.retainAll(c);
        print(pqCopy.iterator());

        System.out.print("\nTest case 8: Method removeAll\n");
        pqCopy = (PriorityQueue<MapEntry<String,ArrayList<String>>>) (pq.clone());
        pqCopy.removeAll(c);
        print(pqCopy.iterator());

        System.out.println("\nPriority Queue with flowers ordered by their number of colors");
        class ComparatorByColors implements Comparator<MapEntry<String, ArrayList<String>>>{
            public int compare(MapEntry<String, ArrayList<String>> e1,
                               MapEntry<String, ArrayList<String>> e2){
                return e1.getValue().size() - e2.getValue().size();
            }
        };
        pq = new PriorityQueue<>(new ComparatorByColors());
        
        readFile(pq, "flowers.txt");
        System.out.print("\nTest case 9: PriorityQueue constructued using a custom order: the number of colors \n");
        print(pq.iterator());

        /////////////////////////////////////
        // Testing the TreeMap
        ////////////////////////////////////
        System.out.println("\n\nTreeMap with flowers ordered by name");
        TreeMap<String,ArrayList<String>> tree = new TreeMap<>(null);
        
        
        System.out.print("\nTest case 10: BST constructued correctly  (method put) \n");
        readFile(tree, "flowers.txt");
        System.out.println(tree.size() + " flowers read from the file and added to the binary search tree");
        print(tree.iterator());
        System.out.println();

        System.out.print("\nTest case 11: Method contains (successful)\n");
        System.out.println("BST contains \"Rose\"? " + tree.containsKey("Rose"));

        System.out.print("\nTest case 12: Method get (successful)\n");
        System.out.println("BST get \"Rose\": " + tree.get("Rose"));

        System.out.print("\nTest case 13: Method contains (fail)\n");
        System.out.println("BST contains \"Coquelicot\"? " + tree.containsKey("Coquelicot"));

        System.out.print("\nTest case 14: Method get (fail)\n");
        System.out.println("BST get \"Coquelicot\": " + tree.get("Coquelicot"));

        System.out.print("\nTest case 15: Method remove (successful)\n");
        System.out.println("\"Hyacinth\" removed? " + tree.remove("Hyacinth"));

        System.out.print("\nTest case 16: Method remove (fail)\n");
        System.out.println("\"Musk\" removed? " + tree.remove("Musk"));

        System.out.print("\nTest case 17: Method addAll\n");
        TreeMap<String,ArrayList<String>> treeCopy = (TreeMap<String,ArrayList<String>>) (tree.clone());
        treeCopy.addAll(c);
        print(treeCopy.iterator());

        System.out.print("\nTest case 18: Method retainAll\n");
        treeCopy = (TreeMap<String,ArrayList<String>>) (tree.clone());
        treeCopy.retainAll(c);
        print(treeCopy.iterator());

        System.out.print("\nTest case 19: Method removeAll\n");
        treeCopy = (TreeMap<String,ArrayList<String>>) (tree.clone());
        treeCopy.removeAll(c);
        print(treeCopy.iterator());

        System.out.print("\nTest case 20: Method first\n");
        System.out.println("First: " + tree.first());

        System.out.print("\nTest case 21: Method last\n");
        System.out.println("Last : " + tree.last());

        System.out.print("\nTest case 22: Method ceiling (succesful: key is the ceiling)\n");
        System.out.println("Ceiling(\"Daisy\") : " + tree.ceiling("Daisy"));

        System.out.print("\nTest case 23: Method ceiling (succesful: ceiling found)\n");
        System.out.println("Ceiling(\"Dandrum\") : " + tree.ceiling("Dandrum"));

        System.out.print("\nTest case 24: Method ceiling (fail: no ceiling)\n");
        System.out.println("Ceiling(\"Zebra\") : " + tree.ceiling("Zebra"));

        System.out.print("\nTest case 25: Method floor (successful: key is the floor)\n");
        System.out.println("floor(\"Iris\") : " + tree.floor("Iris"));

        System.out.print("\nTest case 26: Method floor (successful: floor found)\n");
        System.out.println("floor(\"Boba\") : " + tree.floor("Boba"));

        System.out.print("\nTest case 27: Method floor (fail: no floor)\n");
        System.out.println("floor(\"Art\") : " + tree.floor("Art"));

        System.out.print("\nTest case 28: Method keys\n");
        System.out.println("BST keys: " + tree.keys());

        System.out.print("\nTest case 29: Method values\n");
        System.out.println("BST values: " + tree.values());

        System.out.println("\n\nTreeMap with flowers orderd by their name length");
        class ComparatorByLength implements Comparator<String>{
            public int compare(String s1, String s2){
                return s1.length() - s2.length();
            }
        };
        tree.clear();
        tree = new TreeMap<>(new ComparatorByLength());
        readFile(tree, "flowers.txt");
        System.out.print("\nTest case 30: BST constructued using a custom order: the flower name length\n");
        print(tree.iterator());
        System.out.println();
    }
    public static void readFile(TreeMap<String,ArrayList<String>> tree, String filename){
        try{
            Scanner read = new Scanner(new File(filename));
            while(read.hasNextLine()){
                String line = read.nextLine();
                String[] items = line.split(",");
                ArrayList<String> colors = new ArrayList<>();
                for(int i=1; i<items.length; i++){
                    colors.add(items[i]);
                }
                tree.put(items[0], colors);
            }
            read.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
    }
    public static void readFile(PriorityQueue<MapEntry<String,ArrayList<String>>> pq, String filename){
        try{
            Scanner read = new Scanner(new File(filename));
            while(read.hasNextLine()){
                String line = read.nextLine();
                String[] items = line.split(",");
                ArrayList<String> colors = new ArrayList<>();
                for(int i=1; i<items.length; i++){
                    colors.add(items[i]);
                }
                pq.offer(new MapEntry<String, ArrayList<String>>(items[0], colors));
            }
            read.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
    }
    public static <E> void print(Iterator<E> iter){
        while(iter.hasNext()){
            System.out.println(iter.next());
        }
    }
}