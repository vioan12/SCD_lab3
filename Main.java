public class Main {

    private static Thread[] thread=new Thread[2];
    public static void main(String[] args) {

        HtmlExtract HE = new HtmlExtract("http://www.cel.ro/casti/");
        HE.extract_all_products();
    }
}
