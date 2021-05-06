public class App {
    public static void main(String[] args) throws Exception {
        
        Serie tbbt = new Serie("The Big Bang Theory");
        
        Season s1 = new Season("Season 1", 15);
        Season s2 = new Season("Season 2", 13);

        tbbt.addSeason(s1);
        tbbt.addSeason(s2);

        System.out.println(tbbt);
    }
}
