package niuke;

import java.util.*;

/**
 * Created by DamonLiu on 2017/6/13.
 */
public class p3 {
    public enum OPERATION {
        BUY(1),
        USE(0),
        UNKNOWN(-1);
        int opNum;
        OPERATION(int i) {
            this.opNum = i;
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[][] input = new int[n][2];
            for (int i = 0; i < n; i++) {
                String label = sc.next();
                switch (label) {
                    case "I":
                        input[i][0] = 1;
                        input[i][1] = sc.nextInt();
                        break;
                    case "O":
                        input[i][0] = 0;
                        input[i][1] = sc.nextInt();
                        break;
                    case "?":
                        input[i][0] = -1;
                        input[i][1] = -1;
                        break;
                }
            }
            System.out.println(maxLeagleLineIndex(input));
        }
    }

    /**
     * -1 无措 或 x(1 ≤ x ≤ m) 其中x为使得1到x-1这些记录合法的最大行号。
     * also need to remember the last position where a ticket is used.
     * the last time point where a ticket is bought.
     * the list of ?s .
     * @param input
     * @return
     */
    public static int maxLeagleLineIndex(int[][] input) {
        HashMap<Integer,Integer> ticketBoughtTimePoint = new HashMap<>(); // save the <ticket,line_index> pairs, all bought tickets.
        HashMap<Integer,Integer> ticketUsedTimePoint = new HashMap<>();
        List<Integer> unknownOperations = new ArrayList<>(); // save the ? indexes.
        HashSet<Integer> ticketInHand = new HashSet<>();

        int ticket;
        OPERATION op;
        int errorLineIndex = -1;// line starts from 1.

        for (int line = 0; line < input.length; line++) {
            ticket = input[line][1];
            op = operation(input[line][0]);
            switch (op){
                case BUY:
                    if (canBuy(ticket,ticketInHand)){
                        ticketInHand.add(ticket);
                        ticketBoughtTimePoint.put(ticket,line);
                    } else {
                        //try to find a feasible solution.
                        int lastBoughtLineIndex = ticketBoughtTimePoint.get(ticket);
                        int currentLineIndex = line;
                        int feasibleSolution = -1;
                        int count = 0;
                        for (int unknowIndex : unknownOperations){
                            if (unknowIndex < currentLineIndex && unknowIndex > lastBoughtLineIndex){
                                feasibleSolution = unknowIndex;
                                break; // find the first feasible solution and return the line index.
                            }
                            count++;
                        }

                        //if have a solution.
                        if (feasibleSolution >= 0){ // if found a feasible solution
                            input[feasibleSolution][0] = 0;
                            input[feasibleSolution][1] = ticket;
                            unknownOperations.remove(count);
                            ticketBoughtTimePoint.put(ticket,line);
                            ticketUsedTimePoint.put(ticket,feasibleSolution);
                        } else{
                            errorLineIndex = line+1;
                            break;
                        }
                    }
                    break;

                case USE:
                    if (canUse(ticket,ticketInHand)){
                        ticketInHand.remove(ticket);
                        ticketUsedTimePoint.put(ticket,line);
                    }else{
                        int beginLineIndex = 0;
                        if (ticketUsedTimePoint.containsKey(ticket)){
                            beginLineIndex = ticketUsedTimePoint.get(ticket);
                        }
                        int feasible = -1;
                        for (int i = 0; i < unknownOperations.size(); i++) {
                            if (unknownOperations.get(i) >= beginLineIndex && unknownOperations.get(i) < line){
                                feasible = unknownOperations.remove(i);
                                break;
                            }
                        }

                        if (feasible >= 0){ // find the solution, set the bought time .
                            input[feasible][0] = 1;
                            input[feasible][1] = ticket;
                            ticketBoughtTimePoint.put(ticket,feasible);
                            ticketUsedTimePoint.put(ticket,line);
                        } else{  // not found a solution.
                            errorLineIndex = line+1;
                            break;
                        }
                    }
                    break;

                case UNKNOWN:
                    unknownOperations.add(line);
                    break;
            }

        }
        return errorLineIndex;
    }

    public static boolean canBuy(int ticketNumber,HashSet<Integer> ticketInHand){
        return !(ticketInHand.contains(ticketNumber));
    }

    public static boolean canUse(int ticketNumber,HashSet<Integer> ticketInHand){
        return (ticketInHand.contains(ticketNumber));
    }

    public static OPERATION operation(int operation){
        switch (operation){
            case 0:
                return OPERATION.USE;
            case 1:
                return OPERATION.BUY;
            case -1:
                return OPERATION.UNKNOWN;
        }

        return OPERATION.UNKNOWN;
    }

}
