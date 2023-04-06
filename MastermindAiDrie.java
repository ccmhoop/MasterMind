package ai;
import java.util.*;

public class MastermindAiDrie {
    static ArrayList<String> color = new ArrayList<>();
    static ArrayList<String> combi = new ArrayList<>();
    //static ArrayList<String> crack = new ArrayList<>();
    static String []crack = new String[3];
    static ArrayList<String> neut = new ArrayList<>();
    static ArrayList<String> colorShrink = new ArrayList<>();
    static int colorC;
    static int teller = 12;
    static int teller2 = 1;
    static Random rn = new Random();
    static int conColor = 0;

    /*
    *-----------------Fixed------------
    */
    public static void main(String[] args) {
       arrayCombi();
    }
    public static void color() {
        color.add("Groen");
        color.add("Geel");
        color.add("Blauw");
        color.add("Rood");
        color.add("Zwart");
        color.add("Wit");
        /*
        color.add("Oranje");
        color.add("Paars");
         */
    }
    public static void arrayCombi() {
        color();
        String b;
        for (int i = 0; i < 4; i++) {
            int a = rn.nextInt(6);
            b = color.get(a);
            combi.add(b);
        }
        while (true) {
            System.out.println("\nAttempts left\n " + teller );
            guess();
            System.out.println(combi + " combination");
            System.out.println(neut + " guess");
            teller--;
            for (int i = 1; i < 13; i++) {
                containCheck();
                    int l = 0;
                    if (Objects.equals(combi.get(0), neut.get(0))) {
                        l++;
                    }
                    if (Objects.equals(combi.get(1), neut.get(1))) {
                        l++;
                    }
                    if (Objects.equals(combi.get(2), neut.get(2))) {
                        l++;
                    }
                    if (Objects.equals(combi.get(3), neut.get(3))) {
                        l++;
                    }
                    if (l > 2) {
                        while(true) {
                            crack = new String[]{neut.get(0), neut.get(1), neut.get(2), neut.get(3)};
                            System.out.println("\nNew try Attempts Left : " + teller);
                            System.out.println(combi + " combination");
                            String gu;
                            if (!Objects.equals(combi.get(0), neut.get(0))) {
                                guess(1);
                                neut.addAll(List.of(crack));
                                gu = neut.get(0);
                                crack[0] = gu;
                            }
                            if (!Objects.equals(combi.get(1), neut.get(1))) {
                                guess(1);
                                neut.addAll(List.of(crack));
                                gu = neut.get(0);
                                crack[1] = gu;
                            }
                            if (!Objects.equals(combi.get(2), neut.get(2))) {
                                guess(1);
                                neut.addAll(List.of(crack));
                                gu = neut.get(0);
                                crack[2] = gu;
                            }
                            if (!Objects.equals(combi.get(3), neut.get(3))) {
                                guess(1);
                                neut.addAll(List.of(crack));
                                gu = neut.get(0);
                                crack[3] = gu;
                            }
                            if (!combi.contains(neut.get(0))) {
                                color.remove(neut.get(0));
                            }
                            colorC = color.size();
                            teller--;
                            System.out.println(Arrays.toString(crack));
                            System.out.println("Amount of Colors in correct location " + l);
                            neut.clear();
                            neut.addAll(List.of(crack));
                            if(neut.equals(combi)){
                                break;
                            } else if (teller<1) {
                                break;
                            }
                        }
                    }
                    if (neut.equals(combi)) {
                        System.out.println("\nComplete\nCode Cracked");
                        System.out.println(combi + " combination");
                        System.out.println(neut + " Crack");
                        System.out.println("Amount of Games : " + teller2 + "\nAttempts Left " + teller);
                        return;
                    } else if (l < 3) {
                        System.out.println("\nNew try Attempts Left : " + teller);
                        colorC = color.size();
                        System.out.println(combi + " combination");
                        guess();
                        System.out.println(neut + " guess");
                        teller--;
                    }
                    if (teller < 1) {
                        color.clear();
                        color();
                        System.out.println("\nNew Game\n");
                        teller = 12;
                        teller2++;
                        combi.clear();
                        for (i = 0; i < 4; i++) {
                            int a = rn.nextInt(6);
                            b = color.get(a);
                            combi.add(b);
                        }
                        break;
                    }
            }

        }
    }

    public static void guess(){
            int op;
            neut.clear();
            colorC = color.size();
            for (int i = 0; i <= 3; i++) {
                op = rn.nextInt(Math.max(colorC, 0));
                neut.add(color.get(op));
            }
    }
    public static void guess(int a){
        int op;
        neut.clear();
        colorC = color.size();

        for(int i =0;i<=a;i++){
            op = rn.nextInt(Math.max(colorC, 0));
            neut.add(color.get(op));
        }
    }
    public static void containCheck() {
        colorShrink.clear();
        int count, compare, swa = 0;
        int bla=0;
        for (String check : neut) {
            if (!colorShrink.contains(check)) {
                colorShrink.add(check);
            }
        }
        for (String s : colorShrink) {
            compare = Collections.frequency(neut, s);
            count = Collections.frequency(combi, s);
            if(compare==count){
                bla++;
            }
            swa = (swa + compare);
            conColor = (conColor + count);
        }
        if (conColor==4 && bla==1){
            System.out.println("Current guess contains " + (conColor-1));
        } else if (conColor>swa && swa==3) {
            System.out.println("Current guess contains " + colorShrink.size());
        } else if (conColor < swa) {
            System.out.println("Current guess contains " + conColor);
        } else if (conColor > swa) {
            System.out.println("Current guess contains " + swa );
        } else if(conColor==0){
            System.out.println("No Matches");
            color.removeAll(neut);
        }else if(conColor==4){
            System.out.println("Current guess contains " + conColor);
            color.retainAll(combi);
        }
        conColor = 0;
    }
}






